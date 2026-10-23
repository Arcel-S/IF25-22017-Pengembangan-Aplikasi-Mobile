// Hands-on 3: Request Handling & Status Code
// Solusi: Membaca request body (POST /echo), validasi HTTP Method (405), dan fallback 404 handler

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    // 1. Endpoint Handler POST /echo:
    server.createContext("/echo") { exchange ->
        // 2. Validasi HTTP Method:
        //    Endpoint ini hanya melayani metode POST. Jika client mengirim selain POST (misal GET/PUT/DELETE),
        //    kembalikan HTTP status 405 (Method Not Allowed).
        //    Parameter rCode=405 dan responseLength=-1 memberi sinyal bahwa response tidak memiliki body (empty body).
        if (exchange.requestMethod != "POST") {
            exchange.sendResponseHeaders(405, -1)
            exchange.responseBody.close()
            return@createContext
        }

        // 3. Membaca Request Body:
        //    Gunakan fungsi ekstensi Kotlin `readBytes()` pada InputStream requestBody untuk membaca
        //    seluruh payload data yang dikirim oleh client.
        val requestBytes: ByteArray = exchange.requestBody.readBytes()

        // 4. Echo Response (Status 200 OK):
        //    Kirimkan kembali byte request yang sama persis sebagai response body
        exchange.sendResponseHeaders(200, requestBytes.size.toLong())
        exchange.responseBody.use { os ->
            os.write(requestBytes)
        }
    }

    // 5. Fallback Root Handler ("/") untuk Status 404 (Not Found):
    //    Context "/" bertindak sebagai default catch-all handler. Semua request yang path URL-nya
    //    tidak cocok dengan endpoint terdaftar lain (misal /foo, /bar, /tidak-ada) akan masuk ke sini.
    server.createContext("/") { exchange ->
        val path = exchange.requestURI.path
        val response = "Not Found: $path".toByteArray()

        // Kirim status 404 Not Found beserta pesan informatif
        exchange.sendResponseHeaders(404, response.size.toLong())
        exchange.responseBody.use { os ->
            os.write(response)
        }
    }

    server.start()
    println("HTTP Server aktif di port 8080")
    println("Uji POST /echo     : curl -X POST -d 'halo dunia' http://localhost:8080/echo")
    println("Uji Method Not Allowed : curl -i http://localhost:8080/echo")
    println("Uji 404 Not Found  : curl -i http://localhost:8080/tidak-ada")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan.")
}
