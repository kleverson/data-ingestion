import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.jetbrains.compose") version "1.6.0"
}

kotlin {
    jvmToolchain(17)
}

group = "com.kholanda.runnableapi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    // Necessário para ícones usados no projeto (~30MB adicionais)
    implementation(compose.materialIconsExtended)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-cio:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
}

compose.desktop {
    application {
        mainClass = "MainKt"

         buildTypes.release.proguard {
             configurationFiles.from(project.file("proguard-rules.pro"))
             isEnabled.set(false)
             obfuscate.set(false)
             optimize.set(true)
         }

        jvmArgs += listOf(
            "-XX:+UseCompressedOops",
            "-XX:+UseCompressedClassPointers",
            "-Dsun.java2d.noddraw=true"
        )

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )
            packageName = "RunnableApi"
            packageVersion = "1.0.0"
            description = "Desktop application built to process data ingestion via API"
            vendor = "KHolanda"

            modules(
                "java.base",
                "java.desktop",
                "java.logging",
                "java.net.http"
            )

            includeAllModules = false

            jvmArgs += listOf(
                "-Xmx384m",
                "-Xms64m",
                "-XX:+UseG1GC",
                "-XX:+UseStringDeduplication",
                "-XX:+UseCompressedOops",
                "-XX:+UseCompressedClassPointers",
                "-Dfile.encoding=UTF-8"
            )

            // Configurações específicas do Windows para MSI
            windows {
                menuGroup = "RunnableApi"
                upgradeUuid = "a4c8b2d3-e5f6-4a7b-8c9d-0e1f2a3b4c5d"
                perUserInstall = true
                dirChooser = true
                // iconFile.set(project.file("src/main/resources/icon.ico"))
            }

            // linux {
            //     iconFile.set(project.file("src/main/resources/icon.png"))
            // }

            // macOS {
            //     iconFile.set(project.file("src/main/resources/icon.icns"))
            // }
        }
    }
}
