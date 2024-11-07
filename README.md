[По-русски](doc%2Freadme%2FREADME_rus.md)

# DI-object-data-extractor

___

The module of the 'rzd-engineer-assistant' project responsible for obtaining information about infrastructure facilities.
It simulates a connection to the database of passport data for infrastructure facilities EK ASUI.

---

![ekasui-passport.jpg](doc%2Freadme%2Fekasui-passport.jpg)

When creating documents in the infrastructure directorate departments, it is often necessary to access the database of 
passport data of the objects to provide the required information. In most cases, engineers know how to properly use the 
existing services to obtain the necessary information, but this process, like document creation in general, takes a 
long time. With the automated document creation through the 'rzd-engineer-assistant' application, the above-described 
process is simplified by sending a request to this service and receiving the necessary data in special DTOs.

---

The application's API consists of GET endpoints (currently 8 endpoints) specifying the necessary parameters. It 
returns DTOs with the required data of the infrastructure object or for document creating. Microsoft Excel sheets with 
fictional data used, their PDF representations, and the OpenAPI specification reflecting the endpoints and DTO models 
are provided in the [doc](doc) folder. The used ER diagram of fictional database entities is presented as:

[diode-database-er-diagram.pdf](doc%2Fdiode-database-er-diagram.pdf)

---

Java version - 21;

The application runs on the Spring Boot framework v. 3.3.3;

Build system - Apache Maven;

Database - PostgreSQL, data migration system - Flyway;

Database access and entity mapping - spring-boot-starter-data-jpa, Hibernate;

Testing - JUnit, spring-boot-test, Mockito;

---

The service is divided into 3 working modules and 3 auxiliary modules:

client - contains the Spring component (@Service) client that allows sending an HTTP request to this service and 
processing the response to obtain the necessary data.

diodedtos - contains the DTO model of returned data and a utility class for creating test objects.

server - contains the logic for obtaining and processing requests for data about infrastructure objects.

tcddtos - contains DTO models for document creation, related to the 'three-click-doc' project module, necessary for 
processing requests for comprehensive information needed for document creation.

ededtos - contains DTO models for obtaining extended information about employees of departments, related to the 
'employee-data-extractor' project module, and is a dependency of the tcddtos module.

commonclasses - a module of the "Engineer Assistant RZD" project that contains common classes for all modules 
(exceptions, DTOs, etc.)

---

Instructions for running the application locally:

1. To run the application, the following software is required:
- Git (installation guide - https://learn.microsoft.com/ru-ru/devops/develop/git/install-and-set-up-git);
- JDK (Java SE 21, installation guide - https://blog.sf.education/ustanovka-jdk-poshagovaya-instrukciya-dlya-novichkov/);
- Apache Maven (installation guide for Windows - https://byanr.com/installation-guides/maven-windows-11/);
- PostgreSQL database, preferably the latest version.
2. Open a terminal/command prompt/PowerShell and execute the following commands:

```
cd [target directory for project download]

git clone git@github.com:RuslanYapparov/di-object-data-extractor.git

cd di-object-data-extractor/

mvn install

cd server/

mvn spring-boot:run

```

3. Before starting the application, ensure that port 8070 is free - by default, it will accept HTTP requests in 
accordance with the API (http://localhost:8070/). If necessary, you can change the corresponding setting in the 
[application.properties](server%2Fsrc%2Fmain%2Fresources%2Fapplication.properties).