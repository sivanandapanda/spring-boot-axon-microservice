#!/bin/bash

mvn clean compile
mvn package -DskipTests

java -jar ./target/discovery-server-0.0.1-SNAPSHOT.jar

