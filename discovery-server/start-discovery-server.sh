#!/bin/bash

mvn clean compile

/usr/bin/env /usr/lib/jvm/java-14-openjdk-amd64/bin/java -XX:+ShowCodeDetailsInExceptionMessages -Dfile.encoding=UTF-8 @/tmp/cp_dpnnl87oncdfuozslt9j33zo2.argfile com.example.discovery.DiscoveryServerApplication
