// Hands-on 2: Bounded Type Parameter
// Solusi: Pencarian nilai maksimum dengan constraint Comparable<T>

// 1. Batasi type parameter <T : Comparable<T>> agar elemen list dapat diperbandingkan
fun <T : Comparable<T>> findMax(items: List<T>): T {
    // 2. Validasi pencegahan error jika list kosong
    if (items.isEmpty()) {
        throw IllegalArgumentException("List tidak boleh kosong")
    }

    // 3. Telusuri list dan perbarui nilai terbesar yang ditemukan
    var max = items[0]
    for (item in items) {
        if (item > max) {
            max = item
        }
    }
    return max
}

fun main() {
    println(findMax(listOf(3, 7, 2, 9, 4)))              // 9
    println(findMax(listOf(1.5, 2.8, 0.3)))            // 2.8
    println(findMax(listOf("apel", "jeruk", "duku")))   // "jeruk"
}
