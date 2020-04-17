FROM java:8
EXPOSE 8082

VOLUME /tmp
ADD duty-server.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
