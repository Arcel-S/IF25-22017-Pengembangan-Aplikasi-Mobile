// Hands-on 2: ExecutorService & Future
// Solusi: Eksekusi task paralel menggunakan thread pool dan pengambilan hasil via Future

import java.util.concurrent.Callable
import java.util.concurrent.Executors

// 1. Simulasi task komputasi berat / I/O lambat (membutuhkan waktu 1 detik per kalkulasi)
fun hitungKuadrat(n: Int): Int {
    Thread.sleep(1000)
    return n * n
}

fun main() {
    val angka = listOf(1, 2, 3, 4)
    val startTime = System.currentTimeMillis()

    // 2. Buat thread pool berukuran tetap (fixed thread pool) dengan 4 worker thread.
    //    Keuntungan: Menghindari biaya mahal pembuatan thread baru di level OS untuk setiap task,
    //    dan membatasi beban konkurensi agar tidak membanjiri CPU/memori.
    val executor = Executors.newFixedThreadPool(4)

    // 3. Kirim task ke executor menggunakan Callable:
    //    Berbeda dengan Runnable yang void, Callable<T> dapat mengembalikan nilai hasil komputasi.
    //    Method .submit() mengembalikan objek Future<T> sebagai 'tiket/janji' nilai asinkron.
    val futures = angka.map { n ->
        executor.submit(Callable { hitungKuadrat(n) })
    }

    // 4. Ambil hasil dari tiap Future menggunakan .get():
    //    Pemanggilan .get() akan memblokir (wait) hingga perhitungan masing-masing selesai.
    //    Karena 4 task berjalan secara paralel pada 4 thread berbeda, total waktu tempuh
    //    hanya ~1000ms (1 detik), bukan 4000ms (4 detik serial)!
    val hasil = futures.map { it.get() }
    println("Hasil kuadrat: $hasil")

    // 5. Selalu tutup executor service dengan .shutdown():
    //    Langkah ini penting agar worker threads berhenti dan JVM bisa exit secara normal (tidak menggantung).
    executor.shutdown()

    val endTime = System.currentTimeMillis()
    val durasi = endTime - startTime
    println("Total waktu eksekusi paralel: ${durasi}ms (~1 detik)")
}
