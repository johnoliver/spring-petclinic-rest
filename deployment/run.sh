#!/bin/bash

if [ -z ${JAVA_OPTS+x} ]; then
    JAVA_OPTS="-Xms157m -Xmx157m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
fi

if [ -z ${DISABLE_MONITORING+x} ]; then
    JAVA_OPTS="$JAVA_OPTS -Dotel.javaagent.extensions=agent-otel-monitoring.jar"
fi

if [ -z ${DISABLE_ILLUMINATE+x} ]; then
    JAVA_OPTS="$JAVA_OPTS -javaagent:/home/app/applicationinsights-agent.jar"
fi

#JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005"

java $JAVA_OPTS -jar spring-petclinic-rest.jar
