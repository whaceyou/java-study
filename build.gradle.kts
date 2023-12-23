plugins {
    id("java")
}

group = "com.ace"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configurations{
    this.compileOnly.get().extendsFrom(this.annotationProcessor.get())
}

java{

}

project.tasks.withType(JavaCompile::class.java).configureEach {
    this.options.encoding = "UTF-8"
    this.sourceCompatibility = JavaVersion.VERSION_17.toString()
    this.targetCompatibility = JavaVersion.VERSION_17.toString()
}


dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}