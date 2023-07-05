plugins {
    id("com.reply.kotlin-application-conventions")
    kotlin("plugin.spring") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.8.22"
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

dependencies {
    api(project(":lib"))
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.8.22")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
//    implementation("org.springframework.security.oauth:spring-security-oauth2:2.4.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.security.oauth:spring-security-oauth2")
//    implementation("com.nimbusds:nimbus-jose-jwt:7.8.1")
}

application {
    mainClass.set("com.reply.authorizationserver.AppKt")
}
