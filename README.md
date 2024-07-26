**Quick Start** 

> ```plaintext
> mvn package -DskipTests
> ```

> ```plaintext
> docker-compose up
> ```

**Server —** processes HTTP requests,  publishes events to message queue, and awaits replies before sending final responses.

**Server Endpoints:**

*   ```plaintext
    [POST] /api/v1/employees
    
    RequestBody:
    	String fullName
    	String address
    	String position
    ```
    
*   ```plaintext
    [POST] /api/v1/phones
    
    RequestBody:
    	String phoneNumber
    	String type
    ```
    
*   ```plaintext
    [PUT] /api/v1/employees/attach-phone
    
    RequestBody:
    	Long employeeId
    	Long phoneId
    ```
    

**Client —** consumes and processes events from a message queue, publishing responses to a reply topic.

**Stack:**

*   **Java 17**
*   **Spring Boot**
*   **PostgreSQL**
*   **Hibernate**
*   **Kafka**
*   **Mapstruct**
*   **Maven**
*   **Docker**