plugins {
    application
    kotlin("jvm") version "1.9.22"
}

group = "dhbw"
version = ""

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.1.0")
}

application {
    mainClass.set("Camera")
}

tasks {
    val buildJar = register<Jar>("buildJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources"))
        archiveFileName.set("camera.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) }
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
                .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }

    build {
        dependsOn(buildJar)
    }
}