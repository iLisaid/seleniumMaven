FROM openjdk:8
WORKDIR /opt
RUN mkdir -p selenium
WORKDIR /opt/selenium
COPY . /opt/selenium
ENTRYPOINT ["/opt/selenium/mvnw", "test"]
