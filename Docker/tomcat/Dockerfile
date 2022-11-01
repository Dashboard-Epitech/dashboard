FROM tomcat:9-jdk17
ARG WAR_PATH
RUN cd /usr/local/tomcat/webapps.dist \
    && cp -R * ../webapps
COPY ${WAR_PATH} /usr/local/tomcat/webapps
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml

ENTRYPOINT ["catalina.sh", "run"]
