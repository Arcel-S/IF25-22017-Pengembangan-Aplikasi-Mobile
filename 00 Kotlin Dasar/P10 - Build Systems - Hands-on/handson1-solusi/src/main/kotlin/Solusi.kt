// Hands-on 1: Resource & Packaging
// Solusi: Membaca berkas konfigurasi dari Classpath JVM menggunakan Properties & ClassLoader

import java.util.Properties

fun main() {
    // 1. Masalah Path di JVM:
    //    Saat aplikasi dikompilasi dan dikemas menjadi berkas JAR, berkas konfigurasi (seperti app.properties)
    //    berada di dalam arsip terkompresi, BUKAN file reguler di path sistem operasi.
    //    Oleh karena itu, jangan pernah menggunakan `File("src/main/resources/...")` yang pasti gagal di produksi.
    //
    //    Solusi: Gunakan ClassLoader untuk membaca berkas dari root classpath JVM sebagai InputStream.
    val classLoader = object {}.javaClass.classLoader
    val inputStream = classLoader.getResourceAsStream("app.properties")
        ?: error("Berkas 'app.properties' tidak ditemukan di classpath!")

    // 2. Inisialisasi java.util.Properties untuk parsing pasangan key=value
    val properties = Properties()

    // 3. Muat seluruh isi stream ke dalam memori
    inputStream.use { stream ->
        properties.load(stream)
    }

    // 4. Ekstrak nilai berdasarkan kunci konfigurasi (key) yang telah didefinisikan di resources
    val appName = properties.getProperty("app.name")
    val appVersion = properties.getProperty("app.version")

    println("Berhasil membaca konfigurasi dari classpath JAR:")
    println("• Nama aplikasi : $appName")
    println("• Versi         : $appVersion")
}
