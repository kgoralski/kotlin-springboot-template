allprojects {

    group = "pl.goralski"

    version = "0.0.1"

    repositories {
        gradleScriptKotlin()
    }
}

apply {
        plugin("base")
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}


