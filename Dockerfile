FROM openjdk:11
ADD target/*.jar docker-spring-boot.jar
EXPOSE 9088
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]