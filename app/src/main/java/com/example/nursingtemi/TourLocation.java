package com.example.nursingtemi;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class TourLocation {

    // Title of the location to display
    private String title;

    // Name of the location, as stored in the Temi
    private String location;

    // description of location
    private String message;

    /** Drawable ID of the QR code to show at this location */
     private int qrId;

    /**
     * Creates a new tour location
     * @param title is the title to display at this location
     * @param locationName is the name of the location in Temi
     */
    public TourLocation(String title, String locationName) {
        this.title = title;
        this.location = locationName;
    }

    /**
     * Creates a new tour location
     * @param title is the title to display at this location
     * @param locationName is the name of the location in Temi
     * @param message is the description of the location
     */
    public TourLocation(String title, String locationName, String message){
        this.title = title;
        this.location = locationName;
        this.message = message;
    }

    /**
     * Gets the title to display
     * @return location title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the location string
     * @return name of location as stored in Temi
     */
    public String getLocation() {
        return this.location;
    }


    /**
     * Gets the message
     * @return message of the room
     */
    public String getMessage(){return this.message;}

    /**
     * Gets the drawable for the QR code to show at this location
     * @param r app resources
     * @return QR code drawable
     */
    public Drawable getQr(Resources r) {
        return r.getDrawable(this.qrId, null);
    }

}