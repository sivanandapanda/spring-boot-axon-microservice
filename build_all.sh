#!/bin/bash

mvn -f discovery-server/pom.xml clean package -DskipTests

mvn -f api-gateway/pom.xml clean package -DskipTests

mvn -f products-service/pom.xml clean package -DskipTests
