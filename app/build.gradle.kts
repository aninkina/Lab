plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.10"

}

android {
    compileSdk = 31

    viewBinding {
        isEnabled = true
    }

    defaultConfig {
        applicationId = "dev.fstudio.weather"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Koin AndroidX Scope features
    implementation("io.insert-koin:koin-androidx-scope:2.2.3")
    // Koin AndroidX ViewModel features
    implementation("io.insert-koin:koin-androidx-viewmodel:2.2.3")
    // Koin AndroidX Fragment features
    implementation("io.insert-koin:koin-androidx-fragment:2.2.3")

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Settings Preference
    implementation ("androidx.preference:preference-ktx:1.1.1")

    // Work Manager
    implementation("androidx.work:work-runtime:2.7.1")
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    implementation("com.google.android.gms:play-services-location:19.0.0")

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

}