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

public class Zone3 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener {

    private Button room371;
    private Button room360;
    private Button room375;
    private Button room372;
    private Button room373;
    private Button room374;
    private TextView doRoom;
    private TextView llounge;
    private TextView cRooms;
    private TextView sRooms;
    private String curLoc;
    private DeliveryItem deliveryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone3);

        deliveryItem = (DeliveryItem) getIntent().getSerializableExtra("item");

        room371 = findViewById(R.id.room371);
        room360 = findViewById(R.id.room360);
        room375 = findViewById(R.id.room375);
        room372 = findViewById(R.id.room372);
        room373 = findViewById(R.id.room373);
        room374 = findViewById(R.id.room374);
        doRoom = findViewById(R.id.doRooms);
        llounge = findViewById(R.id.llounge);
        cRooms = findViewById(R.id.cRooms);
        sRooms = findViewById(R.id.sRooms);


        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room371.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 371!",false));
                Robot.getInstance().goTo("simulation room 371");
            }
        });
        room360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 360!",false));
                Robot.getInstance().goTo("control room 370");
            }
        });

        room375.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 375!",false));
                Robot.getInstance().goTo("control room 375");
            }
        });

        room372.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 372!",false));
                Robot.getInstance().goTo("debriefing 372");
            }
        });

        room373.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 373!",false));
                Robot.getInstance().goTo("debriefing 373");
            }
        });

        room374.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 374!",false));
                Robot.getInstance().goTo("debriefing 374");
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
                room371.setVisibility(View.INVISIBLE);
                room360.setVisibility(View.INVISIBLE);
                room375.setVisibility(View.INVISIBLE);
                room372.setVisibility(View.INVISIBLE);
                room373.setVisibility(View.INVISIBLE);
                room374.setVisibility(View.INVISIBLE);
                doRoom.setVisibility(View.INVISIBLE);
                llounge.setVisibility(View.INVISIBLE);
                sRooms.setVisibility(View.INVISIBLE);
                cRooms.setVisibility(View.INVISIBLE);
                break;
            case "complete":
                Robot.getInstance().speak(TtsRequest.create("I have arrived with your order", false));
                Intent intent = new Intent(Zone3.this, ConfirmMessageActivity.class);
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