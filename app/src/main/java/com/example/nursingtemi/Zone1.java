package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import android.util.Log;
import java.util.Objects;

public class Zone1 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button room372;
    private Button room373;
    private Button room374;
    private TextView debriefing;
    private TextView message;
    private ImageView recordingImage;
    private String deliveryType;
    private String patient;

    private Position currentPosition;
    private boolean updatePosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone1);

        deliveryType = getIntent().getStringExtra("deliveryType");
        //Log.d("FOODELIVERY", deliveryType);
        patient = getIntent().getStringExtra("PatientName");
        room372 = findViewById(R.id.room372);
        room373 = findViewById(R.id.room373);
        room374 = findViewById(R.id.room374);
        debriefing = findViewById(R.id.debriefing);
        message = findViewById(R.id.textMessage);
        message.setVisibility(View.INVISIBLE);
        recordingImage = findViewById(R.id.recording);
        recordingImage.setVisibility(View.INVISIBLE);

        room372.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to room 372!",false));
                Robot.getInstance().goTo("debriefing 372");
            }
        });

        room373.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to room 373!",false));
                Robot.getInstance().goTo("debriefing 373");
            }
        });

        room374.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to make a delivery to room 374!",false));
                Robot.getInstance().goTo("debriefing 374");
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
                recordingImage.setVisibility(View.VISIBLE);
                room372.setVisibility(View.INVISIBLE);
                room373.setVisibility(View.INVISIBLE);
                room374.setVisibility(View.INVISIBLE);
                debriefing.setVisibility(View.INVISIBLE);
                message.setText("For security and monitoring: \n" +
                        "Recording in Progress. ");
                message.setVisibility(View.VISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    message.setVisibility(View.GONE);
                    recordingImage.setVisibility(View.GONE);
                    Intent intent = new Intent(Zone1.this, ConfirmMessageActivity.class);

                    intent.putExtra("deliveryType", deliveryType);
                    intent.putExtra("PatientName", patient);
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
                Robot.getInstance().speak(TtsRequest.create("Delivery suddenly canceled. Please try again. " +
                        "If you see a retry button on my screen, click on it, then reorient me, and give me a " +
                        "little push in the right direction so that I can be on my way.", false));
                break;
        }
    }
}