// Hands-on 2: Routing & JSON Sederhana
// Solusi: Serialisasi JSON manual dan konfigurasi header Content-Type pada response HTTP

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

data class User(val id: Int, val name: String)

// Sumber data statis (mock database)
val users = listOf(
    User(1, "Andi"),
    User(2, "Budi"),
    User(3, "Citra")
)

// 1. Serialisasi JSON Manual:
//    Menggunakan fungsi standar Kotlin `joinToString` untuk merangkai daftar objek User
//    menjadi format array JSON yang valid: [{"id":1,"name":"Andi"}, ...]
//    tanpa memerlukan dependensi library eksternal (Jackson/Gson).
fun usersToJson(users: List<User>): String {
    return users.joinToString(separator = ",", prefix = "[", postfix = "]") { user ->
        """{"id":${user.id},"name":"${user.name}"}"""
    }
}

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    // 2. Mendaftarkan Route Context "/users"
    server.createContext("/users") { exchange ->
        val jsonString = usersToJson(users)
        val responseBytes = jsonString.toByteArray()

        // 3. Konfigurasi Header HTTP (Content-Type):
        //    PENTING: Header harus diatur SEBELUM memanggil `sendResponseHeaders()`.
        //    Header "application/json" memberi tahu client (browser/curl/aplikasi mobile)
        //    bahwa payload data harus di-parse sebagai dokumen JSON, bukan teks biasa (text/plain).
        exchange.responseHeaders.set("Content-Type", "application/json")

        // 4. Kirim status 200 OK beserta Content-Length
        exchange.sendResponseHeaders(200, responseBytes.size.toLong())

        // 5. Tulis data byte JSON ke stream output
        exchange.responseBody.use { os ->
            os.write(responseBytes)
        }
    }

    server.start()
    println("Server berjalan di port 8080")
    println("Uji endpoint JSON: curl -i http://localhost:8080/users")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan.")
}
