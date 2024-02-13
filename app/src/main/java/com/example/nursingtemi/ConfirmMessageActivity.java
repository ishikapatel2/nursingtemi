package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class ConfirmMessageActivity extends AppCompatActivity implements OnRobotReadyListener
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
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);

        Position previousLocation = new Position(x, y, yaw, angle);

        ImageView fingerprintScanner = findViewById(R.id.fingerprintScanner);
        TextView message = findViewById(R.id.textMessage);
        Button homeMenu = findViewById(R.id.homeMenu);
        Button homeBase = findViewById(R.id.homeBase);
        Button prevLoc = findViewById(R.id.prevLoc);

        homeMenu.setVisibility(View.INVISIBLE);
        homeBase.setVisibility(View.INVISIBLE);
        prevLoc.setVisibility(View.INVISIBLE);


        Robot.getInstance().speak(TtsRequest.create("Knock knock, Temi here. Your order of is here.", false));
        message.setText("Please confirm this order by scanning your finger");
        message.setVisibility(View.VISIBLE);
        Robot.getInstance().speak(TtsRequest.create("Please confirm this order by scanning your finger", false));

        fingerprintScanner.setOnLongClickListener(view -> {
            Robot.getInstance().speak(TtsRequest.create("Order confirmed. You may continue.", false));

            message.setText("Please continue using the following options:");
            homeMenu.setVisibility(View.VISIBLE);
            homeBase.setVisibility(View.VISIBLE);
            prevLoc.setVisibility(View.VISIBLE);
            fingerprintScanner.setVisibility(View.GONE);
            return true;
        });

        homeMenu.setOnClickListener((v)->{

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            // Send robot back to the previous location
            Robot.getInstance().goToPosition(previousLocation);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        homeBase.setOnClickListener(v -> {
            // Send robot to its home base
            Robot.getInstance().goTo("home base");
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
            Robot.getInstance().speak(TtsRequest.create("Please confirm this order by scanning your finger", false));
        }
    }
}