pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm") version "1.9.22"
        kotlin("plugin.serialization") version "1.9.22"
        id("org.jetbrains.compose") version "1.6.10"
    }
}

rootProject.name = "RunnableApi"
