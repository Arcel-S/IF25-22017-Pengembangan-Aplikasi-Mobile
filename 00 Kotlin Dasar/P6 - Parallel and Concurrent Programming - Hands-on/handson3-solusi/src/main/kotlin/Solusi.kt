// Hands-on 3: Producer-Consumer dengan BlockingQueue
// Solusi: Komunikasi asinkron aman antar-thread menggunakan LinkedBlockingQueue dan Sentinel Value

import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

// 1. Sentinel Value ("Poison Pill" pattern): Nilai penanda khusus yang dikirimkan oleh producer
//    untuk memberi tahu consumer bahwa tidak ada lagi data yang akan dikirim, sehingga consumer
//    bisa menghentikan loop-nya secara elegan.
const val PESAN_SELESAI = "SELESAI"

fun main() {
    // 2. Inisialisasi BlockingQueue berbasis linked nodes yang thread-safe.
    //    Keuntungan:
    //    - queue.put() otomatis memblokir producer jika kapasitas antrean penuh (backpressure).
    //    - queue.take() otomatis memblokir consumer jika antrean sedang kosong (menghindari CPU burning / busy-waiting).
    val queue = LinkedBlockingQueue<String>()

    // 3. Thread Producer: memproduksi 5 pesan dengan jeda simulasi, lalu mengirim sentinel value
    val producer = thread {
        for (i in 1..5) {
            val pesan = "Pesan #$i"
            queue.put(pesan) // Menyisipkan pesan ke antrean (thread-safe)
            println("[Producer] mengirim: $pesan")
            Thread.sleep(200) // Simulasi waktu pembuatan data
        }
        queue.put(PESAN_SELESAI) // Sinyal penutup bagi consumer
        println("[Producer] selesai memproduksi seluruh pesan.")
    }

    // 4. Thread Consumer: membaca pesan dari antrean secara kontinu
    val consumer = thread {
        while (true) {
            val pesan = queue.take() // Menunggu hingga data tersedia di antrean
            if (pesan == PESAN_SELESAI) {
                println("[Consumer] menerima sinyal SELESAI, keluar dari loop.")
                break
            }
            println("[Consumer] memproses: $pesan")
        }
    }

    // 5. Sinkronisasi thread: Tunggu kedua thread (producer dan consumer) menyelesaikan tugasnya
    producer.join()
    consumer.join()

    println("Pipeline Producer-Consumer berhasil diselesaikan tanpa deadlock.")
}
