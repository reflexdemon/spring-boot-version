FROM adoptopenjdk:11-jdk
EXPOSE 8080
ADD target /app
WORKDIR /app
CMD java -cp . -jar spring-boot-version.jar