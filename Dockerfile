FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/spring-boot-version.war /app/
WORKDIR /app
CMD java -cp . -jar spring-boot-version.war
