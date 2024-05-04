package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class Zone8 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button room322;
    private Button room323;
    private Button room324;
    private Button room325;
    private Button room326;
    private Button room327;
    private Button room328;
    private Button room331;
    private Button room333;
    private Button room321;
    private TextView offices;
    private TextView llounge;
    private TextView meeting;

    private Position currentPosition;
    private boolean updatePosition = true;

    private String deliveryType;
    private String patient;
    private TextView message;
    private ImageView recordingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone8);

        deliveryType = getIntent().getStringExtra("deliveryType");
        patient = getIntent().getStringExtra("PatientName");
        message = findViewById(R.id.textMessage);
        message.setVisibility(View.INVISIBLE);
        recordingImage = findViewById(R.id.recording);
        recordingImage.setVisibility(View.INVISIBLE);

        room322 = findViewById(R.id.room322);
        room323 = findViewById(R.id.room323);
        room324 = findViewById(R.id.room324);
        room325 = findViewById(R.id.room325);
        room326 = findViewById(R.id.room326);
        room327 = findViewById(R.id.room327);
        room328 = findViewById(R.id.room328);
        room331 = findViewById(R.id.room331);
        room333 = findViewById(R.id.room333);
        room321 = findViewById(R.id.room321);

        offices = findViewById(R.id.offices);
        llounge = findViewById(R.id.llounge);
        meeting = findViewById(R.id.meeting);

        room322.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 322!",false));
                Robot.getInstance().goTo("office 322");
            }
        });

        room323.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 323!",false));
                Robot.getInstance().goTo("office 323");
            }
        });

        room324.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 324!",false));
                Robot.getInstance().goTo("office 324");
            }
        });

        room325.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 325!",false));
                Robot.getInstance().goTo("office 325");
            }
        });

        room326.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 326!",false));
                Robot.getInstance().goTo("office 326");
            }
        });

        room327.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 327!",false));
                Robot.getInstance().goTo("office 327");
            }
        });

        room328.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 328!",false));
                Robot.getInstance().goTo("office 328");
            }
        });

        room331.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 331!",false));
                Robot.getInstance().goTo("office 331");
            }
        });

        room333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 333!",false));
                Robot.getInstance().goTo("office 333");
            }
        });

        room321.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to office 321!",false));
                Robot.getInstance().goTo("office 321");
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
            //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
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
                room322.setVisibility(View.INVISIBLE);
                room323.setVisibility(View.INVISIBLE);
                room324.setVisibility(View.INVISIBLE);
                room325.setVisibility(View.INVISIBLE);
                room326.setVisibility(View.INVISIBLE);
                room327.setVisibility(View.INVISIBLE);
                room328.setVisibility(View.INVISIBLE);
                room331.setVisibility(View.INVISIBLE);
                room333.setVisibility(View.INVISIBLE);
                room321.setVisibility(View.INVISIBLE);

                meeting.setVisibility(View.INVISIBLE);
                offices.setVisibility(View.INVISIBLE);
                llounge.setVisibility(View.INVISIBLE);

                recordingImage.setVisibility(View.VISIBLE);
                message.setText("For security and monitoring: \n" +
                        "Recording in Progress. ");
                message.setVisibility(View.VISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    message.setVisibility(View.GONE);
                    recordingImage.setVisibility(View.GONE);
                    Robot.getInstance().speak(TtsRequest.create("I have arrived with your order", false));
                    Intent intent = new Intent(Zone8.this, ConfirmMessageActivity.class);

                    if ("Food".equals(deliveryType)) {
                        intent.putExtra("deliveryType", "Food");
                    }
                    else {
                        intent.putExtra("deliveryType", "Medication");
                    }
                    intent.putExtra("PatientName", patient);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    startActivity(intent);
                }
                else {
                    Log.e("Zone6", "Current position is null");
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