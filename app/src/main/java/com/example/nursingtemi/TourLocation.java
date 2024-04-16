package com.example.nursingtemi;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class TourLocation {

    private String location; // location listed in temi
    private String name; // name of location
    private String message; // location description

    public TourLocation(String locationName, String message, String name){
        this.location = locationName;
        this.message = message;
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public String getMessage() {
        return this.message;
    }

    public String getLocationName() {
        return this.name;
    }
}