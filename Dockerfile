FROM 028686181435.dkr.ecr.ap-south-1.amazonaws.com/adoptopenjdk_17-jre-hotspot:latest

USER root
WORKDIR /apps

RUN adduser --uid 10101 -S retail-store-discount-api
RUN chown -R 10101 /apps

# The application's jar file
ARG JAR_FILE=target/retail-store-discount-api-*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /apps/retail-store-discount-api.jar

USER 10101
EXPOSE 8080

ENTRYPOINT ["java","-jar","/apps/retail-store-discount-api.jar"]
