FROM eclipse-temurin:19-jre-focal
MAINTAINER Daniil_Tereshko

ADD target/book-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/book-service.jar"]

EXPOSE 8080 8089