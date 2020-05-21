FROM openjdk:8
ADD target/UserAndGroupsMicroservice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]