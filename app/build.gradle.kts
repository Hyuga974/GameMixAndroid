plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("org.sonarqube") version "4.4.1.3373"
    id("jacoco")
}



android {
    namespace = "com.example.gamemixandroid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gamemixandroid"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableUnitTestCoverage = true
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
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.ext:truth:1.5.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-accessibility:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-web:3.5.1")
    androidTestImplementation ("androidx.test.espresso.idling:idling-concurrent:3.5.1")
    androidTestImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.activity:activity-compose:1.7.0") // For setContent
    implementation("androidx.compose.ui:ui:1.5.0") // Compose UI
    implementation("androidx.compose.material:material:1.5.0") // Material Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0") // ViewModel with Compose
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("org.mockito:mockito-android:4.0.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    // Define fileFilter before using it
    val fileFilter = listOf(
        "androidx/**",
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*"
    )

    val kotlinDebugTree = fileTree("${layout.buildDirectory}/tmp/kotlin-classes/debug").apply {
        exclude(fileFilter)
    }

    val javaDebugTree = fileTree("${layout.buildDirectory}/intermediates/javac/debug").apply {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(kotlinDebugTree, javaDebugTree))
    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/testDebugUnitTest.exec"))
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}
