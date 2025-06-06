# PyroSight - Real-Time Wildfire Monitoring and Notification System

PyroSight is a smart monitoring platform that leverages drones, satellite imagery, sensors, and weather data to detect and notify about potential wildfires. Built with Java 17 and Spring Boot, it offers a powerful RESTful API to manage drones, sensors, alerts, notifications, and more. The system includes authentication via JWT and interactive documentation with Swagger UI.

## 🚀 Badges

![Java 17](https://img.shields.io/badge/Java-17-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue)
![Status](https://img.shields.io/badge/Status-Finished-brightgreen)

## 🛠 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Swagger / OpenAPI**
- **Apache Maven**
- **Spring Data JPA**
- **PostgreSQL Database**

## ✅ Features

- 🔧 Full CRUD operations for:
    - Sensors
    - Drones
    - Satellite Images
    - Weathers Data
    - Detections
    - Alerts
    - Notifications
    - Users
- 🔐 User authentication and authorization (JWT)
- 📄 API documentation with Swagger UI

## 📦 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/EXAMPLE/Pyro-Sight.git
cd Flame-Guard
```

### 2. Build the project with Maven

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

### 4. Access Swagger UI

Visit: http://localhost:8080/swagger-ui.html
