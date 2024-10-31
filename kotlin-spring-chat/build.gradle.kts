import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
}

group = "com.example.kotlin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {

	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0") // Replace with your Kotlin version
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // Replace with the latest version
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	//implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("io.rsocket:rsocket-core:1.1.2") // Core RSocket functionality
	implementation("io.rsocket:rsocket-transport:1.1.2") // Transport layer for RSocket
	implementation("io.rsocket:rsocket-transport-websocket:1.1.2") // WebSocket transport for RSocket
	implementation("io.rsocket:rsocket-transport-netty:1.1.2") // Netty transport for RSocket

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.github.javafaker:javafaker:1.0.2")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.data:spring-data-relational")
	implementation("org.jetbrains:markdown:0.2.2")
	implementation("org.springframework.data:spring-data-jdbc")

	// H2 Database Dependency
	runtimeOnly("com.h2database:h2")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("io.r2dbc:r2dbc-h2")
	implementation("org.springframework.boot:spring-boot-starter-rsocket")
	testImplementation("app.cash.turbine:turbine:0.4.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
