FROM tomcat:9-jdk17
RUN cd /usr/local/tomcat/webapps.dist \
    && cp -R * ../webapps
COPY ./Docker/tomcat/api.war /usr/local/tomcat/webapps
COPY ./Docker/tomcat/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY ./Docker/tomcat/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml

ENTRYPOINT ["catalina.sh", "run"]
