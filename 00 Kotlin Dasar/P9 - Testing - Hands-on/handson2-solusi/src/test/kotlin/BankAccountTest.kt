// Hands-on 2: Testing Exception
// Solusi: Menguji mutasi saldo dan verifikasi pelemparan exception menggunakan assertThrows

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BankAccountTest {

    @Test
    @DisplayName("Deposit menambah saldo rekening dengan akurat")
    fun testDeposit() {
        // 1. Arrange: Buat akun bank dengan saldo awal 100.0
        val account = BankAccount(100.0)

        // 2. Act: Setor dana sebesar 50.0
        account.deposit(50.0)

        // 3. Assert: Pastikan saldo terkini menjadi 150.0
        assertEquals(150.0, account.balance, "Saldo akhir setelah deposit 50.0 harus 150.0")
    }

    @Test
    @DisplayName("Withdraw mengurangi saldo saat dana mencukupi")
    fun testWithdrawSufficientBalance() {
        // Arrange
        val account = BankAccount(100.0)

        // Act
        account.withdraw(40.0)

        // Assert
        assertEquals(60.0, account.balance, "Saldo akhir setelah penarikan 40.0 harus 60.0")
    }

    @Test
    @DisplayName("Withdraw melempar InsufficientFundsException saat saldo tidak mencukupi")
    fun testWithdrawInsufficientBalance() {
        // 4. Arrange: Akun hanya memiliki saldo 50.0
        val account = BankAccount(50.0)

        // 5. AssertThrows (Negative Testing):
        //    Fungsi idiomatis Kotlin `assertThrows<T> { ... }` memverifikasi bahwa blok kode di dalamnya
        //    HARUS melempar tipe exception T (InsufficientFundsException).
        //    Jika method withdraw tidak melempar exception atau melempar tipe lain, unit test akan dianggap GAGAL.
        val exception = assertThrows<InsufficientFundsException> {
            account.withdraw(100.0) // Act penarikan melampaui saldo (100 > 50)
        }

        // Verifikasi opsional: memastikan pesan error dari exception informatif
        println("Pesan exception yang diverifikasi: ${exception.message}")
    }
}
