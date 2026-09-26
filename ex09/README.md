# Experiment 9 — Spring Boot Employee CRUD Web Application with MySQL

## Aim
To develop a full-featured web application using Spring Boot and Spring MVC with complete CRUD operations (Create, Read, Update, Delete) to manage employee records persisted in a MySQL database with form validation and Thymeleaf templates.

---

## 1. Project Directory Structure

```text
ex09/
├── pom.xml                                               # Maven dependencies & build settings
├── README.md                                             # Execution guide
└── src/
    └── main/
        ├── java/com/example/employeecrud/
        │   ├── EmployeeCrudApplication.java             # Spring Boot Main Application
        │   ├── controller/
        │   │   └── EmployeeController.java              # MVC controller handling CRUD web routes
        │   ├── model/
        │   │   └── Employee.java                        # JPA Entity with validation annotations
        │   └── repo/
        │       └── EmployeeRepository.java              # Spring Data JPA Repository
        └── resources/
            ├── application.properties                   # MySQL connection credentials & config
            └── templates/
                ├── employee-form.html                   # Thymeleaf form for Add / Edit employee
                └── employees.html                       # Table displaying all employee records
```

---

## 2. Server & Database Requirements

> [!NOTE]
> - **External Tomcat Needed? NO.** Spring Boot includes an **embedded Tomcat server**.
> - **MySQL Service Required:** Ensure MySQL is running on port `3306`.
> - **Database Auto-Creation:** Handled automatically by `createDatabaseIfNotExist=true` in `application.properties`.
> - **Password Note:** Default password is `MySQL@123`. If your lab PC has a different MySQL password (or blank `""`), update `spring.datasource.password` in `src/main/resources/application.properties`.

---

## 3. How to Run the Application

### Step 1: Ensure MySQL is Running
```cmd
net start MySQL80
```
*(Or verify MySQL is active in Services / XAMPP / MySQL Workbench)*

### Step 2: Launch the App

#### Option A: Run directly in VS Code (Recommended)
1. Open folder `c:\Users\SAI\Desktop\Lab\IWP\ex09` in VS Code.
2. Open `src/main/java/com/example/employeecrud/EmployeeCrudApplication.java`.
3. Click the **`Run`** button that appears right above `public static void main(String[] args)` (or press `F5`).
4. Look for:
   ```text
   Tomcat started on port 8080 (http) with context path '/ex09'
   Started EmployeeCrudApplication in X.XXX seconds
   ```

#### Option B: Run via Maven CLI
Open Command Prompt in `ex09` and run:
```cmd
cd c:\Users\SAI\Desktop\Lab\IWP\ex09
mvn clean package -DskipTests
mvn spring-boot:run
```

*Or execute the packaged JAR:*
```cmd
java -jar target\employee-crud-0.0.1-SNAPSHOT.jar
```

---

## 4. Run and Test the Application in Browser

### 1. View Employee List (Read)
Open your web browser and navigate to:
```text
http://localhost:8080/ex09/employees
```

### 2. Add New Employee (Create)
1. Click **+ Add Employee** or go to:
   ```text
   http://localhost:8080/ex09/employees/new
   ```
2. Enter valid details:
   - **Name:** `Sarah Connor`
   - **Email:** `sarah@example.com`
   - **Department:** `Engineering`
   - **Salary:** `75000`
3. Click **Save**.
4. The record is inserted into MySQL, and you are redirected to the employee table.

### 3. Validation Test
- Try submitting the form with an empty name or negative salary (e.g. `-500`).
- The page will display red inline validation errors preventing bad data from entering MySQL.

### 4. Edit Employee (Update)
1. In the employee table, click **Edit** next to `Sarah Connor`.
2. Update the salary to `82000` or change department.
3. Click **Save**.
4. The updated information is saved to MySQL and displayed in the table.

### 5. Delete Employee (Delete)
1. Click **Delete** next to an employee record and confirm in the popup.
2. The record is permanently deleted from MySQL, and the table refreshes.

---

## 5. Stop the Application (When Finished)

- **In VS Code:** Click the red square **Stop** button.
- **In Terminal:** Press `Ctrl + C` and type `Y`.

---

## 6. Common Errors & Fixes

| Error | Cause | Solution |
| :--- | :--- | :--- |
| **`Access denied for user 'root'@'localhost'`** | Lab MySQL password is not `MySQL@123`. | Change `spring.datasource.password` in `src/main/resources/application.properties`. |
| **`Communications link failure`** | MySQL service is not running. | Run `net start MySQL80` or start MySQL from Services / XAMPP. |
| **`Port 8080 was already in use`** | Another process is occupying port 8080. | Change `server.port=8081` in `application.properties` and browse to `http://localhost:8081/ex09/employees`. |
| **`404 Not Found`** | Omitted the context path `/ex09`. | Visit `http://localhost:8080/ex09/employees` (not `/employees`). |
