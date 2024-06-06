package com.example.nursingtemi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import org.w3c.dom.Text;

import java.util.Objects;

public class NotifyNurseActivity extends AppCompatActivity  implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {
    private Button room364;
    private Button room366;
    private Button room368;
    private Button room371;
    private Button room376;
    private Button room369;
    private Position currentPosition;
    private boolean updatePosition = true;
    private ImageView recording;
    private TextView label;
    private TextView textMessage;
    private ImageView backButton;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notify_nurse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);
        currentPosition = new Position(x, y, yaw, angle);

        action = getIntent().getStringExtra("Action");

        label = findViewById(R.id.label);
        textMessage = findViewById(R.id.textMessage);
        recording = findViewById(R.id.recording);

        room364 = findViewById(R.id.room364);
        room366 = findViewById(R.id.room366);
        room368 = findViewById(R.id.room368);
        room369 = findViewById(R.id.room369);
        room371 = findViewById(R.id.room371);
        room376 = findViewById(R.id.room376);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) -> {
            Intent obj = new Intent(this, PatientActivity.class);
            startActivity(obj);
        });

        Robot.getInstance().speak(TtsRequest.create("Please tap on your room number",false));

        room364.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                if (Objects.equals(action, "hungry")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
                }
                else if (Objects.equals(action, "water")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are thirsty.", false));

                }
                else if (Objects.equals(action, "restroom")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
                }
                else if (Objects.equals(action, "pain")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));

                }
                else if (Objects.equals(action, "sleepy")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you would like to sleep.", false));
                }
                Robot.getInstance().goTo("control room 365");
            }

        });

        room366.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                if (Objects.equals(action, "hungry")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
                }
                else if (Objects.equals(action, "water")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are thirsty.", false));

                }
                else if (Objects.equals(action, "restroom")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
                }
                else if (Objects.equals(action, "pain")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));

                }
                else if (Objects.equals(action, "sleepy")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you would like to sleep.", false));
                }
                Robot.getInstance().goTo("control room 365");
            }
        });

        room368.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                if (Objects.equals(action, "hungry")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
                }
                else if (Objects.equals(action, "water")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are thirsty.", false));

                }
                else if (Objects.equals(action, "restroom")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
                }
                else if (Objects.equals(action, "pain")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));

                }
                else if (Objects.equals(action, "sleepy")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you would like to sleep.", false));
                }
                Robot.getInstance().goTo("control room 367");
            }
        });

        room369.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                if (Objects.equals(action, "hungry")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
                }
                else if (Objects.equals(action, "water")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are thirsty.", false));

                }
                else if (Objects.equals(action, "restroom")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
                }
                else if (Objects.equals(action, "pain")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));

                }
                else if (Objects.equals(action, "sleepy")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you would like to sleep.", false));
                }
                Robot.getInstance().goTo("control room 370");
            }
        });

        room371.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                if (Objects.equals(action, "hungry")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
                }
                else if (Objects.equals(action, "water")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are thirsty.", false));

                }
                else if (Objects.equals(action, "restroom")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
                }
                else if (Objects.equals(action, "pain")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));

                }
                else if (Objects.equals(action, "sleepy")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you would like to sleep.", false));
                }
                Robot.getInstance().goTo("control room 370");
            }
        });

        room376.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                if (Objects.equals(action, "hungry")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
                }
                else if (Objects.equals(action, "water")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are thirsty.", false));

                }
                else if (Objects.equals(action, "restroom")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
                }
                else if (Objects.equals(action, "pain")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));

                }
                else if (Objects.equals(action, "sleepy")) {
                    Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you would like to sleep.", false));
                }
                Robot.getInstance().goTo("control room 375");
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
        Robot.getInstance().removeOnCurrentPositionChangedListener(this);
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
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                room364.setVisibility(View.GONE);
                room366.setVisibility(View.GONE);
                room368.setVisibility(View.GONE);
                room369.setVisibility(View.GONE);
                room371.setVisibility(View.GONE);
                room376.setVisibility(View.GONE);
                recording.setVisibility(View.VISIBLE);
                label.setVisibility(View.GONE);
                textMessage.setText("Busy at work. Please do not interrupt me. \n For security and monitoring: \n" +
                        "  Recording in Progress. ");

                updatePosition = false;

                break;
            case "complete":
                if (currentPosition != null) {
                    Intent intent = new Intent(NotifyNurseActivity.this, PatientAssistance.class);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    intent.putExtra("Action", action);
                    startActivity(intent);
                }
                else {
                    Log.e("Nursing Delivery", "Current position is null");
                }
                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I am having trouble reaching my destination. ", false));
                break;
        }
    }
}