plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "swa.pin.livechatapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "swa.pin.livechatapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    val nav_version = "2.8.9"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    ksp("com.google.dagger:hilt-compiler:2.56.1")
    implementation("com.google.dagger:hilt-android:2.56.1")

    // For instrumentation tests
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.56.1")
    kspAndroidTest("com.google.dagger:hilt-compiler:2.56.1")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.56.1")
    kspTest("com.google.dagger:hilt-compiler:2.56.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}