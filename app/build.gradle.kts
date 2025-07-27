plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.facerecognitionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.facerecognitionapp"
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
}

dependencies {




    implementation ("com.cloudinary:cloudinary-android:1.27.0") // Ensure correct version

    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))

    implementation ("com.cloudinary:cloudinary-android:2.0.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")   // Required for network requests

    implementation ("com.cloudinary:cloudinary-android:2.3.1")

    implementation ("com.google.android.material:material:1.11.0")  // Latest Material 3

    // Firebase Authentication
    implementation ("com.google.firebase:firebase-auth-ktx")

    // Firestore (for storing user details)
    implementation ("com.google.firebase:firebase-firestore-ktx")

    // Google Sign-In
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    // Retrofit (for API calls)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Glide (for image loading)
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    // CameraX (for scanning)
    implementation ("androidx.camera:camera-core:1.2.0")
    implementation ("androidx.camera:camera-camera2:1.2.0")
    implementation ("androidx.camera:camera-lifecycle:1.2.0")

    // Cloudinary SDK
    implementation ("com.cloudinary:cloudinary-android:2.0.0")
}


// Apply Google Services Plugin (OUTSIDE `dependencies`)
apply(plugin = "com.google.gms.google-services")
