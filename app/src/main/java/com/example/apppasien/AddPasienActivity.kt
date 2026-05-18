package com.example.apppasien

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.apppasien.databinding.ActivityAddPasienBinding
import com.example.apppasien.model.Pasien
import com.example.apppasien.model.PasienCreateRequest
import com.example.apppasien.network.ApiClient
import com.example.apppasien.utils.SessionManager
import kotlinx.coroutines.launch
import java.util.Calendar

class AddPasienActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPasienBinding
    private lateinit var sessionManager: SessionManager
    private var existingPasien: Pasien? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Cek apakah mode Edit
        existingPasien = intent.getSerializableExtra("PASIEN_DATA") as? Pasien
        if (existingPasien != null) {
            setupEditMode(existingPasien!!)
        }

        binding.etTanggalLahir.setOnClickListener {
            showDatePicker()
        }

        binding.btnSimpan.setOnClickListener {
            simpanPasien()
        }
    }

    private fun setupEditMode(pasien: Pasien) {
        supportActionBar?.title = "Edit Pasien"
        binding.btnSimpan.text = "Update Data"
        
        binding.etNama.setText(pasien.nama)
        binding.etTanggalLahir.setText(pasien.tanggalLahir)
        binding.etAlamat.setText(pasien.alamat)
        binding.etNoTelepon.setText(pasien.noTelepon)
        
        if (pasien.jenisKelamin == "L") {
            binding.rbLaki.isChecked = true
        } else {
            binding.rbPerempuan.isChecked = true
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                binding.etTanggalLahir.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun simpanPasien() {
        val nama = binding.etNama.text.toString().trim()
        val tanggalLahir = binding.etTanggalLahir.text.toString().trim()
        val alamat = binding.etAlamat.text.toString().trim()
        val noTelepon = binding.etNoTelepon.text.toString().trim()
        val jenisKelamin = if (binding.rbLaki.isChecked) "L" else "P"

        if (nama.isEmpty() || tanggalLahir.isEmpty() || alamat.isEmpty() || noTelepon.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val request = PasienCreateRequest(nama, tanggalLahir, jenisKelamin, alamat, noTelepon)
        val token = "Bearer ${sessionManager.getAuthToken()}"

        binding.progressBar.visibility = View.VISIBLE
        binding.btnSimpan.isEnabled = false

        lifecycleScope.launch {
            try {
                val response = if (existingPasien == null) {
                    ApiClient.instance.createPasien(token, request)
                } else {
                    ApiClient.instance.updatePasien(token, existingPasien!!.id, request)
                }

                if (response.isSuccessful && response.body()?.success == true) {
                    val message = if (existingPasien == null) "Berhasil menambah pasien" else "Berhasil update pasien"
                    Toast.makeText(this@AddPasienActivity, message, Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK)
                    finish()
                } else {
                    Toast.makeText(this@AddPasienActivity, "Gagal: ${response.body()?.message ?: "Terjadi kesalahan"}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@AddPasienActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
                binding.btnSimpan.isEnabled = true
            }
        }
    }
}