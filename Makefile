DOCKER_TOMCAT = $(shell docker ps -a --filter name=tomcat -q)
DOCKER_TOMCAT_IMAGE = $(shell docker images --filter=reference='*tomcat:*latest' -q)
DOCKER_DANGLING = $(shell docker images --filter dangling=true -q)
DOCKER_MYSQL = $(shell docker ps -a --filter name=mysql -q)
MAKEFLAGS += --silent

start:
	- docker-compose up -d 

stop:
	- docker-compose down

restart:
	- docker-compose down && docker-compose up -d

mysql-console: 
	- docker exec -it $(DOCKER_MYSQL) mysql -u root -p

build: 
	- cd dashboard/api && ./gradlew build -x test
	- cp dashboard/api/build/libs/api.war Docker/tomcat

deploy:
	- cd dashboard/api && ./gradlew build -x test \
	  && cp build/libs/api.war ../../Docker/tomcat \
	  && docker stop $(DOCKER_TOMCAT) \
	  && docker rm $(DOCKER_TOMCAT)
	- docker rmi $(DOCKER_DANGLING) 
	- docker rmi $(DOCKER_TOMCAT_IMAGE) 
	- docker-compose up -d

reset-tomcat: 
	- docker stop $(DOCKER_TOMCAT) 
	- docker rm $(DOCKER_TOMCAT) 
	- docker rmi $(DOCKER_DANGLING)
	- docker rmi $(DOCKER_TOMCAT_IMAGE)
	- cd Docker && docker-compose up -d

