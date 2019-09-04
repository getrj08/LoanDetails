
# Loan-plan-details

In order to inform borrowers about their repayment schedule, the following program will help them to find every necessary details regarding the next payment amount, interest for total number of days, next installment date,outstanding amount etc.

## Libraries
-	Spring Boot 2.1.7.RELEASE
-	Swagger API
-	Maven

## Highlights
Interest will be calculated for total number of days in a month like 28,30 and 31 days.If the installment year is a leap year interest and annuity will be calculated accordingly.

## Requirements
Java(Required) along with Maven is needed to run the application

# How to run and test the application
	1- Clone the project from the link:
	 https://github.com/getrj08/LoanDetails.git
 
	2- Once downloaded use the following commands to run the application

                 mvn clean install
                 mvn spring-boot:run

	Additionally a Jar file is also provided in the project folder so that it can be directly added as an external library if required.

	To Run the application using this jar file:

                 java -jar loandetails.jar

To get the loan details the following service can be used

# Swagger

http://localhost:8080/swagger-ui.html#!/plan45controller/generatePlanUsingPOST

Request Body:
```
{
 "loanAmount": 5000,
 "nominalRate": 5,
 "duration": 24,
 "startDate": "2019-01-01T05:45:01Z"
}
```

Since it is an rest API we can use postman or ARC to test and obtain the required output.

# Postman/ARC
POST - http://localhost:8080/generate-plan
Headers:
```Content-Type : application/json``` 


Request Body:
```
{
 "loanAmount": 5000,
 "nominalRate": 5,
 "duration": 24,
 "startDate": "2019-01-01T05:45:01Z"
}
```