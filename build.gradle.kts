plugins {
    idea
    `java-library`
    // https://github.com/spring-projects/spring-boot/blob/v2.7.18/gradle.propertie   kotlinVersion=1.6.21
    kotlin("jvm").version("1.6.21").apply(false)
    id("org.springframework.boot").version("2.7.18").apply(false)
}

allprojects {
    group = "com.ace"
    version = "1.0-SNAPSHOT"
}

subprojects {

    apply(plugin = "java-library")
    apply(plugin = "org.gradle.idea")

    repositories {
        mavenCentral()
    }

    idea {
        module {
            isDownloadSources = true
            isDownloadJavadoc = true
        }

    }


    configurations {
        this.compileOnly.get().extendsFrom(this.annotationProcessor.get())
    }

    java {

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
}

project("spring-boot2") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.1")
        testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.3.1")
        "developmentOnly"("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("com.mysql:mysql-connector-j")
    }


}






