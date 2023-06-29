plugins {
    id("com.reply.kotlin-application-conventions")
    kotlin("plugin.spring") version "1.8.22"
}

dependencies {
    api(project(":lib"))
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}

application {
    mainClass.set("com.reply.blockservice.AppKt")
}
