# The Retail Store Discount API

## Description

On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

Write a Spring Boot Java application with test cases, which exposes an API such that given a bill, it finds the net payable amount. Please note the stress is on object oriented approach and test coverage. User interface is not required, only an API exposed. What we care about:

object oriented programming approach, provide a high level UML class diagram of all the key classes in your solution. This can either be on paper or using a CASE tool
1. unit test and code coverage
2. code to be generic and simple

leverage today's best coding practices
clear README.md that explains how the code and the test can be run and how the coverage reports can be generated
state what you will use to document your API, with an example sample request and response


### *Assumption*
Discount will be applied on below order and rules:
  - If the user is an employee of the store, he gets a 30% discount
  - If the user is an affiliate of the store, he gets a 10% discount
  - If the user has been a customer for over 2 years, he gets a 5% discount.
  - For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you
	get $ 45 as a discount).
  - The percentage based discounts do not apply on groceries.
  - A user can get only one of the percentage based discounts on a bill.
	   
## Installing

After checking out the git repo run the following maven command

```bash
mvn clean install
```

This should install all packages, run all unit tests

## Starting

To start the server please run the following maven command

```bash
mvn spring-boot:run
```


### *Java*
  - Java 17

### *Database*
  - Spring Boot H2 Database
  
##### DB scripts: 
  - We are using 'Customer' Table in order to get customer details before applying the discounts.
  - Creating some upfront dummy entry while application is getting up using below scripts which is present under folder 'src/main/resources'
    		
	    		schema-h2.sql
    			data.sql 
    		
    
  
##### H2 Console: 
 - H2 console link is [H2 Console](http://localhost:8093/retail-store-discount/h2-console/)
 - Password is configured in application.properties file.
 

### *Postman Curl*
  - Please find attached named 'retail-store-discount-assignments.postman_collection.json' to use the application.
  
  
### *Swagger URL*
Once the application is up, the swagger URL is [Swagger](http://localhost:8093/retail-store-discount/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/)

Replace the base url with the corresponding environment's url for get Swagger to that env.

### *Health Check URL*
Once the application is up, the Health check URL is [Actuator](http://localhost:8093/retail-store-discount/actuator/health)

Replace the base url with the corresponding environment's url for get Actuator to that env.

## EclEmma Software

EclEmma used as a Java code coverage tool.

## SonarLint Software

SonarLint software used to detect and fix quality and security issues as you write code in Java

## Testing

To execute the unit tests against the business logic service classes please run the following maven command

```bash
mvn test
```

## Code coverage Results:
-----------------------------------
		Package	         |   Coverage
-----------------------------------------
		utility	      	    81.8%
-----------------------------------------
		service  	            97.9%
-----------------------------------------
		controller	  	    100%
-----------------------------------------
