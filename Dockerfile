FROM adoptopenjdk:11-jdk
EXPOSE 8080
ADD target/spring-boot-version.jar spring-boot-version.jar
ENTRYPOINT ["java","-jar","/spring-boot-version.jar"]