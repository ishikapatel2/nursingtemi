package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class DeliveryContinuationActivityNurse extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button room366;
    private Button room364;
    private Button room363;
    private Button room367;
    private Button room365;
    private TextView simRoomTextView;
    private TextView controlRTextVIew;
    private TextView officesTextView;
    private ImageView recordingImage;
    private TextView message;

    private Position currentPosition;
    private boolean updatePosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation_nurse);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, DeliveryActivity.class);
            startActivity(obj);
        });

        message = findViewById(R.id.textMessage);
        message.setVisibility(View.INVISIBLE);
        room366 = findViewById(R.id.room366);
        room364 = findViewById(R.id.room364);
        room363 = findViewById(R.id.room363);
        room367 = findViewById(R.id.room367);
        room365 = findViewById(R.id.room365);
        simRoomTextView = findViewById(R.id.simRooms);
        controlRTextVIew = findViewById(R.id.cRooms);
        officesTextView = findViewById(R.id.offices);
        recordingImage = findViewById(R.id.recording);
        recordingImage.setVisibility(View.INVISIBLE);


        room366.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will visit the pharmacy to retrieve your requested item ",false));
                Robot.getInstance().goTo("pharmacy");
            }
        });

        room364.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will visit the pharmacy to retrieve your requested item ",false));
                Robot.getInstance().goTo("pharmacy");
            }
        });

        room363.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will visit the pharmacy to retrieve your requested item ",false));
                Robot.getInstance().goTo("pharmacy");
            }
        });
        room367.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will visit the pharmacy to retrieve your requested item ",false));
                Robot.getInstance().goTo("pharmacy");
            }
        });

        room365.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will visit the pharmacy to retrieve your requested item ",false));
                Robot.getInstance().goTo("pharmacy");
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
            Robot.getInstance().speak(TtsRequest.create("Please select the room are you currently in?", false));
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                updatePosition = false;
                room366.setVisibility(View.INVISIBLE);
                room364.setVisibility(View.INVISIBLE);
                room363.setVisibility(View.INVISIBLE);
                room367.setVisibility(View.INVISIBLE);
                room365.setVisibility(View.INVISIBLE);
                simRoomTextView.setVisibility(View.INVISIBLE);
                controlRTextVIew.setVisibility(View.INVISIBLE);
                officesTextView.setVisibility(View.INVISIBLE);
                message.setText("I am busy requesting a delivery.\n Please do not interrupt me.");
                message.setVisibility(View.VISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    message.setVisibility(View.GONE);
                    Intent intent = new Intent(DeliveryContinuationActivityNurse.this, ConfirmMessageActivityNurse.class);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    startActivity(intent);
                }
                else {
                    Log.e("Nursing Delivery", "Current position is null");
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