package com.example.nursingtemi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class DeliveryContinuationActivityNurse extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button room364;
    private Button room366;
    private Button room368;
    private Button room371;
    private Button room376;
    private Button room369;
    private TextView label;
    private String sender;
    private String quantity;
    private String item;
    private ImageView recording;
    private Position currentPosition;
    private boolean updatePosition = true;

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation_nurse);

        recording = findViewById(R.id.recording);
        sender = getIntent().getStringExtra("sender");
        item = getIntent().getStringExtra("item");
        quantity = getIntent().getStringExtra("quantity");

        label = findViewById(R.id.sRooms);

        room364 = findViewById(R.id.room364);
        room366 = findViewById(R.id.room366);
        room368 = findViewById(R.id.room368);
        room369 = findViewById(R.id.room369);
        room371 = findViewById(R.id.room371);
        room376 = findViewById(R.id.room376);

        text = findViewById(R.id.textMessage);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, DeliveryActivityNurse.class);
            startActivity(obj);
        });

        Robot.getInstance().speak(TtsRequest.create("Please indicate which room you are currently in",false));

        room364.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will now visit the pharmacy to retrieve the " + item.toString(),false));
                Toast.makeText(getApplicationContext(), "Going to retrieve your requested item", Toast.LENGTH_SHORT).show();
                Robot.getInstance().goTo("control room 365");
            }
        });

        room366.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will now visit the pharmacy to retrieve the " + item.toString(),false));
                Toast.makeText(getApplicationContext(), "Going to retrieve your requested item", Toast.LENGTH_SHORT).show();
                Robot.getInstance().goTo("control room 365");
            }
        });

        room368.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will now visit the pharmacy to retrieve the " + item.toString(),false));
                Toast.makeText(getApplicationContext(), "Going to retrieve your requested item", Toast.LENGTH_SHORT).show();
                Robot.getInstance().goTo("control room 367");
            }
        });

        room369.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will now visit the pharmacy to retrieve the " + item.toString(),false));
                Toast.makeText(getApplicationContext(), "Going to retrieve your requested item", Toast.LENGTH_SHORT).show();
                Robot.getInstance().goTo("control room 370");
            }
        });

        room371.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will now visit the pharmacy to retrieve the " + item.toString(),false));
                Toast.makeText(getApplicationContext(), "Going to retrieve your requested item", Toast.LENGTH_SHORT).show();
                Robot.getInstance().goTo("control room 370");
            }
        });

        room376.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I will now visit the pharmacy to retrieve the " + item.toString(),false));
                Toast.makeText(getApplicationContext(), "Going to retrieve your requested item", Toast.LENGTH_SHORT).show();
                Robot.getInstance().goTo("control room 375");
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
            //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
        }
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                room364.setVisibility(View.GONE);
                room366.setVisibility(View.GONE);
                room368.setVisibility(View.GONE);
                room369.setVisibility(View.GONE);
                room371.setVisibility(View.GONE);
                room376.setVisibility(View.GONE);
                label.setVisibility(View.GONE);
                recording.setVisibility(View.VISIBLE);
                text.setText("Busy at work. Please do not interrupt me. \n For security and monitoring: \n" +
                        "  Recording in Progress. ");

                recording.setVisibility(View.VISIBLE);

                updatePosition = false;

                break;
            case "complete":
                if (currentPosition != null) {
                    Intent intent = new Intent(DeliveryContinuationActivityNurse.this, ConfirmMessageActivityNurse.class);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());

                    intent.putExtra("item", item);
                    intent.putExtra("quantity", quantity);
                    intent.putExtra("sender", sender);

                    startActivity(intent);
                }
                else {
                    Log.e("Nursing Delivery", "Current position is null");
                }
                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I am having trouble reaching my destination. " +
                        "Please click 'retry' on my screen, then turn me, and give me a little push in the right direction" +
                        "to continue requesting a delivery ", false));
                break;
        }
    }

}