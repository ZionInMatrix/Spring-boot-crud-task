# Java CRUD-Spring-Boot-JPA-Hibernate-MySQL


# CRUD Spring-Boot-REST-JPA-Hibernate-MySQL (User with personal data accessing by UUID access token)

### 1. You can clone it from GitHub by running following command

```
  $ git clone https://github.com/ZionInMatrix/spring-boot-crud-task.git
```

### 2. Import project into Intellij IDEA Ultimate

### 3. Update MySQL connection configurations into application.properties available in src/main/java/resources

```
# MySQL connection configurations
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/FunTaskProject
spring.datasource.username=root
spring.datasource.password=yourpasswd
#spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
```

### 4. Please install Tomcat to run the application locally

### 5. As you can see, UserServiceImpl have the possibility: ADD, GET ALL, DELETE, UPDATE, LOGIN, FIND BY UUID TOKEN and GET USER PERSONAL DATA
And PersonalDataService: ADD, GET ALL, GET BY ID, DELETE BY ID, UPDATE BY ID.

### 6. To ADD user with personal data, use following url with `POST` request type in Postman and select Body->Json and post something like this:

```
http://localhost:8080/rest/user

{
    "userName": "Don",
    "email": "don.juan@seznam.cz",
    "password": 999666,
    "personalData": {
        "dateOfBirthday": "2021-06-22",
        "address": "Prague 9",
        "phoneNumber": "666 999 666"
        }
}
```


### 7. To GET all users with personal data, use following url with `GET` request type in Postman. 

```
http://localhost:8080/rest/users
```

### 8.To UPDATE user by ID with personal data, use following url with `PUT` request type in Postman and update with Body->Json existing information

```
http://localhost:8080/rest/user

{
        "userName": "Maria",
        "email": "maria.antuaneta@seznam.cz",
        "password": "777"
        "personalData": {
            "dateOfBirthday": "2021-06-22",
            "address": "Prague 1",
            "phoneNumber": "777 777 777"
        }
    }
```
### 9. To DELETE user by ID with personal data, use following url with `DELETE` request type in Postman

```
http://localhost:8080/rest/user/1
```
### 10. User personal Data have security access, firstly you need to do login to GET access token and secondly, you will need to enter this token in the request   
### type and authorization with Bearer Token in Postman to GET user personal data without information about the user. Use Params Key and Value in it

```
http://localhost:8080/token?userName=Maria&password=777

Params: 
KEY: userName, password
VALUE: Maria, 777
```
```
http://localhost:8080/api/user/data/b50c1672-7df7-4da0-a756-9034cdeb7261

Authorization -> BearerToken->b50c1672-7df7-4da0-a756-9034cdeb7261
```

### 11. To access SWAGGER use:

```
# http://localhost:8080/swagger-ui.html#
```

### 12. Docker starting and runnig

1. Verify the target directory(spring-boot-crud-task.jar)
2. Creating the Dockerfile to create the docker image

```
FROM openjdk:12
ADD target/spring-boot-crud-task.jar spring-boot-crud-task.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-crud-task.jar"]
```

3. Create the Docker Image using below command from the directory where Dockerfile is placed. This command instruct Docker to create the image of our application and tagged it as spring-boot-crud-task

```
docker build -t spring-boot-crud-task . 
```

4. Once the image is built, use the following command to view the list of images:

```
docker images
```

5. In order to start MySQL, we need MySQL Docker image. We can pull the same from Docker Hub. Note that Docker Hub is the repository which contains the images of existing software.

```
docker pull mysql:8.0.23
```

6. Once the pull is done, verify the list of images in local server using below command an entry of MySQL will be available

```
docker images
```

7. Following to this, we will run the MySQL server to run as a Docker container

```
docker run --name mysql-example -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:8.0.23
```

8. Verify the MySQL start up logs using the following command:

```
docker container logs mysql-example
```

9. Starting the application container. We can run our Sprint Boot application in the same manner using the following command:

```
docker run -d -p 8080:8080 -name user-mysql --link mysql-example:mysql user-mysql
```

10. Verify the application start up logs using the following command:

```
docker container logs user-mysql
```

11. Testing the REST API




