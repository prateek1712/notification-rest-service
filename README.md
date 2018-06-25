# Notification Service

This is a RESTful API written in Java. It uses the following components:
* MySQL
* Maven
* Spring Boot
* Spring MVC
* Spring Data JPA
* Docker

## About the Service
A **Client** can subscribe to the service and send a **Notification** to a **User** using any of the following packages:

| Subscription Type | Price | Daily Notification Limit | Allowed Notification Types | 
| --- | --- |--- | --- |
| Silver | 49 | 1 Million | Email |
| Gold | 99 | 10 Million | Email, SMS |
| Platinum | 500 | Unlimited | Email, SMS, Push |

All packages have a validity of a **Month** i.e 30 days.

## System Requirements

1. [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. [Maven](https://maven.apache.org/download.cgi)
3. [Docker CE](https://store.docker.com/search?type=edition&offering=community)

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

## Endpoints

### Client

| URI | HTTP Method | Description |
| --- | --- |--- |
| /clients?id={id} | GET | Get Details of Client |
| /clients | POST | Create New Client |
| /clients/{id} | PUT | Update Existing Client |
| /clients/{id} | DELETE | Delete Existing Client |
| /clients/{id}/subscription | GET | Get Subscription Details |
| /clients/list | GET | Get List of all Clients |

### User

| URI | HTTP Method | Description |
| --- | --- |--- |
| /users?id={id} | GET | Get Details of User |
| /users | POST | Create New User |
| /users/{id} | PUT | Update Existing User |
| /users/{id} | DELETE | Delete Existing User |
| /users/list | GET | Get List of all Users |
| /users/{id}/block | PUT | Block User |
| /users/{id}/unblock | PUT | Unblock User |

### Notification

| URI | HTTP Method | Description |
| --- | --- |--- |
| /notifications?id={id} | GET | Get Notification Details |
| /notifications | POST | Send New Notification |
| /notifications/remaining?clientId={clientId} | GET | Get Remaining Notification Count |

Checkout the **docs** folder for detailed documentation of Requests & Responses.






