plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.google.code.gson:gson:2.10.1")
    compileOnly ("org.projectlombok:lombok:1.18.34")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.11.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.11.1")
}

/* application {
    mainClass = "org.example.Main"
} */

tasks.test {
    useJUnitPlatform()
}