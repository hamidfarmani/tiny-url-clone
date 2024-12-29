# Tiny URL (clone) Microservices Project

## **Overview**
This project is a microservices-based implementation of a URL shortening service inspired by platforms like TinyURL. The architecture incorporates modern best practices for scalability, fault tolerance, and maintainability.

### **Key Features**
- **URL Shortening**: Converts long URLs into short, easily shareable links.
- **Dynamic Service Discovery**: Services are registered and discovered dynamically via Eureka.
- **Centralized Configuration Management**: Uses Spring Cloud Config for centralized configuration.
- **Load Balancing**: API Gateway and service-to-service communication use load-balanced requests.
- **Key Management Service (KGS)**: Efficient generation and storage of unique keys for shortened URLs.
- **API Gateway**: A single entry point for all clients.

---

## **Architecture**
The project follows a microservices architecture with the following components:

### **1. API Gateway**
- Acts as the single entry point for all client requests.
- Handles routing to the appropriate services.
- Provides centralized logging, monitoring, and request transformation.

### **2. Discovery Service (Eureka)**
- Manages service registration and discovery.
- Allows dynamic scaling of services without manual reconfiguration.

### **3. Config Service**
- Centralized configuration management using Spring Cloud Config.
- Stores environment-specific configurations.

### **4. Key Generation Service (KGS)**
- Generates unique keys for the URL shortening service.
- Uses a buffer and replenishment mechanism for efficient key management.
- Backed by a PostgreSQL database.

### **5. URL Service**
- Handles URL creation, storage, and retrieval.
- Interacts with KGS to fetch unique keys.
- Uses Cassandra for scalable and efficient data storage.

---

## **Tech Stack**

### **Backend**
- **Java 21**: For modern and efficient server-side development.
- **Spring Boot**: Framework for building microservices.
- **Spring Cloud**: For API Gateway, service discovery, and config management.
- **PostgreSQL**: Database for the KGS.
- **Cassandra**: Database for storing URLs.

### **Infrastructure**
- **Docker**: Containerized deployment for all services.
- **Eureka**: Service discovery and registry.
- **Spring Cloud Config**: Centralized configuration management.

### **Tools**
- **Feign**: For inter-service communication.
- **Load Balancer**: Built-in Spring Cloud load balancing for distributed services.
- **Virtual Threads**: Lightweight concurrency for key generation and replenishment.

---

## **Getting Started**

### **1. Clone the Repository**
```bash
git clone https://github.com/hamidfarmani/tiny-url-clone.git
cd tiny-url-clone
```

### **2. Prerequisites**
- **Java 21**
- **Docker** and **Docker Compose**
- **Maven**

### **3. Build and Run Services**

#### **Build All Services**
```bash
mvn clean install
```

#### **Run Using Docker Compose**
Ensure you have a `docker-compose.yml` file to start all services:
```bash
docker-compose up --build
```

This will start:
- Eureka Discovery Service
- Config Service
- API Gateway
- KGS
- URL Service

---

## **Service Details**

### **1. API Gateway**
- **Port**: `8084`
- **Base URL**: `http://<gateway-host>:8084`
- **Endpoints**:
    - `/kgs/**`: Routes requests to KGS.
    - `/api/**`: Routes requests to URL Service.

### **2. Discovery Service (Eureka)**
- **Port**: `8761`
- **Dashboard**: `http://localhost:8761`

### **3. Config Service**
- **Port**: `8888`
- **Configuration Files Location**: `src/main/resources/configurations`
- **Default Config Endpoint**: `http://localhost:8888/{application}/{profile}`

### **4. Key Generation Service (KGS)**
- **Port**: `8081`
- **Endpoints**:
    - `GET /kgs/fetch`: Fetches a batch of unique keys.
    - `GET /kgs/monitor/unused-keys/check`: Monitors unused keys.

### **5. URL Service**
- **Port**: `8082`
- **Endpoints**:
    - `POST /api/create`: Creates a new shortened URL.
    - `GET /api/{shortUrl}`: Retrieves the original URL for a given short URL.

---

## **Environment Variables**

### **Eureka Discovery Service**
- `EUREKA_INSTANCE_HOSTNAME`: Hostname of the Eureka server.

### **Config Service**
- `SPRING_PROFILES_ACTIVE`: Active profile (e.g., `dev`, `prod`).

### **KGS**
- `SPRING_DATASOURCE_URL`: JDBC URL for PostgreSQL.
- `SPRING_DATASOURCE_USERNAME`: Database username.
- `SPRING_DATASOURCE_PASSWORD`: Database password.

### **URL Service**
- `SPRING_DATA_CASSANDRA_CONTACT_POINTS`: Cassandra cluster contact points.
- `SPRING_DATA_CASSANDRA_PORT`: Cassandra port.
- `SPRING_DATA_CASSANDRA_KEYSPACE_NAME`: Keyspace name.

---

## **Development Notes**

### **1. Running Services Locally**
- Start Eureka first (`8761`), followed by Config Service (`8888`), and then the other services.
- Use `localhost` for development and update `/etc/hosts` if testing logical names.

### **2. Testing API Gateway**
- Test API Gateway routing using:
  ```bash
  curl http://localhost:8084/kgs/fetch?count=10
  ```

### **3. Debugging Tips**
- Check Eureka Dashboard to verify service registrations.
- Use `docker logs <container-name>` to debug Docker services.

---

## **Future Enhancements**
- Add user authentication and authorization.
- Implement rate limiting in API Gateway.
- Add telemetry for request tracking and usage analytics.
- Deploy services to Kubernetes for production scalability.

---

## **Contributors**
- **Hamid Farmani** - [GitHub](https://github.com/hamidfarmani)

Feel free to contribute by submitting issues or pull requests!
