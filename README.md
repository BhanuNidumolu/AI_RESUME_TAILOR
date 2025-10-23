# AI Resume Builder

Welcome to the **AI Resume Builder** project! This backend application helps users generate professional, tailored resumes using advanced AI and natural language processing.

---

## Features

- **Custom Resume Generation**: Input your resume, job description, and instructions to create a tailored resume.  
- **AI-Powered**: Uses OpenAI models to craft impact-oriented resumes optimized for ATS screening.  
- **Multi-format Support**: Supports `.pdf`, `.docx`, `.doc`, and `.txt`.  
- **User Authentication**: Registration and login for personalized services.  
- **Modular Design**: Includes services for text extraction, user management, and resume generation.  

---

## Project Structure

The backend is developed in **Java** with **Spring Boot**.

### Controllers
- **ResumeBuilderController**: `/api/generateResume` endpoint to generate tailored resumes.  
- **ChatController**: `/api/llm/generate` endpoint for AI interactions.  
- **UserLoginController**: `/api/register` and `/api/login` endpoints for user management.

### Services
- **ResumeBuilderService**: Processes files and user instructions for AI input.  
- **HelperService**: Extracts text from `.pdf`, `.docx`, `.doc`, `.txt`, and other formats using Apache Tika.  
- **LLMService**: Constructs AI prompts and enhances output for ATS and formatting.  
- **UserService**: Manages registration, login, and user data retrieval.

### Mappers
- **UserToDto**: Maps `UserEntity` to `UserDto` for simplified data handling.

### Models
- **UserEntity**: Represents user data in the database.  
- **UserDto**: Data Transfer Object for user info.

### External Libraries
- **Spring Boot**  
- **PDFBox**  
- **Apache POI**  
- **Apache Tika**  
- **OpenAI API**

---

## Setup and Usage

### Prerequisites
- Java 8 or higher  
- Maven  
- OpenAI API Key

### Installation
```bash
git clone https://github.com/<your-username>/AI_Resume_Builder.git
cd AI_Resume_Builder
mvn clean install


