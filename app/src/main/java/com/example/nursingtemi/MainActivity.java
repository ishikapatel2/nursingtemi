package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button tourButton;
    private Button deliveryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        tourButton = findViewById(R.id.tourButton);
        tourButton.setOnClickListener((v) -> {
            openActivity(TourActivity.class);
        });

        deliveryButton = findViewById(R.id.deliveryButton);
        deliveryButton.setOnClickListener((v)-> {
            openActivity(DeliveryActivity.class);
        });

    }


    public void openActivity(Class a){
        Intent obj = new Intent(this,a);
        startActivity(obj);
    }



    public void startTitle(){}

}