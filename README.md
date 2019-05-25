'Account' Rest Service
account-service is a REST microservice application.

Implementation
Following tech spec is used for the TDD based implementation.

Java
Spring Boot
maven
JUnit
h2
The project is organized as a maven project and in order to compile, test and create a package maven is used.

Building the application
You could use maven to test and build the jar file.

In the root directory of the project run the following commands
# Compile
mvn -B clean compile

#Test
mvn -B clean test


#Create the package
mvn -B clean package
Using the API
Running the application
Start the application with the following command:
#Under the root folder of the project

java -jar target/account-service-1.0-SNAPSHOT.jar
Request
The endpoint of the application as given in the following table.

GET Account By Id
End Point	Operation	Port
http://localhost:8080/account/1	GET	8080
Sample Account Request
{
  "id" : "1",
  "name" : "account1"
}

GET All Account
End Point	Operation	Port
http://localhost:8080/account	GET	8080
Sample Account Request
[{
  "id" : "1",
  "name" : "account1"
}]

Update Account By Id
End Point	Operation	Port
http://localhost:8080/account/id	PUT	8080

Delete Account By Id
End Point	Operation	Port
http://localhost:8080/account/id	DELETE	8080


Database
TABLE NAME : ACCOUNTS

Column Name	Type	Not Null
ID	BIGINT	Y
NAME VARCHAR(255)	Y