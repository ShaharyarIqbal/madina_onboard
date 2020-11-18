package com.example.onboard.infrastructure.util;


import com.example.onboard.domain.model.BaseModel;

@SuppressWarnings({"unused"})
public final class ValidationUtils {

    private ValidationUtils() {
        // satisfy sonar lint, no need to write anything here
    }

    public static boolean isNotEmptyAndNull(String someString) {
        return someString != null && someString.trim().length() != 0;
    }

    public static boolean isNotNull(Object object){
        return object != null;
    }

    public static boolean isNew(BaseModel baseModel){
        return baseModel != null && baseModel.getId() == null;
    }

    public static boolean isNotNullOrZero(Integer value) {
        return value != null && !value.equals(0);
    }
}
