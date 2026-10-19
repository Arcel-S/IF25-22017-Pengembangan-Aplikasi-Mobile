// Hands-on 2: Custom Gradle Task
// Solusi: Petunjuk eksekusi custom task Gradle yang dikonfigurasi di build.gradle.kts

fun main() {
    // 1. Pada latihan ini, logika utama berada di berkas konfigurasi Gradle (build.gradle.kts),
    //    bukan di file Kotlin ini.
    // 2. Task kustom `printProjectInfo` dibuat untuk membaca metadata internal build project.
    // 3. Untuk menjalankan task ini lewat terminal, gunakan perintah:
    //    ./gradlew :handson2-solusi:printProjectInfo
    println("Custom Gradle task telah terdaftar di build.gradle.kts!")
    println("Jalankan melalui terminal: ./gradlew :handson2-solusi:printProjectInfo")
}
