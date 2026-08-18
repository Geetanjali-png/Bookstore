📚 Bookstore Management System

A web-based Bookstore Management System developed using Spring Boot, MySQL, Thymeleaf, Spring Data JPA, and Spring Security.

🚀 Features

- User registration and login
- Secure authentication using Spring Security
- Password encryption using BCrypt
- Browse available books
- View book details
- Manage books, authors, and categories
- Add books to cart
- Update and remove cart items
- Add and remove books from wishlist
- Place orders
- MySQL database integration

🛠️ Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- MySQL
- Thymeleaf
- HTML
- CSS
- JavaScript
- Maven
- IntelliJ IDEA

🏗️ Project Architecture

The project follows a layered architecture:

Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
MySQL Database

📂 Project Structure

src/
├── main/
│   ├── java/
│   │   └── com.bookstore/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── repository/
│   │       ├── service/
│   │       └── security/
│   │
│   └── resources/
│       ├── templates/
│       ├── static/
│       └── application.properties/
│
└── test/

🗄️ Database

The application uses MySQL as the database.

Main entities include:

- User
- Book
- Author
- Category
- Cart
- Wishlist
- Order

⚙️ Configuration

Configure the MySQL database in:

src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.thymeleaf.cache=false

▶️ How to Run

1. Create a MySQL database named "bookstore".
2. Configure the database username and password in "application.properties".
3. Open the project in IntelliJ IDEA.
4. Build the project using Maven.
5. Run "BookstoreApplication.java".
6. Open the application in your browser:

http://localhost:8080

🔐 Security

Spring Security is used for:

- User authentication
- Password encryption
- Login and logout
- Protected application pages
- Authorization

Passwords are securely stored using BCryptPasswordEncoder.

🎯 Project Objective

The objective of this project is to develop a complete online bookstore application where users can securely register, browse books, manage their cart and wishlist, and place orders.

🔮 Future Enhancements

- Online payment integration
- Admin dashboard
- Book reviews and ratings
- Advanced search and filtering
- Order tracking
- Email notifications
- Book recommendations
- Forgot-password functionality

👩‍💻 Author

Geetanjali

Information Science Engineering Student
