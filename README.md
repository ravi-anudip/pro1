# Employee Manager – Spring Boot MVC CRUD

Spring Boot 3 · Spring MVC · Thymeleaf · Spring Data JPA · Bean Validation · H2 · Bootstrap 5

## Features
- Create / Read / Update / Delete employees
- Search by name, email or department
- Server-side validation with inline errors + duplicate-email check
- Flash messages, 404 error page, layered architecture (Controller → Service → Repository)
- Seed data + MockMvc tests

## Run
```bash
mvn spring-boot:run      # requires JDK 17+ and Maven
```
Open http://localhost:8080
H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:employeedb`, user `sa`, blank password)

## Test
```bash
mvn test
```

## Routes
| Method | URL                       | Purpose           |
|--------|---------------------------|-------------------|
| GET    | /employees?keyword=       | List / search     |
| GET    | /employees/{id}           | View details      |
| GET    | /employees/new            | Create form       |
| GET    | /employees/{id}/edit      | Edit form         |
| POST   | /employees/save           | Create or update  |
| POST   | /employees/{id}/delete    | Delete            |

## Structure
```
src/main/java/com/example/employeecrud
├── EmployeeCrudApplication.java
├── controller/EmployeeController.java
├── service/EmployeeService.java, EmployeeServiceImpl.java
├── repository/EmployeeRepository.java
├── model/Employee.java
└── exception/ResourceNotFoundException.java, GlobalExceptionHandler.java
src/main/resources
├── application.properties, data.sql
└── templates/ layout.html, error-page.html, employees/{list,form,view}.html
```
