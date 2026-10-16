// Hands-on 1: Unit Test Dasar (Pola AAA)
// Solusi: Menerapkan pengujian unit dengan pola Arrange-Act-Assert dan JUnit 5 assertions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CalculatorTest {

    // 1. Objek target pengujian (System Under Test / SUT)
    private val calculator = Calculator()

    @Test
    @DisplayName("Penjumlahan dua bilangan positif menghasilkan total yang tepat")
    fun testAdd() {
        // 2. Pola AAA - Arrange: Siapkan data masukan (fixtures/parameters)
        val a = 2
        val b = 3

        // 3. Pola AAA - Act: Jalankan fungsi yang sedang diuji
        val hasil = calculator.add(a, b)

        // 4. Pola AAA - Assert: Verifikasi bahwa hasil aktual sama dengan ekspektasi
        assertEquals(5, hasil, "2 + 3 seharusnya bernilai 5")
    }

    @Test
    @DisplayName("Pengurangan dengan pengurang lebih besar menghasilkan angka negatif")
    fun testSubtractNegativeResult() {
        // Arrange
        val a = 3
        val b = 5

        // Act
        val hasil = calculator.subtract(a, b)

        // Assert
        assertEquals(-2, hasil, "3 - 5 seharusnya bernilai -2")
    }

    @Test
    @DisplayName("Pembagian dua bilangan bulat menghasilkan kuosien yang benar")
    fun testDivide() {
        // Arrange
        val a = 10
        val b = 2

        // Act
        val hasil = calculator.divide(a, b)

        // Assert
        assertEquals(5, hasil, "10 / 2 seharusnya bernilai 5")
    }
}
