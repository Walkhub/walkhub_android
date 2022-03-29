plugins {
    id("com.android.library")
    kotlin("android")
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
    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.mockito)
    testImplementation(Dependency.Test.mockitoKotlin)
    testImplementation(Dependency.Test.mockitoInline)

    implementation(Dependency.Coroutine.core)

    implementation(Dependency.DI.inject)

    implementation(Dependency.ThreeTenAndroidBackport.threeTenAbp)
}