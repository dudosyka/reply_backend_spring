

plugins {
    id("com.reply.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    api(project(":lib"))
}

application {
    // Define the main class for the application.
    mainClass.set("com.reply.discoveryserver.AppKt")
}
