# Experiment 7 — Spring Boot & Spring MVC Student CRUD Application with MySQL Integration

## Aim
To develop a Spring Boot and Spring MVC web application that performs full CRUD (Create, Read, Update, Delete) operations for managing student records using Spring Data JPA, MySQL database, and Thymeleaf UI templates.

---

## 1. Project Directory Structure

```text
ex07/
├── pom.xml                                               # Maven dependencies & build config
├── README.md                                             # Execution guide
└── src/
    └── main/
        ├── java/com/example/studentcrud/
        │   ├── StudentCrudApplication.java              # Spring Boot Main Entry Point
        │   ├── controller/
        │   │   └── StudentController.java               # Spring MVC HTTP routing controller
        │   ├── model/
        │   │   └── Student.java                         # JPA Entity mapped to MySQL table
        │   └── repo/
        │       └── StudentRepository.java               # Spring Data JPA CRUD Repository
        └── resources/
            ├── application.properties                   # MySQL connection & server configs
            └── templates/
                ├── form.html                            # Add / Edit student Thymeleaf form
                └── list.html                            # Student records list table view
```

---

## 2. Source Code & Configuration Overview

### A. Database Configuration (`src/main/resources/application.properties`)
Configures MySQL connection and context path `/ex07`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=MySQL@123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.thymeleaf.cache=false

server.port=8080
server.servlet.context-path=/ex07
```

> **Note:** If your lab computer has a different MySQL root password (e.g., `root`, `admin`, or empty `""`), update `spring.datasource.password` in line 3.

### B. Student Entity (`model/Student.java`)
Defines the `Student` table schema with validation:
- `id` (Auto-increment Primary Key)
- `name` (`@NotBlank(message = "Name is required")`)
- `email` (`@Email @NotBlank @Column(unique = true)`)
- `course` (`@NotBlank(message = "Course is required")`)
- `phone` (`@Pattern(regexp = "\\d{10}", message = "10-digit phone")`)

### C. Student Repository (`repo/StudentRepository.java`)
Extends `JpaRepository<Student, Long>` providing built-in database operations (`save`, `findAll`, `findById`, `deleteById`).

### D. Student Controller (`controller/StudentController.java`)
- `GET /ex07/students` — Displays list of all students (`list.html`).
- `GET /ex07/students/new` — Displays form to add a new student (`form.html`).
- `POST /ex07/students` — Validates and saves/updates student in MySQL.
- `GET /ex07/students/{id}/edit` — Populates edit form with existing student details.
- `POST /ex07/students/{id}/delete` — Deletes the student by ID.

---

## 3. Step-by-Step Execution

> **Important:** Spring Boot includes an **embedded Tomcat server**. You do **NOT** need to install or run Apache Tomcat for this experiment!

### Step 1: Ensure MySQL Service is Running
Make sure MySQL is started on your system:
```cmd
net start MySQL80
```
*(Or start MySQL via XAMPP, MySQL Workbench, or Services panel)*

---

### Step 2: Run the Application

#### Option A: Run directly in VS Code (Recommended)
1. Open VS Code and open folder `c:\Users\SAI\Desktop\Lab\IWP\ex07`.
2. Open `src/main/java/com/example/studentcrud/StudentCrudApplication.java`.
3. Click the **`Run`** button that appears right above `public static void main(String[] args)` (or press `F5`).
4. Watch the integrated terminal until you see:
   ```text
   Tomcat started on port 8080 (http) with context path '/ex07'
   Started StudentCrudApplication in X.XXX seconds
   ```

#### Option B: Run via Maven CLI
Open Command Prompt in `ex07` and run:
```cmd
cd c:\Users\SAI\Desktop\Lab\IWP\ex07
mvn clean package -DskipTests
mvn spring-boot:run
```

*Or run the packaged JAR directly:*
```cmd
java -jar target\student-crud-0.0.1-SNAPSHOT.jar
```

---

## 4. Run and Test the Application in Browser

### 1. View Student List (Read)
Open your browser and navigate to:
```text
http://localhost:8080/ex07/students
```

### 2. Add a New Student (Create)
1. Click **+ Add Student** or navigate to:
   ```text
   http://localhost:8080/ex07/students/new
   ```
2. Enter student details:
   - **Name:** `Alice Johnson`
   - **Email:** `alice@example.com`
   - **Course:** `B.Tech CSE`
   - **Phone:** `9876543210` *(Must be exactly 10 digits)*
3. Click **Save**.
4. The record is saved into MySQL, and you are redirected to the student list displaying Alice.

### 3. Update Student Record (Update)
1. In the student table, click **Edit** next to `Alice Johnson`.
2. Modify details (e.g., change Course to `B.Tech AIDS`).
3. Click **Save**.
4. The list will update with the new course.

### 4. Delete Student Record (Delete)
1. Click **Delete** next to any record and confirm in the popup.
2. The record will be permanently deleted from MySQL and the table refreshed.

---

## 5. Stop the Application (When Finished)

- **In VS Code:** Click the red square **Stop** button on the debug bar.
- **In Terminal:** Press `Ctrl + C` and type `Y`.

---

## 6. Common Errors & Troubleshooting

| Error | Cause | Solution |
| :--- | :--- | :--- |
| **`Access denied for user 'root'@'localhost'`** | Lab MySQL root password is not `MySQL@123`. | Open `src/main/resources/application.properties` and change `spring.datasource.password` to your lab MySQL password. |
| **`Port 8080 was already in use`** | Another application (Tomcat or previous experiment) is using port 8080. | In `application.properties`, change `server.port=8081` and access via `http://localhost:8081/ex07/students`. |
| **`10-digit phone` validation error** | Phone entered was not exactly 10 digits. | Enter a valid 10-digit phone number (e.g., `9876543210`). |
| **`404 Not Found`** | Visited `http://localhost:8080/students` without the context path. | Always include `/ex07`: `http://localhost:8080/ex07/students`. |
