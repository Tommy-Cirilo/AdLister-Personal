package com.codeup.adlister.util;

public class InputError {
    private String name;
    private boolean value;
    private String message;

    public InputError(String name, boolean value, String message) {
        this.name = name;
        this.value = value;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public boolean isValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
