// Hands-on 1: Inspeksi Kelas dengan KClass
// Solusi: Menggunakan Kotlin Reflection API untuk inspeksi metadata kelas dan properti secara dinamis

import kotlin.reflect.full.memberProperties

data class Product(val nama: String, val harga: Int)

fun main() {
    val product = Product("Kopi Susu", 18000)

    // 1. KClass sebagai Entry Point Refleksi Kotlin:
    //    Sintaks `product::class` menghasilkan instance `KClass<Product>`, yaitu representasi runtime
    //    resmi dari kelas Kotlin (berbeda dengan Java Class `Product::class.java`).
    val kClass = product::class

    // 2. Membaca metadata nama kelas tanpa hardcode String
    println("Kelas: ${kClass.simpleName}")

    // 3. Inspeksi Properti Dinamis:
    //    `memberProperties` (ekstensi dari kotlin.reflect.full) mengembalikan koleksi seluruh KProperty
    //    yang dideklarasikan di dalam kelas. Kita dapat mengekstrak nama properti via `.name` tanpa
    //    perlu mengetahui atau menuliskan nama field-nya secara hardcoded.
    println("Properties:")
    for (prop in kClass.memberProperties) {
        println("- ${prop.name}")
    }
}
