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

import org.w3c.dom.Text;

import java.util.Objects;

public class ConfirmMessageActivityNurse extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener
{
    private Button homeMenu;
    private Button prevLoc;
    private Button homeBase;
    private TextView message;
    private float yaw;
    private int angle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_confirm_message_nurse);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        angle = getIntent().getIntExtra("positionTiltAngle", 0);

        Position previousLocation = new Position(x, y, yaw, angle);

        message = findViewById(R.id.textMessage);
        homeMenu = findViewById(R.id.homeMenu);
        homeBase = findViewById(R.id.homeBase);
        prevLoc = findViewById(R.id.prevLoc);

        homeMenu.setVisibility(View.INVISIBLE);
        homeBase.setVisibility(View.INVISIBLE);
        prevLoc.setVisibility(View.INVISIBLE);

        Robot.getInstance().speak(TtsRequest.create("Hello. I am here to pick an order that was requested. Please read the order and place it on me before sending me back.", false));
        message.setText("Please read the following order and place it on me.\n Order Requested: ..");
        Robot.getInstance().speak(TtsRequest.create("Please click on previous location or contact the nurse before canceling the order.", false));

        homeMenu.setVisibility(View.VISIBLE);
        homeBase.setVisibility(View.VISIBLE);
        prevLoc.setVisibility(View.VISIBLE);

        homeMenu.setOnClickListener((v)->{

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            // Send robot back to the previous location
            Robot.getInstance().goToPosition(previousLocation);
            //Intent intent = new Intent(this,ConfirmMessageActivity.class);
            //startActivity(intent);
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

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                homeMenu.setVisibility(View.INVISIBLE);
                prevLoc.setVisibility(View.INVISIBLE);
                homeBase.setVisibility(View.INVISIBLE);
                message.setText("For security and monitoring: \n" +
                        "Recording in Progress. ");
                break;
            case "complete":

                message.setVisibility(View.GONE);
                Intent intent = new Intent(ConfirmMessageActivityNurse.this, ConfirmMessageActivity.class);
                intent.putExtra("positionX", prevLoc.getX());
                intent.putExtra("positionY", prevLoc.getY());
                intent.putExtra("positionYaw", yaw);
                intent.putExtra("positionTiltAngle", angle);
                startActivity(intent);

                break;
            case "abort":
                Robot.getInstance().speak(TtsRequest.create("I am experiencing problems.", false));
                break;
        }
    }
}