plugins {
    id("com.reply.kotlin-application-conventions")
    kotlin("plugin.spring") version "1.8.22"
}

dependencies {
    implementation(project(":lib"))
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}

application {
    // Define the main class for the application.
    mainClass.set("com.reply.testservice.AppKt")
}
