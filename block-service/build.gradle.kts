plugins {
    id("com.reply.kotlin-application-conventions")
    kotlin("plugin.spring") version "1.8.22"
}

dependencies {
    api(project(":lib"))
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
}

application {
    mainClass.set("com.reply.blockservice.AppKt")
}
