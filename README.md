# ATM Spring Boot Api
---
### Summary
---
The project consists of an API to be used for basic ATM operations ans customer/account management

## Technologies Used

| Technologies Used   |
|---------------------|
| Java 17             |
| Spring Boot 3.2.2   |
| Spring Security     |
| Spring Data JPA     |
| Docker              |
| Docker compose      |
| Postgresql Database |
| IntelliJ IDEA       | 
| Maven               |
| Lombok              |
| Postman Client      |

### Run & Build

---
There are 2 ways of run & build the application.

#### Docker Compose

For docker compose usage

You just need to run `docker-compose up` command
___
*$PORT: 8082*
```ssh
$ cd account
$ docker-compose up
```

#### Maven



___
*$PORT: 8082*
```
$ cd account/account-api
$ mvn clean install
$ mvn spring-boot:run

