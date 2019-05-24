import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
        reports {
            html.isEnabled = true
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-api:${Versions.slf4j}")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit5Version}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junit5Version}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Versions.junit5Version}")
    testImplementation("lt.neworld:kupiter:${Versions.kupiter}")


}

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}
