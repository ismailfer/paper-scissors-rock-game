FROM openjdk:17-jdk-alpine
EXPOSE 9080
ARG JAR_FILE=target/paper-scissors-rock-game-1.0.jar
ADD ${JAR_FILE} paper-scissors-rock-game-1.0.jar
ENTRYPOINT ["java","-jar","paper-scissors-rock-game-1.0.jar"]