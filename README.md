## Obsidian POS — Enterprise-Style SaaS Point of Sale System

Obsidian POS is a production-style, multi-tenant POS system designed to model real-world retail workflows, secure access control, and scalable backend architecture.

The project focuses on enterprise backend engineering, including authentication, role-based permissions, modular REST APIs, and data isolation across stores and branches.

This repository represents my first end-to-end system built beyond tutorials, with an emphasis on clean architecture, security, and maintainability.

## Status
Active development.
Core backend architecture and API modules are implemented; frontend features are being expanded iteratively.

## System Architecture
*  Multi-tenant design supporting independent stores and branches
*  Role-based access control (Store Admin, Branch Manager, Employee etc.)
*  JWT-based authentication using Spring Security
*  Modular REST API architecture (Orders, Inventory, Products, Employees, Customers)
*  Transactional MySQL data model optimisation for retail workflows

## Tech Stack
Frontend

* React 
* Redux 
* Axios 
* shadcn/ui 

Backend

* Java 
* Spring Boot 
* Spring Security 
* JPA / Hibernate 
* REST API Architecture 

Database

* MySQL 

## Implemented Features
* Secure authentication & authorization (JWT)
* Role-based permissions and access boundaries
* Store & branch employee management
* Product, category and inventory APIs
* Modular controller-service-repository structure

## Roadmap
* Cashier UI 
* Branch Dashboard 
* Store Admin Panel 
* Real-time inventory tracking 
* Analytics & reporting dashboards
* Subscription & licensing logic

## Project Goals

* Build a production-style SaaS POS system from scratch

* Understand the full lifecycle of a modern full-stack application

* Strengthen backend engineering and architectural skills

* Explore scalable state management, API design, and enterprise workflows
