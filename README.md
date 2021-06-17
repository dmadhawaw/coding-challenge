# ANZ Coding-Challenge - CustomerAccountApplication

The CustomerAccountApplication will be able to perform below service operations .
 - View all the account details for given customer.
 - View all the transaction details for given account number.

In preceding Account controller class, has defined a number of RESTful URIs as follows to operate with the resource Customer and Account.
* 	/customers HTTP Get # Get all customers
* 	/customers/{customerId} HTTP Get # Get a customer
* 	/customers/{customerId}/accounts HTTP Get #Get accounts from a customer
* 	/accounts/{accountId}/transactions HTTP Get #Get transactions from a account

## git setup

You can clone using the https url with the following command:
    git clone https://github.com/dmadhawaw/coding-challenge.git
    
## Setup

This application has been developed  in spring boot micro-service architecture and has been used in-memory H2 database. The database migration scripts can be found in the below folder path:

### DB migration scripts : /coding-challenge/src/main/resources/db/migration/

- V1__create_schema.sql - This DDL script will create main three tables [Customer, Account , Transaction].
- V2__insert_schema.sql - This DML script will generate the required initial test data for the application.

- spring-boot version	- 2.5.1
- Java 	version 			- 1.8
- Junit 	version 			- 5

Once application is cloned in to the local folder , then you will have to run the below commands in the /coding-challenge/ folder:

 - mvn install - ‘this will download all the dependencies into the m2 repository. It compiles all Java sources and package them into one JAR file.’
 - java -jar target/<customeraccountapplication.jar> - this will invoke the JAR file and will launch the application. 

### CustomerAccountApplication
- AccountController RESTful api has been exposed and each request will be delegated to the AccountService and will manipulate the request integrating required data components and data will be retrieved accordingly.
- CustomertController RESTful api has been exposed and each request will be delegated directly to the  data repository component and data will be retrieved accordingly.
- Transaction Repository has been exposed and used to invoke transactions mapping for given account number.
- Controller Advice has been implemented to manipulate exception handling for HttpStatus code NOT_FOUND & BAD_REQUEST. A custom exception has been implemented to manage ‘ResourceNotFoundException’.
- Request parameter has been validated using the Java validation framework and hence Controller Advice has been implemented to trigger any ‘ConstraintViolationException’ and HttpStatsu BAD_REQUES will be thrown.
- ApiResponse and ErrorResponse has been declared to manipulate the Api errors. It mainly contains error response details and status code.
- Lombok has been used to mock some of the entities in the application.

## Testing

There are two testing targets: **unit** and **integration**.

### Unit Testing

- The unit testing lives under coding-challenge/src/test and has been used mockito framework to mock the required components:

- Account Controller Unit Test : This tests will test the Account Controller in REST pattern suing MockMvc URL mapping. This test will mock the required service   components and data repository components.

- Account Service Unit Test : This test will just test the account service component unit. In this case all the dependent data access components will be mocked. In this test also we instruct to JUnit to NOT to load any web environment or any controller components.

### Integration Testing

Account Controller Integration Test :  This test will tests the Account REST controller in REST style. 

Account Service Integration Test : this interacts between account service and it’s data dependencies. This tests doesn’t run any controller components, instead will isolate account service and data components.  This test connects to a real data source. 

### Swagger 2
Swagger 2 is an open-source project and has been used in this application to describe and document RESTful APIs.


### TODO & Assumptions

- As a further enhancement the application can be containerization using the docker.
- Since the current application has been implemented for GET requests , transaction handling should be implemented for REST idempotent methods.
- Request Filter Wrapper can be implemented to extend mainly for POST/PUT/ http methods.
- In this application main implementation is central to the AccountController and AccountService. As a better design approach TransactionService and Customer Service should be implemented and separated from the account service.
- In this application assume user/customer is already logged in and account & transactions will be mapped based on the customer Id along with the account number.
- Assume the user login security will be placed and the user is already authenticated and authorized. 




