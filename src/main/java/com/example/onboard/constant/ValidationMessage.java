package com.example.onboard.constant;

public enum ValidationMessage {

    USER_ALREADY_EXISTS("User already exists"),
    USER_NOT_FOUND("No user found"),
    ENTITY_NOT_FOUND("Entity Not Found")
    ;

    String name;
    ValidationMessage(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
}
