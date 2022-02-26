object Dependency {

    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val androidKtx = "androidx.activity:activity-ktx:${Version.androidKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"

    object UI {
        const val material = "com.google.android.material:material:${Version.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val compose = "androidx.compose.ui:ui:${Version.jetpackCompose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.jetpackCompose}"
        const val composePreview =
            "androidx.compose.ui:ui-tooling-preview:${Version.jetpackCompose}"
        const val composeMaterial =
            "androidx.compose.material:material:${Version.jetpackCompose}"
        const val composeCompiler =
            "androidx.compose.compiler:compiler:${Version.jetpackCompose}"
        const val activityCompose =
            "androidx.activity:activity-compose:${Version.activityCompose}"
        const val coilCompose =
            "io.coil-kt:coil-compose:${Version.coilCompose}"
    }

    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Version.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
        const val service = "com.google.gms:google-services:${Version.service}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
        const val mockito = "org.mockito:mockito-core:${Version.mockito}"
        const val androidJunit = "androidx.test.ext:junit:${Version.androidJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val mockitoKotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlin}"
        const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInline}"
        const val threeTenAbp = "org.threeten:threetenbp:${Version.threeTenAbp}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"
    }

    object DI {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        const val inject = "javax.inject:javax.inject:1"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltCompose}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    }

    object LocalStorage {
        const val room = "androidx.room:room-ktx:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
        const val sharedPreference =
            "androidx.preference:preference-ktx:${Version.sharedPreference}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
        const val runTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    }

    object GooglePlayService {
        const val fitness = "com.google.android.gms:play-services-fitness:${Version.googleFit}"
        const val auth = "com.google.android.gms:play-services-auth:${Version.googleAuth}"
    }

    object ThreeTenAndroidBackport {
        const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Version.threeTenAbp}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
        const val moshiCompiler = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshiKotlin}"
    }

    object Navigation {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    }

    object WorkManager {
        const val ktx = "androidx.work:work-runtime-ktx:${Version.workManager}"
        const val hiltExtension = "androidx.hilt:hilt-work:${Version.workManagerHiltExtension}"
    }
    
    object FireBase {
        const val fcm = "com.google.firebase:firebase-analytics-ktx:${Version.fcm}"
        const val message = "com.google.firebase:firebase-messaging:${Version.message}"
    }

    object Permission {
        const val tedPermission =
            "io.github.ParkSangGwon:tedpermission-normal:${Version.tedPermission}"
    }

    object CircleImageView {
        const val circleImage = "de.hdodenhof:circleimageview:${Version.circleImage}"
    }

    object Glide {
        const val glideCore = "com.github.bumptech.glide:glide:${Version.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    }

    object Socket {
        const val socketIo = "io.socket:socket.io-client:${Version.socket}"
    }
}