# ToDo-List

The ToDo List is a Java-based Spring Boot application that provides functionality for managing ToDo tasks. It allows users to create, retrieve, update, and delete tasks, and also includes features such as task prioritization and categorization.

## Table of Contents
- [Features](#features)
- [Usage](#usage)
- [Configuration](#configuration)
  - [Asynchronous Processing](#asynchronous-processing)
  - [Encryption](#encryption)
  - [OpenAPI (Swagger) Documentation](#openAPI-(swagger)-documentation)
  - [Spring Boot Admin](#spring-boot-admin)
- [License](#license)

## Features

- **Task Management:** Create, update, retrieve, and delete tasks with ease.
- **Category Support:** Organize tasks by assigning them to categories.
- **Validation:** Input validation ensures data integrity and security.
- **Asynchronous Processing:** Utilizes asynchronous processing for improved responsiveness and performance.
- **Caching:** Implements caching to optimize task retrieval.
- **Swagger Documentation:** Includes OpenAPI (Swagger) documentation for API exploration.
- **Spring Boot Admin Integration:** Integrates with Spring Boot Admin for monitoring and management.
- **Jasypt Encryption:** Supports data encryption for sensitive information.
- **Custom ExecutorService:** Configures a custom `ThreadPoolTaskExecutor` for asynchronous task execution.
- **Global Exception Handling:** Provides global exception handling.

## Usage
Access the application through your web browser or API client at http://localhost:8080.

Use the API endpoints to create, retrieve, update, and delete ToDo tasks.

## Configuration
The application uses Spring Boot and Spring Caching for performance optimization.

### Asynchronous Processing
Asynchronous processing is employed in this application to enhance responsiveness and performance. Key components related to asynchronous processing include:

A custom `ThreadPoolTaskExecutor` bean for executing tasks asynchronously.
`CompletableFuture` and the `@Async` annotation to perform tasks asynchronously.
Offloading time-consuming operations to separate threads for improved user experience.

A custom `ThreadPoolTaskExecutor` is defined in ExecutorServiceConfig. You can adjust its core pool size, maximum pool size, queue capacity, and thread name prefix according to your requirements.

### Encryption
If you require encryption for sensitive data, you can set up Jasypt encryption by modifying EncryptionConfiguration. The application supports both property-based and environment variable-based encryption configuration.

### OpenAPI (Swagger) Documentation
This application provides OpenAPI (Swagger) documentation for easy API exploration. You can access the API documentation at http://localhost:9090/actuator/swagger-ui/index.html.

### Spring Boot Admin
This application is configured to interact with Spring Boot Admin. You can access the Spring Boot Admin dashboard at http://localhost:8080/applications to monitor the application's health and status.

## License
This project is licensed under the Mozilla Public License (MPL) - see the LICENSE file for details.

Happy coding!
