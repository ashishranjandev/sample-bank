## **Sample Bank Application**

A sample bank application with basic features

* Create account
* Get balance info & transaction summary
* Add/Delete beneficiary
* Transfer funds instant and schedule for given date and time
* Calculate balance for user account for future date, given base interest rate of 4%

### Prerequisites

```
Java 8
```

### Code Structure

This application uses the parent spring-boot-starter-parent to manage dependencies and other shared maven configurations.

### Running Tests

E2E test are mentioned in Class: org.sample.bank.samplebank.integration.SampleBankApplicationTests

### Runtime

Start application from command line : java -jar sample-bank-<version>.jar --spring.profiles.active=$env-name

### Rest End Points

The Core functionalities are exposed as Rest End points.

	- Create Account [POST] http://<host>:<port>/accounts
		[Expects: {"name" : "John","address" : "SF","balance" : 900}]
		[Returns the created Account Details]
	- Get Account [GET] http://<host>:<port>/accounts/{accountNumber}
		[Returns the Account Details]
	- Create Beneficiary [POST] http://<host>:<port>/accounts/{accountNumber}/beneficiaries
		[Expects: {"beneficiaryAccountNumber" : 7882,"bankIdentificationCode" : "IFSC2121"}]
		[Returns the created Beneficiary]
	- Get Beneficiaries [GET] http://<host>:<port>/accounts/{accountNumber}/beneficiaries
		[Returns the Beneficiaries of the account]
	- Create Transaction Request [POST] http://<host>:<port>/accounts/{accountNumber}/transactionrequests
		[Expects Transcation Request {"beneficiaryId": 11, "isInstant": true, "amount": 200.00, "scheduledTime": null}]
		[Returns Created Transcation Request]
	- Get Transaction Request [POST] http://<host>:<port>/accounts/{accountNumber}/transactionrequests
		[Returns Transcation Requests]
	- Delete Beneficiaries 
		[DELETE] http://<host>:<port>/accounts/{accountNumber}/beneficiaries/{beneficaryId}
	- Get Balance for Future Date [GET] http://<host>:<port>/accounts/{accountNumber}/balance
		[Request Param dateStr to be providede in DD-MM-YYYY format i.e. 25-07-2019]
		
### Authors

* **Ashish Ranjan** - [My Git Hub](https://github.com/ashishranjandev)

	