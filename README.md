# Portfolio & Resume Management System

A full-stack backend application built with **Java Spring Boot** for managing resumes, certificates, and project documents.  
Users can securely upload files, categorize them, download documents, and analyze resumes using AI-powered features.

---

# 🚀 Features

## 📂 Document Management
- Upload resumes in PDF format
- Upload certificates
- Upload project documents
- Categorize files:
  - Resume
  - Certificate
  - Project
- Download uploaded documents
- Delete documents
- 
---

## 📄 Pagination & Sorting
- Paginated document listing
- Sort documents by:
  - Upload Date
  - File Size
- Filter by category

---

## 🔐 Authentication & Security
- User Registration
- User Login
- JWT Authentication
- Secure API endpoints
- User-specific file access

---

# 🏗️ Tech Stack

## Backend
- Java 17+
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate

## Database
- PostgreSQL

## File Handling
- Local File Storage
- Multipart Upload

## PDF Parsing
- Apache PDFBox

---

# 📁 Project Structure

```bash
com.portfolio.manager
│
├── config
├── controller
├── service
├── service.impl
├── repository
├── entity
├── dto
│   ├── request
│   └── response
├── exception
├── util
└── PortfolioManagerApplication.java
