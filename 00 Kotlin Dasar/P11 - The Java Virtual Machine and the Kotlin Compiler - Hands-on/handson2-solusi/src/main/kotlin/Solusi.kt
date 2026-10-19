// Hands-on 2: Tailrec Optimization
// Solusi: Mengoptimasi rekursi ekor menjadi perulangan (loop) di tingkat bytecode menggunakan modifier tailrec

// 1. Masalah Rekursi Biasa di JVM:
//    Setiap kali sebuah fungsi memanggil dirinya sendiri, JVM mengalokasikan satu STACK FRAME baru
//    ke call stack thread (ukuran stack thread terbatas, default ~1024 KB).
//    Jika n sangat besar (mis. 100.000), tumpukan frame akan melebihi kapasitas memori stack dan
//    menimbulkan crash fatal: `java.lang.StackOverflowError`.
fun factorialBiasa(n: Long, acc: Long = 1): Long {
    if (n <= 1) return acc
    return factorialBiasa(n - 1, acc * n)
}

// 2. Solusi Optimasi Compiler (tailrec):
//    - Syarat: Pemanggilan rekursif harus berada di posisi ekor (TAIL CALL) — yaitu operasi terakhir
//      sebelum fungsi return, tanpa ada operasi pending lain setelah fungsi kembali.
//    - Akumulator (acc): Hasil sementara dibawa sebagai parameter fungsi.
//    - Cara Kerja: Compiler Kotlin mendeteksi modifier `tailrec` dan secara otomatis menulis ulang
//      fungsi rekursif ini menjadi perulangan `while (true)` biasa di tingkat JVM Bytecode!
//      Artinya: Berjalan dalam 1 stack frame konstan (O(1) memory stack), sehingga 100% aman dari StackOverflowError.
tailrec fun factorialTailrec(n: Long, acc: Long = 1): Long {
    if (n <= 1) return acc
    return factorialTailrec(n - 1, acc * n)
}

fun main() {
    println("--- 1. Uji Nilai Kecil (n = 20) ---")
    println("factorialBiasa(20)   = ${factorialBiasa(20)}")
    println("factorialTailrec(20) = ${factorialTailrec(20)}\n")

    println("--- 2. Uji Nilai Ekstrem (n = 100.000) ---")

    // Rekursi biasa akan crash StackOverflowError
    try {
        println("Memanggil factorialBiasa(100000)...")
        factorialBiasa(100000)
    } catch (e: StackOverflowError) {
        println("✗ factorialBiasa(100000) -> StackOverflowError! (Kapasitas stack frame JVM habis)")
    }

    // Versi tailrec berjalan mulus karena dieksekusi sebagai loop di tingkat bytecode
    val hasilTailrec = factorialTailrec(100000)
    println("✓ factorialTailrec(100000) = $hasilTailrec (Sukses berjalan tanpa overflow stack)")
}
