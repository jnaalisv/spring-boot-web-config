buildscript {

    val springBootVersion = "1.5.4.RELEASE"
    var kotlinVersion: String by extra
    kotlinVersion = "1.1.2"

    repositories {
        //gradleScriptKotlin()
        jcenter()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
//        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }

}

val kotlinVersion: String by extra

apply {
    plugin("org.springframework.boot")
    plugin("groovy")
    plugin("java")
}

//bootRepackage {
//    mainClass = 'example.ExampleApplication'
//}

repositories {
    jcenter()
}

configurations.compile.exclude("commons-logging")

dependencies {
    runtime ("org.codehaus.groovy:groovy")

    compile ("org.slf4j:slf4j-api")
    compile ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compile ("org.springframework.boot:spring-boot-starter")
    compile ("org.springframework.boot:spring-boot-starter-web")
    compile ("org.springframework.boot:spring-boot-starter-tomcat")

    testCompile ("org.springframework.boot:spring-boot-starter-test")
}

//wrapper {
//    gradleVersion = "4.0"
//}
