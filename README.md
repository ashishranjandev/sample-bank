## **Sample Bank Application**

A sample bank application with basic features

a. Create account
b. Get balance info & transaction summary
c. Add/Delete beneficiary
d. Transfer funds instant and schedule for given date and time
e. Calculate balance for user account for future date, given base interest rate of 4%

### Code Structure

This application uses the parent spring-boot-starter-parent to manage dependencies and other shared maven configurations.

### Runtime

Start application from command line : java -jar sample-bank-<version>.jar --spring.profiles.active=$env-name

### Rest End Points

The Core functionalities are exposed as Rest End points
	- Create Account [POST] http://<host>:<port>/accounts
		[Expects: {"name" : "John","address" : "SF","balance" : 900}]
		[Returns the created Account Details]