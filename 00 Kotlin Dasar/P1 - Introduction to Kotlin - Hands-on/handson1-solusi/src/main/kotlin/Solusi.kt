// Hands-on 1: Variabel, Fungsi & String Template
// Solusi: Implementasi describeProfile() dengan safe call (?.) dan Elvis (?:)

fun describeProfile(nama: String, umur: Int?, kota: String = "Tidak diketahui"): String {
    // 1. Format umur dengan penanganan null-safety:
    //    Jika umur != null -> safe call (?.) menjalankan .let dan menghasilkan "$it tahun"
    //    Jika umur == null -> operator Elvis (?:) mengambil teks cadangan "umur tidak diketahui"
    val umurText = umur?.let { "$it tahun" } ?: "umur tidak diketahui"

    // 2. Gabungkan profil ke dalam satu string menggunakan string template ($variabel)
    return "Nama: $nama, Umur: $umurText, Kota: $kota"
}

fun main() {
    // 1. Pemanggilan dengan argumen posisi lengkap
    println(describeProfile("Andi", 20, "Bandar Lampung"))

    // 2. Pemanggilan dengan umur null (kota otomatis memakai default value)
    println(describeProfile("Budi", null))

    // 3. Pemanggilan menggunakan named arguments
    println(describeProfile(nama = "Citra", umur = 19))
}
