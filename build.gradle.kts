buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(GradlePlugins.androidBuildTools)
        classpath(GradlePlugins.kotlin)
        classpath(GradlePlugins.navigationComponentSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}