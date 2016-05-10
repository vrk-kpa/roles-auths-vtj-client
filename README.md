# roles-auths-vtj-client

VTJ client for requesting Roles and authorizations related data over Xroad from VTJ.

## Getting Started
Maven commands:
* mvn install
* mvn spring-boot:run

## Start standalone jar
java -Dserver.port=8002 -jar target/roles-auths-vtj-client.jar

# About application properties
ssl-prefixed properties can be left empty if https is not used for communicating with Xroad server

## License headers

The license headers in source code files have been generated using the maven-license-plugin and enforced during project build.

If the build fails due to missing license headers they can be updated easily using `mvn license:format`.
