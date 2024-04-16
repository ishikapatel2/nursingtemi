package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.model.Position;

import org.w3c.dom.Text;

public class PatientAssistance extends AppCompatActivity implements OnRobotReadyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_assistance);

        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);

        String action = getIntent().getStringExtra("Action");
        Position previousLocation = new Position(x, y, yaw, angle);

        Button homeMenu = findViewById(R.id.homeMenu);
        Button prevLoc = findViewById(R.id.prevLoc);
        TextView message = findViewById(R.id.message);

        if (action.compareTo("hungry") == 0) {
            Robot.getInstance().speak(TtsRequest.create("Hello, your patient is hungry and would like something to eat. I can take you to your patient when you're ready.", false));
            message.setText("Your patient is hungry.");
        }
        else if (action.compareTo("water") == 0) {
            Robot.getInstance().speak(TtsRequest.create("Hello, your patient is thirsty. I can take you to your patient when you're ready.", false));
            message.setText("Your patient is thirsty.");
        }
        else if (action.compareTo("restroom") == 0) {
            Robot.getInstance().speak(TtsRequest.create("Hello, your patient needs assistance going to the restroom. I can take you to your patient when you're ready.", false));
            message.setText("Your patient needs assistance using to restroom.");
        }
        else if (action.compareTo("pain") == 0) {
            Robot.getInstance().speak(TtsRequest.create("Hello, your patient is in pain. I can take you to your patient when you're ready.", false));
            message.setText("Your patient is in pain .");
        }
        else if (action.compareTo("sleepy") == 0) {
            Robot.getInstance().speak(TtsRequest.create("Hello, your patient is tired and would like to go to bed. I can take you to your patient when you're ready.", false));
            message.setText("Your patient would like to sleep.");
        }

        homeMenu.setOnClickListener((v)->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            Robot.getInstance().speak(TtsRequest.create("Follow me!", false));

            Robot.getInstance().goToPosition(previousLocation);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
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
        }
    }
}

