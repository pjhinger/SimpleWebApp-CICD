FROM openjdk:12

ENV directory /simplewebapp

WORKDIR ${directory}

COPY . ${directory}

RUN apt-get update
RUN apt-get install -y maven pandoc
RUN mvn package

EXPOSE 8080

RUN sh target/bin/simplewebapp
