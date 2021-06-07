#!/bin/bash

mvn clean compile

/usr/bin/env /usr/lib/jvm/java-14-openjdk-amd64/bin/java -XX:+ShowCodeDetailsInExceptionMessages -Dfile.encoding=UTF-8 @/tmp/cp_4t49pldjuzg9fr1jdhkyrzgee.argfile com.example.apigateway.ApiGatewayApplication 
