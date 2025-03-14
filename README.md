# Pattimpulu Checklist Application 📝✅

A full-stack **Checklist Management Application** built using **Spring Boot 3 (Java 17)** for the backend and **HTML5, CSS3, JavaScript (ES6+), and Fetch API** for the frontend. It supports **CRUD operations, search, UI validation, and versioned updates**. The project is designed to work with an **H2 in-memory database** but also supports persistence mode.

---

## 🔹 Features
- **Create, Read, Update, and Delete (CRUD) Tasks** 🤞👁️✏️❌
- **Mark Tasks as Completed** ✅ (strikethrough effect on UI)
- **Search Feature** 🔍 (Search tasks by title)
- **Bulk Task Addition API** 🛂 (Add multiple tasks in one API call)
- **Validation Rules**
  - No empty/blank tasks can be added ❌
  - Buttons are **disabled** until valid input is provided 🎯
  - Only **completed** and **version** fields can change during completion updates 🛠️
- **Task Versioning** (To handle concurrency and avoid unintended overwrites) 🔄
- **Front-end & Back-end Connected** 🔗
- **H2 Database Support** (In-memory & persistent) 📚
- **Swagger API Documentation** 📝
- **Unit Testing** with JUnit (30+ Test Cases) 🧪
- **Uses Modern Development Tools** (ESLint, Spring DevTools, Postman, Networking Tools)

---

## 🚀 Technologies Used
### Backend
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA (Hibernate)**
- **Spring Web (REST APIs)**
- **Spring DevTools**
- **Springdoc OpenAPI for Swagger**
- **Lombok (Reduces Boilerplate Code)**
- **H2 Database (Supports both in-memory & persistent mode)**

### Frontend
- **HTML5 + CSS3**
- **JavaScript (ES6+)**
- **Fetch API for API calls**
- **Networking Tools (Browser DevTools, Postman)**
- **ESLint for Code Quality**

### Testing & Development Tools
- **JUnit 5.11.4**
- **Mockito**
- **Postman (For API Testing)**
- **ESLint (For frontend JS linting)**
- **Spring DevTools (Hot Reload for development)**
- **H2 Console (for debugging the in-memory DB)**

---

## 📂 Project Structure
```
pattimpulu/
│— src/
│   ├— main/
│   │   ├— java/dev/backend/pattimpulu
│   │   │   ├— controller/      # REST API Controllers
│   │   │   ├— service/         # Business Logic
│   │   │   ├— repository/      # JPA Repository
│   │   │   ├— model/           # Entity Models
│   │   │   ├— exception/       # Custom Exceptions
│   │   ├— resources/
│   │   │   ├— application.properties  # Spring Config
│   │   │   ├— data.sql         # Preloaded Data for Persistence
│   │   ├— static/
│   │   │   ├— index.html       # Frontend UI
│   │   │   ├— styles.css       # Styles
│   │   │   ├— api.js           # API Calls
│   │   │   ├— checklist.js     # UI Logic
│   ├— test/
│   │   ├— java/dev/backend/pattimpulu
│   │   │   ├— service/ChecklistItemServiceTest.java # JUnit Tests
│   ├— pom.xml                  # Maven Dependencies
│— README.md
```

---

## 🛠️ How to Run the Application
### 1⃣ Backend Setup
#### Run via IDE
- Open in **IntelliJ IDEA** / **VS Code**
- Run `PattimpuluApplication.java`

#### Run via Terminal
```bash
mvn clean install
mvn spring-boot:run
```

### 2⃣ Frontend Setup
- Open `index.html` in **any browser** (Google Chrome, Edge, etc.)

---

## 📆 API Endpoints
| Method | Endpoint                  | Description                   |
|--------|---------------------------|-------------------------------|
| GET    | `/api/checklist`          | Fetch all tasks               |
| GET    | `/api/checklist/{id}`      | Get task by ID                |
| POST   | `/api/checklist`          | Create a new task             |
| POST   | `/api/checklist/bulk`     | Create multiple tasks         |
| PUT    | `/api/checklist/{id}`      | Update a task                 |
| DELETE | `/api/checklist/{id}`      | Delete a task                 |
| GET    | `/api/checklist/search?keyword=xxx` | Search tasks by title |

---

## 🌟 Why These Technologies?
- **Spring Boot 3** → Modern, scalable backend framework
- **H2 Database** → Lightweight & can switch to persistent mode
- **Fetch API (JS)** → Simpler than Axios, native JS solution
- **ESLint** → Ensures frontend code quality
- **Lombok** → Reduces boilerplate code in Java
- **Spring DevTools** → Auto-restart for backend
- **Postman** → Manual API testing tool
- **Networking Tools** → Check API calls in the browser

---

## 🚀 Future Enhancements
- Add **JWT Authentication**
- Convert UI to **React or Angular**
- Add **Pagination**
- Improve **Error Handling**
- Deploy to **AWS or Heroku**

---
