// Hands-on 3: Dependency Declaration
// Solusi: Deklarasi dependensi pihak ketiga (third-party dependency) pada konfigurasi Gradle

dependencies {
    // 1. Format deklarasi dependensi: "group:artifact:version"
    //    Konfigurasi `implementation` memastikan library ini dapat diakses oleh source code
    //    pada compile time maupun runtime JVM.
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
}
