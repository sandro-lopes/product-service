# Product Service

This microservice is responsible for managing products in the e-commerce platform.

## Technologies

- Java 21
- Spring Boot 3.4
- MongoDB
- RabbitMQ
- Docker

## Prerequisites

- Docker and Docker Compose
- Java 21 or higher
- Maven

## Running Locally

### 1. Start the Infrastructure

The project uses Docker Compose to set up the necessary infrastructure (MongoDB and RabbitMQ).

```bash
docker-compose up -d
```

### 2. Wait for Services to Start

- MongoDB will be available on port 27017
- MongoDB Compass (web interface) will be available at http://localhost:8081 (use admin/password as credentials)
- RabbitMQ Management UI will be available at http://localhost:15672 (use guest/guest as credentials)

### 3. Run the Application

You can run the application using Maven with the dev profile:

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"
```

#### Running in Debug Mode

To run the application in debug mode:

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=dev" "-Dspring-boot.run.jvmArguments=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

Then connect your IDE to port 5005.

#### IDE Configuration

**VSCode**:

1. Create a `.vscode/launch.json` file with the following content:
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Product Service (dev)",
      "request": "launch",
      "mainClass": "com.codingbetter.ProductServiceApplication",
      "projectName": "product-service",
      "args": "--spring.profiles.active=dev",
      "env": {
        "SPRING_PROFILES_ACTIVE": "dev"
      }
    }
  ]
}
```

**IntelliJ IDEA**:

1. Go to Run > Edit Configurations
2. Click the + button and select Spring Boot
3. Set the Main class to `com.codingbetter.ProductServiceApplication`
4. In the Program arguments field, add `--spring.profiles.active=dev`
5. In the Environment variables field, add `SPRING_PROFILES_ACTIVE=dev`
6. Click Apply and OK

### 4. Verify the Application

The application will be available at http://localhost:8080

## API Documentation

The service exposes the following endpoints:

- `POST /api/products` - Create a new product
- `PATCH /api/products/{id}/activate` - Activate a product

## Accessing MongoDB Compass

MongoDB Compass is a web interface for managing MongoDB. To access it:

1. Go to http://localhost:8081 in your browser
2. Use the following credentials:
   - Username: admin
   - Password: password

In Compass, you can:
- View and edit documents
- Run queries
- Create indexes
- Monitor database performance

## Tests

### Unit Tests

```bash
mvn test
```

### Integration Tests

```bash
mvn test -Dtest=*IntegrationTest
```

## Importing the Insomnia Collection

An Insomnia collection is provided in the `insomnia` folder. You can import it into Insomnia to test the API endpoints.

## Stopping the Infrastructure

```bash
docker-compose down
```

To remove volumes as well:

```bash
docker-compose down -v
```

## MongoDB Configuration

MongoDB is configured with the following default credentials:

- **Username**: admin
- **Password**: password
- **Database**: productdb
- **Port**: 27017
- **Connection URL**: mongodb://admin:password@localhost:27017/productdb?authSource=admin

These settings can be changed in the `docker-compose.yml` and `application-dev.yml` files.