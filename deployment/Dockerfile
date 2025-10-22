######################################################
## Build Deployment
FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu

COPY --chown=app:app target/spring-petclinic-rest-*.jar /home/app/spring-petclinic-rest.jar
COPY --chown=app:app run.sh /home/app/run.sh

RUN chmod +x /home/app/run.sh

WORKDIR /home/app
USER app

EXPOSE 8080

CMD /home/app/run.sh

