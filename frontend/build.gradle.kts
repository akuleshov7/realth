import io.freefair.gradle.plugins.jsass.SassCompile
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    kotlin("js")
    id("io.freefair.jsass-base") version "5.3.0"  // to compile scss
//    id("com.eriwen.gradle.css") version "2.14.0"  // to minify compiled css, not currently compatible with gradle 6+
}

kotlin {
    js(LEGACY).browser {
            repositories {
                jcenter()
                maven("https://kotlin.bintray.com/js-externals")
            }
        }
    sourceSets["main"].dependencies {
        compileOnly(npm("bootstrap", "4.5.3"))
        compileOnly(npm("@fortawesome/fontawesome-free", "5.15.1"))
        compileOnly("kotlin.js.externals:kotlin-js-jquery:3.2.0-0")  // todo: use react instead of jquery
        implementation("org.jetbrains:kotlin-react:${Versions.KOTLIN_REACT}")
        implementation("org.jetbrains:kotlin-react-dom:${Versions.KOTLIN_REACT}")
        implementation(npm("chart.js", "2.9.4"))
        implementation(npm("react", Versions.REACT))
        implementation(npm("react-dom", Versions.REACT))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES}")
    }
    sourceSets["test"].dependencies {
        implementation(kotlin("test-js"))
    }
}

tasks.getByName("processResources", ProcessResources::class) {
    exclude("scss")
}

val compileSassTaskProvider = tasks.register("compileSass", SassCompile::class) {
    dependsOn(rootProject.tasks.getByName("kotlinNpmInstall"))  // to download dependencies
    dependsOn("processResources")
    source(fileTree("$projectDir/src/main/resources/scss").apply { include("/**/*.scss") })
    include("$projectDir/src/main/resources/scss/**/*.scss")
    destinationDir.set(file("$buildDir/processedResources/js/main/css"))
    doLast {
        copy {
            // add fontawesome font into the build
            from("$rootDir/build/js/node_modules/@fortawesome/fontawesome-free/webfonts")
            into("$buildDir/processedResources/js/main/webfonts")
        }
    }
}
tasks.withType<KotlinWebpack>().forEach {
    it.dependsOn(compileSassTaskProvider)
    it.doLast {
        copy {
            from("$buildDir/distributions")
            include("*.js")
            into("$buildDir/processedResources/js/main/js")
        }
    }
}
