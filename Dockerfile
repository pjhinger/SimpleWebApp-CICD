FROM ubuntu:16.04
FROM openjdk:11-jdk

ENV directory /simplewebapp

WORKDIR ${directory}

COPY . ${directory}

RUN apt-get update
RUN apt-get install -y maven pandoc texlive-latex-base texlive-fonts-recommended
RUN mvn package

EXPOSE 8080

CMD sh target/bin/simplewebapp
