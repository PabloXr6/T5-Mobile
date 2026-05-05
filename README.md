# Aplikasi Data Pasien (Android Kotlin)

**Dibuat Oleh:** M. Bayu Aji
**NIM:** F1D02310144
**Matakuliah:** Pemrograman Mobile

Tugas Akhir Pemrograman Mobile - Aplikasi Android untuk melakukan autentikasi ke API dan menampilkan daftar data pasien menggunakan RecyclerView.

## 🚀 Fitur Utama
- **Login API**: Autentikasi user menggunakan endpoint POST `/api/login`.
- **Session Management**: Menyimpan Token (Bearer) dan data user secara aman menggunakan `SharedPreferences`.
- **Daftar Pasien**: Mengambil data dari endpoint GET `/api/pasien` dengan proteksi `Authorization Header`.
- **RecyclerView**: Menampilkan informasi pasien (Nama, TTL, Gender, Alamat, No Telp) dalam bentuk list yang rapi.
- **Indikator Loading**: Menampilkan `ProgressBar` saat proses request ke server berlangsung.

## 🛠️ Tech Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: XML Layout (ViewBinding)
- **Networking**: [Retrofit 2](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson)
- **HTTP Client**: OkHttp3 & Logging Interceptor
- **Async Process**: Kotlin Coroutines & Lifecycle Scope
- **Architecture**: Modern Android Coding Patterns

## 📸 Screenshots
*(Setelah Anda mengambil screenshot aplikasi, masukkan filenya ke folder `screenshots` di repository ini dan sesuaikan link di bawah)*

| Halaman Login | Daftar Data Pasien |
|---|---|
| <img src="screenshots/login.png" width="250"> | <img src="screenshots/main_activity.png" width="250"> |

## ⚙️ Cara Menjalankan
1. Clone repository ini:
   ```bash
   git clone https://github.com/Pabloz666999/T5-Mobile.git
   ```
2. Buka project menggunakan **Android Studio (Giraffe/Hedgehog atau versi terbaru)**.
3. Tunggu proses **Gradle Sync** selesai.
4. Jalankan aplikasi pada **Emulator** atau **Perangkat Fisik (Android)**.
5. Gunakan kredensial login yang telah disediakan untuk mengakses data pasien.

## 🔗 Endpoint API
- **Base URL**: `https://api.pahrul.my.id/`
- **Login**: `POST /api/login`
- **Data Pasien**: `GET /api/pasien` (Wajib Header `Authorization: Bearer {token}`)

---

