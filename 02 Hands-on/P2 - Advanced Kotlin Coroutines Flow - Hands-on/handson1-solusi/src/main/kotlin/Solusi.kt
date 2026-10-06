import kotlinx.coroutines.*

// Hands-on 1: Coroutines Dasar — Solusi
// Mengambil data dari 2 sumber secara paralel menggunakan async/await.

suspend fun fetchUserProfile(userId: String): String {
    delay(1000)
    return "User: John Doe"
}

suspend fun fetchUserPosts(userId: String): List<String> {
    delay(800)
    return listOf("Post 1", "Post 2", "Post 3")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val profileDeferred = async { fetchUserProfile("user123") }
    val postsDeferred = async { fetchUserPosts("user123") }

    val profile = profileDeferred.await()
    val posts = postsDeferred.await()

    println("Profile: $profile")
    println("Posts: $posts")

    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}
