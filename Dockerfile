FROM openjdk:14
ADD ./target/rest-chat-0.0.1-SNAPSHOT.jar backand.jar
ENTRYPOINT ["java", "-jar", "backand.jar"]