import org.cqfn.realth.infra.configureDetekt
import org.cqfn.realth.infra.createDiktatTask
import org.cqfn.realth.infra.configureDiktat
import org.cqfn.realth.infra.createDetektTask
import org.cqfn.realth.infra.installGitHooks

plugins {
    kotlin("jvm") version Versions.KOTLIN apply false
    id("org.cqfn.diktat.diktat-gradle-plugin")
}

allprojects {
    repositories {
        jcenter()
    }

    configureDiktat()
    configureDetekt()
}

diktat {
    inputs = files("buildSrc/**/*.kt")
}
createDiktatTask()
createDetektTask()

installGitHooks()
