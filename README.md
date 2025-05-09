# To-do-Project
A Todo REST API built with Spring Boot featuring session-based authentication. Supports user registration, login (returns session ID), and CRUD operations on tasks. All task operations require a valid session ID. Tech: Spring Boot, JPA, MySQL, Lombok, Java Bean Validation.

✅ Todo REST API with Session-Based Authentication
A simple yet secure Todo Management REST API built using Spring Boot, with full CRUD operations and manual session-based authentication.

🚀 Features
🔐 User Authentication with session IDs
📝 Create, Read, Update, Delete tasks
🔒 Session ID required for all task operations
🧾 User-specific task access for better data security

🛠 Tech Stack
☕ Spring Boot - REST API framework
📦 Spring Data JPA - ORM and data persistence
🔐 Manual Session Management (No Spring Security)
🧰 Lombok - Reduces boilerplate code
📊 MySQL - Relational database

🛡 Java Bean Validation - Input validation
🔐 Authentication Flow
Register – Create a new user with validation.
Login – Validates credentials and generates a unique session ID.
Authorized Actions – All endpoints related to tasks require a valid session ID in the header.

📋 API Endpoints Overview
🧑‍💼 User
POST /register – Register a new user
POST /login – Login and receive a session ID

📌 Task (Requires Session ID in Header)
POST /tasks – Create a task
GET /tasks – Get all tasks for the logged-in user
GET /tasks/{id} – Get task by ID
PUT /tasks/{id} – Update task by ID
DELETE /tasks/{id} – Delete task by ID

🖼 Sample Screenshots (to include in repo)
✅ Postman: Registration & Login with session response
🛡 Authenticated task creation/update/delete

📂 Project structure with controller/service/repository layers
src/
 └── main/
     ├── java/com/example/todo/
     │   ├── controller/
     │   ├── service/
     │   ├── repository/
     │   ├── model/
     │   ├── dto/
     │   └── TodoApplication.java
     └── resources/
         └── application.properties

💾 MySQL task table with data

💡 What I Learned
Building REST APIs with Spring Boot from scratch
Implementing custom session authentication without Spring Security
Securing APIs using session management
Validating and handling errors gracefully
Designing clean, modular application architecture

🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change or improve.

📬 Feedback
Feel free to reach out for suggestions, feedback, or collaboration!

