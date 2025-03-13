# ✅ Pattimpulu Checklist Application

A **full-stack** task management system that allows users to **add, update, mark as completed, delete, and search tasks**.  
The project follows best practices in **Spring Boot, REST APIs, H2 Database, HTML, CSS, JavaScript, and Fetch API**.

---

## 🚀 Features

### 📌 Core Features
- **CRUD Operations**: Add, View, Edit, Delete tasks.
- **Task Completion**: Mark tasks as completed (tasks are strikethrough when done).
- **Task List Update**: Refreshes automatically after every action.
- **Search Functionality**: Find tasks using keywords (both UI and API).
- **Optimistic Locking**: Prevents unintended overwrites when updating tasks.
- **Bulk Task Creation**: Supports multiple task insertions at once.
- **User-Friendly UI**: Simple and responsive checklist view.

### 🛠 Backend Features
- **Spring Boot**: RESTful APIs for task management.
- **Spring Data JPA**: Simplifies database operations.
- **H2 In-Memory Database**: Enables fast testing & persistence.
- **Global Exception Handling**: Handles errors for missing/invalid data.
- **Swagger OpenAPI**: API documentation enabled at `/swagger-ui.html`.
- **Unit Testing**: JUnit 5 and Mockito tests included.

### 🎨 Frontend Features
- **Vanilla JavaScript UI**: No frameworks, just **HTML, CSS, and JavaScript**.
- **Modal Popups**: Allows in-place editing of tasks.
- **Dynamic Task List**: Fetches latest tasks after every operation.
- **Real-time Search**: Instant search results while typing.
- **Form Validation**: Prevents invalid task creation.

---

## 🏷️ Technologies Used

| Layer        | Technologies |
|-------------|-------------|
| **Frontend** | HTML5, CSS3, JavaScript, Fetch API |
| **Backend**  | Java 17, Spring Boot, Spring Data JPA |
| **Database** | H2 In-Memory Database |
| **Testing**  | JUnit 5, Mockito |
| **API Docs** | Swagger OpenAPI |
| **Build Tools** | Maven |

---

## 🏠 Project Structure

```
📂 pattimpulu-checklist
│── 📂 src/main/java/dev/backend/pattimpulu
│   │── 📂 controller        # REST Controllers
│   │── 📂 service           # Business Logic
│   │── 📂 repository        # Spring Data Repositories
│   │── 📂 model             # JPA Entity Models
│   │── 📂 exception         # Custom Exception Handlers
│   │── 📂 config            # Configuration Files
│── 📂 src/main/resources
│   │── application.properties   # Database and App Configs
│   │── data.sql                 # Preloaded tasks
│   │── schema.sql               # Database Schema
│── 📂 src/test/java/dev/backend/pattimpulu
│   │── 📂 service           # JUnit and Mockito Tests
│── 📂 frontend
│   │── index.html           # Main UI
│   │── styles.css           # UI Styling
│   │── checklist.js         # Task Management Logic
│   │── api.js               # API Calls
│── pom.xml                 # Maven Dependencies
```

---

## 📖 API Documentation

### ✅ Base URL:  
```
http://localhost:8080/api/checklist
```

### 📌 **1. Get All Tasks**
```http
GET /api/checklist
```
**Response**
```json
[
  {
    "id": 1,
    "title": "Master Data Structures",
    "description": "Sorting, Searching, Trees",
    "completed": false,
    "version": 0
  }
]
```

### 📌 **2. Get Task by ID**
```http
GET /api/checklist/{id}
```
**Response**
```json
{
  "id": 1,
  "title": "Master Data Structures",
  "description": "Sorting, Searching, Trees",
  "completed": false,
  "version": 0
}
```

### 📌 **3. Create a New Task**
```http
POST /api/checklist
Content-Type: application/json
```
**Request Body**
```json
{
  "title": "Solve 300+ Leetcode Problems",
  "description": "Practice daily",
  "completed": false
}
```

### 📌 **4. Update a Task**
```http
PUT /api/checklist/{id}
```

### 📌 **5. Delete a Task**
```http
DELETE /api/checklist/{id}
```

---

## 💪 Running the Application

### 🔹 **Step 1: Clone Repository**
```sh
git clone https://github.com/your-repo/pattimpulu-checklist.git
cd pattimpulu-checklist
```

### 🔹 **Step 2: Build and Run Backend**
```sh
mvn clean install
mvn spring-boot:run
```

### 🔹 **Step 3: Open Frontend**
1. Open `frontend/index.html` in a browser.
2. Start using the checklist UI.

---

## 🚪 Future Enhancements
- **User Authentication** (Login system)
- **Task Priorities** (High, Medium, Low)
- **Due Date Reminders** (Alerts)
- **Dark Mode UI** (Theme Toggle)
- **Export Tasks to CSV/PDF**

---

## 💪 Contributors
- **Bala Vamsi Maragani**  
- Technologies: Java, Spring Boot, JavaScript, CSS

---

## 📓 License
This project is **open-source** under the **MIT License**.

