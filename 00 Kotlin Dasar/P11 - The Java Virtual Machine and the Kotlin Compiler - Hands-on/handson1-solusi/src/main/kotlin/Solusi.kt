// Hands-on 1: Inline Function & Reified Generics
// Solusi: Mengatasi JVM Type Erasure dengan kombinasi keyword inline dan reified

// 1. Masalah JVM Type Erasure:
//    Arsitektur JVM menghapus (erase) semua informasi tipe generik pada saat kompilasi ke bytecode .class.
//    Akibatnya, pada saat runtime JVM tidak tahu apa itu `T`, sehingga fungsi generic biasa TIDAK BISA
//    menjalankan pengecekan tipe seperti `value is T` (compiler error: Cannot use 'T' as reified type parameter).
//
// 2. Solusi Kotlin (inline + reified):
//    - `inline`: Compiler menyalin (inline) seluruh isi badan fungsi langsung ke lokasi pemanggilannya.
//    - `reified`: Karena fungsi disalin langsung ke call-site, tipe konkret T (misalnya String atau Int)
//      sudah pasti diketahui pada saat kompilasi, sehingga tipe aslinya dipertahankan dan operasi `is T`
//      dapat dievaluasi secara valid pada saat runtime.
inline fun <reified T> isType(value: Any): Boolean {
    // 3. Pengecekan tipe aman saat runtime berkat reified generic
    return value is T
}

fun main() {
    println("--- Uji Coba isType<T>(value) ---")

    // Di-inline oleh compiler menjadi: "Hello" is String
    val cek1 = isType<String>("Hello")
    println("isType<String>(\"Hello\") -> $cek1 (true)")

    // Di-inline oleh compiler menjadi: "Hello" is Int
    val cek2 = isType<Int>("Hello")
    println("isType<Int>(\"Hello\")    -> $cek2 (false)")

    // Di-inline oleh compiler menjadi: 123 is String
    val cek3 = isType<String>(123)
    println("isType<String>(123)     -> $cek3 (false)")
}
