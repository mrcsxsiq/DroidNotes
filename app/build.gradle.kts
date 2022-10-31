import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.application")
    id("com.google.protobuf")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

apply(from = "../ktlint.gradle.kts")

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "dev.marcos.droidnotes"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        
        
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }

}

protobuf {
    protoc {
            val isMacM1 = if (osdetector.os == "osx") { ":osx-x86_64" } else { "" }
        artifact = "com.google.protobuf:protoc:3.17.3$isMacM1"
    }

    plugins {
        val isMacM1 = if (osdetector.os == "osx") { ":osx-x86_64" } else { "" }
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.39.0$isMacM1"
        }
        id("javalite") {
            artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0$isMacM1"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("java") {
                    option("lite")
                }
                id("grpc") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material-icons-extended:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.foundation:foundation:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
        implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.runtime:runtime-livedata:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
       implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.20.0")

    // Room
      implementation ("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    kapt ("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
    implementation ("androidx.room:room-ktx:${rootProject.extra["room_version"]}")
    testImplementation ("androidx.room:room-testing:${rootProject.extra["room_version"]}")

    // gRPC
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")
    implementation("io.grpc:grpc-okhttp:1.41.0")
    implementation("io.grpc:grpc-protobuf-lite:1.41.0")
    implementation("io.grpc:grpc-stub:1.41.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}
