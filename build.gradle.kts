
val javaVersion = 17
val kspigotVersion = "1.19.0"

plugins {
    kotlin("jvm") version "1.7.10"
    id("io.papermc.paperweight.userdev") version "1.3.8"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    kotlin("plugin.serialization") version "1.7.10"
}

group = "de.airblocks"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // PaperMC Dependency
    paperDevBundle("1.19.2-R0.1-SNAPSHOT")

    // KSpigot dependency
    implementation("net.axay", "kspigot", kspigotVersion)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xjdk-release=$javaVersion",
            )
            jvmTarget = "$javaVersion"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    assemble {
        dependsOn(reobfJar)
    }
}

bukkit {
    name = "JumpAndRun"
    apiVersion = "1.19"
    authors = listOf(
        "dino",
    )
    main = "$group.jumpandrun.JumpAndRun"
    version = getVersion().toString()
    commands {
        register("jumpandrun")
    }
    libraries = listOf(
        "net.axay:kspigot:$kspigotVersion",
        "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0",
    )
}
