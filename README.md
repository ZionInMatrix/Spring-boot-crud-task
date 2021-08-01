# Java CRUD-Spring-Boot-JPA-MySQL


# CRUD Example of Spring-Boot-REST-JPA-MySQL (User personal data with access by UUID Token)

### 1. You can clone it from GiHhub by running following command

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

# To access SWAGGER use --->
# http://localhost:8080/swagger-ui.html#
```

### 4. Please install Tomcat to run the application locally

### 5. As you see UserServiceImpl have the possibility: ADD, GET ALL, DELETE, UPDATE, LOGIN, FIND BY UUID TOKEN and GET USER PERSONAL DATA

### 6. To ADD user with personal data, use following url with `POST` request type in Postman and select Body->Json and post something like this:

```
http://localhost:8080/rest/user

{
    "userName": "Don Juan",
    "email": "don.juan@seznam.cz",
    "password": 999666,
    "personalData": {
        "dateOfBirthday": "00.00.0000",
        "address": "Prague 9",
        "phoneNumber": "666 999 666"
        }
}
```


### 7. To GET all users with personal data, use following url with `GET` request type in Postman

```
http://localhost:8080/rest/users
```

### 8.To UPDATE user by ID with personal data, use following url with `PUT` request type in Postman and update with Body->Json existing information

```
http://localhost:8080/rest/user

{
        "userName": "Maria Antuaneta",
        "email": "maria.antuaneta@seznam.cz",
        "password": "777"
        "personalData": {
            "dateOfBirthday": "2.11.1975",
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
### type and authorization with Bearer in Postman to GET user personal data without information about the user. Use Params Key and Value in it

```
http://localhost:8080/token?userName=Kristina&password=234

Params: 
KEY: userName, password
VALUE: Maria Antuaneta, 777
```




