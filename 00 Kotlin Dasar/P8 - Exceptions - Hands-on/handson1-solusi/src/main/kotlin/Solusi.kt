// Hands-on 1: Try-Catch-Finally Dasar
// Solusi: Penanganan ArithmeticException menggunakan try-catch-finally sebagai expression

fun safeDivide(a: Int, b: Int): Int {
    // 1. Di Kotlin, `try-catch` adalah sebuah ekspresi (expression) yang dapat langsung mengembalikan nilai.
    //    Nilai baris terakhir dari blok `try` atau blok `catch` akan menjadi nilai kembalian fungsi.
    return try {
        // 2. Operasi rawan exception: Pembagian integer dengan nol (b = 0) akan melempar ArithmeticException di level JVM
        a / b
    } catch (e: ArithmeticException) {
        // 3. Menangkap exception spesifik dan mengembalikan nilai fallback yang aman (0)
        println("Peringatan: Terjadi pembagian dengan nol (${e.message}) -> fallback ke 0")
        0
    } finally {
        // 4. Blok finally dijamin SELALU dieksekusi oleh runtime JVM, baik saat operasi sukses
        //    maupun saat exception tertangkap. Sangat berguna untuk cleanup resource (tutup koneksi, file handle, dsb).
        println("[FINALLY] Operasi safeDivide($a, $b) selesai dievaluasi.")
    }
}

fun main() {
    println("--- Pengujian 1: Pembagian Normal ---")
    println("Hasil: ${safeDivide(10, 2)}\n")

    println("--- Pengujian 2: Pembagian dengan Nol (Exception) ---")
    println("Hasil: ${safeDivide(10, 0)}\n")

    println("--- Pengujian 3: Pembagian Normal Kedua ---")
    println("Hasil: ${safeDivide(9, 3)}")
}
