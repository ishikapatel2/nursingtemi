package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

import java.util.Objects;

public class Zone5 extends AppCompatActivity {

    private Button room329;
    private Button room330;
    private Button room333;
    private Button room322;
    private Button room323;
    private Button room324;
    private Button room325;
    private Button room326;
    private Button room327;
    private Button room328;
    private Button room331;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone5);

        room329 = findViewById(R.id.room329);
        room330 = findViewById(R.id.room330);
        room333 = findViewById(R.id.room333);
        room322 = findViewById(R.id.room322);
        room323 = findViewById(R.id.room323);
        room324 = findViewById(R.id.room324);
        room325 = findViewById(R.id.room325);
        room326 = findViewById(R.id.room326);
        room327 = findViewById(R.id.room327);
        room328 = findViewById(R.id.room328);
        room331 = findViewById(R.id.room331);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room329.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 329!",false));
                // fix name
                Robot.getInstance().goTo("office 329");
            }
        });

        room330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 330!",false));
                // fix name
                Robot.getInstance().goTo("office 330");
            }
        });

        room333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to the meeting room!",false));
                // fix name
                Robot.getInstance().goTo("meeting room 333");
            }
        });

        room322.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 322!",false));
                // fix name
                Robot.getInstance().goTo("office 322");
            }
        });

        room323.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 323!",false));
                // fix name
                Robot.getInstance().goTo("office 323");
            }
        });

        room324.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 324!",false));
                // fix name
                Robot.getInstance().goTo("office 324");
            }
        });

        room325.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 325!",false));
                // fix name
                Robot.getInstance().goTo("office 325");
            }
        });

        room326.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 326!",false));
                // fix name
                Robot.getInstance().goTo("office 326");
            }
        });

        room327.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 327!",false));
                // fix name
                Robot.getInstance().goTo("office 327");
            }
        });

        room328.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 328!",false));
                // fix name
                Robot.getInstance().goTo("office 328");
            }
        });

        room331.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 331!",false));
                // fix name
                Robot.getInstance().goTo("office 331");
            }
        });
    }
}