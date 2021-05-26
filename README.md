# Employee Reimbursement System

### Description
Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement. 

---

### Technologies & Languages used
- Java 8              
- JDBC
- Jackson Databind 2.12.3         
- Log4j 2.14.1
- Maven 3.63
- Apache tomcat 8.5.63
- PostreSQL 10.16
- Javax servlet API 4.0.1
- JUnit 5.7
- Tomcat-Catalina 9.0.45
- JavaScript
- CSS
- HTML

### Features
- The user can view their pending and resolved reimbursement requests
- The user can view and update their information
- Administrators can approve/deny reimbursement information
- Administrators can view all past requests and see which administrator resolved it.

### TO-DO list
- Users can upload their receipts when which the administrator can view 
- Users can receive emails when one of their reimbursement request is resolved

---
### Getting started

- Environment setup (minimum version)
  - Tomcat 8.5.63 
  - JDK 1.8 
  - PostgreSQL 10.16

- To create database and use the DBSetup.sql
- Clone the repository:
```
 https://github.com/rss32/Employee-Reimbursement-System.git
```
- Include connection.properties file with your URL, username and password in the src/ main/ resources folder.
