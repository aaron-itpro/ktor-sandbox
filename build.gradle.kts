import java.net.URI
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    kotlin("jvm") version "1.2.41"
    application
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

application {
    mainClassName = "com.itprofound.sandbox.ktor.HelloWorldKt"
}

repositories {
    jcenter()
}

dependencies {
    kotlin()
    junit()
    ktor()
    logback()
}

fun DependencyHandlerScope.kotlin() {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    configurations.all {
        "org.jetbrains.kotlin".let {
            // Exclude deprecated "jre*" modules as transitive dependencies
            exclude(group = it, module = "kotlin-stdlib-jre7")
            exclude(group = it, module = "kotlin-stdlib-jre8")
        }
    }
}

fun DependencyHandlerScope.junit() {
    val junitVersion = "5.2.0"

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

fun DependencyHandlerScope.ktor() {
    val ktorVersion = "0.9.0"

    repositories {
        maven { url = URI("https://dl.bintray.com/kotlin/kotlinx") }
        maven { url = URI("https://dl.bintray.com/kotlin/ktor") }
    }

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
}

fun DependencyHandlerScope.logback() {
    implementation("ch.qos.logback:logback-classic:1.2.1")
}
