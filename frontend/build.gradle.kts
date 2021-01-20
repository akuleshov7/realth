import io.freefair.gradle.plugins.jsass.SassCompile
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

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
        runTask {
            devServer = KotlinWebpackConfig.DevServer(
                open = false,
                port = 3000,
                contentBase = listOf("$buildDir/processedResources/js/main")
            )
        }
    }
    sourceSets["main"].dependencies {
        implementation(project(":common"))
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

// generate kotlin file with project version to include in web page
val generateVersionFileTaskProvider = tasks.register("generateVersionFile") {
    val versionsFile = File("$buildDir/generated/src/generated/Versions.kt")

    outputs.file(versionsFile)

    doFirst {
        versionsFile.parentFile.mkdirs()
        versionsFile.writeText(
            """
            package generated

            internal const val REALTH_VERSION = "$version"

            """.trimIndent()
        )
    }
}
val generatedKotlinSrc = kotlin.sourceSets.create("jsGenerated") {
    kotlin.srcDir("$buildDir/generated/src")
}
kotlin.sourceSets.getByName("main").dependsOn(generatedKotlinSrc)
tasks.withType<Kotlin2JsCompile>().forEach {
    it.dependsOn(generateVersionFileTaskProvider)
}

// setup SASS compilation
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
//    it.dependsOn(compileSassTaskProvider)
    it.doLast {
        copy {
            from("$buildDir/distributions")
            include("*.js")
            into("$buildDir/processedResources/js/main/js")
        }
    }
}
