plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}
dependencies {
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
}