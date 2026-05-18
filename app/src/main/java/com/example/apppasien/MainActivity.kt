package com.example.apppasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasien.adapter.PasienAdapter
import com.example.apppasien.databinding.ActivityMainBinding
import com.example.apppasien.model.Pasien
import com.example.apppasien.network.ApiClient
import com.example.apppasien.utils.SessionManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            refreshData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        val token = sessionManager.getAuthToken()
        val userName = sessionManager.getUserName()

        if (token == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.tvWelcome.text = "Halo, $userName"

        binding.btnLogout.setOnClickListener {
            sessionManager.clearSession()
            Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddPasienActivity::class.java)
            resultLauncher.launch(intent)
        }

        setupRecyclerView()
        refreshData()
    }

    private fun setupRecyclerView() {
        binding.rvPasien.layoutManager = LinearLayoutManager(this)
    }

    private fun refreshData() {
        val token = sessionManager.getAuthToken()
        if (token != null) {
            fetchDataPasien("Bearer $token")
        }
    }

    private fun fetchDataPasien(tokenAuth: String) {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = ApiClient.instance.getPasien(tokenAuth)
                if (response.isSuccessful && response.body()?.success == true) {
                    val listPasien = response.body()?.data ?: emptyList()
                    val adapter = PasienAdapter(
                        listPasien,
                        onEditClick = { pasien ->
                            val intent = Intent(this@MainActivity, AddPasienActivity::class.java)
                            intent.putExtra("PASIEN_DATA", pasien)
                            resultLauncher.launch(intent)
                        },
                        onDeleteClick = { pasien ->
                            showDeleteConfirmDialog(pasien)
                        }
                    )
                    binding.rvPasien.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error koneksi: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun showDeleteConfirmDialog(pasien: Pasien) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Pasien")
            .setMessage("Apakah Anda yakin ingin menghapus ${pasien.nama}?")
            .setPositiveButton("Hapus") { _, _ ->
                deletePasien(pasien.id)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deletePasien(id: Int) {
        val token = "Bearer ${sessionManager.getAuthToken()}"
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = ApiClient.instance.deletePasien(token, id)
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@MainActivity, "Berhasil menghapus pasien", Toast.LENGTH_SHORT).show()
                    refreshData()
                } else {
                    Toast.makeText(this@MainActivity, "Gagal menghapus", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}