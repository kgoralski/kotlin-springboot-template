buildscript {
	val springBootVersion = "1.4.2.RELEASE"
	val kotlinVersion = "1.0.5-2"
	extra["kotlinVersion"] = kotlinVersion

	repositories {
		mavenCentral()
		gradleScriptKotlin()
	}

	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
	}
}

apply {
	plugin("kotlin")
	plugin("application")
	plugin("idea")
	plugin("org.springframework.boot")
}

version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
	setSourceCompatibility(1.8)
	setTargetCompatibility(1.8)
}

repositories {
	mavenCentral()
}

val kotlinVersion = extra["kotlinVersion"] as String

dependencies {
	compile(project(":kotlinboot-commons"))
	compile("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("org.springframework.boot:spring-boot-starter-data-jpa")
	compile("org.springframework.boot:spring-boot-starter-jersey")
	compile("org.springframework.boot:spring-boot-starter-data-cassandra") // for now Cassandra 2.X.X
	compile("org.springframework.hateoas:spring-hateoas:0.21.0.RELEASE")
	compile("org.springframework.boot:spring-boot-starter-data-rest")
	compile("com.h2database:h2")
	compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.4")
	testCompile("org.spockframework:spock-spring:1.0-groovy-2.4")
	testCompile("org.springframework.boot:spring-boot-starter-test")
	testCompile("org.cassandraunit:cassandra-unit-spring:2.1.9.2")
}

