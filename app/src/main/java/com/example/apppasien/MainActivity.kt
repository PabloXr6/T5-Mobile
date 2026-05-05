package com.example.apppasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppasien.adapter.PasienAdapter
import com.example.apppasien.databinding.ActivityMainBinding
import com.example.apppasien.network.ApiClient
import com.example.apppasien.utils.SessionManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

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

        setupRecyclerView()
        fetchDataPasien("Bearer $token") // Format "Bearer {token}"
    }

    private fun setupRecyclerView() {
        binding.rvPasien.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchDataPasien(tokenAuth: String) {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = ApiClient.instance.getPasien(tokenAuth)
                if (response.isSuccessful && response.body()?.success == true) {
                    val listPasien = response.body()?.data ?: emptyList()
                    val adapter = PasienAdapter(listPasien)
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
}