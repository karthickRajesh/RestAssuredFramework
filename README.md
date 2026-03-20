# RestAssured Framework

A comprehensive REST API testing framework built with **RestAssured**, **TestNG**, and **Maven**. This framework provides a robust foundation for testing RESTful APIs with features like authentication, request/response handling, logging, and test listeners.

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Components](#project-components)
- [Running Tests](#running-tests)
- [Logging](#logging)
- [Contributing](#contributing)

## Overview

This RestAssured Framework is designed to automate API testing with a focus on:

- **Easy API Testing**: Simplify REST API testing with a fluent interface
- **Authentication Management**: Built-in support for token-based authentication
- **Comprehensive Logging**: Log all requests and responses for debugging
- **Test Reporting**: Generate detailed test reports with TestNG
- **Modular Architecture**: Well-organized code structure for maintainability and scalability

## Project Structure

```
RestAssuredFramework/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       │   └── com/api/
│       │       ├── base/              # Base classes for API services
│       │       │   ├── BaseService.java
│       │       │   ├── AuthService.java
│       │       │   └── UserService.java
│       │       ├── filters/           # Custom filters for logging
│       │       │   └── LoggingFilter.java
│       │       ├── listeners/         # TestNG listeners
│       │       │   └── TestListener.java
│       │       ├── models/            # Request/Response models
│       │       │   ├── request/
│       │       │   │   ├── LoginRequest.java
│       │       │   │   └── UpdateUserProfileRequest.java
│       │       │   └── Response/
│       │       │       └── LoginResponse.java
│       │       └── tests/             # Test classes
│       │           ├── LoginTest.java
│       │           ├── GetProfileTest.java
│       │           └── UpdateUserProfileTest.java
│       └── resources/
├── logs/                              # Log files directory
│   └── test.log
├── test-output/                       # TestNG reports
├── pom.xml                           # Maven configuration
├── testng.xml                        # TestNG configuration
└── README.md                         # This file
```

## Technologies Used

- **Java**: Programming language
- **RestAssured 6.0.0**: REST API testing library
- **TestNG 7.12.0**: Testing framework
- **Maven**: Build and dependency management
- **Jackson 2.21.1**: JSON processing library
- **Log4j 2.25.3**: Logging framework

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 8 or higher
- **Apache Maven** 3.6.0 or higher
- **Git** (optional, for version control)

Verify installation:
```bash
java -version
mvn -version
```

## Installation

1. **Clone or download the project**:
   ```bash
   git clone <repository-url>
   cd RestAssuredFramework
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

   This will download all required dependencies specified in `pom.xml`.

## Configuration

### Base URL Configuration

The base URL for API requests is configured in `BaseService.java`:

```java
private static final String BASE_URL = "http://64.227.160.186:8080";
```

Update this URL to your API server endpoint.

### API Endpoints

Service classes define API paths:

- **AuthService**: `/api/auth/` - Authentication endpoints
- **UserService**: `/api/users/` - User profile endpoints

### Logging Configuration

Logs are configured using Log4j. Log files are stored in the `logs/` directory.

## Usage

### Running All Tests

```bash
mvn test
```

### Running Specific Test Suite

```bash
mvn test -Dsuite=testng.xml
```

### Running Specific Test Class

```bash
mvn test -Dtest=LoginTest
```

### Running Specific Test Method

```bash
mvn test -Dtest=LoginTest#loginTest
```

## Project Components

### 1. Base Classes

#### **BaseService.java**
The foundation class for all API service classes. Provides common functionality:

- `setAuthToken(String token)`: Sets authorization header with bearer token
- `postRequest(Object payload, String endpoint)`: Sends POST request
- `getRequest(String endpoint)`: Sends GET request
- `putrequest(Object payload, String endpoint)`: Sends PUT request

**Features**:
- Automatic logging filter integration
- Centralized base URL management
- Built-in content type handling

#### **AuthService.java**
Extends `BaseService` to handle authentication:

- `login(LoginRequest payload)`: Authenticate user
- `getToken(LoginRequest payload)`: Extract token from login response

#### **UserService.java**
Extends `BaseService` to handle user operations:

- `getProfile(String token)`: Retrieve user profile
- `updateUserProfile(String token, UpdateUserProfileRequest payload)`: Update user profile

### 2. Filters

#### **LoggingFilter.java**
Custom RestAssured filter that logs:
- Request details (method, URL, headers, body)
- Response details (status code, headers, body)
- Request/response time

### 3. Listeners

#### **TestListener.java**
Implements `ITestListener` for test lifecycle management:

- `onTestStart`: Logs when a test begins
- `onTestSuccess`: Logs successful test completion
- `onTestFailure`: Logs failures and exceptions
- `onTestSkipped`: Logs skipped tests
- `onStart`: Logs test suite start
- `onFinish`: Logs test suite completion

### 4. Models (POJOs)

#### Request Models
- **LoginRequest**: Encapsulates login credentials
- **UpdateUserProfileRequest**: Contains user profile update data

#### Response Models
- **LoginResponse**: Represents authentication response with token, user details

### 5. Test Classes

#### **LoginTest.java**
Tests user authentication:

- Sends login request with credentials
- Extracts authentication token
- Validates response data

#### **GetProfileTest.java**
Tests fetching user profile:

- Uses authentication token
- Retrieves user profile information

#### **UpdateUserProfileTest.java**
Tests updating user profile:

- Uses authentication token
- Updates user profile data
- Validates successful update

## Running Tests

### Execute All Tests with Detailed Logging

```bash
mvn clean test
```

### Execute Tests and Generate Report

```bash
mvn clean test -Dtest=* -Dsuite=testng.xml
```

### View Test Reports

After test execution, open the HTML report:

```bash
open test-output/index.html
```

### Check Logs

View detailed logs:

```bash
tail -f logs/test.log
```

## Logging

The framework uses **Apache Log4j 2** for comprehensive logging:

- **Log Location**: `logs/test.log`
- **Log Levels**: INFO, DEBUG, WARN, ERROR, FATAL
- **Logged Information**:
  - API requests (method, URL, headers, payload)
  - API responses (status, body, headers)
  - Test execution events
  - Errors and stack traces

### Example Log Output

```
[INFO] Test Started: loginTest
[INFO] Request: POST http://64.227.160.186:8080/api/auth/login
[INFO] Response: 200 OK
[INFO] Test Success: loginTest
```

## Best Practices

1. **Use Service Classes**: Always use service classes (`AuthService`, `UserService`) instead of direct RestAssured calls
2. **Manage Tokens**: Store tokens securely and pass them as parameters
3. **Use POJO Models**: Convert responses to POJO objects for better readability and maintainability
4. **Implement Assertions**: Add assertions to validate response data and status codes
5. **Clean Logs Regularly**: Monitor and clean log files periodically
6. **Version Control**: Use meaningful commit messages when updating test cases

## Troubleshooting

### Common Issues

**1. Connection Refused**
- Verify the base URL is correct
- Ensure the API server is running
- Check network connectivity

**2. Authentication Failed**
- Verify credentials in `LoginRequest`
- Check if API requires specific header formats
- Confirm token format matches expectations

**3. Maven Build Failure**
- Clear Maven cache: `mvn clean install -U`
- Check Java version compatibility
- Verify all dependencies are available

**4. No Test Output**
- Ensure test methods are annotated with `@Test`
- Check test class names match patterns
- Verify testng.xml configuration

## Contributing

To contribute to this project:

1. Create a feature branch (`git checkout -b feature/YourFeature`)
2. Commit your changes (`git commit -m 'Add YourFeature'`)
3. Push to the branch (`git push origin feature/YourFeature`)
4. Create a Pull Request

## License

This project is provided as-is for educational and testing purposes.

## Contact & Support

For issues, questions, or suggestions, please reach out to the development team or create an issue in the repository.

---

**Last Updated**: March 2026

**Framework Version**: 0.0.1-SNAPSHOT

**Maintainer**: Your Development Team
