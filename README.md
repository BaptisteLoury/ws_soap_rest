# INSTALLATION STEPS

## Requirements

Docker and eventually Maven

## I .jar and .war files are not present (they should be)
Go in TrainBookingWS then execute

    mvn clean package

Then go in ClientApp and execute

    mvn clean package

## Launch services
Go in the root folder then:

    docker compose up

## Launch client
When the services are up and running, go in the root folder then:

    docker build -f Dockerfile.client.java-mvn . -t ws_soap_rest-java-client
    docker run --network=host -it ws_soap_rest-java-client java -jar client-app.jar