package com.example.apppasien.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppasien.databinding.ItemPasienBinding
import com.example.apppasien.model.Pasien

class PasienAdapter(
    private val listPasien: List<Pasien>,
    private val onEditClick: (Pasien) -> Unit,
    private val onDeleteClick: (Pasien) -> Unit
) : RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    inner class PasienViewHolder(private val binding: ItemPasienBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pasien: Pasien) {
            binding.tvNama.text = pasien.nama
            binding.tvTglLahirGender.text = "${pasien.tanggalLahir} | ${pasien.jenisKelamin}"
            binding.tvNoTelp.text = pasien.noTelepon
            binding.tvAlamat.text = pasien.alamat

            binding.btnEdit.setOnClickListener { onEditClick(pasien) }
            binding.btnDelete.setOnClickListener { onDeleteClick(pasien) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        val binding = ItemPasienBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PasienViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
        holder.bind(listPasien[position])
    }

    override fun getItemCount(): Int = listPasien.size
}