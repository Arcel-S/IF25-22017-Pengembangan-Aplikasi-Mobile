// Hands-on 1: Higher-Order Function
// Solusi: Fungsi penerima parameter fungsi/lambda

// 1. Terima parameter bertipe fungsi `(Int, Int) -> Int`
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    // 2. Jalankan fungsi operation terhadap argumen a dan b
    return operation(a, b)
}

fun main() {
    // 3. Panggil dengan berbagai macam operasi lambda (trailing lambda syntax)
    val tambah = calculate(10, 4) { x, y -> x + y }
    println("Tambah: $tambah")

    val kurang = calculate(10, 4) { x, y -> x - y }
    println("Kurang: $kurang")

    val kali = calculate(10, 4) { x, y -> x * y }
    println("Kali: $kali")
}
