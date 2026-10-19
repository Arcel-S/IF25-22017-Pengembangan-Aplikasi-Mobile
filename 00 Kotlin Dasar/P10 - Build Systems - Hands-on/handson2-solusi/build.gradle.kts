// Hands-on 2: Custom Gradle Task
// Solusi: Mendaftarkan custom task Gradle untuk inspeksi metadata project

// 1. Tetapkan versi proyek modul ini
version = "1.0.0"

// 2. tasks.register: Praktik terbaik Gradle modern (Task Configuration Avoidance API).
//    Task hanya akan dikonfigurasi saat dieksekusi, sehingga mempercepat fase evaluasi Gradle build.
tasks.register("printProjectInfo") {
    // 3. Metadata task: Mengelompokkan task ke grup 'kotlin dasar' agar mudah ditemukan via `./gradlew tasks`
    group = "kotlin dasar"
    description = "Mencetak nama project, versi project, dan versi Gradle yang sedang aktif"

    // 4. doLast: Blok aksi fase eksekusi (execution phase). Kode di dalam blok ini HANYA
    //    dijalankan ketika task `printProjectInfo` dipanggil secara eksplisit.
    doLast {
        println("Nama project  : " + project.name)
        println("Versi project : " + project.version)
        println("Versi Gradle  : " + gradle.gradleVersion)
    }
}
