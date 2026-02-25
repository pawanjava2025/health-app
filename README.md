# health-app

A simple Spring Boot Health Check Microservice exposing a REST endpoint
to return application health details.

This project includes a GitHub Actions CI pipeline that automatically
builds and tests the application on every push.

------------------------------------------------------------------------

## Tech Stack

-   Java 17
-   Spring Boot
-   Maven
-   REST API
-   GitHub Actions (CI/CD)
-   JUnit

------------------------------------------------------------------------

## Project Structure

health-app\
├── .github/workflows\
├── src\
│ ├── main\
│ │ ├── java\
│ │ └── resources\
│ └── test\
├── pom.xml\
├── mvnw / mvnw.cmd\
└── .gitignore

------------------------------------------------------------------------

## API Endpoint

### GET `/health/details`

Returns application health information.

### Sample Response

``` json
{
    "buildVersion": "NOT_SET",
    "configValue": "DevOps-Screening",
    "applicationName": "health-service",
    "timestamp": "2026-02-25T14:09:55.4945323"
}
```

------------------------------------------------------------------------

# Part 3 --- Troubleshooting Scenario

## Log Snippet

    2026-01-15 10:22:14 ERROR Failed to load config value: secure.mode
    java.lang.IllegalArgumentException: No such key 'secure.mode'

------------------------------------------------------------------------

## Top 3 Things I Would Check

### 1. Configuration Files

-   Verify if `secure.mode` exists in:
    -   `application.yaml`
    -   `application.properties`
    -   Profile-specific files (`application-dev.yaml`,
        `application-prod.yaml`)
-   Check YAML indentation (YAML is indentation-sensitive).
-   Ensure correct structure:

``` yaml
secure:
  mode: true
```

### 2. Active Spring Profile

Check active profile:

    echo $SPRING_PROFILES_ACTIVE

Ensure the property exists in the active profile configuration.\
Confirm profile is not overridden in CI/CD or environment.

### 3. Environment Variables / External Configuration

Verify if the property is expected from:

-   Environment variable
-   Docker / Kubernetes configuration
-   Jenkins / GitHub Actions pipeline
-   Spring Cloud Config Server

Check environment variable:

    echo $SECURE_MODE

If code uses:

    @Value("${secure.mode}")

and no default value is provided, the application will fail at startup
if the key is missing.

------------------------------------------------------------------------

## 2️ How I Would Fix This Issue

###  Add Missing Property

``` yaml
secure:
  mode: false
```

OR

    secure.mode=false

###  Provide Default Value (Safer Approach)

    @Value("${secure.mode:false}")
    private boolean secureMode;

###  Set Environment Variable (If Required)

    export SECURE_MODE=false

Docker:

    ENV SECURE_MODE=false

Kubernetes:

    env:
      - name: SECURE_MODE
        value: "false"

------------------------------------------------------------------------

## 3️ How I Would Prevent This From Happening Again

-   Use `@ConfigurationProperties` instead of scattered `@Value`
    annotations
-   Add validation using `@Validated` and `@NotNull`
-   Provide default values for critical configuration keys
-   Add configuration validation tests in CI/CD
-   Use centralized configuration management (Spring Config Server / AWS
    Parameter Store / Kubernetes ConfigMaps)
-   Document required environment variables clearly

# Part 4 --- Basic Linux Tasks

## 1️ View Last 20 Lines of Application Log

    tail -n 20 application.log

## 2️ Check if a Java Process is Running

    ps -ef | grep java

OR

    jps -l

## 3️ Export an Environment Variable

    export BUILD_VERSION=1.0.0

## 4️ Find application.yaml Anywhere on the System

    find / -name application.yaml 2>/dev/null
