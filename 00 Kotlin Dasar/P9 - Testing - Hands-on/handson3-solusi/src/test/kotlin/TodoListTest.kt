// Hands-on 3: Test Lifecycle & Setup dengan @BeforeEach
// Solusi: Mengisolasi state pengujian menggunakan lifecycle hook @BeforeEach dan assertion kondisi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TodoListTest {

    // 1. Properti lateinit: Variabel dideklarasikan tanpa inisialisasi awal langsung,
    //    karena inisialisasinya akan didelegasikan ke lifecycle method setUp().
    private lateinit var todoList: TodoList

    // 2. Lifecycle Hook @BeforeEach:
    //    Method ini dijalankan secara otomatis SEBELUM SETIAP @Test dieksekusi.
    //    Keuntungan Krusial:
    //    Menjamin isolasi state (Test Isolation). Setiap fungsi pengujian menerima objek TodoList yang BARU
    //    dan KOSONG, sehingga aksi penambahan/penghapusan pada satu test tidak menimbulkan efek samping (side-effect)
    //    pada test lainnya (mencegah flaky tests).
    @BeforeEach
    fun setUp() {
        todoList = TodoList()
    }

    @Test
    @DisplayName("TodoList yang baru diinstansiasi harus dalam kondisi kosong")
    fun testNewListIsEmpty() {
        // 3. Verifikasi bahwa list baru selalu kosong (isEmpty() bernilai true)
        assertTrue(todoList.isEmpty(), "Instance TodoList baru harusnya kosong")
    }

    @Test
    @DisplayName("Menambah satu item menaikkan ukuran list menjadi 1")
    fun testAddIncreasesSize() {
        // Act
        todoList.add("Belajar Kotlin")

        // Assert
        assertEquals(1, todoList.size(), "Ukuran list harus bertambah menjadi 1 setelah add")
    }

    @Test
    @DisplayName("Menghapus item yang ada mengembalikan true dan mengosongkan list")
    fun testRemoveExistingItem() {
        // Arrange
        todoList.add("Belajar Kotlin")

        // Act: Hapus item yang sebelumnya dimasukkan
        val removed = todoList.remove("Belajar Kotlin")

        // Assert: Pastikan status penghapusan sukses (true) dan size kembali 0
        assertTrue(removed, "Penghapusan elemen yang terdaftar harus menghasilkan true")
        assertEquals(0, todoList.size(), "Ukuran list harus kembali 0 setelah item dihapus")
    }
}
