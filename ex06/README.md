# Experiment 6 — User Feedback System using JSP, Sessions, Cookies, and JSTL Tags

## Aim
To implement a dynamic web-based feedback system using JavaServer Pages (JSP) incorporating Session Handling, HTTP Cookies, and JSTL core tags (`<c:forEach>`), deployed on Apache Tomcat.

---

## 1. Project Directory Structure

Ensure the `ex06` folder contains the following structure:

```text
ex06/
├── feedback.jsp                   # JSP input form for feedback
├── submitFeedback.jsp             # JSP processing sessions, cookies, and JSTL
├── README.md                      # Execution guide
└── WEB-INF/
    ├── web.xml                    # Web deployment descriptor
    └── lib/                       # Jakarta EE JSTL dependencies
        ├── jakarta.servlet.jsp.jstl-3.0.1.jar
        └── jakarta.servlet.jsp.jstl-api-3.0.0.jar
```

---

## 2. Server Requirement

> [!IMPORTANT]
> **Apache Tomcat 10.x or 11.x is REQUIRED for this experiment.**  
> JSP files contain server-side Java directives and JSTL tags that require Tomcat's Jasper JSP compiler.  
> Note: Must be **Tomcat 10.x or 11.x** to match the modern Jakarta EE JSTL taglib (`jakarta.tags.core`).

---

## 3. How to Run in VS Code (Using Community Server Connectors)

If your lab uses VS Code with the **Community Server Connectors** extension:

1. **Add Tomcat to VS Code (if not already added):**
   - Open the **Servers** panel in VS Code's sidebar.
   - Click **Create New Server...** -> Select **Apache Tomcat** -> Choose **Tomcat 10.x** (or 11.x).
   - Point to your local Tomcat folder (e.g., `C:\apache-tomcat-10.1.x`).

2. **Deploy `ex06`:**
   - Right-click your Tomcat server in the **SERVERS** view -> Select **Add Deployment...**.
   - Select the `ex06` folder (`c:\Users\SAI\Desktop\Lab\IWP\ex06`).

3. **Start the Server:**
   - Right-click the server -> Click **Start Server**.
   - Watch the server initialization logs in the terminal.

4. **Open in Browser:**
   - Open: `http://localhost:8080/ex06/feedback.jsp`

---

## 4. Alternative: Run via Command Prompt (Tomcat CLI)

### Step 1: Deploy to Apache Tomcat
Copy the `ex06` folder into Tomcat's `webapps` directory:
```cmd
xcopy /E /I /Y "c:\Users\SAI\Desktop\Lab\IWP\ex06" "%CATALINA_HOME%\webapps\ex06"
```
*(If `%CATALINA_HOME%` is not set, manually copy and paste the `ex06` folder into `C:\apache-tomcat-10.1.x\webapps\ex06`)*

### Step 2: Start Apache Tomcat
```cmd
cd %CATALINA_HOME%\bin
startup.bat
```
*(Or navigate to `C:\apache-tomcat-10.1.x\bin` and double-click `startup.bat`)*

---

## 5. Run and Test the Application

### 1. Open the Feedback Form
Open your browser and navigate to:
```text
http://localhost:8080/ex06/feedback.jsp
```

### 2. Submit Feedback 1
- **Name:** `Alice`
- **Feedback:** `The course material is very comprehensive!`
- Click **Submit Feedback**

**Expected Output:**
```text
Thank You, Alice!
Your feedback has been submitted successfully.

Previous Feedbacks:
• Alice: The course material is very comprehensive!
```

### 3. Submit Feedback 2 (Verify Session, Cookies, & JSTL List)
- Return to `http://localhost:8080/ex06/feedback.jsp`
- **Name:** `Bob`
- **Feedback:** `Great hands-on coding exercises.`
- Click **Submit Feedback**

**Expected Output:**
```text
Thank You, Bob!
Your feedback has been submitted successfully.

Previous Feedbacks:
• Alice: The course material is very comprehensive!
• Bob: Great hands-on coding exercises.

Last visitor: Alice
```
*(Notice: The cookie displays `Last visitor: Alice` because cookies track the prior visitor from the browser).*

---

## 6. Stop Apache Tomcat (When Finished)

- **In VS Code:** Right-click the server in the Servers panel -> Click **Stop Server**.
- **In Terminal:** Run `shutdown.bat` from Tomcat's `bin` folder, or close the Tomcat console window.
