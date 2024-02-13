package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class Zone5 extends AppCompatActivity  implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

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
    private TextView wRoom;
    private TextView llounge;
    private TextView offices;

    private Position currentPosition;
    private boolean updatePosition = true;

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
        wRoom = findViewById(R.id.wRoom);
        llounge = findViewById(R.id.llounge);
        offices = findViewById(R.id.offices);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room329.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 329!",false));
                Robot.getInstance().goTo("office 329");
            }
        });

        room330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 330!",false));
                Robot.getInstance().goTo("office 330");
            }
        });

        room333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to the meeting room!",false));
                Robot.getInstance().goTo("meeting room 333");
            }
        });

        room322.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 322!",false));
                Robot.getInstance().goTo("office 322");
            }
        });

        room323.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 323!",false));
                Robot.getInstance().goTo("office 323");
            }
        });

        room324.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 324!",false));
                Robot.getInstance().goTo("office 324");
            }
        });

        room325.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 325!",false));
                Robot.getInstance().goTo("office 325");
            }
        });

        room326.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 326!",false));
                Robot.getInstance().goTo("office 326");
            }
        });

        room327.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 327!",false));
                Robot.getInstance().goTo("office 327");
            }
        });

        room328.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 328!",false));
                Robot.getInstance().goTo("office 328");
            }
        });

        room331.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to office 331!",false));
                Robot.getInstance().goTo("office 331");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
        Robot.getInstance().addOnGoToLocationStatusChangedListener(this);
        Robot.getInstance().addOnCurrentPositionChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
        Robot.getInstance().removeOnGoToLocationStatusChangedListener(this);
    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        if (updatePosition) {
            currentPosition = position;
            Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
        }
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Please select a room", false));
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                updatePosition = false;
                room329.setVisibility(View.INVISIBLE);
                room330.setVisibility(View.INVISIBLE);
                room333.setVisibility(View.INVISIBLE);
                room322.setVisibility(View.INVISIBLE);
                room323.setVisibility(View.INVISIBLE);
                room324.setVisibility(View.INVISIBLE);
                room325.setVisibility(View.INVISIBLE);
                room326.setVisibility(View.INVISIBLE);
                room327.setVisibility(View.INVISIBLE);
                room328.setVisibility(View.INVISIBLE);
                room331.setVisibility(View.INVISIBLE);
                wRoom.setVisibility(View.INVISIBLE);
                llounge.setVisibility(View.INVISIBLE);
                offices.setVisibility(View.INVISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    Intent intent = new Intent(Zone5.this, ConfirmMessageActivity.class);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    startActivity(intent);
                }
                else {
                    Log.e("Zone1", "Current position is null");
                }
                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I am experiencing problems.", false));
                break;
        }
    }
}