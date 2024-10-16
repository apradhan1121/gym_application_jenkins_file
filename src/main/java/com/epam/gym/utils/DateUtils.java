package com.epam.gym.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtils {

    static LocalDate today = LocalDate.now();
    public static LocalTime time = LocalTime.now();

    public static int getCurrentYear(){
        return today.getYear();
    }

    public static String getCurrentMonth(){
        String month = String.valueOf(today.getMonth()).toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        return month;
    }

    public static int getCurrentDate(){
        return today.getDayOfMonth();
    }

    public static int getCurrentMonthValue(){
        return today.getMonthValue();
    }

    public static int getCurrentHour(){
        return time.getHour();
    }

    public static int getCurrentMinute(){
        return time.getMinute();
    }

    public static int convertMonthToNumber(String month) {
        switch (month.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
}
