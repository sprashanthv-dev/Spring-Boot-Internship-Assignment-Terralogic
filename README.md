# Spring-Boot-Internship-Assignment-Terralogic
Repository containing the implementation of the assignment requirements as part of Terralogic internship process

#### Submitted by: Prashanth Srinivasan

#### Tech Stack:
1) Spring Boot
2) Maven for dependency management
3) H2 for database operations

#### Summary
Spring Boot Web Application that generates a CSV containing node information with 10,000 records (configurable), imports it into H2 database and provides the ability to fetch all node information and to create new nodes.

#### Application Flow
1) On application startup, the user's `home` directory is checked for the existence of the `node_info.csv` file.
2) If the file is not present, the `CSVService` class along with the `CSVHelper` and `CSVMapper` helper classes, generates the CSV with 10,000 sample records.
3) The application then checks for the existence of records within the `NODE` table in the H2 database. If no records are present, it proceeds to bulk insert these records.
4) `spring-data-jdbc` is used for database operations that extends `CrudRepository`
5) `NodeController` receives the incoming user request, calls the corresponding method in `NodeService` class which then queries the database through the `NodeRepository` class
6) While adding new node information, the request body is validated through custom `NodeValidator` class
7) A `GlobalExceptionHandler` class handles all exceptions in the application that propagates from the repository to the controller layer.
8) Custom Response formatter classes `ApiResponse` and `ErrorResponse` are used to send back `JSON` responses to the client.