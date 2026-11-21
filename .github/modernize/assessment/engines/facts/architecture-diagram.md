# Architecture Diagram

Bookora is a containerized Spring Boot event-booking API with JWT authentication, JPA persistence, Redis caching, and deployment paths for Docker and AWS.

## Application Architecture

<!-- mermaid-checked: no \n, no em-dash/en-dash, no {} in labels, subgraphs are id["label"], arrows are -->|"label"|, all subgraphs closed by end, ids unique -->
~~~mermaid
flowchart TD
    subgraph Clients["Client and API Consumers"]
        HTTP["HTTP Client"]
        Swagger["Swagger OpenAPI"]
    end
    subgraph Docker["Docker Runtime"]
        App["Spring Boot API"]
    end
    subgraph Application["Application Layer"]
        Controllers["REST Controllers"]
        Security["Spring Security and JWT"]
        Services["Business Services"]
    end
    subgraph Data["Data and Cache Layer"]
        JPA["JPA EntityManager"]
        DB[("MySQL 8")]
        Redis[("Redis Cache")]
    end
    subgraph AWS["AWS Deployment"]
        ElasticBeanstalk["Elastic Beanstalk"]
        RDS[("Amazon RDS")]
    end

    HTTP -->|"REST requests"| Controllers
    Swagger -->|"API exploration"| Controllers
    Controllers -->|"authentication"| Security
    Security -->|"authorized request"| Services
    Services -->|"JPA operations"| JPA
    JPA -->|"SQL"| DB
    Services -->|"cache read or write"| Redis
    App -.-> Docker
    ElasticBeanstalk -->|"hosts container"| App
    App -->|"AWS profile"| RDS
~~~

### Technology Stack Summary

| Layer | Technology | Version | Purpose |
|---|---|---|---|
| Runtime | Java | 17 | Application runtime |
| Framework | Spring Boot | 4.1.1 | REST API and dependency injection |
| Security | Spring Security with JWT | jjwt 0.12.6 | Stateless authentication and authorization |
| Data access | Spring Data JPA and JPA EntityManager | Spring Boot managed | Persistence and native ticket updates |
| Database | MySQL | 8 | Users, events, and bookings |
| Cache | Redis | Latest | Service-level caching |
| API documentation | SpringDoc OpenAPI | 3.0.1 | Swagger UI and API contract |
| Build | Maven | 3.9.16 style wrapper or container | Dependency resolution and packaging |
| Container | Docker and Docker Compose | Multi-stage Eclipse Temurin image | Local deployment |
| Cloud | AWS Elastic Beanstalk and RDS | AWS | Cloud hosting and managed database |

### Data Storage & External Services

MySQL stores user, event, and booking entities, while Redis caches user lists, events, and booking responses for faster reads. Docker Compose provides a local application, MySQL, Redis network, and persistent MySQL volume. The AWS deployment profile targets Amazon RDS and is packaged for Elastic Beanstalk using Docker.

### Key Architectural Decisions

- Uses constructor injection, a service-oriented business layer, and a DAO interface implementation over JPA.
- Uses stateless JWT security with BCrypt password hashing and public authentication endpoints.
- Uses transactional booking logic and an atomic native SQL ticket decrement to avoid overselling available tickets.

## Component Relationships

<!-- mermaid-checked: no \n, no em-dash/en-dash, no {} in labels, subgraphs are id["label"], arrows are -->|"label"|, all subgraphs closed by end, ids unique -->
~~~mermaid
flowchart LR
    subgraph Presentation["Presentation"]
        cLogin["LoginController"]
        cEvent["EventController"]
        cBooking["BookingsController"]
    end
    subgraph SecurityLayer["Security"]
        cJwt["JwtService"]
        cFilter["JwtAuthenticationFilter"]
        cConfig["AppConfig"]
    end
    subgraph Business["Business Logic"]
        cService["Servicelayer"]
        cExceptions["Global Exception Handler"]
    end
    subgraph DataAccess["Data Access"]
        cDao["DaoImplementation"]
        cInterface["DaoInterface"]
        cEntities["User, Eventdetails, Booking"]
    end
    subgraph Infrastructure["Infrastructure"]
        cMysql[("MySQL")]
        cRedis[("Redis")]
    end

    cLogin -->|"authentication and users"| cService
    cEvent -->|"event operations"| cService
    cBooking -->|"booking operations"| cService
    cFilter -->|"validates token"| cJwt
    cFilter -->|"loads identity"| cConfig
    cConfig -.->|"protects routes"| Presentation
    cService -->|"uses"| cInterface
    cInterface -.->|"implemented by"| cDao
    cDao -->|"EntityManager"| cEntities
    cEntities -->|"JPA persistence"| cMysql
    cService -->|"cache annotations"| cRedis
    cService -.->|"domain exceptions"| cExceptions
~~~

### Component Inventory

| Component | Layer | Type | Responsibility |
|---|---|---|---|
| LoginController | Presentation | REST controller | User creation, authentication, and user listing |
| EventController | Presentation | REST controller | Event creation and patch updates |
| BookingsController | Presentation | REST controller | Booking creation and retrieval |
| JwtAuthenticationFilter | Security | Servlet filter | Extracts and validates bearer JWT tokens |
| JwtService | Security | Service | Generates, parses, and validates JWT claims |
| AppConfig | Security | Configuration | Password encoding, authentication providers, and route authorization |
| Servicelayer | Business logic | Spring service | Coordinates users, events, bookings, caching, and transactions |
| GlobalexceptionHandler | Cross-cutting | Controller advice | Maps runtime and domain exceptions to API responses |
| DaoInterface | Data access | Repository contract | Defines persistence operations |
| DaoImplementation | Data access | Repository implementation | Executes JPA and native SQL operations |
| User | Data access | JPA entity | Stores account details and bookings |
| Eventdetails | Data access | JPA entity | Stores event and ticket inventory details |
| Booking | Data access | JPA entity | Links a user to an event |
