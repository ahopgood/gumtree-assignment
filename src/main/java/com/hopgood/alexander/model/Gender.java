package com.hopgood.alexander.model;

public enum Gender {
    MALE, FEMALE;
    public static Gender fromString(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
}
