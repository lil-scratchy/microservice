#!/bin/bash
mvn -U clean package -DskipTests
java -jar target/scratchy-spring.jar
