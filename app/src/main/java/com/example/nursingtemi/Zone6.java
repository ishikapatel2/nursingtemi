package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
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

import org.w3c.dom.Text;

import java.util.Objects;

public class Zone6 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button room366;
    private Button room365;
    private Button room364;
    private TextView sRooms;
    private TextView cRooms;

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
        setContentView(R.layout.activity_zone6);

        deliveryType = getIntent().getStringExtra("deliveryType");
        patient = getIntent().getStringExtra("PatientName");
        message = findViewById(R.id.textMessage);
        message.setVisibility(View.INVISIBLE);
        recordingImage = findViewById(R.id.recording);
        recordingImage.setVisibility(View.INVISIBLE);

        room364 = findViewById(R.id.room364);
        room365 = findViewById(R.id.room365);
        room366 = findViewById(R.id.room366);
        sRooms = findViewById(R.id.sRooms);
        cRooms = findViewById(R.id.cRooms);

        room364.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to room 364!",false));
                Robot.getInstance().goTo("simulation room 364");
            }
        });

        room365.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to room 365!",false));
                Robot.getInstance().goTo("control room 365");
            }
        });

        room366.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to room 366!",false));
                Robot.getInstance().goTo("simulation room 366");
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
                room364.setVisibility(View.INVISIBLE);
                room365.setVisibility(View.INVISIBLE);
                room366.setVisibility(View.INVISIBLE);
                sRooms.setVisibility(View.INVISIBLE);
                cRooms.setVisibility(View.INVISIBLE);
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
                    Intent intent = new Intent(Zone6.this, ConfirmMessageActivity.class);
                    intent.putExtra("deliveryType", deliveryType);
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
                Robot.getInstance().speak(TtsRequest.create("Delivery suddenly canceled. Please try again. " +
                        "If you see a retry button on my screen, click on it, then reorient me, and give me a " +
                        "little push in the right direction so that I can be on my way.", false));                break;
        }
    }
}