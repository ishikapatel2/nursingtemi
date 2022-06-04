package com.example.nursingtemi;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * This class represents a location in the demo tour
 */
public class TourLocation {

    /** Title of the location to display */
    private String title;
    /** Name of the location, as stored in the Temi */
    private String location;
    /** Drawable ID of the QR code to show at this location */
    private int qrId;

    /**
     * Creates a new tour location
     * @param title is the title to display at this location
     * @param locationName is the name of the location in Temi
     * @param qrId is the ID of the QR code drawable
     */
    public TourLocation(String title, String locationName, int qrId) {
        this.title = title;
        this.location = locationName;
        this.qrId = qrId;
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
     * Gets the drawable for the QR code to show at this location
     * @param r app resources
     * @return QR code drawable
     */
    public Drawable getQr(Resources r) {
        return r.getDrawable(this.qrId, null);
    }

}