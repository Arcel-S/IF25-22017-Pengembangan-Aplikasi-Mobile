// Hands-on 2: Custom Exception & Exception Hierarchy
// Solusi: Memodelkan error domain bisnis dengan Custom Exception dan validasi saldo

// 1. Custom Exception: Turunan langsung dari java.lang.Exception untuk merepresentasikan kondisi error
//    spesifik domain perbankan (saldo tidak mencukupi) agar pemanggil dapat menangani kondisi ini
//    secara terarah, bukan sekadar menangkap Generic Exception.
class InsufficientBalanceException(message: String) : Exception(message)

class BankAccount(private val owner: String, startBalance: Double) {
    // 2. Enkapsulasi: `private set` memastikan saldo hanya bisa dibaca publik, tetapi perubahannya
    //    harus melewati metode deposit() dan withdraw() yang memiliki penjagaan validasi (guard checks).
    var balance: Double = startBalance
        private set

    fun deposit(amount: Double) {
        // 3. Validasi argumen dengan require(): melempar IllegalArgumentException jika amount <= 0
        require(amount > 0) { "Jumlah deposit harus lebih besar dari 0" }
        balance += amount
    }

    fun withdraw(amount: Double) {
        require(amount > 0) { "Jumlah penarikan harus lebih besar dari 0" }

        // 4. Pengecekan aturan bisnis (business invariant):
        //    Jika penarikan melebihi saldo tersedia, lempar InsufficientBalanceException beserta pesan informatif
        if (amount > balance) {
            throw InsufficientBalanceException("Penarikan gagal untuk $owner: saldo $balance tidak cukup untuk menarik $amount")
        }
        balance -= amount
    }
}

fun main() {
    val account = BankAccount("Andi", 100_000.0)

    // Deposit normal
    account.deposit(50_000.0)
    println("Saldo setelah deposit: Rp${account.balance}")

    // 5. Uji coba skenario gagal: Menarik nominal yang melebihi saldo akun
    try {
        println("Mencoba menarik Rp500.000 (melebihi saldo)...")
        account.withdraw(500_000.0)
    } catch (e: InsufficientBalanceException) {
        // Tertangkap di handler khusus domain error
        println("✓ Berhasil menangkap custom exception: ${e.message}")
    }

    // Penarikan normal yang valid
    account.withdraw(30_000.0)
    println("Saldo akhir setelah penarikan valid: Rp${account.balance}")
}
