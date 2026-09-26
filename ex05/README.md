# Experiment 5 — Servlet-Based Login System with User Authentication (GET & POST)

## Aim
To develop a Java Servlet-based login system that performs user authentication using both `doGet()` and `doPost()` methods, configured using a web deployment descriptor (`web.xml`), and deployed on Apache Tomcat.

---

## 1. Project Directory Structure

```text
ex05/
├── login.html                     # HTML Login form
├── README.md                      # Execution guide
├── src/
│   └── LoginServlet.java          # Java Servlet source code
└── WEB-INF/
    ├── web.xml                    # Deployment descriptor
    ├── lib/
    │   ├── jakarta.servlet-api.jar
    │   ├── jakarta.servlet.jsp.jstl-3.0.1.jar
    │   └── jakarta.servlet.jsp.jstl-api-3.0.0.jar
    └── classes/
        └── LoginServlet.class     # Compiled servlet bytecode
```

---

## 2. Server Requirement

> [!IMPORTANT]
> **Apache Tomcat 10.x or 11.x is REQUIRED for this experiment.**  
> Servlets cannot run on VS Code Live Server or standalone Java because they need a Servlet Container.  
> Note: Must be **Tomcat 10.x or 11.x** (not Tomcat 9) because the code uses modern **Jakarta EE** (`jakarta.servlet.*`).

---

## 3. How to Run in VS Code (Using Community Server Connectors)

If your lab uses VS Code with the **Community Server Connectors** extension:

1. **Compile the Servlet:**
   Open the integrated terminal in VS Code (`Ctrl + \``) and compile:
   ```cmd
   cd ex05
   javac -cp "WEB-INF\lib\jakarta.servlet-api.jar" -d WEB-INF\classes src\LoginServlet.java
   ```

2. **Add Tomcat to VS Code:**
   - In VS Code's left sidebar, click the **Servers** icon (or look for the **SERVERS** panel).
   - Click **Create New Server...** -> Select **Apache Tomcat** -> Choose **Tomcat 10.x** (or 11.x).
   - Browse and select your Tomcat installation directory (e.g., `C:\apache-tomcat-10.1.x`).

3. **Deploy `ex05`:**
   - Right-click your Tomcat server in the **SERVERS** panel -> Select **Add Deployment...**.
   - Select the `ex05` folder (`c:\Users\SAI\Desktop\Lab\IWP\ex05`).

4. **Start the Server:**
   - Right-click Tomcat -> Click **Start Server**.
   - Watch the server start in the terminal output.

5. **Open in Browser:**
   - Open: `http://localhost:8080/ex05/login.html`

---

## 4. Alternative: Run via Command Prompt (Tomcat CLI)

If running outside VS Code via Command Prompt:

### Step 1: Compile the Java Servlet
```cmd
cd c:\Users\SAI\Desktop\Lab\IWP\ex05
javac -cp "WEB-INF\lib\jakarta.servlet-api.jar" -d WEB-INF\classes src\LoginServlet.java
```

### Step 2: Deploy to Apache Tomcat
Copy the `ex05` folder into Tomcat's `webapps` directory:
```cmd
xcopy /E /I /Y "c:\Users\SAI\Desktop\Lab\IWP\ex05" "%CATALINA_HOME%\webapps\ex05"
```
*(If `%CATALINA_HOME%` is not set, manually copy the `ex05` folder and paste it into your Tomcat `webapps` directory, e.g. `C:\apache-tomcat-10.1.x\webapps\ex05`)*

### Step 3: Start Apache Tomcat
```cmd
cd %CATALINA_HOME%\bin
startup.bat
```
*(Or navigate to `C:\apache-tomcat-10.1.x\bin` and double-click `startup.bat`)*

---

## 5. Run and Test the Application

### Test Case 1: HTML Login Form (POST Method)
1. Open your web browser and go to:
   ```text
   http://localhost:8080/ex05/login.html
   ```

2. **Valid Login:**
   - Username: `admin`
   - Password: `12345`
   - Click **Login**
   - **Expected Output:**
     ```text
     Login Successful! Welcome, admin.
     ```

3. **Invalid Login:**
   - Username: `admin`
   - Password: `wrongpassword`
   - Click **Login**
   - **Expected Output:**
     ```text
     Login Failed! Invalid username or password.
     Try Again
     ```

### Test Case 2: Direct Servlet Access (GET Method)
1. In the browser, navigate directly to:
   ```text
   http://localhost:8080/ex05/LoginServlet
   ```
2. The servlet's `doGet()` method will execute and dynamically render the login page in the browser.
3. Enter credentials and click **Login** to test the `POST` authentication flow.

---

## 6. Stop Apache Tomcat (When Finished)

- **In VS Code:** Right-click the server in the Servers panel -> Click **Stop Server**.
- **In Terminal:** Run `shutdown.bat` from Tomcat's `bin` folder, or close the Tomcat console window.

---

## 7. Common Errors & Fixes

| Error | Cause | Solution |
| :--- | :--- | :--- |
| **`Cannot POST /LoginServlet`** or **`404`** | Opened `login.html` with VS Code **Live Server** instead of Tomcat. | Servlets require Tomcat. Access via `http://localhost:8080/ex05/login.html`. |
| **`ClassNotFoundException: jakarta.servlet...`** | Used Tomcat 9.x or older instead of Tomcat 10+. | Use **Apache Tomcat 10.x or 11.x**. |
| **Tomcat `startup.bat` flashes and closes** | `JAVA_HOME` environment variable is not set. | Set `JAVA_HOME` to your JDK path (e.g., `C:\Java\jdk-17.0.20.1`). |
| **`package jakarta.servlet does not exist`** | Ran `javac` without the `-cp` flag or outside `ex05`. | Make sure to `cd ex05` and include `-cp "WEB-INF\lib\jakarta.servlet-api.jar"`. |
