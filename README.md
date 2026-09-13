# Bookora

Bookora is a Spring Boot-based backend API for an event booking and registration platform. The application supports user creation, login with JWT authentication, event creation and event update flows, and booking creation/retrieval workflows.

## Project Overview

This project is designed around a lightweight event ticketing workflow:

- Users can register an account and authenticate securely.
- Users can create or update event pools.
- Users can create bookings for available events.
- Booking details can be retrieved through the booking API.

The project uses Spring Boot, Spring Security, JWT authentication, MySQL as the data store, Swagger/OpenAPI support, and Docker for containerization.

## Tech Stack

- Java 17
- Spring Boot 4.1.1 / Spring Framework ecosystem
- Spring Security
- Spring Data JPA
- MySQL Connector/J
- JWT (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`)
- SpringDoc OpenAPI
- Maven
- Docker and Docker Compose
- AWS services for deployment and infrastructure

## Project Structure

```text
src/
  main/
    java/
      com/springProject/Bookora/
        ConfigPackage/
        ControllerPackage/
        DaoDetails/
        Dto/
        Entities/
        ExceptionPackage/
        ServiceDetails/
    resources/
      application.properties
      application-dev.properties
      application-docker.properties
      application-aws.properties
```

## Main REST Endpoints

### Authentication and Users

- `POST /login/createuser` — Create a new user
- `POST /login/authenticate` — Sign in and receive a JWT token
- `GET /login/userlist` — Retrieve all users

### Events

- `POST /event/createEvent` — Create a new event
- `PATCH /event/updateEvent/{eventId}` — Update event details by event ID

### Bookings

- `POST /booking/createbooking` — Create a booking for a user and event
- `GET /booking/{bookingId}` — Retrieve booking details by booking ID

## Security

The application uses Spring Security with stateless session management and JWT-based authentication. Public endpoints are limited to user creation and authentication. Protected event and booking endpoints require a valid JWT token.

## Configuration

The application configuration is separated by environment profile:

- `application.properties` — default local configuration
- `application-dev.properties` — development configuration
- `application-docker.properties` — Docker profile configuration
- `application-aws.properties` — AWS deployment configuration

The current application setup is configured through Spring profile activation using Docker Compose or environment variables.

## Local Setup

### Prerequisites

- Java 17+
- Maven
- MySQL 8
- Docker (optional, for containerized setup)

### Run Locally

Clone the repository and run:

```bash
mvn clean install
mvn spring-boot:run
```

Configure your local database and JWT properties in the relevant `application.properties` or environment settings.

## Docker Setup

The repository ships with a Dockerfile and a `docker-compose.yaml` configuration.

The Dockerfile uses:

- Maven image for dependency download and package building
- Eclipse Temurin Java runtime image for the final app image

The `docker-compose.yaml` file includes:

- `bookora_compose` application service using the project image from Docker Hub
- `mysql_compose` MySQL 8 service with persistent volume storage

Example Docker Compose service configuration:

```yaml
services:
  app:
    container_name: bookora_compose
    image: ajayrohith/bookorahub:1.0.0
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker

  mysql:
    container_name: sql_compose
    image: mysql:8
    ports:
      - "3307:3306"
```

## AWS Deployment Notes

The project has been prepared and deployed through an AWS deployment lifecycle as follows:

1. The application was containerized using Docker.
2. The container image was pushed to Docker Hub.
3. An Amazon RDS database instance was created for the application data layer.
4. The Spring Boot application was deployed using AWS Elastic Beanstalk.
5. EC2 instances were provisioned with required security groups and access controls.
6. The deployed application was tested and verified before being handled for operational shutdown.

## Cost and Resource Management

The deployed AWS infrastructure has been stopped due to cost-related considerations. Before stopping the cloud resources, the required AWS RDS snapshots were taken to preserve database state.

The infrastructure package and cloud resources were also saved in an AWS CloudFormation template for future redeployment and restoration.

## Future Enhancements

Possible future improvements include:

- Add role-based access control for administrators and users.
- Add event category, organizer, and location management.
- Improve booking cancellation and ticket inventory workflows.
- Add richer exception handling and validation messages.
- Add full CI/CD pipeline integration with GitHub Actions or Azure DevOps.
- Add frontend UI for the event booking experience.
- Improve observability with centralized logging, metrics, and health checks.
- Add automated integration and end-to-end testing for API flows.

## License

This project is created for educational and portfolio demonstration purposes. Please adjust or add an appropriate license if this repository is intended for production or open-source distribution.
