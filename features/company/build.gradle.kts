plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.aaglobal.jnc_playground.company"
    compileSdk = Versions.compileSdkVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Kotlin
    compileOnly(Libs.kotlin)

    // AndroidX
    compileOnly(Libs.androidX.coreKtx)
    compileOnly(Libs.androidX.appCompat)

    // UI libraries
    compileOnly(Libs.ui.materialComponents)
    compileOnly(Libs.androidX.constraintLayout)

    // Navigation
    compileOnly(Libs.jetpack.navigationComponentFragmentKtx)
    compileOnly(Libs.jetpack.navigationComponentUiKtx)
    compileOnly(Libs.ui.adapterDelegateKotlinDsl)
    compileOnly(Libs.ui.adapterDelegateKotlinDslLayoutContainer)
}