// Hands-on 3: Variance — declaration-site "out"
// Solusi: Deklarasi covariant interface Container<out T>

open class Animal(val name: String)
class Cat(name: String) : Animal(name)

// 1. Tambahkan modifier 'out' agar tipe bersifat covariant (Container<Cat> adalah subtipe Container<Animal>)
interface Container<out T> {
    fun get(): T
}

// 2. Implementasikan container khusus untuk tipe turunan Cat
class CatContainer(private val cat: Cat) : Container<Cat> {
    override fun get(): Cat = cat
}

// 3. Fungsi menerima Container<Animal> yang dapat menerima Container<Cat> berkat modifier 'out'
fun printAnimalName(container: Container<Animal>) {
    println("Nama hewan: ${container.get().name}")
}

fun main() {
    val catContainer: Container<Cat> = CatContainer(Cat("Whiskers"))
    printAnimalName(catContainer)
}
