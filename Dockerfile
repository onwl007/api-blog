FROM hub.c.163.com/bingohuang/jdk8:latest

MAINTAINER onwl007 <onwl007@126.com>

COPY api-blog-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]