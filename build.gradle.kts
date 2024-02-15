import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    idea
    `java-library`
    // https://github.com/spring-projects/spring-boot/blob/v2.7.18/gradle.propertie   kotlinVersion=1.6.21
    kotlin("jvm").version("1.6.21").apply(false)
    id("org.springframework.boot").version("2.7.18").apply(false)
//    id("io.spring.dependency-management") version "1.1.4"
}

allprojects {
    group = "com.ace"
    version = "1.0-SNAPSHOT"
}

subprojects {

    apply(plugin = "java-library")
    apply(plugin = "org.gradle.idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    val springCloudVersion by extra("2021.0.8")
    val springCloudAlibabaVersion by extra("2021.0.5.0")
    val springBootVersion by extra("2.7.18")
    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:$springCloudAlibabaVersion")
        }
    }

    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public")
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
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}
fun DependencyHandler.developmentOnly(dependencyNotation: Any): Dependency? =
    add("developmentOnly", dependencyNotation)

project("spring-boot2") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")

        implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.1")
        testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.3.1")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("com.mysql:mysql-connector-j")

        // actuator 和 metrics
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        runtimeOnly("io.micrometer:micrometer-registry-prometheus")
        //cloud
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    }


}






