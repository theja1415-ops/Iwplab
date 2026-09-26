# Experiment 10 — Real-Time Data Visualization using MongoDB and Spring Boot (Reactive WebFlux & SSE)

## Aim
To create a reactive web application that integrates MongoDB Change Streams with Spring Boot WebFlux to stream real-time sensor data using Server-Sent Events (SSE) and visualize it dynamically on a live Chart.js dashboard.

---

## 1. Project Directory Structure

```text
ex10/
├── pom.xml                                               # Maven dependencies (WebFlux & Reactive Mongo)
├── README.md                                             # Execution guide
└── src/
    └── main/
        ├── java/com/example/realtime/
        │   ├── RealtimeMongoVisualizationApplication.java # Spring Boot WebFlux Entry Point
        │   ├── config/
        │   │   └── DemoDataRunner.java                  # Automatic sensor data simulator (every 2 sec)
        │   ├── model/
        │   │   └── SensorReading.java                   # MongoDB Document entity
        │   ├── repo/
        │   │   └── SensorReadingRepository.java         # Reactive Mongo repository
        │   └── web/
        │       └── StreamController.java                # SSE change stream & REST endpoints
        └── resources/
            ├── application.properties                   # MongoDB replica set connection URI
            └── static/
                └── index.html                           # Live Chart.js dashboard UI
```

---

## 2. Server & Database Requirements

> [!IMPORTANT]
> - **External Tomcat Needed? NO.** Spring WebFlux uses **embedded Reactor Netty**. Do not use external Tomcat.
> - **MongoDB with Replica Set Required:** MongoDB Change Streams **require a replica set** (`rs0`) to read from the operations log (oplog). Running in standard standalone mode without `--replSet rs0` will cause an error!

---

## 3. Step-by-Step Execution

### Step 1: Start MongoDB with Replica Set (Terminal 1)
Open a Command Prompt window and start MongoDB with the replica set flag:

```cmd
mkdir C:\data\db
mongod --replSet rs0 --dbpath "C:\data\db"
```
*(Keep this Command Prompt window open and running in the background).*

---

### Step 2: Initialize the Replica Set (Terminal 2)
Open a **second Command Prompt** window and run:

```cmd
mongosh --eval "rs.initiate()"
```

You should see:
```json
{ "ok": 1 }
```
*(Once `{ "ok": 1 }` appears, you can close Terminal 2).*

---

### Step 3: Run the Application

#### Option A: Run directly in VS Code (Recommended)
1. Open folder `c:\Users\SAI\Desktop\Lab\IWP\ex10` in VS Code.
2. Open `src/main/java/com/example/realtime/RealtimeMongoVisualizationApplication.java`.
3. Click the **`Run`** button visible above `public static void main(String[] args)` (or press `F5`).
4. Watch the VS Code terminal output:
   ```text
   Netty started on port 8080 (http) with context path '/ex10'
   Started RealtimeMongoVisualizationApplication in X.XXX seconds
   ```

#### Option B: Run via Maven CLI
Open Command Prompt in `ex10` and run:
```cmd
cd c:\Users\SAI\Desktop\Lab\IWP\ex10
mvn clean package -DskipTests
mvn spring-boot:run
```

*Or run using the built JAR:*
```cmd
java -jar target\realtime-mongo-visualization-0.0.1-SNAPSHOT.jar
```

---

## 4. Run and Test the Application in Browser

### 1. Open the Live Chart Dashboard
Open your web browser and navigate to:
```text
http://localhost:8080/ex10/index.html
```

You will see:
- **Status Indicator:** Shows **`Live connected`**.
- **Real-Time Live Chart:** The line chart plots new sensor data automatically every 2 seconds as `DemoDataRunner` feeds simulated data into MongoDB.

### 2. Test Manual Data Insertion
1. In the input box on the web page, type a value (e.g., `48.5`).
2. Click **Insert Reading**.
3. Watch the chart immediately plot a spike at `48.5` as the MongoDB change stream broadcasts the new database event to the browser.

---

## 5. Stop the Services (When Finished)

1. **In Spring Boot / VS Code:** Click the red square **Stop** button, or press `Ctrl + C` in the app terminal.
2. **In MongoDB (Terminal 1):** Press `Ctrl + C` to shut down `mongod`.

---

## 6. Common Errors & Fixes

| Error | Cause | Solution |
| :--- | :--- | :--- |
| **`Server at localhost:27017 is not a member of rs0`** | MongoDB was started without `--replSet rs0` or `rs.initiate()` was not run. | Follow Step 1 & Step 2 to start `mongod --replSet rs0` and run `mongosh --eval "rs.initiate()"`. |
| **`Address already in use` on port 27017** | MongoDB is already running as an automatic Windows service. | Run `net stop MongoDB` in Admin CMD, then restart with `--replSet rs0`. |
| **`Port 8080 was already in use`** | Another service (Tomcat/Spring Boot) is using port 8080. | In `application.properties`, set `server.port=8081` and visit `http://localhost:8081/ex10/index.html`. |
