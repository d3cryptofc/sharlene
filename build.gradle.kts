plugins {
    java
    id("com.gradleup.shadow") version "9.3.0"
}

group = properties["projectGroup"] as String
version = properties["projectVersion"] as String

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-api:2.12.4")
    implementation("org.apache.logging.log4j:log4j-core:2.12.4")
    implementation("info.picocli:picocli:4.7.7")
    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.compileJava {
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "${project.group}.Main"
        attributes["Implementation-Version"] = project.version
    }
}

tasks.shadowJar {
    mergeServiceFiles()
}

tasks.named<Javadoc>("javadoc") {
    options.memberLevel = JavadocMemberLevel.PRIVATE
}
