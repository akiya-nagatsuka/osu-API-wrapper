plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.github.nagatsukaakiya.osuapi.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.github.nagatsukaakiya.osuapi.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "CLIENT_ID", "\"${System.getenv("CLIENT_ID")}\"")
        buildConfigField("String", "CLIENT_SECRET", "\"${System.getenv("CLIENT_SECRET")}\"")
        buildConfigField("String", "REDIRECT_URL", "\"${System.getenv("REDIRECT_URL")}\"")
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xskip-prerelease-check")
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.0")
    implementation("com.patrykandpatrick.vico:compose:1.6.5")
}