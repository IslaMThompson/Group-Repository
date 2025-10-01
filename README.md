# Group project for software engineering methods<br>
**Code Review 1 – Lab Week 4**
![workflow](https://github.com/IslaMThompson/Group-Repository/actions/workflows/main.yml/badge.svg)<br>
[![LICENSE](https://img.shields.io/github/license/IslaMThompson/Group-Repository.svg?style=flat-square)](https://github.com/IslaMThompson/Group-Repository/blob/master/LICENSE)<br>
[![Releases](https://img.shields.io/github/release/IslaMThomspson/Group-Repository/all.svg?style=flat-square)](https://github.com/IslaMThompson/Group-Repository/releases)

## 🚀 Project Setup & Workflow

This repository represents our **team’s foundation** for the coursework project.  
For **Code Review 1**, our focus was not on delivering features, but on ensuring a **robust development workflow** that supports the rest of the project.

We chose the following stack and practices:

| Tool/Method      | Purpose |
|------------------|---------|
| **IntelliJ IDEA** | Main development environment |
| **Maven**        | Dependency management & JAR packaging |
| **Docker**       | Containerisation & reproducibility |
| **GitHub Actions** | CI/CD pipeline (build + Docker test) |
| **GitFlow**      | Branching strategy (`master`, `develop`, `release`) |

---

## 📦 Build & Deployment

### Maven Build in IntelliJ IDEA
- The project is built using **Maven** (`mvn clean install`)
- Produces a **self-contained JAR** in `/target`

### Dockerised Application
- A **Dockerfile** builds and runs the JAR inside a container
- Validated both locally and on **GitHub Actions**

### CI/CD Pipeline
- Every push triggers **GitHub Actions**
- Workflow runs:
    1. Maven build → JAR
    2. Docker build → container
    3. Run container tests

---

## 🔀 GitFlow in Action

We follow **GitFlow branching strategy**: 