#!/bin/bash
mvn -U clean package -DskipTests
nohup java -jar target/scratchy-spring.jar &
