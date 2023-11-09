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

public class Zone1 extends AppCompatActivity implements OnRobotReadyListener {

    private Button room366;
    private Button room364;
    private Button room363;
    private Button room367;
    private Button room365;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone1);

        room366 = findViewById(R.id.room366);
        room364 = findViewById(R.id.room364);
        room363 = findViewById(R.id.room363);
        room367 = findViewById(R.id.room367);
        room365 = findViewById(R.id.room365);

        room366.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 366!",false));
                Robot.getInstance().goTo("simulation room 366");
            }
        });

        room364.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 364!",false));
                // name may be wrong
                Robot.getInstance().goTo("simulation room 364");
            }
        });

        room363.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 363!",false));
                // name may be wrong
                Robot.getInstance().goTo("simulation room 363");
            }
        });
        room367.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 367!",false));
                // name may be wrong
                Robot.getInstance().goTo("simulation room 367");
            }
        });

        room365.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 365!",false));
                Robot.getInstance().goTo("control room 365");
            }
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{

            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Please select a room", false));
        }
    }
}