// Hands-on 2: Interface & Data Class
// Solusi: Interface Payable dan data class Employee

// 1. Definisikan kontrak abstraksi fungsi tanpa state
interface Payable {
    fun calculateSalary(): Double
}

// 2. Implementasikan Payable pada data class Employee (otomatis mendapat copy, equals, toString)
data class Employee(
    val name: String,
    val baseSalary: Double,
    val bonus: Double
) : Payable {
    override fun calculateSalary(): Double {
        return baseSalary + bonus
    }
}

fun main() {
    val alice = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)

    // 3. Gandakan objek dengan modifikasi property tertentu menggunakan copy()
    val bob = alice.copy(name = "Bob")

    println("Gaji ${alice.name}: ${alice.calculateSalary()}")
    println("Gaji ${bob.name}: ${bob.calculateSalary()}")

    // 4. Bandingkan kesetaraan isi objek (structural equality) dengan ==
    val aliceDuplicate = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)
    println("alice == aliceDuplicate? ${alice == aliceDuplicate}")

    // 5. Cetak representasi teks objek secara otomatis via toString() data class
    println(alice)
}
