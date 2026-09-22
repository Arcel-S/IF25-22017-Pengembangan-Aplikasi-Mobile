// Hands-on 2: Control Flow — when sebagai Expression
// Solusi: Implementasi gradeOf() menggunakan when sebagai expression

// 1. Definisikan fungsi berpenentu nilai tunggal (single-expression) dengan '='
fun gradeOf(nilai: Int): String = when (nilai) {
    // 2. Cek rentang nilai ujian menggunakan operator rentang (in x..y)
    in 90..100 -> "A"
    in 80..89 -> "B"
    in 70..79 -> "C"
    in 60..69 -> "D"
    // 3. Cabang fallback (else) untuk semua nilai selain rentang di atas
    else -> "E"
}

fun main() {
    val nilaiMahasiswa = listOf(95, 82, 71, 60, 45)

    // Iterasi setiap nilai dan cetak grade yang dihasilkan
    for (nilai in nilaiMahasiswa) {
        println("Nilai $nilai -> Grade ${gradeOf(nilai)}")
    }
}
