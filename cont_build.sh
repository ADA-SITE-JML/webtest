mvn clean test
mvn build:package
docker build -t springio/webtest .
