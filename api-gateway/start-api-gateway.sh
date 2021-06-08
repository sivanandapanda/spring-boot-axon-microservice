#!/bin/bash

mvn clean compile
mvn package -DskipTests

java -jar ./target/api-gateway-0.0.1-SNAPSHOT.jar
