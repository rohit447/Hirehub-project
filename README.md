# HireHub – Scalable Job Posting & Recruitment Platform

HireHub is a full-stack web application for job posting and recruitment.  
It includes **React frontend** and **Spring Boot backend**, with features like:

- Job listing with **pagination**
- **Search** by job title
- **Sorting** (Salary high→low, low→high, latest)
- **Filtering** by location
- **Apply Job** feature
- **Create Job** form (for admins/recruiters)
- Backend supports **CORS** for frontend integration

---

## 📁 Project Structure

hirehub-project/
├─ frontend/ (React frontend)
│ ├─ src/
│ │ ├─ api/
│ │ │ └─ axiosClient.js
│ │ ├─ components/
│ │ │ ├─ JobCard.jsx
│ │ │ ├─ Header.jsx
│ │ │ └─ Pagination.jsx
│ │ ├─ pages/
│ │ │ ├─ Home.jsx
│ │ │ ├─ Jobs.jsx
│ │ │ ├─ JobDetails.jsx
│ │ │ └─ CreateJob.jsx
│ │ ├─ routes/
│ │ │ └─ AppRoutes.jsx
│ │ ├─ App.jsx
│ │ └─ main.jsx
│ └─ package.json
├─ backend/ (Spring Boot backend)
│ ├─ src/
│ │ ├─ main/java/com/hirehub/
│ │ │ ├─ controller/JobController.java
│ │ │ ├─ model/Job.java
│ │ │ └─ repository/JobRepository.java
│ │ │ └─ service/JobService.java
│ │ └─ resources/application.properties
│ └─ pom.xml
└─ README.md

---

## ⚙️ Prerequisites

- Node.js ≥ 18
- npm ≥ 10
- Java 17+
- Maven
- MySQL 8+ (or compatible)
- IDE: IntelliJ / VSCode

---

## 🏃 How to Run

### 1. Backend (Spring Boot)

1. Database setup:

```sql
CREATE DATABASE hirehub;
USE hirehub;
------------------------------
application.properties example:
------------------------------
spring.datasource.url=jdbc:mysql://localhost:3306/hirehub
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
-------------------------------
Run Spring Boot:
--------------------------------
cd backend
mvn clean install
mvn spring-boot:run
--------------------------
Frontend (React)
--------------------------
cd frontend
npm install
npm run dev



-------------------------------------------------------------------------------------------
| Method | URL                                                                      | Description                        | Request Body                                        | Response                                                           |
| ------ | ------------------------------------------------------------------------ | ---------------------------------- | --------------------------------------------------- | ------------------------------------------------------------------ |
| GET    | `/api/jobs?page=0&size=5`                                                | Fetch jobs with pagination         | -                                                   | `{ content: [...], totalPages: x }`                                |
| GET    | `/api/jobs?page=0&size=5&search=Java&sort=salaryDesc&location=Hyderabad` | Fetch jobs with search/sort/filter | -                                                   | `{ content: [...], totalPages: x }`                                |
| GET    | `/api/jobs/{id}`                                                         | Fetch job details                  | -                                                   | `{ id, title, company, location, salary, description, createdAt }` |
| POST   | `/api/jobs`                                                              | Create new job                     | `{ title, company, location, salary, description }` | Success message                                                    |
| POST   | `/api/jobs/{id}/apply`                                                   | Apply for a job                    | `{ name, email, resume }`                           | Success message

Get- http://localhost:8080/api/jobs?page=0&size=5&search=Java%20Developer&sort=salaryDesc&location=Hyderabad
post - http://localhost:8080/api/jobs
payload- {
    "title": " Analyst",
    "company": "accenture",
    "location": "Hyderbad",
    "salary": 200000,
    "description": "Spring Boot analyst Role"
}


