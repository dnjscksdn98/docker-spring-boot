## How to containerize Spring-Boot application and MySQL with Docker

### Create profiles
```yml
spring:
  profiles:
    active: dev

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/admin?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul
    username: root
    password: root

  jpa:
    database-platform: org.hibernate.dialect.MySQL5Dialect
    show-sql: true
    hibernate:
      ddl-auto: update

  jackson:
    property-naming-strategy: SNAKE_CASE
```

```yml
spring:
  profiles:
    active: prod

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://mysql-standalone:3306/admin?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul
    username: user
    password: password

  jpa:
    database-platform: org.hibernate.dialect.MySQL5Dialect
    show-sql: true
    generate-ddl: true
    hibernate:
      ddl-auto: update

  jackson:
    property-naming-strategy: SNAKE_CASE
```    

### Build Jar
```
./mvnw clean build
```

### Create Dockerfile
```dockerfile
FROM openjdk:8
ADD target/docker-0.0.1-SNAPSHOT.jar app.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Create Springboot App Image
```
docker build --build-arg ENVIRONMENT=prod -t springboot-mysql:1.0.0 .
```

### Run MySQL Image
```
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=db -e MYSQL_USER=user -e MYSQL_PASSWORD=password -d mysql:5.7
```

### Run Springboot App Image
```
docker run -p 8080:8080 --name springboot-mysql -d springboot-mysql:1.0.0
```

### Docker Compose to run all at once
```yml
version: '3'

services:
  mysql-standalone:
    image: mysql:5.7
    volumes:
      - ./data/mysql:/var/lib/mysql
    environment:
      - MYSQL_ROOT_PASSWORD=password
      - MYSQL_DATABASE=admin
      - MYSQL_USER=user
      - MYSQL_PASSWORD=password

  springboot-mysql:
    image: springboot-mysql:1.0.0
    ports:
      - "8080:8080"
    build:
      context: ./
      args:
        ENVIRONMENT: prod
      dockerfile: Dockerfile
    depends_on:
      - mysql-standalone
```

### Run Docker Compose
```
docker-compose up
```
