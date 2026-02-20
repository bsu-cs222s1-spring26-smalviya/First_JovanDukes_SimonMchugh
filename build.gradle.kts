plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of("21"))
    }
}

javafx {
    version = "21.0.3"
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

application {
    mainClass.set("Wikipedia.WikiUI")
    // mainModule.set("edu.bsu.cs") // <- uncomment if you have a module-info.java named 'module edu.bsu.cs { ... }'
}