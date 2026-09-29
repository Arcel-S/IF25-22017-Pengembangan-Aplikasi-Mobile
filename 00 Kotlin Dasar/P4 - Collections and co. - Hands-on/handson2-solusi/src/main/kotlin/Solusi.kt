// Hands-on 2: Grouping & Aggregation
// Solusi: Pengelompokan dengan groupBy dan indeks lookup dengan associateBy

data class Transaksi(val id: String, val kategori: String, val nominal: Int)

fun totalPerKategori(transaksi: List<Transaksi>): Map<String, Int> {
    return transaksi
        // 1. Kelompokkan transaksi berdasarkan nama kategorinya
        .groupBy { it.kategori }
        // 2. Hitung jumlah total nominal untuk setiap grup kategori
        .mapValues { (_, daftarTransaksi) -> daftarTransaksi.sumOf { it.nominal } }
}

fun transaksiById(transaksi: List<Transaksi>): Map<String, Transaksi> {
    // 3. Petakan list menjadi Map dengan 'id' sebagai key untuk pencarian cepat (O(1))
    return transaksi.associateBy { it.id }
}

fun main() {
    val transaksi = listOf(
        Transaksi("TRX01", "Makanan", 50_000),
        Transaksi("TRX02", "Transportasi", 20_000),
        Transaksi("TRX03", "Makanan", 35_000),
        Transaksi("TRX04", "Hiburan", 100_000),
        Transaksi("TRX05", "Transportasi", 15_000)
    )

    println("Total per kategori: ${totalPerKategori(transaksi)}")

    val byId = transaksiById(transaksi)
    println("Cari TRX03: ${byId["TRX03"]}")
}
