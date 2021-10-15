# Patient Health API
This API collects and persists information relating to patients and healthcare encounters 
for a network of healthcare providers. 

## Getting Started
### Start the Server
Right click AppRunner, and select "Run 'AppRunner.main()'"

### Connections

By default, this service starts up on port 8085 and accepts cross-origin
requests from `*`.

### Dependencies

#### JDK

You must have a JDK installed on your machine.

#### Postgres

This server requires that you have Postgres installed and running on the default Postgres port of 5432. 
It requires that you have a database created on the server with the name of `postgres`
Your username should be `postgres`
Your password should be `root`

### Testing

Unit and Integration tests are provided.  To run tests with coverage, right-click 
on the test package you wish to run and select `Run With Coverage`.  Coverage exceeds 
a standard of 100% method coverage and 75% line coverage.  

### Postman

Postman collection containing all 2xx and 4xx scenarios can be found here
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/16776033-2eee7897-a167-4f06-bf54-a965dd0e9214?action=collection%2Ffork&collection-url=entityId%3D16776033-2eee7897-a167-4f06-bf54-a965dd0e9214%26entityType%3Dcollection%26workspaceId%3Db8eb4c28-95c7-4d4c-9e99-9341b9efc96f)

NOTE:POSTMAN REQUESTS SHOULD BE RUN IN ORDER TO ENSURE PROPER DUPLICATION OF CERTAIN ERRORS

### Swagger
Swagger documentation can be seen by running the API and navigating to (http://localhost:8085/swagger-ui.html)

Swagger configuration can be found in src/main/java/io/catalyte/training/patienthealth/config/SwaggerConfiguration.java

### Linting
Project is linted according to Google Java coding standards.  Code may be manually linted by pressing `ctrl + alt + l`

### Logging
Log is managed with Log4j2, and is stored in a rolling file at C:\temp