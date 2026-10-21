// Hands-on 2: Baca Nilai Property Dinamis
// Solusi: Mengakses nilai properti secara generik melalui KProperty.getter.call()

import kotlin.reflect.full.memberProperties

data class Mahasiswa(val nim: String, val nama: String, val ipk: Double)

// 1. Fungsi inspeksi generik: Menerima objek bertipe `Any` (tipe tertinggi di hierarki Kotlin),
//    mirip seperti cara kerja serializer JSON (misal Jackson/Moshi) atau logger otomatis.
fun printFields(obj: Any) {
    // 2. Dapatkan KClass runtime dari objek yang dilewatkan
    val kClass = obj::class

    // 3. Iterasi setiap properti yang dimiliki objek tersebut
    for (prop in kClass.memberProperties) {
        // 4. Membaca nilai dinamis:
        //    Alih-alih mengakses `obj.nama` secara langsung (hardcoded), kita memanggil method getter
        //    reflektif `prop.getter.call(obj)` dengan meneruskan objek target sebagai argumen pemanggilan.
        val value = prop.getter.call(obj)

        // 5. Cetak pasangan nama properti beserta nilai dinamisnya
        println("${prop.name} = $value")
    }
}

fun main() {
    val mhs = Mahasiswa("122140000", "Budi Santoso", 3.75)

    println("Mencetak seluruh field dari objek Mahasiswa secara otomatis via refleksi:")
    printFields(mhs)
}
