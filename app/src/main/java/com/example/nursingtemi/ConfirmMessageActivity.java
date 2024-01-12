package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class ConfirmMessageActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_confirm_message);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        Position previousLocation = new Position(x, y, 999, 0); // Set yaw to 999, tiltAngle is ignored

        //Position previousLocation = getIntent("previousLocation");

        TextView message = findViewById(R.id.textMessage);
        Button homeMenu = findViewById(R.id.homeMenu);
        Button homeBase = findViewById(R.id.homeBase);
        Button prevLoc = findViewById(R.id.prevLoc);

        Robot.getInstance().speak(TtsRequest.create("Knock knock, Temi here", false));
        homeMenu.setOnClickListener((v)->{

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            // Send robot back to the previous location

            Robot.getInstance().goToPosition(previousLocation);
        });

        homeBase.setOnClickListener(v -> {
            // Send robot to its home base
            Robot.getInstance().goTo("home base");
        });

    }
}