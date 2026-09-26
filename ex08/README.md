# Experiment 8 — Aspect-Oriented Programming (AOP) for Logging Method Execution Details in Spring Boot

## Aim
To create a Spring Boot application demonstrating Aspect-Oriented Programming (AOP) by implementing cross-cutting concerns (logging method execution, arguments, execution duration, and return values) using `@Aspect` and `@Around` advice without altering business logic.

---

## 1. Project Directory Structure

```text
ex08/
├── pom.xml                                               # Maven dependencies (spring-boot-starter-aop)
├── README.md                                             # Execution guide
└── src/
    └── main/
        ├── java/com/example/aopdemo/
        │   ├── AopDemoApplication.java                  # Spring Boot Entry Point
        │   ├── aspect/
        │   │   └── LoggingAspect.java                   # AOP Aspect with @Around advice
        │   ├── controller/
        │   │   └── StudentController.java               # REST Controller endpoints
        │   └── service/
        │       └── StudentService.java                  # Target Business Service
        └── resources/
            └── application.properties                   # Server port & context path
```

---

## 2. Server & Database Requirements

> [!NOTE]
> - **External Tomcat Needed? NO.** Spring Boot includes an **embedded Tomcat server**.
> - **Database Needed? NO.** This experiment uses an in-memory service (`StudentService.java`). No MySQL or MongoDB is required!

---

## 3. How to Run the Application

### Option A: Run directly in VS Code (Recommended)
1. Open folder `c:\Users\SAI\Desktop\Lab\IWP\ex08` in VS Code.
2. Open `src/main/java/com/example/aopdemo/AopDemoApplication.java`.
3. Click the **`Run`** button that appears right above `public static void main(String[] args)` (or press `F5`).
4. Watch the VS Code integrated terminal start the server:
   ```text
   Tomcat started on port 8080 (http) with context path '/ex08'
   Started AopDemoApplication in X.XXX seconds
   ```

### Option B: Run via Maven CLI
Open Command Prompt in `ex08` and run:
```cmd
cd c:\Users\SAI\Desktop\Lab\IWP\ex08
mvn clean package -DskipTests
mvn spring-boot:run
```

*Or execute the packaged JAR:*
```cmd
java -jar target\aopdemo-0.0.1-SNAPSHOT.jar
```

---

## 4. Run and Test the Application

### Test Case 1: GET Request (Fetch Student by ID)
Open your web browser and navigate to:
```text
http://localhost:8080/ex08/students/101
```

**Browser Displays:**
```text
Student{id=101, name='Aswathi E'}
```

**Server Console / VS Code Terminal Displays (AOP Interception):**
```text
Executing method: StudentService.getStudentById(..)
Arguments: 
 - 101
Method StudentService.getStudentById(..) executed successfully
Return Value: Student{id=101, name='Aswathi E'}
⏱ Execution Time: 2 ms
```

---

### Test Case 2: POST Request (Register Student)
Open PowerShell and run:
```powershell
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/ex08/students/register?name=Rajesh"
```

*Or using Command Prompt curl:*
```cmd
curl.exe -X POST "http://localhost:8080/ex08/students/register?name=Rajesh"
```

**Terminal Response:**
```text
Student registered successfully!
```

**Server Console Displays (AOP Interception):**
```text
Executing method: StudentService.registerStudent(..)
Arguments: 
 - Rajesh
Registering student: Rajesh
Method StudentService.registerStudent(..) executed successfully
Return Value: null
⏱ Execution Time: 1 ms
```

---

## 5. Stop the Application (When Finished)

- **In VS Code:** Click the red square **Stop** button on the debug bar.
- **In Terminal:** Press `Ctrl + C` and type `Y`.
