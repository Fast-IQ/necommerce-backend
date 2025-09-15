import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jetbrains.kotlin.plugin.jpa") version "2.2.20"
    id("org.sonarqube") version "6.3.1.5724"
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.spring") version "2.2.20"
}

group = "ru.netology"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    // Замена устаревшего spring-security-jwt: используй OAuth2 Resource Server для JWT
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.tika:tika-parsers-standard:3.2.2") // Актуальная, включает parsers
    implementation("com.google.firebase:firebase-admin:9.6.0") // Актуальная
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

sonar {
    properties {
        property("sonar.projectKey", "Fast-IQ_necommerce-backend")
        property("sonar.organization", "fast-iq180211")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "2.0" // Для Kotlin 2.2.x
}
