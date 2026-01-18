plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.facebook.react")
}

android {
    namespace = "com.example.androidhostapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.androidhostapp"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // React Native build config fields
        buildConfigField("boolean", "IS_NEW_ARCHITECTURE_ENABLED", project.findProperty("newArchEnabled")?.toString() ?: "true")
        buildConfigField("boolean", "IS_HERMES_ENABLED", project.findProperty("hermesEnabled")?.toString() ?: "true")
        buildConfigField("boolean", "IS_EDGE_TO_EDGE_ENABLED", project.findProperty("edgeToEdgeEnabled")?.toString() ?: "false")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // React Native dependencies
    implementation("com.facebook.react:react-android")
    implementation("com.facebook.react:hermes-android")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

react {
    // Configure paths for brownfield setup
    root = file("../../react-native-embedded-app")
    reactNativeDir = file("../../react-native-embedded-app/node_modules/react-native")
    cliFile = file("../../react-native-embedded-app/node_modules/react-native/cli.js")
    entryFile = file("../../react-native-embedded-app/index.js")
    
    // Full path to node for Android Studio compatibility (nvm path)
    nodeExecutableAndArgs = listOf("/Users/rick/.nvm/versions/node/v20.19.4/bin/node")
    
    // Needed to enable Autolinking
    autolinkLibrariesWithApp()
}

// Disable generated entry point as we use our own MainApplication
afterEvaluate {
    tasks.findByName("generateReactNativeEntryPoint")?.enabled = false
}