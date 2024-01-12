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

    private Button room366;
    private Button room364;
    private Button room363;
    private Button room367;
    private Button room365;
    private TextView statusTextView;
    private TextView simRoomTextView;
    private TextView controlRTextVIew;
    private TextView simORooms;
    private DeliveryItem deliveryItem;

    private Position currentPosition;
    private boolean updatePosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone1);

        deliveryItem = (DeliveryItem) getIntent().getSerializableExtra("item");

        room366 = findViewById(R.id.room366);
        room364 = findViewById(R.id.room364);
        room363 = findViewById(R.id.room363);
        room367 = findViewById(R.id.room367);
        room365 = findViewById(R.id.room365);
        simRoomTextView = findViewById(R.id.simRooms);
        controlRTextVIew = findViewById(R.id.cRooms);
        simORooms = findViewById(R.id.simORooms);
        statusTextView = findViewById(R.id.statusTextView);

        room366.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 366!",false));
                Robot.getInstance().goTo("simulation room 366");
            }
        });

        room364.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = false;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 364!",false));
                Robot.getInstance().goTo("simulation room 364");
            }
        });

        room363.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = false;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 363!",false));
                Robot.getInstance().goTo("simulation office 363");
            }
        });
        room367.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = false;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 367!",false));
                Robot.getInstance().goTo("simulation room 367");
            }
        });

        room365.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = false;
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 365!",false));
                Robot.getInstance().goTo("control room 365");
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
        Robot.getInstance().removeOnCurrentPositionChangedListener(this);
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
                room366.setVisibility(View.INVISIBLE);
                room364.setVisibility(View.INVISIBLE);
                room363.setVisibility(View.INVISIBLE);
                room367.setVisibility(View.INVISIBLE);
                room365.setVisibility(View.INVISIBLE);
                simRoomTextView.setVisibility(View.INVISIBLE);
                controlRTextVIew.setVisibility(View.INVISIBLE);
                simORooms.setVisibility(View.INVISIBLE);
                break;
            case "complete":
                //Robot.getInstance().speak(TtsRequest.create("I have arrived with your order", false));

                Intent intent = new Intent(Zone1.this, ConfirmMessageActivity.class);
                intent.putExtra("positionX", currentPosition.getX());
                intent.putExtra("positionY", currentPosition.getY());
                intent.putExtra("positionYaw", currentPosition.getYaw());
                intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                intent.putExtra("item", deliveryItem); // CHECK THIS
                startActivity(intent);
                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I am experiencing problems.", false));
                break;
        }
    }
}