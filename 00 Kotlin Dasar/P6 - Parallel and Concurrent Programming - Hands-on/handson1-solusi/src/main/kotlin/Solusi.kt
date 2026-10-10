// Hands-on 1: Race Condition & Thread Safety
// Solusi: Mengamankan operasi mutasi bersama (shared mutable state) dengan AtomicInteger

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class Counter {
    // 1. Masalah: Operasi `count++` biasa pada tipe Int bukan operasi atomik, melainkan terdiri dari
    //    tiga langkah bytecode JVM: read value -> modify value -> write back. Jika dijalankan di multi-thread,
    //    thread dapat saling menimpa hasil perhitungan (Race Condition).
    //
    //    Solusi: Menggunakan AtomicInteger yang memanfaatkan instruksi CPU Compare-And-Swap (CAS)
    //    secara hardware-level (lock-free) sehingga menjamin keutuhan nilai tanpa overhead synchronized lock.
    private val c = AtomicInteger(0)

    // 2. Operasi increment thread-safe: menambah nilai sebesar 1 dan mengembalikan nilai baru secara atomik
    fun increment() {
        c.incrementAndGet()
    }

    // 3. Mengambil nilai terkini dengan jaminan memory visibility antar core CPU (volatility semantics)
    fun value(): Int {
        return c.get()
    }
}

fun main() {
    val counter = Counter()
    val iterasi = 100_000

    println("Memulai 2 thread konkuren, masing-masing melakukan $iterasi kali increment...")

    // 4. Jalankan thread pertama dan kedua secara paralel di background thread pool OS
    val t1 = thread {
        repeat(iterasi) { counter.increment() }
    }
    val t2 = thread {
        repeat(iterasi) { counter.increment() }
    }

    // 5. Blok thread utama (main) menggunakan .join() sampai kedua thread pekerja selesai berjalan
    t1.join()
    t2.join()

    // 6. Cetak hasil akhir: jika thread-safe, nilainya tepat 200.000 tanpa kehilangan data
    val hasil = counter.value()
    val target = iterasi * 2
    println("Hasil akhir: $hasil (target: $target) -> ${if (hasil == target) "SUKSES: Bebas Race Condition" else "GAGAL"}")
}
