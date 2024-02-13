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

public class Zone4 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button room334;
    private TextView sRooms;

    private Position currentPosition;
    private boolean updatePosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone4);


        room334 = findViewById(R.id.room334);
        sRooms = findViewById(R.id.sRooms);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room334.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 334!",false));
                Robot.getInstance().goTo("skills lab 334");
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
            Robot.getInstance().speak(TtsRequest.create("Please select a room", false));
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                updatePosition = false;
                room334.setVisibility(View.INVISIBLE);
                sRooms.setVisibility(View.INVISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    Intent intent = new Intent(Zone4.this, ConfirmMessageActivity.class);
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
                Robot.getInstance().speak(TtsRequest.create("I am experiencing problems.", false));
                break;
        }
    }
}