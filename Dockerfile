FROM openjdk:8-jre-alpine
RUN apk update && apk add bash && apk add mysql
ADD /target/cadastropf.jar cadastropf.jar
ENV JAVA_OPTS="-Xms128m -Xmx128m -XX:MaxMetaspaceSize=64m -XX:CompressedClassSpaceSize=8m -Xss256k -Xmn8m -XX:InitialCodeCacheSize=4m -XX:ReservedCodeCacheSize=8m -XX:MaxDirectMemorySize=16m"
EXPOSE 8086
ENTRYPOINT exec java $JAVA_OPTS -jar cadastropf.jar