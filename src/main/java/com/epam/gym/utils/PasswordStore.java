package com.epam.gym.utils;

public class PasswordStore {
    private static String currentPassword;

    public static String getCurrentPassword() {
        return currentPassword;
    }

    public static void setCurrentPassword(String setPassword){
        System.out.println("Setting new password in the password Store: " + setPassword);
        currentPassword = setPassword;

    }
}
