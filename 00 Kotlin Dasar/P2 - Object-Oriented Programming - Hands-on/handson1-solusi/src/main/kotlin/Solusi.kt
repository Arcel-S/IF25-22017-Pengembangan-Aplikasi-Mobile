// Hands-on 1: Class & Inheritance
// Solusi: Hierarki class Vehicle, Car, dan Motorcycle

// 1. Buat open class agar dapat diwariskan ke subclass
open class Vehicle(val name: String, val maxSpeed: Int) {
    // Open method agar implementasinya dapat di-override oleh subclass
    open fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h"
    }
}

// 2. Subclass Car mewarisi Vehicle dan menetapkan maxSpeed = 180
class Car(name: String, val numberOfDoors: Int) : Vehicle(name, 180) {
    override fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h dan punya $numberOfDoors pintu"
    }
}

// 3. Subclass Motorcycle mewarisi Vehicle dan menetapkan maxSpeed = 220
class Motorcycle(name: String, val hasSidecar: Boolean) : Vehicle(name, 220) {
    override fun describe(): String {
        val sidecarText = if (hasSidecar) "dengan sidecar" else "tanpa sidecar"
        return "$name dapat melaju hingga $maxSpeed km/h ($sidecarText)"
    }
}

fun main() {
    // 4. Buat polymorphic collection bertipe List<Vehicle>
    val vehicles = listOf<Vehicle>(
        Car("Toyota", numberOfDoors = 4),
        Motorcycle("Ninja", hasSidecar = false)
    )

    // Panggil describe() yang secara dinamis mengeksekusi versi subclass masing-masing
    vehicles.forEach { println(it.describe()) }
}
