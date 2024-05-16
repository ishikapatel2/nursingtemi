package com.example.nursingtemi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ConfirmMessageActivity extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener
{

    private EditText name;
    private Button confirm;
    private String patient;
    private String deliveryType;

    private ImageView recording;
    private TextView goMessage;

    private Position currentPosition;
    private boolean updatePosition = true;

    private boolean correctOrder = true;

    private TextView message;
    private Button homeMenu;
    private Button homeBase;
    private Button prevLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_confirm_message);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);

        deliveryType = getIntent().getStringExtra("deliveryType");
        patient = getIntent().getStringExtra("PatientName");

        Position previousLocation = new Position(x, y, yaw, angle);
        currentPosition = previousLocation;

        ImageView fingerprintScanner = findViewById(R.id.fingerprintScanner);
        message = findViewById(R.id.textMessage);
        homeMenu = findViewById(R.id.homeMenu);
        homeBase = findViewById(R.id.homeBase);
        prevLoc = findViewById(R.id.prevLoc);

        recording = findViewById(R.id.recording);
        goMessage = findViewById(R.id.goMessage);

        confirm = findViewById(R.id.confirm_button);

        name = findViewById(R.id.name);

        homeMenu.setVisibility(View.INVISIBLE);
        homeBase.setVisibility(View.INVISIBLE);
        prevLoc.setVisibility(View.INVISIBLE);


        if ("Food".equals(deliveryType)) {
            fingerprintScanner.setVisibility(View.GONE);
            message.setText("Please confirm you received your food by entering your full name below.");
            Robot.getInstance().speak(TtsRequest.create("Please confirm you received your food by entering your name below. Select confirm to continue.", false));
            confirm.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);


        }
        else {
            message.setText("Please confirm this order by scanning your finger");
            Robot.getInstance().speak(TtsRequest.create("Knock knock, this is Temi. I have arrived with the order you requested." +
                    "Please confirm this order by scanning your finger, then continue.", false));
        }

        message.setVisibility(View.VISIBLE);

        fingerprintScanner.setOnLongClickListener(view -> {
            Robot.getInstance().speak(TtsRequest.create("Order confirmed. You may continue.", false));

            message.setText("Please continue using the following options:");
            homeMenu.setVisibility(View.VISIBLE);
            homeBase.setVisibility(View.VISIBLE);
            prevLoc.setVisibility(View.VISIBLE);
            fingerprintScanner.setVisibility(View.GONE);
            return true;
        });

        confirm.setOnClickListener((v)-> {
            String text = name.getText().toString();
            if (!emptyCredentials(name) && text.equals(patient)) {
                confirm.setVisibility(View.INVISIBLE);
                Robot.getInstance().speak(TtsRequest.create("Thank you. If you no longer need any more assistance, " +
                        "please send me back to the food court. Enjoy your meal! ", false));
                message.setText("Please click on one of the following options to send me back.");
                homeMenu.setVisibility(View.VISIBLE);
                homeBase.setVisibility(View.VISIBLE);
                prevLoc.setVisibility(View.VISIBLE);

            }
            if (!text.equals(patient)) {
                correctOrder = false;
                Robot.getInstance().speak(TtsRequest.create("This does not appear to be your order. If you entered your name incorrectly, try again. Otherwise," +
                        "return me to your nurse so they can fix your order", false));
                prevLoc.setVisibility(View.VISIBLE);
            }
        });

        homeMenu.setOnClickListener((v)->{

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            Robot.getInstance().goToPosition(previousLocation);
        });

        homeBase.setOnClickListener(v -> {
            // Send robot to its home base
            Robot.getInstance().goTo("home base");
        });
    }

    private boolean emptyCredentials(EditText patientName) {
        if  (patientName.getText().toString().isEmpty()) {
            patientName.setError("Please fill this field");
            return true;
        }
        return false;
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
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
        }
    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        if (updatePosition) {
            currentPosition = position;
            //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
        }
    }


    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                if (correctOrder == true) {
                    updatePosition = true;
                }
                else {
                    updatePosition = false;
                }
                recording.setVisibility(View.GONE);
                message.setVisibility(View.INVISIBLE);
                homeMenu.setVisibility(View.INVISIBLE);
                homeBase.setVisibility(View.INVISIBLE);
                prevLoc.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);
                confirm.setVisibility(View.INVISIBLE);
                message.setVisibility(View.INVISIBLE);
                recording.setVisibility(View.VISIBLE);
                goMessage.setText("For security and monitoring: \n" +
                        "Recording in Progress. ");
                goMessage.setVisibility(View.VISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    Intent intent;
                    if (correctOrder != true) {
                        Robot.getInstance().speak(TtsRequest.create("Hello. Your patient, " + patient + "received the wrong order." +
                                "Please fix the order and make another delivery.", false));
                    }
                    else {
                        Robot.getInstance().speak(TtsRequest.create("Hello! I am back! ", false));
                    }
                    intent = new Intent(this,MainActivity.class);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    startActivity(intent);
                }
                else {
                    Log.e("ConfirmMessageActivity", "Current position is null");
                }
                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I am experiencing difficulty completing the task.", false));
                break;
        }
    }
}