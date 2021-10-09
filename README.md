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