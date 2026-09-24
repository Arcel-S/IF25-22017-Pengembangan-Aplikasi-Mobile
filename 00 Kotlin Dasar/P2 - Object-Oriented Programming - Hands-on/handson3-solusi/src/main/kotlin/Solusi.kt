// Hands-on 3: Sealed Class untuk State
// Solusi: Memodelkan network result dengan sealed class dan when exhaustive

// 1. Definisikan sealed class sebagai root hierarki state terbatas
sealed class NetworkResult

// 2. Buat state singleton (object) untuk status tanpa data
object Loading : NetworkResult()

// 3. Buat state pembawa data (data class) untuk status Success dan Error
data class Success(val data: String) : NetworkResult()
data class Error(val message: String) : NetworkResult()

fun describe(result: NetworkResult): String {
    // 4. Tangani seluruh kemungkinan state secara exhaustive tanpa perlu cabang else
    return when (result) {
        is Loading -> "Sedang memuat..."
        is Success -> "Berhasil: ${result.data}"
        is Error -> "Gagal: ${result.message}"
    }
}

fun main() {
    println(describe(Loading))
    println(describe(Success("Data pengguna berhasil diambil")))
    println(describe(Error("Koneksi terputus")))
}
