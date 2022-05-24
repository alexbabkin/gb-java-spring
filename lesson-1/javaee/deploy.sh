#! /bin/sh 
/opt/apache-tomcat-9.0.63/bin/shutdown.sh

[ "$#" -gt 0 ] && [ "$1" = -s ] && exit

mvn clean package
cp target/javaee.war /opt/apache-tomcat-9.0.63/webapps/javaee.war

[ "$#" -gt 0 ] && [ "$1" = -r ] && /opt/apache-tomcat-9.0.63/bin/startup.sh
