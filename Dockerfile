FROM openjdk:8
WORKDIR /opt
RUN mkdir -p selenium
WORKDIR /opt/selenium
COPY . /opt/selenium
RUN chmod +x  /opt/selenium/mvnw
RUN chmod +x /usr/local/bin/chromedriver
ENTRYPOINT ["/opt/selenium/mvnw", "test"]
