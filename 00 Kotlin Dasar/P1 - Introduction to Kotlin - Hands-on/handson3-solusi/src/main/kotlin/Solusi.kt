// Hands-on 3: Loops, Ranges & Null Safety
// Solusi: ScoreBoard dan perulangan range ber-step

class ScoreBoard(private val skorMentah: List<Int?>) {
    // 1. Bersihkan elemen null dari daftar skor mentah menggunakan filterNotNull()
    val skorValid: List<Int> = skorMentah.filterNotNull()

    fun skorKelulusan(batasLulus: Int): List<Int> {
        // 2. Saring skor yang memenuhi batas lulus, lalu urutkan dari terbesar ke terkecil
        return skorValid.filter { it >= batasLulus }.sortedDescending()
    }
}

fun cetakRentangGanjil(sampai: Int) {
    // 3. Perulangan dengan range 'step 2' untuk mencetak deret bilangan ganjil
    for (i in 1..sampai step 2) {
        print("$i ")
    }
    println()
}

fun main() {
    val papan = ScoreBoard(listOf(85, null, 72, 90, null, 55, 100))
    println("Skor lulus (>= 70): ${papan.skorKelulusan(70)}")

    cetakRentangGanjil(10)
}
