plugins {
    java
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    implementation("org.apache.logging.log4j:log4j-core:2.14.0")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = "org.example"
            artifactId = "my-library"
            version = "1.0-SNAPSHOT"

            pom {
                name.set("My Library")
                description.set("A concise description of my library.")
                url.set("http://www.example.com/library")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("developerId")
                        name.set("Developer Name")
                        email.set("developer@example.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/YashzAlphaGeek/Github_Gradle.git")
                    developerConnection.set("scm:git:ssh://github.com/YashzAlphaGeek/Github_Gradle.git")
                    url.set("https://github.com/YashzAlphaGeek/Github_Gradle")
                }
            }
        }
    }

    println("GPR User: ${project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME_GITHUB")}")
    println("GPR Token: ${project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN_GITHUB")}")

    repositories {
        maven {
            name = "Github_Gradle"
            url = uri("https://maven.pkg.github.com/YashzAlphaGeek/Github_Gradle")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME_GITHUB")
                password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN_GITHUB")
            }
        }
    }
}
