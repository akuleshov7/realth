import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.LEGACY

plugins {
    kotlin("js") version Versions.KOTLIN
}

kotlin.js(LEGACY) {
    browser()
}