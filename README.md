# NinjaShop Automation Framework

## Overview
NinjaShop Automation Framework is a comprehensive test automation solution built on the **Page Object Model (POM)** design pattern for testing the TutorialsNinja e-commerce platform. This framework is designed to validate core e-commerce functionalities including authentication, product browsing, cart management, and checkout processes.

---

## Key Features

### 1. **Authentication & Registration**
   - User login functionality with valid/invalid credentials
   - User registration with form validation
   - Error message handling for authentication failures

### 2. **Product Management**
   - Product browsing and search capabilities
   - Product filtering and sorting
   - Product detail validation
   - Search functionality with result validation

### 3. **Shopping Cart Operations**
   - Add products to cart
   - View cart contents
   - Update product quantities
   - Remove items from cart
   - Cart total calculation

### 4. **Checkout Process**
   - Checkout workflow validation
   - Delivery address handling
   - Order placement
   - Order confirmation verification

### 5. **Validation Testing**
   - Form field validation
   - Input error handling
   - Data integrity checks
   - Negative scenario testing

---

## Test Cases Handled

### Test Categories:
- **AuthTest**: Authentication and login scenarios
- **RegisterTest**: User registration and account creation
- **ProductTest**: Product browsing, search, and filtering
- **CartTest**: Shopping cart operations and management
- **CheckoutTest**: End-to-end checkout workflow
- **ValidationTest**: Form validation and error scenarios

### Parallel Execution:
- Tests are configured to run in parallel with a thread count of 5
- Ensures faster test execution and better resource utilization
- Uses TestNG for orchestration

---

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 8+ | Programming Language |
| Selenium | 4.34.0 | Web Automation |
| TestNG | 7.10.2 | Test Framework |
| WebDriverManager | 5.9.2 | Driver Management |
| Apache POI | 5.4.1 | Excel Data Handling |
| Extent Reports | 5.1.1 | Test Reporting |
| GSON | 2.10.1 | JSON Processing |
| Maven | Latest | Build Tool |

---

## Project Structure

```
ninjashop-automation/
├── pom.xml                                    # Maven configuration and dependencies
├── testng.xml                                 # TestNG configuration for test execution
├── README.md                                  # Project documentation
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/srm/hackathon/ninjashop/
│   │   │       ├── base/
│   │   │       │   └── BasePage.java          # Base class for all Page Objects
│   │   │       ├── factory/
│   │   │       │   ├── DriverFactory.java     # WebDriver initialization
│   │   │       │   └── DriverManager.java     # WebDriver lifecycle management
│   │   │       ├── pages/
│   │   │       │   ├── AccountPage.java       # User account page object
│   │   │       │   ├── AccountSuccessPage.java# Success page after account operations
│   │   │       │   ├── CartPage.java          # Shopping cart page object
│   │   │       │   ├── CheckoutPage.java      # Checkout page object
│   │   │       │   ├── HomePage.java          # Home page object
│   │   │       │   ├── LoginPage.java         # Login page object
│   │   │       │   ├── ProductPage.java       # Product listing page object
│   │   │       │   ├── RegisterPage.java      # Registration page object
│   │   │       │   └── SearchResultsPage.java # Search results page object
│   │   │       └── utils/
│   │   │           ├── ConfigReader.java      # Configuration file reader
│   │   │           ├── ExtentManager.java     # Extent Reports setup
│   │   │           ├── JsonUtil.java          # JSON data handling
│   │   │           ├── ScreenshotUtil.java    # Screenshot capture utility
│   │   │           └── WaitUtil.java          # Explicit and implicit waits
│   │   └── resources/
│   │       ├── config.properties              # Configuration properties
│   │       └── log4j.properties               # Logging configuration
│   └── test/
│       ├── java/
│       │   └── com/srm/hackathon/ninjashop/
│       │       ├── base/
│       │       │   └── BaseTest.java          # Base test class with setup/teardown
│       │       ├── dataprovider/
│       │       │   └── DataProviderUtil.java  # TestNG data providers
│       │       ├── listeners/
│       │       │   └── TestListeners.java     # TestNG listeners for reporting
│       │       └── tests/
│       │           ├── AuthTest.java          # Authentication test cases
│       │           ├── CartTest.java          # Shopping cart test cases
│       │           ├── CheckoutTest.java      # Checkout test cases
│       │           ├── ProductTest.java       # Product test cases
│       │           ├── RegisterTest.java      # Registration test cases
│       │           └── ValidationTest.java    # Validation test cases
│       └── resources/
│           └── testdata/
│               └── loginData.json             # Test data in JSON format
├── reports/
│   └── report.html                            # Extent report output
├── screenshots/                               # Test execution screenshots
├── target/                                    # Build artifacts and compiled classes
└── test-output/                               # TestNG output reports

```

