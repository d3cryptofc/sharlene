plugins {
    java
}

group = properties["projectGroup"] as String
version = properties["projectVersion"] as String

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "${project.group}.Main"
        attributes["Implementation-Version"] = project.version
    }
}

tasks.named<Javadoc>("javadoc") {
    options.memberLevel = JavadocMemberLevel.PRIVATE
}
