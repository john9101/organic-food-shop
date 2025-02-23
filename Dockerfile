FROM openjdk:21-jdk-oracle

CMD ["./gradlew", "clean", "bootJar"]

COPY build/libs/*.jar organicbox-api.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/organicbox-api.jar"]