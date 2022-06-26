package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button tourButton;
    private Button deliveryButton;
    private Button surveyButton;
    private Button exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        animationBackground();


        tourButton = findViewById(R.id.tourButton);
        tourButton.setOnClickListener((v) -> {
            openActivity(TourActivity.class);
        });

        deliveryButton = findViewById(R.id.deliveryButton);
        deliveryButton.setOnClickListener((v)-> {
            openActivity(DeliveryActivity.class);
        });

        surveyButton = findViewById(R.id.surveyButton);
        surveyButton.setOnClickListener((v)-> {
            openActivity(SurveyActivity.class);
        });

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener((v) ->{
            finishAffinity();
            System.exit(0);
        });
    }




    public void openActivity(Class a){
        Intent obj = new Intent(this,a);
        startActivity(obj);
    }



    public void animationBackground(){
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    public void openInformation (View v) {
        Intent obj = new Intent(this, InfoActivity.class);
        startActivity(obj);
    }

}













