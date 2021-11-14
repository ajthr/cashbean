FROM maven:3.6.3-openjdk-11-slim AS BUILD_IMAGE
ARG JAR_FILE=target/*.jar
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM openjdk:11-slim
RUN useradd -m cashbean
USER cashbean
WORKDIR /home/cashbean
COPY --from=BUILD_IMAGE target/*.jar ./cashbean.jar
ENTRYPOINT ["java","-jar","cashbean.jar"]
