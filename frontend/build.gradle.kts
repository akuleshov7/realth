import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.LEGACY

plugins {
    kotlin("multiplatform") version Versions.KOTLIN
}

kotlin.js(LEGACY) {
    browser()
}