FROM openjdk:8-alpine
COPY target/twitter-1.0-SNAPSHOT.jar /usr/local/app/twitter/lib/twitter.jar
ENTRYPOINT ["java","-jar","/usr/local/app/twitter/lib/twitter.jar"]
