// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.4.0")
    var room_version by  extra("2.5.0")

    repositories {
        google()
         mavenCentral()
    }
    
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.17")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}




