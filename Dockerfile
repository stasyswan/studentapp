# fetch basic image
FROM maven:3.3.9-jdk-8

# application placed into /opt/app
RUN mkdir -p /opt/app
WORKDIR /opt/app

# selectively add the POM file and
# install dependencies
COPY pom.xml /opt/app/

# rest of the project
COPY src /opt/app/src

RUN cd /opt/app/ && mvn clean install compile package

# local application port
EXPOSE 5000

# execute it
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.gd.studentapp.App", "-Dlog4j.configurationFile=src/main/resources/log4j2.yaml", "-Djava.net.preferIPv4Stack=true"]