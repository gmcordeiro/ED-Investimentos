import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "br.com"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
	implementation("com.squareup.moshi:moshi-adapters:1.14.0")
	implementation("com.squareup.okhttp3:okhttp:4.11.0")
	implementation("io.github.microutils:kotlin-logging:2.0.10")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
