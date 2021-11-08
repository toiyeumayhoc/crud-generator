package com.github.crud.generator.constant;

public enum LibraryPackage {
    MONGO_REPOSITORY("org.springframework.data.mongodb.repository.MongoRepository"), 
    SERVICE_ANNOTATION("org.springframework.stereotype.Service"),
    REPOSITORY_ANNOTATION("org.springframework.stereotype.Repository"),
    LIST("java.util.List"),
    AUTOWIRED("org.springframework.beans.factory.annotation.Autowired"),
    OVERRIDE("java.lang.Override"),
    OPTIONAL("java.util.Optional"),
    COLLECTORS("java.util.stream.Collectors"),
    DELETE_MAPPING("org.springframework.web.bind.annotation.DeleteMapping"),
    POST_MAPPING("org.springframework.web.bind.annotation.PostMapping"),
    GET_MAPPING("org.springframework.web.bind.annotation.GetMapping"),
    PUT_MAPPING("org.springframework.web.bind.annotation.PutMapping"),
    PATH_VARIABLE("org.springframework.web.bind.annotation.PathVariable"),
    REQUEST_BODY("org.springframework.web.bind.annotation.RequestBody"),
    REQUEST_MAPPING("org.springframework.web.bind.annotation.RequestMapping"),
    REST_CONTROLLER("org.springframework.web.bind.annotation.RestController");
 
    private LibraryPackage(final String value) {
        this.value = value;
    }
 
    private String value;
 
    public String getValue() {
        return value;
    }
}