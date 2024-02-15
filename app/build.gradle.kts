plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.pokefriend"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.pokefriend"
        minSdk = Versions.MIN_ANDROID_SDK
        targetSdk = Versions.TARGET_ANDROID_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kapt {
        correctErrorTypes = true
    }
}



dependencies {
    core()
    compose()
    firebase()
    hilt()
    test()

    implementation("com.google.accompanist:accompanist-permissions:0.30.1")

    implementation(project(":core:core"))
    implementation(project(":core:navigator"))
    implementation(project(":feature:pokelist"))
    implementation(project(":feature:mylocations"))
    implementation(project(":shared:components"))
    implementation(project(":shared:location"))
}