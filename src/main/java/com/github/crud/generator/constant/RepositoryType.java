package com.github.crud.generator.constant;

public enum RepositoryType {
	MONGO("MongoRepository", LibraryPackage.MONGO_REPOSITORY), 
    JPA("JpaRepository", LibraryPackage.JPA_REPOSITORY);
 
    private RepositoryType(final String value, final LibraryPackage libraryPackage) {
    	this.libraryPackage = libraryPackage;
        this.value = value;
    }
 
    private String value;
    private LibraryPackage libraryPackage;
 
    public String getValue() {
        return value;
    }
    
    public String getPackage() {
    	return libraryPackage.getValue();
    }
}
