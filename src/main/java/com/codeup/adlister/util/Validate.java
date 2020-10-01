package com.codeup.adlister.util;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.InputError;
import com.codeup.adlister.models.User;

import java.util.*;

public class Validate {

    public static HashMap<String, Boolean> getErrorList(String name, String email, String password, String checkPassword) {
        HashMap<String, Boolean> errorList = new HashMap<>();
        errorList.put("nameNotEmpty", !name.isEmpty());
        System.out.println(errorList.get("nameNotEmpty"));
        errorList.put("nameAvailable", username(name.toLowerCase()));
        System.out.println(errorList.get("nameAvailable"));
        errorList.put("emailNotEmpty", !email.isEmpty());
        System.out.println(errorList.get("emailNotEmpty"));
        errorList.put("emailAvailable", email(email.toLowerCase()));
        System.out.println(errorList.get("emailAvailable"));
        errorList.put("passwordNotEmpty", !password.isEmpty());
        System.out.println(errorList.get("passwordNotEmpty"));
        errorList.put("checkPasswordNotEmpty", !checkPassword.isEmpty());
        System.out.println(errorList.get("checkPasswordNotEmpty"));
        errorList.put("checkPasswordEquals", (errorList.get("passwordNotEmpty") && errorList.get("checkPasswordNotEmpty")) && password.equals(checkPassword));
        System.out.println(errorList.get("checkPasswordEquals"));
        return errorList;
    }

    public static boolean checkForErrors(HashMap<String, Boolean> errorList) {
        int total = 1;
        for (Map.Entry<String, Boolean> stringBooleanEntry : errorList.entrySet()) {
            Map.Entry entry = stringBooleanEntry;
            total *= (boolean) entry.getValue() ? 1 : 0;
        }
//        boolean tf = total == 1;
//        System.out.println("OVERALL: " + tf);
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
