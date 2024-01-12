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

import java.util.Objects;

public class Zone2 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener {

    private Button room368;
    private Button room369;
    private Button room376;
    private Button room370;
    private Button room360;
    private TextView cRooms;
    private TextView mRoom;
    private TextView sRooms;
    private String curLoc;
    private DeliveryItem deliveryItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone2);

        room368 = findViewById(R.id.room368);
        room369 = findViewById(R.id.room369);
        room376 = findViewById(R.id.room376);
        room370 = findViewById(R.id.room370);
        room360 = findViewById(R.id.room360);
        cRooms = findViewById(R.id.cRooms);
        sRooms = findViewById(R.id.sRooms);
        mRoom = findViewById(R.id.mRoom);
        deliveryItem = (DeliveryItem) getIntent().getSerializableExtra("item");

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room368.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 365";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 368!",false));
                Robot.getInstance().goTo("simulation room 368");
            }
        });

        room369.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 365";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 369!",false));
                Robot.getInstance().goTo("simulation room 369");
            }
        });

        room376.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 365";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 365!",false));
                Robot.getInstance().goTo("simulation room 376");
            }
        });

        room370.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 365";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 370!",false));
                Robot.getInstance().goTo("control room 370");
            }
        });

        room360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 365";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 360!",false));
                Robot.getInstance().goTo("medication room 360");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
        Robot.getInstance().addOnGoToLocationStatusChangedListener(this);
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
            Robot.getInstance().speak(TtsRequest.create("Please select a room", false));
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":

                room368.setVisibility(View.INVISIBLE);
                room369.setVisibility(View.INVISIBLE);
                room376.setVisibility(View.INVISIBLE);
                room370.setVisibility(View.INVISIBLE);
                room360.setVisibility(View.INVISIBLE);
                sRooms.setVisibility(View.INVISIBLE);
                cRooms.setVisibility(View.INVISIBLE);
                mRoom.setVisibility(View.INVISIBLE);
                break;
            case "complete":
                Robot.getInstance().speak(TtsRequest.create("I have arrived with your order", false));
                Intent intent = new Intent(Zone2.this, ConfirmMessageActivity.class);
                intent.putExtra("previousLocation", curLoc);
                intent.putExtra("item", deliveryItem);
                startActivity(intent);
                break;
            case "abort":
                Robot.getInstance().speak(TtsRequest.create("I am experiencing problems.", false));
                break;
        }
    }




}