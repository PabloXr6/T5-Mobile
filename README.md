# Aplikasi Data Pasien (Android Kotlin)

**Dibuat Oleh:** M. Bayu Aji

**NIM:** F1D02310144

**Matakuliah:** Pemrograman Mobile

Tugas Akhir Pemrograman Mobile - Aplikasi Android untuk melakukan manajemen data pasien secara CRUD (Create, Read, Update, Delete) melalui integrasi REST API.

## 🚀 Fitur Utama
- **Autentikasi User**: Login menggunakan endpoint API dan manajemen session menggunakan `SharedPreferences` untuk menyimpan Token (Bearer).
- **CRUD Pasien**: 
    - **Create**: Menambah data pasien baru dengan validasi input dan *Date Picker* untuk tanggal lahir.
    - **Read**: Menampilkan daftar pasien secara real-time dari server menggunakan `RecyclerView`.
    - **Update**: Memperbarui informasi data pasien yang sudah ada.
    - **Delete**: Menghapus data pasien dengan dialog konfirmasi keamanan.
- **Manajemen Session**: Fitur Logout dan pengecekan otomatis status login saat aplikasi dibuka.
- **UI/UX Modern**: Menggunakan Material Design components, FloatingActionButton, dan indikator loading (`ProgressBar`).

## 🛠️ Tech Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: XML Layout (ViewBinding)
- **Networking**: [Retrofit 2](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson)
- **HTTP Client**: OkHttp3 & Logging Interceptor
- **Async Process**: Kotlin Coroutines & Lifecycle Scope
- **Architecture**: Modern Android Coding Patterns

## 📸 Screenshots

- **Halaman Login & Daftar Pasien**:
<table>
  <tr>
    <td><img width="300" alt="Login" src="https://github.com/user-attachments/assets/ca71e18b-e6d0-4434-9e8c-56bebb73bb52" /></td>
    <td><img width="300" alt="Daftar Pasien" src="https://github.com/user-attachments/assets/a8225037-5ec3-48d2-845e-cc8c42737a68" /></td>
  </tr>
</table>

- **Edit & Hapus Data**:
<table>
  <tr>
    <td><img width="300" alt="Edit Data" src="https://github.com/user-attachments/assets/54680a59-871e-4a1a-bfcd-00b863c43dec" /></td>
    <td><img width="300" alt="Hapus Data" src="https://github.com/user-attachments/assets/a87b766e-1f0d-4a4d-b66c-f7bc162ee60c" /></td>
  </tr>
</table>

## ⚙️ Cara Menjalankan
1. Clone repository ini:
   ```bash
   git clone https://github.com/PabloXr6/T5-Mobile.git
   ```
2. Buka project menggunakan **Android Studio (Giraffe/Hedgehog atau versi terbaru)**.
3. Tunggu proses **Gradle Sync** selesai.
4. Jalankan aplikasi pada **Emulator** atau **Perangkat Fisik (Android)**.
5. Gunakan kredensial login yang telah disediakan untuk mengakses data pasien.

## 🔗 Endpoint API
- **Base URL**: `https://api.pahrul.my.id/`
- **Login**: `POST /api/login`
- **Get Pasien**: `GET /api/pasien`
- **Create Pasien**: `POST /api/pasien`
- **Update Pasien**: `PUT /api/pasien/{id}`
- **Delete Pasien**: `DELETE /api/pasien/{id}`
*(Semua endpoint pasien memerlukan Header `Authorization: Bearer {token}`)*

---
