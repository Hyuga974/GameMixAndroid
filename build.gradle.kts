buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.9.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}
plugins {
    kotlin("android") version "1.9.20" apply false
    kotlin("plugin.serialization") version "1.9.20" apply false
}