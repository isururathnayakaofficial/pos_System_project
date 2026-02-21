POS System - Spring Boot + Hibernate
📌 Overview

POS System developed using Spring Boot + Hibernate + MySQL

Modules: Customer | Item | Order Management

🏗 Technologies

Java

Spring Boot

Spring Data JPA

Hibernate

MySQL

REST API

📂 Features

✅ Customer CRUD Operations

✅ Item Management

✅ Order Placement

✅ Transaction Handling

✅ DTO + Layered Architecture

🗄 Database Relationships
Customer 1 → M Orders
Order 1 → M Order Details
Item 1 → M Order Details
Mappings used:
@OneToMany
@ManyToOne
🔑 ID Generation

Custom ID format:

C001 | I001 | O001
Using Hibernate ID generators.
⚙ Setup    
git clone https://github.com/yourusername/pos-system.git
Update database config in:
application.properties
Run project:

mvn spring-boot:run
📺 Explanation Video

👉 video part 1 - https://youtu.be/iRBYWiYmFJY?si=fMemxNhpKlf5NoGp
👉 video part 2 - https://youtu.be/rphxPzJ8YbI?si=Sp5mQKNt02Uoq_Pm



________
