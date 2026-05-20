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
