# Inventory Management System

This project is a **Spring Boot** and **MySQL** based inventory management system demonstrating the implementation of Design Patterns in real-world scenarios.

## Architecture and Design Patterns

Object-Oriented Programming principles and the following design patterns formed the basis of this project's development:

### 1. Strategy Pattern

Used to provide flexibility in the pricing mechanism.

- **Goal:** To enable the selection of different pricing algorithms at runtime.
- **Usage:** Normal or discounted campaign prices are calculated dynamically via the `PricingStrategy` interface.

### 2. Observer Pattern

Used to track critical data changes within the system.

- **Goal:** To automatically notify dependent objects when a change occurs in an observed object.
- **Usage:** When a product or stock change occurs in the database, `AdminEmailObserver` is triggered to send the necessary notification to the administrator.

### 3. Composite Pattern

Used to manage warehouse and shelf layout hierarchically.

- **Goal:** To establish a part-whole relationship, treating individual objects (Shelves) and composite objects (Warehouses/Sections) uniformly.
- **Usage:** The warehouse hierarchy (Main Warehouse -> Aisle -> Shelf) is maintained and managed in a tree structure.

---

## 🛠 Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Database:** MySQL
- **ORM:** Hibernate / JPA
- **Build Tool:** Maven

---

## 🚀 Installation & Setup

Follow these steps to run the project on your local machine:

### 1. Clone the Repository

```bash
git clone https://github.com/omer-ondr/inventory-management-system.git
cd inventory-management-system
``` 
### 2. Database Configurations

Create a MySQL database:

```sql
CREATE DATABASE inventory_db;
```

### 3. Configure Database Credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.username=YOUR_USERNAME_HERE
spring.datasource.password=YOUR_PASSWORD_HERE
```

Replace `YOUR_USERNAME_HERE` with your MySQL username and `YOUR_PASSWORD_HERE` with your MySQL password.

### 4. Build the Project

Using Maven:

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8081`

