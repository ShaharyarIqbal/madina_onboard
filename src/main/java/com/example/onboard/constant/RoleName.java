package com.example.onboard.constant;


public enum RoleName {
    ADMIN,
    USER;

    public static RoleName getrole(String value){
        RoleName role = null;
        for(RoleName roleName: RoleName.values()){
            if(roleName.name().contains(value)){
                role = roleName;
            }
        }
        return role;
    }

}
