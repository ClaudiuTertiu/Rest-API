## Purpose
 -> The aim of this project is to build a REST API from scratch.

## Info
-> This project was created using no framework as required for the technical interview of 2Checkout's company. All the data is stored locally using JSON format. For the BasicAuth the valid credentials are "username" = "admin" && "password" = "admin".

#### Endpoints:
* api/products -> To list all products.
* api/products/add -> To add a product
* api/users -> To list all the users registered.
* api/users/register -> To perform a registration.

### TO-DO
-> It's needed to complete the paths for the files located in database package. Complete the paths in "Constants" file.
## Technology
* Java 8
* Maven

## Dependencies
* Lombok
* Jackson
* Vavr

<dependencies>
 
    <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.11.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.vavr/vavr -->
    <dependency>
        <groupId>io.vavr</groupId>
        <artifactId>vavr</artifactId>
        <version>0.10.3</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>
    
</dependencies>

## Examples of terminal commands to test the functionalities:
* curl -v -X POST localhost:8000/api/users/register -d '{"username": "test" , "password" : "test"}'
* curl -v -X POST localhost:8000/api/products/add -d '{"name": "productTest", "price":"5" , "category":"test"}'
* curl -v -X GET localhost:8000/api/products
* curl -v -X GET localhost:8000/api/users


