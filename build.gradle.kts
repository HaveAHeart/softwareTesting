import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
    application
}

group = "com.testing"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.code-intelligence:jazzer-api:0.10.0")
    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.koverMergedHtmlReport {
    isEnabled = true                        // false to disable report generation
    htmlReportDir.set(layout.projectDirectory.dir("results/coverage"))
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = ""
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    configurations.compileClasspath.get().forEach {
        from(if (it.isDirectory) it else zipTree(it))
    }
}