---

## Configuration

### config.properties
Located at `src/main/resources/config.properties`

```properties
browser=chrome              # Browser choice: chrome, firefox, edge
baseUrl=https://tutorialsninja.com/demo  # Base URL for testing
timeout=10                  # Implicit wait timeout in seconds
headless=false              # Headless mode: true or false
```

### testng.xml
Configured for parallel test execution with:
- 5 concurrent threads
- 6 test suites (Auth, Registration, Product, Cart, Checkout, Validation)
- Custom test listener for enhanced reporting

---

## Test Case Handling Approach

### 1. **Page Object Model (POM)**
   - Each web page is represented as a separate class
   - Locators are centralized in page classes
   - Methods encapsulate user interactions
   - Improves maintainability and reduces code duplication

### 2. **Base Classes**
   - `BasePage`: Common methods for all pages (navigation, waits, assertions)
   - `BaseTest`: Test setup and teardown, driver initialization

### 3. **Data-Driven Testing**
   - Test data stored in JSON format
   - DataProviderUtil supplies data to test methods
   - Enables testing with multiple data sets

### 4. **Logging & Reporting**
   - Extent Reports for comprehensive HTML reports
   - Screenshots captured on test failures
   - Test listener tracks pass/fail/skip status
   - Detailed logs for debugging

### 5. **Wait Strategies**
   - Explicit waits for critical elements
   - Implicit waits as fallback
   - WaitUtil provides reusable wait methods

### 6. **Error Handling**
   - Validation tests handle edge cases and negative scenarios
   - Error messages are captured and verified
   - Form validation scenarios are thoroughly tested

---

## Prerequisites

- **Java**: Version 8 or higher
- **Maven**: Latest version
- **Browser**: Chrome (default, can be configured for Firefox/Edge)
- **WebDriver**: Automatically managed by WebDriverManager

---

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd ninjashop-automation
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Properties (Optional)
Edit `src/main/resources/config.properties` if needed

---

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=AuthTest
```

### Run Using TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run Specific Test Method
```bash
mvn test -Dtest=AuthTest#testValidLogin
```

---

## Test Reports

After test execution:
- **Extent Report**: `reports/report.html`
- **TestNG Report**: `test-output/index.html`
- **Screenshots**: `screenshots/` directory (on failures)

Open the report HTML files in a browser to view detailed results, logs, and screenshots.

---

## Project Highlights

✅ **Page Object Model**: Clean separation of concerns with dedicated page classes
✅ **Parallel Execution**: Faster test runs with multi-threaded execution
✅ **Data-Driven**: JSON-based test data management
✅ **Comprehensive Reporting**: Extent Reports with screenshots and logs
✅ **Listener Implementation**: Automatic screenshots on test failures
✅ **Config Management**: Externalized configuration for easy environment switching
✅ **Reusable Utilities**: Common utilities for waits, screenshots, and data handling
✅ **Validation Testing**: Extensive negative scenario and validation testing

---

## Author
SRM Hackathon Team

## Last Updated
April 2026
