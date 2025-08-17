import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.kotlin.dsl.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.gamemixandroid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gamemixandroid"
        minSdk = 24
        targetSdk = 35
        versionCode = 3
        versionName = "0.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableUnitTestCoverage = true
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

val junitVersion = "5.10.1"

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("androidx.datastore:datastore-preferences:1.0.0")


    // ✅ JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // ✅ Mockito
    testImplementation("org.mockito:mockito-core:5.12.0")
    androidTestImplementation("org.mockito:mockito-android:4.0.0")

    // ✅ Firebase

    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation ("com.google.firebase:firebase-analytics-ktx")// Analytics
    implementation ("com.google.firebase:firebase-crashlytics-ktx")// Crashlytics
    implementation ("com.google.firebase:firebase-perf-ktx")// Performance Monitoring
    implementation ("com.google.firebase:firebase-config-ktx")// Remote Config

    // AndroidTest + Compose
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.ext:truth:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-accessibility:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-web:3.5.1")
    androidTestImplementation("androidx.test.espresso.idling:idling-concurrent:3.5.1")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.register<JacocoReport>("jacocoTestReport") {
    description = "Generates the HTML report documentation for this project."
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "**/*Preview*.*",
        "**/di/**",
        "androidx/**",
        "com/google/**"
    )

    val kotlinDebugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    val mainSrc = file("$projectDir/src/main/java")
    val kotlinSrc = file("$projectDir/src/main/kotlin")

    classDirectories.setFrom(files(kotlinDebugTree))
    sourceDirectories.setFrom(files(mainSrc, kotlinSrc))

    val execFiles = fileTree(buildDir).apply {
        include("jacoco/testDebugUnitTest.exec", "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
    }.files.filter { it.exists() }

    executionData.setFrom(files(execFiles))
}

sonarqube {
    properties {
        property("sonar.projectKey", "gamemix_android")
        property("sonar.projectName", "GameMix Pipeline")
        property("sonar.language", "kotlin")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.sources", "src/main/java,src/main/kotlin")
        property("sonar.tests", "src/test,src/androidTest")
        property("sonar.junit.reportPaths", "${layout.buildDirectory}/test-results/testDebugUnitTest")
        property("sonar.coverage.jacoco.xmlReportPaths", "${layout.buildDirectory}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
        property("sonar.exclusions", "**/R.class,**/R$*.class,**/BuildConfig.*,**/Manifest*.*,**/*Test*,**/*Preview*.*,**/di/**")
    }
}
