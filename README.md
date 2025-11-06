# User Authentication Backend

This is the backend service for the User Authentication System.  
It is built with Spring Boot and PostgreSQL, providing secure user authentication, role-based access, JWT token handling, and audit logging.

---

## Features

- User registration and login with JWT authentication  
- Password encryption using BCrypt  
- Role-based access (USER / ADMIN)  
- Account lock after 3 failed login attempts  
- Audit logging for login and logout actions  
- Email notification support  
- CORS configuration for frontend integration  

---

## Technologies Used

- Java 17  
- Spring Boot 3+  
- Spring Security  
- Spring Data JPA  
- PostgreSQL  
- Maven  
- JWT (io.jsonwebtoken)  


---

## Configuration

Update the following in `src/main/resources/application.properties`:

- spring.datasource.url=jdbc:postgresql://localhost:5432/user_auth_db  
- spring.datasource.username=your_db_username  
- spring.datasource.password=your_db_password  
- spring.jpa.hibernate.ddl-auto=update  
- jwt.secret=your_jwt_secret_key  
- jwt.expiration=3600000  

---

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and generate JWT token |

### User
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/user/info` | Get user login and logout audit details |
| GET | `/api/user/audit` | Get user’s own audit logs |

### Admin
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/admin/users` | List all registered users |
| GET | `/api/admin/users/{id}/attempts` | Get user’s failed attempt count |
| POST | `/api/admin/users/{id}/promote` | Promote a user to admin |
| POST | `/api/admin/users/{id}/unlock` | Unlock a user account |

---


1. Make sure PostgreSQL is running.
2. Build and start the project:


