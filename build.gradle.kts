plugins {
    java
    application
    id("org.javamodularity.moduleplugin") version "2.0.0"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.beryx.jlink") version "3.2.1"
    id("com.gradleup.shadow") version  "8.3.0"
}

group = "com.toehisa"
version = "1.0"

repositories {
    mavenCentral()
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("com.toehisa.calculator")
    mainClass.set("com.toehisa.calculator.Launcher")
}

javafx {
    version = "21.0.10"
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation("org.controlsfx:controlsfx:11.2.1")
}


jlink {
    imageZip.set(layout.buildDirectory.file("/distributions/app-${javafx.platform.classifier}.zip"))
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        name = "app"
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.toehisa.calculator.Launcher"
    }
}
