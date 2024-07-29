FROM openjdk:8
EXPOSE 8080
ADD target/CrudDemo-0.0.1-SNAPSHOT.jar CrudDemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/CrudDemo-0.0.1-SNAPSHOT.jar" ] 
