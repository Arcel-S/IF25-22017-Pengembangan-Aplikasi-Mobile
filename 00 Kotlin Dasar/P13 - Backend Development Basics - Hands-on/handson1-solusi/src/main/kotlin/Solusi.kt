// Hands-on 1: HTTP Server Sederhana
// Solusi: Membangun Web Server HTTP native JDK menggunakan com.sun.net.httpserver.HttpServer

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

fun main() {
    // 1. Inisialisasi HTTP Server:
    //    JDK menyediakan server HTTP bawaan (sejak Java 6) tanpa memerlukan dependensi eksternal seperti Tomcat.
    //    - InetSocketAddress("0.0.0.0", 8080): Listen pada semua antarmuka jaringan (all interfaces) di port 8080.
    //    - backlog = 0: Menggunakan default antrean koneksi sistem operasi.
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    // 2. Mendaftarkan Route Context:
    //    Menghubungkan path URL "/hello" dengan lambda handler (HttpHandler).
    server.createContext("/hello") { exchange ->
        // Menyiapkan payload teks response dalam bentuk ByteArray
        val response = "Hello, Kotlin Backend!".toByteArray()

        // 3. Mengirimkan HTTP Response Header:
        //    - HTTP Status 200 (OK).
        //    - Panjang konten (Content-Length) dalam byte: response.size.toLong().
        //      PENTING: Selalu kirim ukuran byte yang tepat agar client tahu kapan response selesai dibaca.
        exchange.sendResponseHeaders(200, response.size.toLong())

        // 4. Menulis Response Body dan Menutup Stream:
        //    Menggunakan ekstensi Kotlin `.use { ... }` untuk memastikan OutputStream selalu di-flush
        //    dan ditutup secara otomatis (mencegah koneksi hanging / resource leak).
        exchange.responseBody.use { os ->
            os.write(response)
        }
    }

    // 5. Menjalankan server di background thread pool
    server.start()
    println("HTTP Server aktif dan berjalan di http://localhost:8080/hello")
    println("Uji melalui terminal lain: curl http://localhost:8080/hello")

    // Server sengaja berjalan selama 10 detik lalu berhenti agar tidak menggantung di background IDE
    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan.")
}
