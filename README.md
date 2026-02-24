# health-app

A simple Spring Boot Health Check Microservice exposing a REST endpoint to return application health details.

This project includes a GitHub Actions CI pipeline that automatically builds and tests the application on every push.

---

## Tech Stack

- Java 17
- Spring Boot
- Maven
- REST API
- GitHub Actions (CI/CD)
- JUnit

---

## Project Structure

health-app  
 ├── .github/workflows  
 ├── src  
 │    ├── main  
 │    │     ├── java  
 │    │     └── resources  
 │    └── test  
 ├── pom.xml  
 ├── mvnw / mvnw.cmd  
 └── .gitignore  

---

## API Endpoint

### GET `/health/details`

Returns application health information.

### Sample Response

```json
{
  "status": "UP",
  "application": "health-app",
  "timestamp": "2026-02-24T18:30:00"
}
