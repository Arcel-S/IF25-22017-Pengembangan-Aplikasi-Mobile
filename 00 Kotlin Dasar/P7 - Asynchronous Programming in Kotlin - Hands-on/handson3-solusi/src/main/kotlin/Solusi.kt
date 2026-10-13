// Hands-on 3: Structured Concurrency & Cancellation
// Solusi: Mengelola lifecycle coroutine hierarkis dan pembatalan kaskade (cascade cancellation)

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 1. DownloadManager mengikat seluruh task unduhan ke dalam CoroutineScope yang diinjeksikan.
//    Prinsip Structured Concurrency: Tidak boleh ada coroutine liar (untracked / orphan coroutine)
//    yang hidup melampaui daur hidup komponen pemiliknya (mencegah memory leak).
class DownloadManager(private val scope: CoroutineScope) {

    // 2. Setiap download berjalan sebagai child coroutine di dalam scope yang sama
    fun downloadFile(name: String, durationMs: Long): Job {
        return scope.launch {
            delay(durationMs) // Simulasi durasi download
            println("✓ $name selesai di-download (${durationMs}ms)")
        }
    }
}

fun main() = runBlocking {
    // 3. Parent Job bertindak sebagai akar (root) hierarki lifecycle
    val parentJob = Job()
    val scope = CoroutineScope(parentJob)

    val manager = DownloadManager(scope)

    println("Memulai 3 proses download simultan...")
    manager.downloadFile("foto.jpg", 1000)      // Selesai di t=1000ms
    manager.downloadFile("dokumen.pdf", 1500)   // Akan dibatalkan sebelum t=1500ms
    manager.downloadFile("video.mp4", 3000)     // Akan dibatalkan sebelum t=3000ms

    // 4. Tunggu selama 1200ms:
    //    Hanya foto.jpg (1000ms) yang sempat selesai, sementara dokumen.pdf dan video.mp4 masih berjalan.
    delay(1200)

    // 5. Pembatalan kaskade (Cancellation Propagation):
    //    Memanggil cancel() pada parentJob otomatis membatalkan SELURUH child coroutine di bawahnya.
    println("\n[!] Membatalkan parentJob (sisa unduhan yang belum selesai akan dihentikan)...")
    parentJob.cancel()

    // 6. Beri jeda 2000ms untuk membuktikan bahwa dokumen.pdf dan video.mp4 TIDAK PERNAH mencetak pesan selesai
    delay(2000)
    println("Selesai: Structured concurrency memastikan sisa unduhan tidak bocor di memori.")
}
