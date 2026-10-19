// Hands-on 3: Dependency Declaration
// Solusi: Menggunakan library eksternal kotlinx-datetime yang dideklarasikan di build.gradle.kts

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun main() {
    // 1. Clock.System.now(): Mengambil timestamp instan waktu saat ini (UTC Instant)
    //    dari library resmi JetBrains kotlinx-datetime.
    val now = Clock.System.now()

    // 2. TimeZone.currentSystemDefault(): Mengonversi instant UTC ke zona waktu lokal mesin (mis. Asia/Jakarta / WIB).
    val timeZone = TimeZone.currentSystemDefault()
    val localDateTime = now.toLocalDateTime(timeZone)

    // 3. Ekstrak hanya komponen tanggal (ISO-8601: YYYY-MM-DD)
    val today = localDateTime.date

    println("Waktu instan (UTC) : $now")
    println("Zona waktu aktif   : $timeZone")
    println("Tanggal hari ini   : $today")
}
