DOCKER_TOMCAT = $(shell docker ps -a --filter name=tomcat -q)
DOCKER_MYSQL = $(shell docker ps -a --filter name=mysql -q)

start:
	- cd Docker && docker-compose up -d 

stop:
	- cd Docker && docker-compose down

restart:
	- cd Docker && docker-compose down && docker-compose up -d

mysql-console: 
	- docker exec -it $(DOCKER_MYSQL) mysql -u root -p

build: 
	- cd dashboard/api && ./gradlew build && cp dashboard/api/build/libs/api.war Docker/tomcat

reset-tomcat: 
	- docker stop $(DOCKER_TOMCAT) \
		&& docker rm $(DOCKER_TOMCAT) \
		&& docker image rm docker_tomcat
	- cd Docker && docker-compose up -d

