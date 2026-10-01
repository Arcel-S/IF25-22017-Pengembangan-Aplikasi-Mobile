// Hands-on 3: Closure — Counter Factory
// Solusi: Fungsi penghasil closure yang menangkap variabel lokal

fun makeCounter(): () -> Int {
    // 1. Inisialisasi variabel state lokal di scope terluar
    var count = 0

    // 2. Kembalikan lambda yang menangkap (capture) dan memutasi variabel count
    return {
        ++count
    }
}

fun main() {
    // 3. Setiap pemanggilan makeCounter() membuat instance state mandiri
    val counterA = makeCounter()
    val counterB = makeCounter()

    println(counterA()) // 1
    println(counterA()) // 2
    println(counterA()) // 3

    println(counterB()) // 1 (independen dari counterA)
    println(counterB()) // 2
}
