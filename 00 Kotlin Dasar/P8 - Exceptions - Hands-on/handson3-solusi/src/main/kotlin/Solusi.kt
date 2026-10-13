// Hands-on 3: Kotlin Idiomatic Exception Handling
// Solusi: Menggunakan try-expression, require(), error(), dan multi-catch

data class Tiket(val nama: String, val umur: Int)

fun parseTiket(input: String): Tiket {
    val bagian = input.split("/")

    // 1. require(kondisi) { pesan }: Standar Kotlin untuk validasi input.
    //    Jika kondisi bernilai false, require() otomatis melempar IllegalArgumentException.
    require(bagian.size == 2) { "Format harus 'nama/umur', diterima: $input" }

    val nama = bagian[0]

    // 2. try-expression untuk parsing nilai:
    //    Nilai hasil toInt() langsung di-assign ke variabel `umur`.
    // 3. error(pesan): Standar Kotlin untuk melempar IllegalStateException saat sistem menemui kondisi yang salah.
    val umur: Int = try {
        bagian[1].toInt()
    } catch (e: NumberFormatException) {
        error("Umur tidak valid: '${bagian[1]}' bukan angka bilangan bulat")
    }

    // 4. Validasi isi nama dan nilai umur non-negatif
    require(nama.isNotBlank()) { "Nama tidak boleh kosong" }
    require(umur >= 0) { "Umur harus lebih besar atau sama dengan 0" }

    return Tiket(nama, umur)
}

fun main() {
    val inputs = listOf(
        "Budi/25",           // Format valid
        "Siti/17",           // Format valid
        "format-salah",      // Gagal: tidak ada pemisah '/' (IllegalArgumentException)
        "Andi/bukan-angka",  // Gagal: konversi angka melempar error() (IllegalStateException)
        "/30"                // Gagal: nama kosong (IllegalArgumentException)
    )

    // 5. Multi-catch: Menangani berbagai tipe exception secara spesifik sesuai jenis kesalahannya
    for (input in inputs) {
        try {
            val tiket = parseTiket(input)
            println("✓ Berhasil: $tiket")
        } catch (e: IllegalArgumentException) {
            println("✗ [IllegalArgumentException] Validasi argumen gagal untuk '$input' -> ${e.message}")
        } catch (e: IllegalStateException) {
            println("✗ [IllegalStateException] Error parsing state untuk '$input' -> ${e.message}")
        }
    }
}
