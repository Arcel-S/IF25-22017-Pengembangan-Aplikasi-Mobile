// Hands-on 2: launch vs async
// Solusi: Perbandingan coroutine builder "fire-and-forget" (launch) vs "return value" (async/await)

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 1. Task background tanpa nilai kembalian (side-effect)
suspend fun writeAccessLog(message: String) {
    delay(500)
    println("[LOG] $message")
}

// 2. Task komputasi yang menghasilkan nilai kembalian
suspend fun calculateTotalPrice(prices: List<Int>): Int {
    delay(700)
    return prices.sum()
}

fun main() = runBlocking {
    val prices = listOf(15000, 25000, 10000)

    // 3. Coroutine builder `launch`:
    //    Digunakan untuk operasi 'fire-and-forget'. Builder ini mengembalikan objek `Job` dan
    //    TIDAK mengembalikan nilai hasil komputasi. Cocok untuk operasi seperti mencatat log,
    //    menyimpan cache lokal, atau mengirim analitik ke server.
    launch {
        writeAccessLog("Checkout dimulai untuk ${prices.size} item")
    }

    // 4. Coroutine builder `async`:
    //    Digunakan saat kita membutuhkan nilai hasil kembalian dari operasi asinkron.
    //    Builder ini mengembalikan objek `Deferred<T>` (sebuah 'promise' atau janji nilai di masa depan).
    val totalDeferred = async {
        calculateTotalPrice(prices)
    }

    // 5. Mengambil hasil via `.await()`:
    //    Fungsi suspend `.await()` menangguhkan pemanggilnya sampai nilai komputasi tersedia.
    //    Berbeda dengan `Future.get()` di JVM yang memblokir OS thread, `.await()` bersifat non-blocking.
    val total = totalDeferred.await()
    println("Total belanja berhasil dihitung: Rp$total")
}
