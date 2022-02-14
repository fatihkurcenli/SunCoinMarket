@file:Suppress("unused")

object AndroidDependencies {

    const val coreLib = "androidx.core:core-ktx:${VersionsLb.coreVersion}"
    const val appcompatLib = "androidx.appcompat:appcompat:${VersionsLb.appcompatVersion}"
    const val coroutinesLib =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${VersionsLb.coroutinesVersion}"
    const val lifecycleKtxLib =
        "androidx.lifecycle:lifecycle-runtime-ktx:${VersionsLb.lifecycleVersion}"

    // UI
    const val materialLib = "com.google.android.material:material:${VersionsLb.materialVersion}"
    const val constraintLib =
        "androidx.constraintlayout:constraintlayout:${VersionsLb.constraintVersion}"

    // Navigation
    const val navFragmentLib =
        "androidx.navigation:navigation-fragment-ktx:${VersionsLb.navVersion}"
    const val navUiLib = "androidx.navigation:navigation-ui-ktx:${VersionsLb.navVersion}"
}

object KtDependencies {
    const val ktStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${VersionsLb.ktVersion}"
}

object RoomDependencies {
    const val roomRuntimeLib = "androidx.room:room-runtime:${VersionsLb.roomVersion}"
    const val roomCompilerLib = "androidx.room:room-compiler:${VersionsLb.roomVersion}"
}

object HiltDependencies {
    const val hiltAndroidLib = "com.google.dagger:hilt-android:${VersionsLb.hiltVersion}"
    const val hiltCompilerLib = "com.google.dagger:hilt-compiler:${VersionsLb.hiltVersion}"
}


object LintDependencies {
    const val lintApiLib =
        "com.android.tools.lint:lint-api:${VersionsLb.lintVersion}"
    const val lintChecksLib = "com.android.tools.lint:lint-checks:${VersionsLb.lintVersion}"
    const val lintLib = "com.android.tools.lint:lint:${VersionsLb.lintVersion}"
    const val lintTestsLib = "com.android.tools.lint:lint-tests:${VersionsLb.lintVersion}"
}

object LeakCanaryDependencies {
    const val leakcanaryLib =
        "com.squareup.leakcanary:leakcanary-android:${VersionsLb.leakcanaryVersion}"
}

object TimberDependencies {
    const val timberLib = "com.jakewharton.timber:timber:${VersionsLb.timberVersion}"
}

object TestDependencies {
    const val junitLib = "junit:junit:${VersionsLb.junitVersion}"
}

object GlideLibrary {
    const val glide = "com.github.bumptech.glide:glide:${VersionsLb.glideVersion}"
    const val glideAnnotation = "com.github.bumptech.glide:glide:${VersionsLb.glideVersion}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${VersionsLb.retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${VersionsLb.retrofitVersion}"
    const val converterScalars =
        "com.squareup.retrofit2:converter-scalars:${VersionsLb.retrofitVersion}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${VersionsLb.okkHttpVersion}"
    const val ulrConnection =
        "com.squareup.okhttp3:okhttp-urlconnection:${VersionsLb.okkHttpVersion}"
}

object Firebase {

    const val bom = "com.google.firebase:firebase-bom:${VersionsLb.firebaseBom}"
    const val analytics =
        "com.google.firebase:firebase-analytics-ktx:${VersionsLb.firebaseAnalytics}"
    const val auth = "com.google.firebase:firebase-auth-ktx:${VersionsLb.firebaseAuth}"
    const val firestore =
        "com.google.firebase:firebase-firestore-ktx:${VersionsLb.firebaseFirestore}"
    const val coroutinesPlayServices =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${VersionsLb.coroutinesPlayServices}"
}