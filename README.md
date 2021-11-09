[![Maven Central](https://img.shields.io/maven-central/v/io.github.toiyeumayhoc/crud-generator.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.toiyeumayhoc/crud-generator/1.0.1/jar)
# CRUD GENERATOR
## _Simple CRUD Generator for Spring Project_
If you get tired of creating Controller, Service and Repository layers over and over again when working with Spring Web Project, this library is for you. With only one simple annotation, you will get back all the layers that needed in a second.

## Features

- Generate Repository layer with Spring JPA Repository or Spring MongoDb Repository
- Generate Service layer with Repository usage
- Generate CRUD API in controller layer
- Support generate code using Lombok library

## Installation

All you need to do is adding crud-generator dependency to your project:

```sh
<dependency>
  <groupId>io.github.toiyeumayhoc</groupId>
  <artifactId>crud-generator</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Usage

In your Entity class, add `@CrudGenerator(basePackage = {YOUR_BASE_PACKAGE})` annotation to class level.

For Example: 
```sh
//Other Annotations
@CrudGenerator(basePackage = "com.demo.project")
public class Employee {
    private String id;
    private String name;

    //Getter and Setter
}
```

And then run maven compile and all done.
```sh
mvn clean compile
```
You will receive back this project structure to work with:
```sh
|-- src/main/java
    |-- com.demo.project
        |-- controller
            |-- EmployeeController.java
        |-- repository
            |-- EmployeeRepository.java
            |-- extend
                |-- EmployeeRepositoryExtend.java
            |-- impl
                |-- EmployeeRepositoryImpl.java
        |-- request
            |-- EmployeeRequest.java
        |-- response
            |-- EmployeeResponse.java
        |-- service
            |-- EmployeeService.java
            |-- impl
                |-- EmployeeServiceImpl.java
```

## Customize Setting

You can change the way this library generating code by adjustment the parameters provided below:

| Parameter | Type | Description | Default value
| ------ | ------ | ------ | ------ |
| basePackage | String | Base Package of your project | *REQUIRED
| useLombok | boolean | generate code using Lombok library | true
| repositoryType | com.github.crud.generator.constant.RepositoryType  | Spring Repository your project is using | RepositoryType.MONGO
| idType | Class<?> | Your entity id type | String.class

## License

MIT

