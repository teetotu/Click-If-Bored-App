package com.example.weatherapp.common;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String OWM_API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static final String NYT_API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static final String TMDB_API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static Location current_location = null;

    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEE MM YYYY");
        return sdf.format(date);
    }

    public static String convertUnixToHour(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
}
