package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        animationBackground();
        deliverStation();
    }




    public void deliverStation(){
        Robot.getInstance().setVolume(3);
        Robot.getInstance().speak(TtsRequest.create("What would you like to deliver? ", false));


    }

    public void animationBackground(){
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }
}