plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.appgym"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    // https://mvnrepository.com/artifact/com.auth0/java-jwt
    implementation("com.auth0:java-jwt:4.4.0")
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    // https://mvnrepository.com/artifact/javax.mail/mail
    implementation("javax.mail:mail:1.4.7")
    // https://mvnrepository.com/artifact/javax.mail/javax.mail-api
    implementation("javax.mail:javax.mail-api:1.6.2")
    // https://mvnrepository.com/artifact/jakarta.inject/jakarta.inject-api
    implementation("jakarta.inject:jakarta.inject-api:1.0")


}

tasks.withType<Test> {
    useJUnitPlatform()
}
