FROM docker-registry.kapa.ware.fi/rova-java-base:1.0

# Deploy project
RUN mkdir -p /opt/rova/roles-auths-vtj-client/
ADD target/roles-auths-vtj-client.jar /opt/rova/roles-auths-vtj-client/
ADD service.properties.template /opt/rova/roles-auths-vtj-client/
WORKDIR /opt/rova/roles-auths-vtj-client/

EXPOSE 8080
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "roles-auths-vtj-client.jar"]
