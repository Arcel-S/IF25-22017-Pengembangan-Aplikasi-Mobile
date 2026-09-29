// Hands-on 3: Sequence vs List (Lazy Evaluation)
// Solusi: Perbandingan efisiensi pipeline eager (List) vs lazy (Sequence)

fun prosesDenganList(data: List<Int>): List<Int> {
    // 1. Evaluasi Eager: seluruh 1 juta data difilter dan dimap secara menyeluruh di memori
    return data
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(5)
}

fun prosesDenganSequence(data: List<Int>): List<Int> {
    // 2. Evaluasi Lazy: elemen diproses satu demi satu hingga 5 elemen pertama terpenuhi
    return data
        .asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(5)
        .toList()
}

fun main() {
    val data = (1..1_000_000).toList()

    val startList = System.currentTimeMillis()
    val hasilList = prosesDenganList(data)
    val waktuList = System.currentTimeMillis() - startList
    println("List  : $hasilList (${waktuList}ms)")

    val startSeq = System.currentTimeMillis()
    val hasilSequence = prosesDenganSequence(data)
    val waktuSequence = System.currentTimeMillis() - startSeq
    println("Sequence: $hasilSequence (${waktuSequence}ms)")
}
