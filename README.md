# Device Management API

This is a Spring Boot application for managing devices, allowing users to perform CRUD operations (Create, Read, Update, Delete) on device entities. The application is containerized using Docker for easy deployment.

## Features

- **Get All Devices**: Retrieve a list of all devices.
- **Get Device by ID**: Retrieve details of a specific device by its ID.
- **Get Device by Brand Name**: Retrieve devices by specifying the brand name.
- **Add Device**: Create a new device entry.
- **Delete Device**: Remove a device by its ID.
- **Update Device**: Update the information of an existing device.
- **Patch Device**: Partially update the information of an existing device.

## Endpoints

| Method | Endpoint                    | Description                           |
|--------|-----------------------------|---------------------------------------|
| GET    | `/devices`                  | Get a list of all devices             |
| GET    | `/devices/{id}`             | Get a specific device by ID           |
| GET    | `/devices/brand/{brand}`    | Get devices by brand name             |
| POST   | `/devices`                  | Add a new device                      |
| DELETE | `/devices/{id}`             | Delete a device by ID                 |
| PUT    | `/devices/{id}`             | Update an existing device             |
| PATCH  | `/devices/{id}`             | Partially update an existing device   |

## Running the Application

### Prerequisites

- Java 17 or higher
- Docker

### Build and Run Locally

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd <repository-directory>

2. **Build the project using Maven:**

   ```bash
   ./mvnw clean install
   
3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run

4. **Access the application**
   
   The API will be accessible at `http://localhost:8080`.

### Running with Docker

1. **Build the Docker image:**

   Ensure you are in the project root directory, then run:

   ```bash
   docker build -t device-app .

2. **Run The Docker Container:**

   ```bash
   docker run -p {HOSTPORT}:8080 device-app

3. **Access the application**

   The API will be accessible at `http://localhost:{HOSTPORT}`.

## Dockerfile Overview

The Dockerfile is configured to:

- **Base Image**: Uses an official OpenJDK image as the base image.
- **Expose Port**: Exposes port `8080` to allow access to the application.

## Unit Tests

Unit tests have been implemented to ensure the correctness of the CRUD operations. To run the tests, use the following command:

   ```bash
   ./mvnw test