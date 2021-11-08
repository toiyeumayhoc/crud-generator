package com.github.crud.generator.constant;

public enum LibraryPackage {
    MONGO_REPOSITORY("org.springframework.data.mongodb.repository.MongoRepository"), 
    SERVICE_ANNOTATION("org.springframework.stereotype.Service"),
    REPOSITORY_ANNOTATION("org.springframework.stereotype.Repository"),
    LIST("java.util.List"),
    AUTOWIRED("org.springframework.beans.factory.annotation.Autowired"),
    OVERRIDE("java.lang.Override"),
    OPTIONAL("java.util.Optional"),
    COLLECTORS("java.util.stream.Collectors");
 
    private LibraryPackage(final String value) {
        this.value = value;
    }
 
    private String value;
 
    public String getValue() {
        return value;
    }
}