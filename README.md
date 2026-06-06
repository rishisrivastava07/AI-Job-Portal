# AI Job Portal

An AI-Powered Job Portal built using **Java (Spring Boot)**, **Angular**, **Google Gemini AI**, and a **Microservices Architecture**. The platform streamlines the hiring process by leveraging AI for intelligent job matching, resume analysis, candidate recommendations, and personalized job discovery.

## 🚀 Features

### For Job Seekers

* User Registration & Authentication
* Profile Management
* Resume Upload & Parsing
* AI-Powered Resume Analysis
* Intelligent Job Recommendations
* Job Search & Filtering
* Application Tracking
* Personalized Career Insights

### For Recruiters

* Company Profile Management
* Job Posting & Management
* Candidate Search
* AI-Assisted Candidate Matching
* Application Review Dashboard
* Recruitment Analytics

### AI Capabilities

* Resume Parsing and Skill Extraction
* Semantic Job Matching
* Candidate Ranking
* AI-Powered Job Recommendations
* Interview Preparation Suggestions
* Career Guidance using Gemini AI

## 🏗️ Architecture

The application follows a Microservices Architecture for scalability, maintainability, and independent service deployment.

### Core Services

* **User Service**

    * Authentication & Authorization
    * User Profile Management

* **Job Service**

    * Job Posting Management
    * Job Search & Filtering

* **Application Service**

    * Job Applications
    * Application Tracking

* **AI Service**

    * Gemini AI Integration
    * Resume Analysis
    * Recommendation Engine

* **Notification Service**

    * Email Notifications
    * Application Updates

* **API Gateway**

    * Centralized Routing
    * Security Enforcement

* **Service Registry**

    * Service Discovery
    * Load Balancing

## 🛠️ Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Spring Cloud
* Maven
* REST APIs

### Frontend

* Angular
* TypeScript
* Angular Material
* RxJS

### AI Integration

* Google Gemini API
* Prompt Engineering
* AI Recommendation Engine

### Database

* MySQL / PostgreSQL

### DevOps & Infrastructure

* Docker
* Kubernetes (Future Scope)
* GitHub Actions
* Azure Cloud Services

## 📂 Project Structure

```text
AI-Portal
│
├── cloud
│   ├── api-gateway
│   ├── service-registry
│   └── config-server
│
├── common-lib
│
├── services
│   ├── job-portal-user-service
│   ├── job-service
│   ├── application-service
│   ├── ai-service
│   └── notification-service
│
└── pom.xml
```

## ⚙️ Getting Started

### Prerequisites

* Java 21+
* Maven 3.9+
* Node.js 20+
* Angular CLI
* MySQL/PostgreSQL
* Git

### Clone the Repository

```bash
git clone https://github.com/<your-username>/AI-Job-Portal.git
cd AI-Job-Portal
```

### Build the Project

```bash
mvn clean install
```

### Run a Service

```bash
cd services/job-portal-user-service
mvn spring-boot:run
```

### Run Angular Frontend

```bash
cd ui
npm install
ng serve
```

## 🔒 Security

* JWT Authentication
* Role-Based Access Control (RBAC)
* Secure API Gateway Routing
* Password Encryption using BCrypt

## 📈 Future Enhancements

* AI Interview Assistant
* Real-Time Chat Between Recruiters and Candidates
* Resume Scorecard
* ATS Compatibility Analysis
* Video Interview Integration
* Multi-Language Support
* Advanced Analytics Dashboard

## 🤝 Contributing

Contributions are welcome. Please create a feature branch, submit a pull request, and follow the project's coding standards.

## 📄 License

This project is licensed under the MIT License.

---

**Built with Spring Boot, Angular, Microservices, and Gemini AI to create the next generation of intelligent recruitment platforms.**