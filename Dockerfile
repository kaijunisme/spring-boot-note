FROM openjdk:8
COPY ./target/*.jar /Documents/docker-workspace/app.jar
WORKDIR /Documents/docker-workspace
RUN sh -c 'touch app.jar'
ENTRYPOINT ["java","-jar","app.jar"]