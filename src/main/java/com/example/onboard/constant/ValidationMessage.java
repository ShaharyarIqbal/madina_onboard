package com.example.onboard.constant;

public enum ValidationMessage {

    USER_ALREADY_EXISTS("User already exists");

    String name;
    ValidationMessage(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
}
