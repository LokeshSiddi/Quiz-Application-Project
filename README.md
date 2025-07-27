
# 📚 Quiz Application

A secure Quiz Application built using **Java**, **Spring Boot**, **PostgreSQL**, **OAuth2**, **JWT**, and **OpenAPI**. This is a monolithic mini project where users must log in to access the system. Only admins can create new admins; all others are regular users who can interact with quiz questions.

---

## 🚀 Features

- 🔐 Login-based access with OAuth2 and JWT
- 👥 Role-based access: Admins and Users
- 🧱 Monolithic Spring Boot application
- 💾 PostgreSQL database integration
- 📘 OpenAPI/Swagger documentation for easy API testing
- 🧰 Lombok for reduced boilerplate
- ✅ Admin-only user creation and question management
- ✅ Users can participate in quizzes

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security (OAuth2 + JWT)
- PostgreSQL
- Lombok
- OpenAPI / Swagger
- Maven

---

## 📦 Getting Started

### Prerequisites

- Java 8+
- Maven
- PostgreSQL running locally

### Setup Instructions

1. **Clone the project**
   ```bash
   git clone https://github.com/your-username/quiz-app.git
   cd quiz-app



1. **Clone the project**
   ```bash
   git clone https://github.com/your-username/quiz-app.git
   cd quiz-app

2. **Configure your PostgreSQL connection in application.yml**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/quizdb
    username: your_username
    password: your_password
```

3. **Run the application**

```bash
    mvn spring-boot:run
```

4. **Access the application**

- Login endpoint: http://localhost:8080/auth/login

- Swagger UI: http://localhost:8080/swagger-ui.html

5. **🔐 Authentication & Roles**
- Login required via ```/auth/login```

- On successful login, you will receive a JWT token

- Use the token in Authorization header in OpenAPI Authorize (At Top Right)  

```
bearer-jwt  (http, Bearer)
Value: token
```
## Roles

- **Admin**

    - Can create & manage new users and admins

    - Can manage quiz questions

- **User**

    - Can Create,view and attempt quizzes

## 🧪 Example Login Request
```json
POST /auth/login
{
  "username": "admin",
  "password": "admin@123"
}
```

or Login at ```/auth/login```
## 🗂 Folder Structure Overview
```css
Copy
Edit
src
├── main
│   ├── java
│   │   └── com.quizapp
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── security
│   │       ├── model
│   │       └── QuizApplication.java
│   └── resources
│       └── application.yml
```
📌 Notes
- This is a solo mini project developed for learning and demonstration purposes.

- Admin credentials must be created manually or seeded initially.

- Users can register directly. 
- Only admins can create new admins.

## Show your Support

Give a ⭐ if this project helped you.

Checkout My other projects too


***Thanks for checking out this project!***
