import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    kotlin("js")
}

kotlin {
    js(LEGACY) {
        browser {
            repositories {
                jcenter()
                maven("https://kotlin.bintray.com/js-externals")
            }
            commonWebpackConfig {
                cssSupport.enabled = true
            }
            runTask {
                devServer = KotlinWebpackConfig.DevServer(
                    open = false,
                    port = 3000,
                    contentBase = listOf("$buildDir/distributions")
                )
            }
        }
        binaries.executable()  // already default for LEGACY, but will be needed for IR
        sourceSets["main"].dependencies {
            implementation(project(":common"))

            // devDependencies for webpack
            compileOnly(devNpm("node-sass", "*"))
            compileOnly(devNpm("sass-loader", "*"))
            compileOnly(devNpm("style-loader", "*"))
            compileOnly(devNpm("css-loader", "*"))
            compileOnly(devNpm("url-loader", "*"))
            compileOnly(devNpm("file-loader", "*"))
//            compileOnly(devNpm("mini-css-extract-plugin", "*"))

            // npm dependencies to include in webpack bundle and some kotlin adapters
            implementation(npm("bootstrap", "4.5.3"))
            compileOnly(npm("@fortawesome/fontawesome-free", "5.15.1"))  // needed to copy fonts to resources, not needed in runtime
            compileOnly("kotlin.js.externals:kotlin-js-jquery:3.2.0-0")  // todo: use react instead of jquery
            implementation("org.jetbrains:kotlin-react:${Versions.KOTLIN_REACT}")
            implementation("org.jetbrains:kotlin-react-dom:${Versions.KOTLIN_REACT}")
            implementation("org.jetbrains:kotlin-react-table:7.6.3-pre.143-kotlin-1.4.21")
            implementation(npm("react", Versions.REACT))
            implementation(npm("react-dom", Versions.REACT))
            implementation(npm("chart.js", "2.9.4"))

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES}")
        }
        sourceSets["test"].dependencies {
            implementation(kotlin("test-js"))
        }
    }
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

val copyWebfontsTaskProvider = tasks.register("copyWebfonts", Copy::class) {
    // add fontawesome font into the build
    dependsOn(rootProject.tasks.getByName("kotlinNpmInstall"))  // to have dependencies downloaded
    from("$rootDir/build/js/node_modules/@fortawesome/fontawesome-free/webfonts")
    into("$buildDir/processedResources/js/main/webfonts")
}
tasks.withType<KotlinWebpack>().forEach {
    it.dependsOn(copyWebfontsTaskProvider)
    it.doFirst {
        val additionalWebpackResources = fileTree("$buildDir/processedResources/js/main/") {
            include("scss/**")
            include("webfonts/**")
        }
        copy {
            from(additionalWebpackResources)
            into("${rootProject.buildDir}/js/packages/realth-${project.name}")
        }
    }
    it.doLast {
        // remove resources that have been bundled into frontend.js
        file("$buildDir/distributions/scss").deleteRecursively()
        file("$buildDir/distributions/webfonts").deleteRecursively()
    }
}

detekt {
    config.setFrom(config.plus(file("detekt.yml")))
}
