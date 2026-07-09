#  Demo Web Shop Automation Framework

<p align="center">

![Java](https://img.shields.io/badge/orange
![Selenium](https://img.shields.io/badge/Selenium-een
![Cucumber](https://img.shieldscumber-BDD-brightgreen
![TestNG](https://imgo/badge/TestNG-Framework-red
![Mps://img.shields.io/badge/Maven-Build-blue
https://img.shields.io/badge/Allure-Reporting-purple
![GitHub](https://s.io/badge/GitHub-VersionControl-black

</p>

---

#  Project Overview

The **Demo Web Shop Automation Framework** is an end-to-end test automation solution developed for automating critical functionalities of the Demo Web Shop e-commerce application.

The framework is built using:

- Java
- Selenium WebDriver
- Cucumber BDD
- TestNG
- Maven
- Page Object Model (POM)
- Apache POI
- Allure Reports
- Extent Reports

The objective of this project is to automate user workflows, improve test coverage, reduce manual testing effort, and provide detailed test execution reports.

---

#  Project Objectives

- Automate end-to-end e-commerce workflows.
- Reduce manual testing effort.
- Improve test coverage and accuracy.
- Implement reusable automation components.
- Generate detailed test execution reports.
- Support data-driven testing using Excel.
- Follow industry-standard automation framework practices.

---

#  Application Under Test

**Website:**  
https://demowebshop.tricentis.com/

The Demo Web Shop application is a sample e-commerce platform used to automate and validate various customer transactions.

---

##  Technology Stack

<h2> Technology Stack</h2>

<table>
<tr>
<th>S.No</th>
<th>Technology/Tool</th>
<th>Purpose</th>
</tr>

<tr>
<td>1</td>
<td>Java 17</td>
<td>Programming Language</td>
</tr>

<tr>
<td>2</td>
<td>Selenium WebDriver</td>
<td>Web Browser Automation</td>
</tr>

<tr>
<td>3</td>
<td>Cucumber BDD</td>
<td>Behavior Driven Development (BDD)</td>
</tr>

<tr>
<td>4</td>
<td>TestNG</td>
<td>Test Execution and Assertions</td>
</tr>

<tr>
<td>5</td>
<td>Maven</td>
<td>Build and Dependency Management</td>
</tr>

<tr>
<td>6</td>
<td>Apache POI</td>
<td>Excel Data Handling</td>
</tr>

<tr>
<td>7</td>
<td>Page Object Model (POM)</td>
<td>Framework Design Pattern</td>
</tr>

<tr>
<td>8</td>
<td>Allure Reports</td>
<td>Test Reporting and Analytics</td>
</tr>

<tr>
<td>9</td>
<td>Extent Reports</td>
<td>Interactive HTML Reporting</td>
</tr>

<tr>
<td>10</td>
<td>Git</td>
<td>Version Control System</td>
</tr>

<tr>
<td>11</td>
<td>GitHub</td>
<td>Source Code Repository Management</td>
</tr>

<tr>
<td>12</td>
<td>Eclipse IDE</td>
<td>Development Environment</td>
</tr>

<tr>
<td>13</td>
<td>Gherkin</td>
<td>Writing BDD Feature Files</td>
</tr>

<tr>
<td>14</td>
<td>Chrome Browser</td>
<td>Application Testing Platform</td>
</tr>

<tr>
<td>15</td>
<td>WebDriverManager</td>
<td>Browser Driver Management</td>
</tr>

</table>




#  Framework Architecture

The framework follows the **Page Object Model (POM)** design pattern.

```text
Feature Files
      ↓
Step Definitions
      ↓
Page Objects
      ↓
Web Elements
      ↓
Selenium WebDriver
      ↓
Browser
```

### Advantages

✅ Code Reusability

✅ Easy Maintenance

✅ Better Readability

✅ Scalability

✅ Reduced Code Duplication

---

# 📂 Project Structure

```text
DemoShop_Cucumber
│
├── src/main/java
│   ├── pages
│   │
│   ├── AccountPage.java
│   ├── CartPage.java
│   ├── CategoryPage.java
│   ├── CheckoutPage.java
│   ├── CompareProductsPage.java
│   ├── ContactUsPage.java
│   ├── GuestCheckoutPage.java
│   ├── HomePage.java
│   ├── LoginPage.java
│   ├── ProductDetailsPage.java
│   ├── ProductFilterPage.java
│   ├── ProductSortingPage.java
│   ├── RecentlyViewedPage.java
│   ├── RegisteredCheckoutPage.java
│   ├── RegisterPage.java
│   ├── ReturnRequestPage.java
│   ├── SearchResultsPage.java
│   └── WishlistPage.java
│
├── src/test/java
│   ├── runner
│   │   └── TestRunner.java
│   │
│   ├── stepdefinitions
│   │
│   └── hooks
│
├── src/test/resources
│   ├── features
│   ├── UserCredentials.xlsx
│   └── extent.properties
│
├── allure-results
├── test-output
├── target
│
├── pom.xml
└── testng.xml
```

---

#  Functionalities Automated

##  User Management

- User Registration
- User Login
- User Logout
- Account Management

---

##  Product Management

- Product Search
- Product Filtering
- Product Sorting
- Product Comparison
- Product Details Validation

---

##  Wishlist Management

- Add Product to Wishlist
- Verify Wishlist Products
- Remove Product from Wishlist

---

##  Shopping Cart

- Add Product to Cart
- Update Product Quantity
- Remove Product from Cart
- Verify Cart Contents

---

##  Checkout Process

### Guest Checkout

- Product Selection
- Cart Review
- Billing Information
- Shipping Method
- Payment Method
- Order Confirmation

### Registered User Checkout

- Login
- Add Product to Cart
- Complete Purchase
- Verify Order Placement

---

## Customer Services

- Contact Us
- Return Request
- Recently Viewed Products

---

#  Data Driven Testing

The framework uses Excel files for managing test data.

### Files Used

```text
UserCredentials.xlsx
ExcelLoginUtility.java
```

### Benefits

- Flexible Test Data Management
- Multiple Input Combinations
- Reusable Test Data
- Easy Maintenance

---

#  Cucumber BDD Implementation

## Sample Feature

```gherkin
Feature: Product Search

Scenario: Search Product

Given the user is on the Demo Web Shop homepage

When the user searches for a valid product

Then matching products should be displayed
```

---

#  Test Execution

## Execute All Tests

```bash
mvn clean test
```

---

## Execute Specific Scenario

```java
@CucumberOptions(
tags = "@ProductSearch"
)
```

Run:

```bash
mvn test
```

---

## Run Through TestNG

```text
testng.xml
```

Right Click → Run As → TestNG Suite

---

#  Reporting

## Allure Reports

### Generate Results

```bash
mvn test
```

### Open Report

```bash
allure serve allure-results
```

### Features

- Dashboard View
- Pass/Fail Statistics
- Execution Timeline
- Detailed Logs

---

## Extent Reports

### Report Location

```text
test-output/
```

### Features

- Interactive HTML Dashboard
- Execution Summary
- Screenshots
- Logs
- Test Status

---

#  Version Control

Git and GitHub are used for:

- Source Code Management
- Branch Management
- Team Collaboration
- Pull Requests
- Version Tracking

---

#  Key Features

 Selenium WebDriver Automation

 Cucumber BDD Framework
 
 TestNG Integration

 Page Object Model

 Data Driven Testing

 Excel Utility Integration

 Allure Reporting

 Extent Reporting

 Maven Build Management

 Reusable Components

 Scalable Architecture

 GitHub Version Control

---

#  Project Outcomes

- Reduced manual testing effort.
- Increased test reliability.
- Improved execution speed.
- Better test coverage.
- Enhanced maintainability.
- Professional reporting integration.

---

#  Future Enhancements

- Selenium Grid Integration
- Jenkins CI/CD Integration
- Docker Support
- Cross Browser Testing
- Parallel Execution
- Cloud-Based Testing

---

#  Project Team

## Guided By

**Anand B**

---

## Submitted By – Group G

- Nisha Mandiwal
- Pammi Yashwitha Reddy
- Pasagadugula Sree Keerthana
- Manikanta Potnuru
- Arthi Prasuna Reddy Musipatla
- Kandukuri Siddarth Goud

---

#  Acknowledgement

We express our sincere gratitude to **Anand B** for guidance, support, and valuable suggestions throughout the development of this automation framework.

---

#  License

This project is developed for learning, training, and demonstration purposes.

---
**Demo Web Shop Automation Testing Framework**
Built with using Selenium, Java, Cucumber, TestNG, Maven & POM