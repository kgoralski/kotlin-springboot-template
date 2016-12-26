buildscript {
    val springBootVersion = "1.4.2.RELEASE"
    extra["springBootVersion"] = springBootVersion
    val kotlinVersion = "1.0.5-2"
    extra["kotlinVersion"] = kotlinVersion

    repositories {
        gradleScriptKotlin()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply {
    plugin("kotlin")
    plugin("idea")
    plugin("org.springframework.boot")
}

version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

val kotlinVersion = extra["kotlinVersion"] as String
val springBootVersion = extra["springBootVersion"] as String

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
    compile(project(":kotlinboot-commons"))
    compile("org.glassfish.jersey.core:jersey-client:2.25")
    compile("org.glassfish.jersey.ext:jersey-proxy-client:2.25")
    compile("org.glassfish.jersey.media:jersey-media-json-jackson:2.25")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.4")

    testCompile(project(":kotlinboot-server"))
    testCompile("org.spockframework:spock-spring:1.0-groovy-2.4")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
