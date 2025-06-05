package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static Date toDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return formatter.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
}
