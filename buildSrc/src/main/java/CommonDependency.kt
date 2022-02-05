object CommonDependency {
    const val DAGGER_VERSION = "2.38.1"
    const val FLOW_VERSION = "1.6.0"
    const val LIFE_CYCLE_VERSION = "2.4.0"
    const val GLIDE_VERSION = "4.12.0"
    const val NAVIGATION_VERSION = "2.5.0-alpha01"
    const val RXJAVA2_VERSION = "2.2.19"
    const val RXJAVA2_ANDROID_VERSION = "2.1.1"
    const val RXJAVA2_ADAPTER_VERSION = "2.9.0"
    const val RXJAVA2_TESTING_VERSION = "2.1.0"
    const val COROUTINES_VERSION = "1.6.0"
    const val MOCK_WEB_SERVER_VERSION = "4.6.0"
    const val MOCK_WEB_SERVER_KOTLIN_VERSION = "2.2.0"
    const val FIREBASE_VERSION = "29.0.4"

    const val DAGGER_HILT_DEP = "com.google.dagger:hilt-android:$DAGGER_VERSION"
    const val DAGGER_COMPILER = "com.google.dagger:hilt-android-compiler:$DAGGER_VERSION"

    const val FLOW_DEP = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$FLOW_VERSION"

    const val LIFECYCLE_DEP = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFE_CYCLE_VERSION"

    const val GLIDE_DEP = "com.github.bumptech.glide:glide:$GLIDE_VERSION"
    const val GLIDE_PRO = "com.github.bumptech.glide:compiler:$GLIDE_VERSION"

    const val SAFE_ARGS_FRAGMENT_DEP = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    const val SAFE_ARGS_UI_DEP = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

    const val RX_JAVA2_DEP = "io.reactivex.rxjava2:rxjava:$RXJAVA2_VERSION"
    const val RX_JAVA2_ANDROID_DEP = "io.reactivex.rxjava2:rxandroid:$RXJAVA2_ANDROID_VERSION"
    const val RX_JAVA2_ADAPTER_DEP = "com.squareup.retrofit2:adapter-rxjava2:$RXJAVA2_ADAPTER_VERSION"
    const val RX_JAVA2_TESTING_DEP = "androidx.arch.core:core-testing:$RXJAVA2_TESTING_VERSION"

    const val COROUTINES_ANDROID_DEP = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
    const val COROUTINES_CORE_DEP = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_VERSION"
    const val COROUTINES_TEST_IMP = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_VERSION"

    const val MOCK_WEB_SERVER_DEP = "com.squareup.okhttp3:mockwebserver:$MOCK_WEB_SERVER_VERSION"
    const val MOCK_WEB_SERVER_KOTLIN_DEP = "com.nhaarman.mockitokotlin2:mockito-kotlin:$MOCK_WEB_SERVER_KOTLIN_VERSION"

    const val FIREBASE_BOM_DEP = "com.google.firebase:firebase-bom:$FIREBASE_VERSION"
    const val FIREBASE_ANALYTICS_DEP = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_CONFIG = "com.google.firebase:firebase-config-ktx"
}