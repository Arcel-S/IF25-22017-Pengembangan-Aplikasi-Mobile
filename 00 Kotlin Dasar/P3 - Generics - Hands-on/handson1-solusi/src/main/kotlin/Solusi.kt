// Hands-on 1: Generic Class — Box<T>
// Solusi: Generic class Box<T> dengan higher-order function map<R>

// 1. Definisikan class dengan type parameter generic <T>
class Box<T>(val value: T) {
    // 2. Transformasikan isi Box<T> menjadi Box<R> baru tanpa mengubah objek asli
    fun <R> map(transform: (T) -> R): Box<R> {
        return Box(transform(value))
    }
}

fun main() {
    val intBox = Box(23)
    println("intBox.value = ${intBox.value}")

    val cupBox = Box("cup")
    println("cupBox.value = ${cupBox.value}")

    // 3. Transformasikan Box<Int> menjadi Box<String>
    val stringBox = intBox.map { "Angka: $it" }
    println("stringBox.value = ${stringBox.value}")

    // 4. Transformasikan Box<String> menjadi Box<Int> (panjang karakter)
    val lengthBox = cupBox.map { it.length }
    println("lengthBox.value = ${lengthBox.value}")
}
