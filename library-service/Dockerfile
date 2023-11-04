FROM eclipse-temurin:19-jre-focal
MAINTAINER Daniil_Tereshko

ADD target/library-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/library-service.jar"]

EXPOSE 8080 8089