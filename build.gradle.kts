buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.9.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}
plugins {
    id("com.android.application") version "8.9.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    alias(libs.plugins.android.test) apply false
}
