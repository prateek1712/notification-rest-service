# Notification Service

This is a RESTful API written in Java. It uses the following components:
* Maven
* Spring Boot
* Spring MVC
* Spring Data JPA
* MySQL
* Docker

## System Requirements

1. Java SDK
2. Maven 
3. Docker CE

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/prateek1712/notification-rest-service.git
```

**2. Run MySQL Docker Container**

```bash
docker run -p 3305:3306 --name notif-mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.6
```


**3. Build and run the application using maven**

```bash
cd notification-rest-service
mvn package
java -jar target/notification-rest-service-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app directly without packaging using:

```bash
mvn spring-boot:run
```
