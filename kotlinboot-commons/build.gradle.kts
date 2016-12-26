buildscript {
    val kotlinVersion = "1.0.5-2"
    extra["kotlinVersion"] = kotlinVersion

    repositories {
        gradleScriptKotlin()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    }
}

apply {
    plugin("kotlin")
    plugin("idea")
}

version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

val kotlinVersion = extra["kotlinVersion"] as String

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
    compile("javax.ws.rs:javax.ws.rs-api:2.0.1")
}
