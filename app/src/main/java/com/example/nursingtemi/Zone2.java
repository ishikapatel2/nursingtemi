package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import java.util.Objects;

public class Zone2 extends AppCompatActivity {

    private Button room368;
    private Button room369;
    private Button room376;
    private Button room370;
    private Button room365;
    private Button room360;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone2);

        room368 = findViewById(R.id.room368);
        room369 = findViewById(R.id.room369);
        room376 = findViewById(R.id.room376);
        room370 = findViewById(R.id.room370);
        room360 = findViewById(R.id.room360);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room368.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 368!",false));
                Robot.getInstance().goTo("simulation room 368");
            }
        });

        room369.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 369!",false));
                Robot.getInstance().goTo("simulation room 369");
            }
        });

        room376.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 365!",false));
                Robot.getInstance().goTo("simulation room 376");
            }
        });

        room370.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 370!",false));
                Robot.getInstance().goTo("control room 370");
            }
        });

        room360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 360!",false));
                // fix name
                Robot.getInstance().goTo("test location");
            }
        });

    }




}