# Spring-Boot with MongoDb example. 

This project will help you connect with MongoDb using Spring-Boot application and do the CRUD operations on MongoDb. Also, this project has the rest controller exception handling for Spring-Boot application.

##### Prerequisites

* Java Development Kit (JDK), version 8 or higher.

* Maven

##### Getting the Project
https://github.com/knoldus/springboot-reactive-mongodb.git

##### Command to start the project

**mvn spring-boot:run**

Json Formats for different Rest services are mentioned below :

**1. Create User:**

> Route(Method - POST) : localhost:9000/users

Rawdata(json): { "id": "1", "name": "Deepak", "address": "Delhi", "age": "28" }

**2. Update User:**

>Route(Method - PUT) : localhost:9000/users

**3. Delete User:**

> Route(Method - DELETE) : localhost:9000/users/:id

**4. Get User details with id:**

> Route(Method - GET) : localhost:9000/users/:id

**5. Get All User details:**

>Route(Method - GET) : localhost:9000/users