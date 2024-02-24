mvn clean compile jib:build -Djib.to.auth.username=$DOCKER_USER -Djib.to.auth.password=$DOCKER_PASS -Dimage=jhasanov/webtest:VERSION
