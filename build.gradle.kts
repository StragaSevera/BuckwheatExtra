import net.minecraftforge.gradle.user.UserExtension

// Project extensions
fun pExt(name: String): String = project.property(name)!!.toString()

plugins {
    base
    kotlin("jvm") version "1.4.21"
}

repositories {
    mavenCentral()
    maven {
        name = "chickenbones"
        url = uri("http://chickenbones.net/maven/")
    }
    maven {
        name = "ic2, forestry"
        url = uri("http://maven.ic2.player.to/")
    }
    maven { // EnderIO & EnderCore
        name = "tterrag Repo"
        url = uri("http://maven.tterrag.com")
    }
    maven("http://www.ryanliptak.com/maven/")
    ivy {
        name = "BuildCraft"
        artifactPattern("http://www.mod-buildcraft.com/releases/BuildCraft/[revision]/[module]-[revision]-[classifier].[ext]")
    }
    ivy {
        name = "CoFHLib"
        artifactPattern("http://addons-origin.cursecdn.com/files/${pExt("cofhlib.cf")}/[module]-[revision].[ext]")
    }
    ivy {
        name = "CoFHCore"
        artifactPattern("http://addons-origin.cursecdn.com/files/${pExt("cofhcore.cf")}/[module]-[revision].[ext]")
    }
    ivy {
        name = "Railcraft"
        artifactPattern("http://addons-origin.cursecdn.com/files/${pExt("railcraft.cf")}/[module]_[revision].[ext]")
    }
    ivy {
        name = "IC2NuclearControl"
        artifactPattern("http://addons-origin.cursecdn.com/files/${pExt("nc.cf")}/[module]-[revision].[ext]")
    }
}

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven(url = "https://files.minecraftforge.net/maven")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
    dependencies {
        classpath("com.anatawa12.forge:ForgeGradle:1.2-1.0.3") {
            isChanging = true
        }
    }
}
apply(plugin = "forge")

group = pExt("mod.group")
version = pExt("mod.version")
base {
    archivesBaseName = pExt("mod.baseName")
}

val shade: Configuration by configurations.creating
configurations.implementation.get().extendsFrom(shade)

dependencies {
    shade(kotlin("stdlib"))
//    implementation("codechicken:CodeChickenLib:${pExt("minecraft.version")}-${pExt("codechickenlib.version")}:dev")
//    implementation("codechicken:CodeChickenCore:${pExt("minecraft.version")}-${pExt("codechickencore.version")}:dev")
//    implementation("codechicken:NotEnoughItems:${pExt("minecraft.version")}-${pExt("nei.version")}:dev")
//    implementation("net.industrial-craft:industrialcraft-2:${pExt("ic2.version")}:dev")
//    implementation("com.enderio.core:EnderCore:${pExt("enderiocore.version")}:dev")
//    implementation("com.enderio:EnderIO:${pExt("enderio.version")}:dev") {
//        isTransitive = false
//    }
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))
}

sourceSets {
    main {
        output.resourcesDir = output.classesDirs.files.first { it.path.contains("""build\classes\kotlin""") }
    }
}

// Minecraft itself
configure<UserExtension> {
    version = "${pExt("minecraft.version")}-${pExt("forge.version")}"
    runDir = "eclipse"
}
// processResources
val Project.minecraft: UserExtension
    get() = extensions.getByName<UserExtension>("minecraft")

tasks.withType<Jar> {
    // this will ensure that this task is redone when the versions change.
    inputs.properties += "version" to project.version
    inputs.properties += "mcversion" to project.minecraft.version

    // replace stuff in mcmod.info, nothing else
    filesMatching("/mcmod.info") {
        expand(mapOf(
            "version" to project.version,
            "mcversion" to project.minecraft.version
        ))
    }

    // shading Kotlin into mod jar
    shade.forEach { file ->
        from(project.zipTree(file)) {
            exclude("META-INF", "META-INF/**")
        }
    }
}
