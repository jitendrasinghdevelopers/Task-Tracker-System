# Task Tracker System

## Description
The **Task Tracker System** is a task management application built using **Spring Boot**. It allows users to create, update, delete, and manage tasks. It supports the following features:
- **CRUD operations** (Create, Read, Update, Delete) for task management.
- **Task status management**: Mark tasks as "Done" or "Not Done".
- **Search tasks** by title (case-insensitive search).
- **Embedded H2 database** for storage.

This project exposes a **RESTful API** for interacting with the tasks and supports API testing through tools like **Postman** or **cURL**.

## Features
- **Create a Task**: Add a new task with title, description, and status.
- **Get All Tasks**: Retrieve a list of all tasks.
- **Update Task**: Modify the title, description, or status of a task.
- **Delete Task**: Remove a task from the system.
- **Change Task Status**: Update the status of a task (Done/Not Done).
- **Search Tasks by Title**: Find tasks by a keyword in the title.

## Technologies Used
- **Spring Boot**: Backend framework for building REST APIs.
- **H2 Database**: In-memory database for storing task data.
- **Lombok**: To simplify code by generating boilerplate code (like getters, setters, constructors).
- **SLF4J + Logback**: Logging framework for logging application behavior.
- **JUnit 5**: For unit testing the application.

## Prerequisites
Before running the project, make sure you have the following installed:
- **Java 17**
- **Maven** (or Gradle) for building the project.

## How to Run the Project

### Step 1: Clone the Repository
Clone the repository to your local machine using the following command:

git clone https://github.com/jitendrasinghdevelopers/task-tracker-system.git

## API Endpoints
Use Postman or any REST client to test the following endpoints:

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| POST   | /tasks               | Create a new task        |
| GET    | /tasks               | Get all tasks            |
| PUT    | /tasks/{id}          | Update a task            |
| DELETE | /tasks/{id}          | Delete a task            |
| PATCH  | /tasks/{id}/status   | Change task status       |
| GET    | /tasks/search        | Search task by title     |
