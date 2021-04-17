FROM adoptopenjdk/openjdk15:latest
RUN mkdir /opt/app
COPY target/codingchallenge-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/codingchallenge-0.0.1-SNAPSHOT.jar"]