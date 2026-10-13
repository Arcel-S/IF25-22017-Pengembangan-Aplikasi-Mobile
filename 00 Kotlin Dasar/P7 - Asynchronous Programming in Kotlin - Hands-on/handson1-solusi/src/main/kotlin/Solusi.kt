// Hands-on 1: Suspend Function Dasar
// Solusi: Menggunakan suspend function dan penundaan non-blocking (delay) dalam runBlocking

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// 1. Suspend function: Fungsi khusus Kotlin yang dapat ditunda eksekusinya (suspend)
//    dan dilanjutkan kembali nanti (resume) tanpa memblokir OS thread tempat ia berjalan.
//    Hanya bisa dipanggil dari dalam coroutine scope atau dari suspend function lainnya.
suspend fun checkOrderStatus(orderId: String): String {
    // 2. Non-blocking delay: Berbeda dari Thread.sleep() yang memblokir thread sistem operasi,
    //    delay(2000) melepaskan thread selama 2 detik agar thread tersebut bebas mengerjakan task lain.
    delay(2000)
    return "Order $orderId: SHIPPED"
}

// 3. runBlocking: Coroutine builder yang bertindak sebagai jembatan antara kode blocking reguler
//    (seperti fungsi main atau unit test) dan kode asinkron coroutine.
//    Fungsi ini memblokir thread utama sampai seluruh coroutine di dalamnya selesai dieksekusi.
fun main() = runBlocking {
    println("Memeriksa status pesanan secara asinkron...")

    // 4. Memanggil suspend function secara sekuensial dan menangkap hasilnya setelah penundaan
    val status = checkOrderStatus("A100")
    println(status) // Output: Order A100: SHIPPED
}
