pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://repo.spring.io/release") }
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
    plugins {
        val bootVersion: String by settings
        id("org.springframework.boot") version bootVersion
    }
}

rootProject.name = "pact-consumer"