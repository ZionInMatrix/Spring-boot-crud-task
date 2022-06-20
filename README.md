# Java 8 CRUD-Spring-Boot-Hibernate-MySQL


# CRUD Spring-Boot-REST-Hibernate-MySQL (User personal data accessing by UUID access token)

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

### 4. Install Tomcat to run the application locally
