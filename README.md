# Spring CRUD Example

Simple CRUD application based on Spring using MySQL, Hibernate, Tomcat, Maven, Spring MVC, Bootstrap.

To run and test the application you can just open the project in your IDE and run it directly using Tomcat Plugin.

To run it manually, here's the recipe:

## Installation 

#### 1. Clone the project
```
git clone https://github.com/dotfinal/spring-crud-example.git
cd spring-crud-example/
```

#### 2. Change a database configuration in src/main/resources/db.properties (you need MySQL server)
```
vim src/main/resources/db.properties
```
The application will automatically create a database and fill it from in src/main/resources/import.sql.

#### 3. Build a war using Maven
```
mvn package
```

#### 4. Deploy a war-file in target/ using Tomcat

## Comments

Feel free to open an issue or send a message, if you see something I can fix or improve.




