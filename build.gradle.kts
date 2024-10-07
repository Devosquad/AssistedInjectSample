buildscript {
    dependencies {
        // Without this dependency, the update of hilt (2.50) for the assitedinject viewmodel don't work
       classpath("com.squareup:javapoet:1.13.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}
