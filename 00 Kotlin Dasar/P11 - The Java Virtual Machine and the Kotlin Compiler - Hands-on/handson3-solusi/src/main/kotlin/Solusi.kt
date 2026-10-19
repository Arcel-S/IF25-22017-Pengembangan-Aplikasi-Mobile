// Hands-on 3: companion object & @JvmStatic
// Solusi: Menghasilkan static method sejati di level JVM bytecode untuk interop Java dan refleksi

class Greeter {
    companion object {
        // 1. Masalah Bytecode companion object default di Kotlin:
        //    Secara default, fungsi di dalam companion object dikompilasi menjadi INSTANCE METHOD
        //    pada inner class singleton bernama `Greeter$Companion.class`.
        //    Artinya, pada level JVM bytecode, kelas `Greeter.class` TIDAK memiliki static method `greet()`!
        //    Ini menyulitkan interop dengan Java biasa dan membuat java.lang.Class reflection gagal menemukan method.
        //
        // 2. Solusi (@JvmStatic):
        //    Anotasi ini memerintahkan compiler backend Kotlin untuk menghasilkan method `public static`
        //    tambahan langsung di kelas utama `Greeter.class` (sebagai wrapper pendelegasi ke Companion).
        @JvmStatic
        fun greet(name: String): String {
            return "Halo, $name!"
        }
    }
}

fun main() {
    // Pemanggilan normal dari kode Kotlin
    println(Greeter.greet("Mahasiswa"))

    // 3. Pembuktian empiris lewat Java Reflection API murni (java.lang.Class):
    //    Kita menginspeksi semua method publik yang menempel langsung pada Greeter.class
    val staticMethodNames = Greeter::class.java.methods.map { it.name }
    println("Daftar method pada Greeter.class: $staticMethodNames")

    // 4. Periksa apakah method "greet" terdaftar langsung sebagai static method di Greeter.class
    val punyaStaticGreet = staticMethodNames.contains("greet")
    println("Apakah Greeter.class memiliki static method 'greet'? $punyaStaticGreet")

    // 5. Assertion check: Memastikan @JvmStatic bekerja dengan benar
    check(punyaStaticGreet) {
        "Validasi gagal: greet() belum menjadi static method di Greeter.class — pastikan @JvmStatic terpasang!"
    }
    println("✓ Verifikasi sukses: Bytecode JVM terbukti memiliki static method 'greet'.")
}
