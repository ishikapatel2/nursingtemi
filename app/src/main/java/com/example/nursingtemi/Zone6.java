package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class Zone6 extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener {

    private Button room301;
    private Button room302;
    private TextView llab;
    private String curLoc;
    private DeliveryItem deliveryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone6);

        deliveryItem = (DeliveryItem) getIntent().getSerializableExtra("item");

        room301 = findViewById(R.id.room301);
        room302 = findViewById(R.id.room302);
        llab = findViewById(R.id.llab);

        room301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 301!",false));
                Robot.getInstance().goTo("learning lab 301");
            }
        });

        room302.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curLoc = "simulation room 366";
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 302!",false));
                Robot.getInstance().goTo("learning lab 302");
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
                room301.setVisibility(View.INVISIBLE);
                room302.setVisibility(View.INVISIBLE);
                llab.setVisibility(View.INVISIBLE);
                break;
            case "complete":
                Robot.getInstance().speak(TtsRequest.create("I have arrived with your order", false));
                Intent intent = new Intent(Zone6.this, ConfirmMessageActivity.class);
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