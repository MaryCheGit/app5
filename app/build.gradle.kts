plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.aaglobal.jnc_playground"
    compileSdk = Versions.compileSdkVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion

        applicationId = "com.aaglobal.jnc_playground"
        versionCode = 1
        versionName = "1.0"

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
    implementation(Libs.kotlin)

    // AndroidX
    implementation(Libs.androidX.coreKtx)
    implementation(Libs.androidX.appCompat)

    // UI libraries
    implementation(Libs.ui.materialComponents)
    implementation(Libs.androidX.constraintLayout)

    // Navigation
    implementation(Libs.jetpack.navigationComponentFragmentKtx)
    implementation(Libs.jetpack.navigationComponentUiKtx)
    implementation(Libs.ui.adapterDelegateKotlinDsl)
    implementation(Libs.ui.adapterDelegateKotlinDslLayoutContainer)

    // Feature modules
    implementation(project(":company"))
    implementation(project(":vacancy"))

    testImplementation(Libs.test.junit)
    androidTestImplementation(Libs.test.junitExt)
    androidTestImplementation(Libs.test.espresso)
}