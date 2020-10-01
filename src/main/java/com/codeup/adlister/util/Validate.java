package com.codeup.adlister.util;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import java.util.*;

public class Validate {
    public static HashMap<String, Boolean> getErrorList(String name, String email, String password, String checkPassword) {
        HashMap<String, Boolean> errorList = new HashMap<>();
        errorList.put("nameNotEmpty", !name.isEmpty());
        errorList.put("nameAvailable", username(name.toLowerCase()));
        errorList.put("emailNotEmpty", !email.isEmpty());
        errorList.put("emailAvailable", email(email.toLowerCase()));
        errorList.put("passwordNotEmpty", !password.isEmpty());
        errorList.put("checkPasswordNotEmpty", !checkPassword.isEmpty());
        errorList.put("checkPasswordEquals", (errorList.get("passwordNotEmpty") && errorList.get("checkPasswordNotEmpty")) && password.equals(checkPassword));
        return errorList;
    }

    public static HashMap<String, Boolean> getErrorList(String title, String description) {
        HashMap<String, Boolean> errorList = new HashMap<>();
        errorList.put("titleNotEmpty", !title.isEmpty());
        errorList.put("descriptionNotEmpty", !description.isEmpty());
        return errorList;
    }

    public static ArrayList<String> getErrorMessages(HashMap<String, Boolean> errorList) {
        ArrayList<String> errorMessages = new ArrayList<>();
        if(!errorList.get("nameNotEmpty")) {
            errorMessages.add("Please input a username");
        } else {
            if(!errorList.get("nameAvailable"))
                errorMessages.add("Username is not available");
        }

        if(!errorList.get("emailNotEmpty")) {
            errorMessages.add("Please input an email");
        } else {
            if(!errorList.get("emailAvailable"))
                errorMessages.add("Email is already in use");
        }

        if(!errorList.get("passwordNotEmpty")) {
            errorMessages.add("Please input a password");
        } else {
            if(!errorList.get("checkPasswordNotEmpty")) {
                errorMessages.add("Please input your password into the password confirmation");
            } else {
                if(!errorList.get("checkPasswordEquals"))
                    errorMessages.add("Passwords do not match");
            }
        }

        return errorMessages;
    }

    public static boolean checkForErrors(HashMap<String, Boolean> errorList) {
        int total = 1;
        for (Map.Entry<String, Boolean> stringBooleanEntry : errorList.entrySet()) {
            Map.Entry entry = stringBooleanEntry;
            total *= (boolean) entry.getValue() ? 1 : 0;
        }
        return total == 1;
    }

    public static boolean length(String input, int min, int max) {
        int length = input.length();
        return length >= min && length <= max;
    }

    public static boolean username(String username) {
        User user = DaoFactory.getUsersDao().findByUsername(username);
        return Objects.isNull(user);
    }

    public static boolean email(String email) {
        User user = DaoFactory.getUsersDao().findByEmail(email);
        return Objects.isNull(user);
    }
}
