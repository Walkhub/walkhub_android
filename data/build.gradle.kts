plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependency.Moshi.moshi)
    kapt(Dependency.Moshi.moshiCompiler)

    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.mockito)
    androidTestImplementation(Dependency.Test.androidJunit)

    implementation(Dependency.Network.retrofit)
    implementation(Dependency.Network.gsonConverter)
    implementation(Dependency.Network.okhttp)

    implementation(Dependency.LocalStorage.room)
    kapt(Dependency.LocalStorage.roomCompiler)

    implementation(Dependency.Coroutine.core)

    implementation(Dependency.DI.inject)

    implementation(Dependency.GooglePlayService.fitness)
    implementation(Dependency.GooglePlayService.auth)

    implementation(Dependency.DI.hiltAndroid)
    kapt(Dependency.DI.hiltCompiler)

    implementation(Dependency.ThreeTenAndroidBackport.threeTenAbp)

    implementation(Dependency.WorkManager.ktx)
    implementation(Dependency.WorkManager.hiltExtension)

    implementation(Dependency.LocalStorage.sharedPreference)
}