// Hands-on 3: Custom Annotation + Reflection Validator
// Solusi: Membuat custom annotation dan validator deklaratif dinamis menggunakan refleksi Kotlin & JVM

import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

// 1. Deklarasi Custom Annotation:
//    - @Target: Membatasi di mana anotasi ini boleh ditempelkan (pada FIELD atau PROPERTY Kotlin).
//    - @Retention(AnnotationRetention.RUNTIME): PENTING! Retention RUNTIME memastikan metadata anotasi
//      disimpan di dalam file .class dan tetap tersedia di memori saat program dieksekusi, sehingga
//      bisa dibaca oleh Reflection API. Jika retention adalah BINARY atau SOURCE, anotasi tidak bisa
//      dibaca saat runtime!
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Required

// 2. Data class representasi form pendaftaran:
//    Field `nama` dan `email` ditandai @Required, sedangkan `alamat` bersifat opsional (nullable).
data class RegistrasiForm(
    @Required val nama: String,
    @Required val email: String,
    val alamat: String?
)

// 3. Validator Berbasis Refleksi (Mirip mekanisme validasi Spring Validation / Hibernate Validator):
//    Fungsi ini memeriksa sembarang objek, mencari properti yang memiliki anotasi @Required,
//    lalu memastikan nilainya tidak null dan tidak berupa string kosong/blank.
fun validate(obj: Any): List<String> {
    val gagal = mutableListOf<String>()

    // 4. Iterasi seluruh properti yang ada di dalam objek
    for (prop in obj::class.memberProperties) {
        // Cek keberadaan anotasi @Required, baik di level property Kotlin maupun backing field JVM
        val hasRequired = prop.findAnnotation<Required>() != null ||
                prop.javaField?.getAnnotation(Required::class.java) != null

        if (hasRequired) {
            // Ambil nilai properti secara dinamis
            val value = prop.getter.call(obj)

            // 5. Aturan validasi: gagal jika bernilai null atau jika bertipe String yang blank ("" / spasi)
            if (value == null || (value is String && value.isBlank())) {
                gagal.add(prop.name)
            }
        }
    }

    return gagal
}

fun main() {
    println("--- 1. Uji Form Valid (nama & email terisi, alamat null tapi opsional) ---")
    val formValid = RegistrasiForm("Siti", "siti@mail.com", null)
    val hasilValid = validate(formValid)
    println("Hasil validasi -> gagal: $hasilValid (Ekspektasi: [])\n")

    println("--- 2. Uji Form Tidak Valid (nama berupa string kosong) ---")
    val formInvalid = RegistrasiForm("", "budi@mail.com", "Jl. Merdeka")
    val hasilInvalid = validate(formInvalid)
    println("Hasil validasi -> gagal: $hasilInvalid (Ekspektasi: [nama])")
}
