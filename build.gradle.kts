plugins {
    java
    // For building a fat JAR.
    id("com.gradleup.shadow") version "9.3.0"
    // For code formatting.
    id("com.diffplug.spotless") version "7.2.1"
}

group = properties["projectGroup"] as String
version = properties["projectVersion"] as String

repositories {
    mavenCentral()
}

dependencies {
    // For logging.
    implementation("org.apache.logging.log4j:log4j-api:2.12.4")
    implementation("org.apache.logging.log4j:log4j-core:2.12.4")
    // For command line parsing.
    implementation("info.picocli:picocli:4.7.7")
    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

// Apply spotless formatting to Java source files.
spotless {
    java {
        googleJavaFormat()
    }
}

// Apply spotless always to Java compilation tasks.
tasks.withType<JavaCompile>().configureEach {
    finalizedBy("spotlessApply")
}

// Add project group/name to compiler args for annotation processing.
tasks.compileJava {
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

// Define function to include licenses in JAR.
fun Jar.includeLicenses() {
    from("LICENSE") { into("") }
    from("NOTICE") { into("") }
    from("LICENSES") { into("LICENSES") }
}

// Generate JAR manifest with main class and version and necessary license files.
tasks.jar {
    manifest {
        attributes["Main-Class"] = "${project.group}.Main"
        attributes["Implementation-Version"] = project.version
    }
    includeLicenses()
}

// Generate a shadow JAR because this project is distributed as a CLI.
tasks.shadowJar {
    mergeServiceFiles()
    includeLicenses()
}

// Generate Javadoc.
tasks.named<Javadoc>("javadoc") {
    options.memberLevel = JavadocMemberLevel.PRIVATE
}
