#  Movie REST API

A Spring Boot REST API for managing movies, casts, and actors. Built with Spring Data JPA, H2 in-memory database, and documented with Swagger UI via springdoc-openapi.

---

##  Table of Contents

- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Data Model](#data-model)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Validation & Duplicate Checking](#validation--duplicate-checking)
- [Swagger UI](#swagger-ui)
- [H2 Console](#h2-console)
- [Error Handling](#error-handling)
- [Configuration](#configuration)

---

##  Tech Stack

| Technology | Purpose |
|---|---|
| Java 11 | Language |
| Spring Boot 2.7.1 | Application framework |
| Spring Data JPA | Database access layer |
| Hibernate | JPA implementation / ORM |
| H2 | In-memory database |
| springdoc-openapi 1.6.15 | Swagger UI & OpenAPI 3 docs |
| Lombok | Boilerplate reduction (`@Data`, `@Builder`, etc.) |
| Spring Validation | Bean validation (`@NotNull`, `@Size`, etc.) |
| Maven | Build & dependency management |

---

##  Project Structure

```
src/main/java/com/James_Morand/MovieRestAPI/
│
├── MovieRestApiApplication.java       
│
├── config/
│   └── SpringFoxConfig.java             # Swagger 
│
├── controller/
│   ├── MovieRestController.java       
│   ├── ActorController.java           
│   └── CastController.java            
│
├── service/
│   ├── MovieService.java
│   ├── ActorService.java              
│   └── CastService.java               
│
├── repository/
│   ├── MovieRepository.java
│   ├── ActorRepository.java           
│   └── CastRepository.java
│
├── model/
│   ├── Movie.java
│   ├── Cast.java
│   └── Actor.java
│
└── exception/
    ├── ResourceNotFoundException.java  
    ├── DuplicateResourceException.java 
    └── GlobalExceptionHandler.java     

src/main/resources/
└── application.properties
```


### Entity Overview

**Movie**
| Field | Type | Constraints |
|---|---|---|
| id | int | Auto-generated PK |
| name | String | Not null, max 100 chars |
| movieLength | int | Positive |
| releaseDate | String | Not null |
| costToMake | double | Zero or positive |
| cast | Cast | OneToOne |

**Cast**
| Field | Type | Notes |
|---|---|---|
| id | int | Auto-generated PK |
| actors | List\<Actor\> | ManyToMany — owns `CAST_ACTOR` join table |
| movie | Movie | Back-reference (inverse side of OneToOne) |

**Actor**
| Field | Type | Constraints |
|---|---|---|
| id | int | Auto-generated PK |
| name | String | Not null, max 100 chars — **must be unique** |
| age | int | Positive |
| casts | List\<Cast\> | ManyToMany — inverse side |

### Database Tables

```
MOVIE          MOVIE_CAST      CAST_ACTOR (join)     ACTOR
─────────      ──────────      ─────────────────     ─────────
id (PK)        id (PK)         cast_id  (FK)         id (PK)
name           ← movie refs    actor_id (FK)         name
movie_length     via cast_id                         age
release_date
cost_to_make
cast_id (FK)
```

---

##  Getting Started

### Prerequisites

- Java 11+
- Maven 3.6+

### Run the Application

```bash
# Clone the repo
git clone https://github.com/James-Morand/movie-rest-api.git
cd movie-rest-api

# Build and run
mvn spring-boot:run
```

The application starts on **`http://localhost:8080`**.

### Build a JAR

```bash
mvn clean package
java -jar target/Movie-Rest-API-0.0.1-SNAPSHOT.jar
```

---

##  API Endpoints

### Movies — `/api/movies`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/movies` | Get all movies |
| GET | `/api/movies/{id}` | Get a movie by ID |
| GET | `/api/movies/search?name=` | Search movies by name (partial, case-insensitive) |
| POST | `/api/movies` | Add a new movie |
| PUT | `/api/movies/{id}` | Update a movie |
| DELETE | `/api/movies/{id}` | Delete a movie |

---

### Actors — `/api/actors`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/actors` | Get all actors |
| GET | `/api/actors/{id}` | Get an actor by ID |
| GET | `/api/actors/search?name=` | Search actors by name (partial, case-insensitive) |
| POST | `/api/actors` | Add a new actor |
| PUT | `/api/actors/{id}` | Update an actor |
| DELETE | `/api/actors/{id}` | Delete an actor |
---

### Casts — `/api/casts`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/casts` | Get all casts |
| GET | `/api/casts/{id}` | Get a cast by ID |
| POST | `/api/casts` | Create a new cast |
| PUT | `/api/casts/{id}` | Replace a cast's actor list |
| DELETE | `/api/casts/{id}` | Delete a cast |
| POST | `/api/casts/{castId}/actors/{actorId}` | Add an actor to a cast ⚠️ actor must not already be in cast |
| DELETE | `/api/casts/{castId}/actors/{actorId}` | Remove an actor from a cast |


##  Swagger UI

Interactive API documentation is available once the app is running:

| Resource | URL |
|---|---|
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **OpenAPI JSON** | http://localhost:8080/v3/api-docs |

All endpoints are grouped by tag (Movies, Actors, Casts) and include request/response schemas, parameter descriptions, and HTTP status code documentation.

---

##  H2 Console

The embedded H2 database console is available for inspecting data at runtime:

**URL:** http://localhost:8080/h2-console

> **Note:** H2 is an in-memory database. All data is cleared when the application stops. To persist data between restarts, switch to a file-based or external database in `application.properties`.


##  Configuration

Key settings in `src/main/resources/application.properties`:

```properties
# H2 Database
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

To switch to MySQL or PostgreSQL, replace the H2 datasource properties and add the appropriate driver dependency to `pom.xml`.

---


