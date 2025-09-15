import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    // Убрали kotlin.plugin.jpa — заменили на noarg напрямую для избежания зависимости с variant-issue
    id("org.sonarqube") version "6.3.1.5724"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.noarg") version "1.9.25"  // Для JPA: генерит no-arg constructors для @Entity и т.д.
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
    // JWT support в SB3
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.tika:tika-parsers-standard:2.9.2")
    implementation("com.google.firebase:firebase-admin:9.3.0")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

noArg {  // Конфиг для JPA (замена plugin.jpa)
    annotation("jakarta.persistence.Entity")  // Для SB3 (Jakarta EE)
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
    invokeInitializers = true
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
    languageVersion = "1.9"
}
