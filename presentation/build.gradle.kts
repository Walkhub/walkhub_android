plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        applicationId = "com.semicolon.walkhub"
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.jetpackCompose
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependency.Moshi.moshi)
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    kapt(Dependency.Moshi.moshiCompiler)
    implementation(Dependency.Moshi.moshiKotlin)

    implementation(Dependency.coreKtx)
    implementation(Dependency.appcompat)
    implementation(Dependency.androidKtx)
    implementation(Dependency.fragmentKtx)

    implementation(Dependency.UI.material)
    implementation(Dependency.UI.constraintLayout)
    implementation(Dependency.UI.compose)
    implementation(Dependency.UI.composeTooling)
    implementation(Dependency.UI.composePreview)
    implementation(Dependency.UI.composeMaterial)
    implementation(Dependency.UI.composeCompiler)
    implementation(Dependency.UI.activityCompose)
    implementation(Dependency.UI.coilCompose)

    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.mockito)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    implementation(Dependency.DI.hiltAndroid)
    implementation(Dependency.DI.hiltCompose)
    kapt(Dependency.DI.hiltCompiler)

    implementation(Dependency.Network.retrofit)
    implementation(Dependency.Network.gsonConverter)
    implementation(Dependency.Network.okhttp)
    implementation(Dependency.Network.loggingInterceptor)

    implementation(Dependency.LocalStorage.room)
    kapt(Dependency.LocalStorage.roomCompiler)

    implementation(Dependency.Coroutine.core)
    implementation(Dependency.Coroutine.android)

    implementation(Dependency.Lifecycle.viewModel)
    implementation(Dependency.Lifecycle.liveData)
    implementation(Dependency.Lifecycle.runTime)

    implementation(Dependency.Navigation.navigationFragment)
    implementation(Dependency.Navigation.navigationUi)

    implementation(Dependency.WorkManager.ktx)
    implementation(Dependency.WorkManager.hiltExtension)

    implementation(Dependency.FireBase.fcm)
    implementation(Dependency.FireBase.message)

    implementation(Dependency.GooglePlayService.fitness)
    implementation(Dependency.GooglePlayService.auth)

    implementation(Dependency.Permission.tedPermission)

    implementation(Dependency.ThreeTenAndroidBackport.threeTenAbp)

    implementation(Dependency.CircleImageView.circleImage)

    implementation(Dependency.Glide.glideCore)
    annotationProcessor(Dependency.Glide.glideCompiler)

    implementation(Dependency.Socket.socketIo) {
        exclude(group = "org.json", module = "json")
    }

    implementation(Dependency.TedImagePicker.tedImagePicker)
}