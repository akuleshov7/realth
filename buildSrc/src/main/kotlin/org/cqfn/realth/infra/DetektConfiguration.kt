package org.cqfn.realth.infra

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

fun Project.configureDetekt() {
    apply<DetektPlugin>()
    configure<DetektExtension> {
        config = rootProject.files("detekt.yml")
        buildUponDefaultConfig = true
    }
}

fun Project.createDetektTask() {
    tasks.register("detektAll") {
        allprojects {
            this@register.dependsOn(tasks.getByName("detekt"))
        }
    }
}