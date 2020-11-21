package com.example.onboard.infrastructure.security.constant;


public enum SpringSecurity {

    BAD_CREDENTIALS_UNAUTHORIZED("Bad Credentials : Invalid username or password"),
    USER_ID("userId"),
    USER_NAME("userName"),
    ROLES("roles"),
    ACCOUNT_INACTIVE("Your account has been inactive please contact your manager"),
    DOMAIN_NAME("domainName");


    String value;

    SpringSecurity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SpringSecurity{" +
                "value='" + value + '\'' +
                '}';
    }
}
