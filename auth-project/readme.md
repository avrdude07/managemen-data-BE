# Midas Mini Project - Backend

This is the backend service for the **Employee Timesheet Management System**, built using **Java** and **Spring Boot**, with **Oracle DB** as the database. It provides secure user authentication, authorization, and timesheet management APIs.

---

## Tech Stack

- **Java**
- **Spring Boot**
- **Oracle Database**
- **Spring Security**
- **JWT (JSON Web Token)**
- **BCrypt** for password hashing

---

## 🔐 Features

### ✅ Authentication
- Integrated with **Spring Security**
- User passwords are **securely encoded using BCrypt**

### ✅ Authorization
- Every client request is protected using **JWT Token**
- Ensures secure access to protected endpoints

---

## ⚙️ Project Setup

Follow these steps to run the backend project locally:

### 1. Clone the repository

```
git clone https://github.com/juniarc/cahya-version-mini-project.git
cd cahya-version-mini-project-main
```

### 2. Configure the application.properties file

Set your Oracle DB connection based on your environment

```
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the project

