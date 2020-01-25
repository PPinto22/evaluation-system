FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD evalsys-backend-1.0.jar evalsys-backend.jar
RUN sh -c 'touch /evalsys-backend.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /evalsys-backend.jar" ]
