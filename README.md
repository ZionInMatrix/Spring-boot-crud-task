# Java CRUD-Spring-Boot-JPA-MySQL


# CRUD Example of Spring-Boot-REST-JPA-MySQL (User personal data with access by UUID Token)

### 1. You can clone it from GiHhub by running following command

```
  $ git clone https://github.com/ZionInMatrix/spring-boot-crud-task.git
```

### 2. Import project into Intellij IDEA Ultimate

### 5. Update MySQL connection configurations into application.properties available in src/main/java/resources

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

### 6. Please install Tomcat to run the application locally

### 9. As you see UserServiceImpl have have posibility where you can ADD, GET ALL, DELETE, UPDATE, LOGIN, FIND BY UUID TOKEN and GET USER PERSONAL DATA




