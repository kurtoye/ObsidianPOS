# Obsidian POS — Multi-Tenant SaaS Point of Sale System

Obsidian POS is a production-style, multi-tenant Point of Sale system with a strong focus on backend system design and architecture.

The project prioritizes secure authentication, role-based authorization, tenant isolation, and scalable REST API development using Spring Boot.

This repository contains a complete backend implementation with an actively developed frontend intended primarily for API integration.

## System Overview

The system is designed backend-first, with emphasis on domain modelling, security boundaries, and enterprise API structure. Frontend development is secondary and focused on consuming backend services.

Obsidian POS operates as a SaaS platform where multiple independent stores and branches share a single system while maintaining strict data isolation.

Key concepts include:
* Store-level tenancy
* Branch-level operations
* Role-based permissions
* Secure, stateless authentication
* Modular service-oriented architecture

## Backend Architecture
* Multi-tenant data model with store and branch isolation
* JWT-based authentication using Spring Security
* Hierarchical role-based access control (Store Admin, Branch Manager, Employee)
* Modular controller → service → repository structure
* RESTful API design following enterprise conventions
* Centralized validation and error handling

## Implemented Features
* Secure authentication and authorization (JWT, Spring Security)
* Role-based permissions and access boundaries
* Store, branch, and employee management
* Product, category, and inventory management APIs
* Order processing and transactional workflows
* Customer and user management
* Dockerized backend services
* Postman-tested API workflows

## Tech Stack
### Backend
* Java
* Spring Boot
* Spring Security
* JWT
* JPA / Hibernate
* MySQL

### Frontend
* React
* Redux
* Axios
* shadcn/ui
  
_Frontend is intentionally lightweight and focused on API consumption rather than UI polish._

### Infrastructure & Tooling
* Docker
* Postman
* Git

## Project Status
Active development.

Core backend systems are implemented and stable.

Frontend features and advanced analytics modules are currently being expanded.

## Design Goals
* Model real-world enterprise workflows rather than demo features
* Prioritize security, maintainability, and scalability
* Apply backend design patterns used in production systems
* Build a system suitable for extension by additional engineers
