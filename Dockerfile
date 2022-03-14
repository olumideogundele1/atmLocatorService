FROM java:8
ARG JAR_FILE=build/libs/*.jar
ARG CONFIG_FILE=config/application.properties
COPY ${JAR_FILE} app.jar
COPY ${CONFIG_FILE} config.properties

EXPOSE 4304
ENTRYPOINT ["java","-Dspring.config.location=/config.properties","-jar", "/app.jar"]