val project_version: String by project
val jdk_version: String by project
val kotlinx_datetime_version: String by project
val hcmc_extension_version: String by project
val exposed_version: String by project

plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "studio.hcmc"
version = project_version

repositories {
    mavenCentral()
    mavenLocal()
    maven { setUrl("https://jitpack.io") }
}

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(jdk_version.toInt())
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "studio.hcmc"
            artifactId = project.name
            version = project_version
            from(components["java"])
        }
        create<MavenPublication>("jitpack") {
            groupId = "com.github.hcmc-studio"
            artifactId = project.name
            version = "$project_version-release"
            from(components["java"])
        }
    }
}

dependencies {
    implementation("com.github.hcmc-studio:exposed-table-extension:$hcmc_extension_version")
    implementation("com.github.hcmc-studio:exposed-transaction-extension:$hcmc_extension_version")
    implementation("com.github.hcmc-studio:kotlin-format-extension:$hcmc_extension_version")
    implementation("com.github.hcmc-studio:kotlin-protocol-extension:$hcmc_extension_version")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$kotlinx_datetime_version")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposed_version")
}