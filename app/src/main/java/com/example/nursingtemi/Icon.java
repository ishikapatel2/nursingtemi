package com.example.nursingtemi;

import android.media.Image;
import android.widget.ImageView;

public class Icon {

    private ImageView id;
    private float x;
    private float y;

    public Icon(ImageView id, float x, float y)
    {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public ImageView getImageView()
    {
        return this.id;
    }

    public float getX()
    {
        return this.x;
    }

    public float getY()
    {
        return this.y;
    }

    public float setX(float newX)
    {
        return this.x = newX;
    }
    public float setY(float newY)
    {
        return this.y = newY;
    }
}
