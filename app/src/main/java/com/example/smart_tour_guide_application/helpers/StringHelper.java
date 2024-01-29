package com.example.smart_tour_guide_application.helpers;

public class StringHelper {


    // Set Regular Expression Pattern for Email:
    public static boolean regexEmailValidationPattern(String email) {
        // Set Pattern:
//        String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
        String regex = "^[A-Za-z0-9]+@[a-zA-Z0-9]+\\\\.(com)$";
        if(email.matches(regex)) {
            return true;
        }
        return false;
    }
    // End Of Set Regular Expression Pattern for Email.
}
