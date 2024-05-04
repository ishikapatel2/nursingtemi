package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import org.w3c.dom.Text;

import java.util.Objects;

public class PatientActivity extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private Button food;
    private Button water;
    private Button bathroom;
    private Button pain;
    private Button sleep;
    private Button call;
    private TextView caption;
    private Intent intent;
    private Position currentPosition;
    private boolean updatePosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);
        currentPosition = new Position(x, y, yaw, angle);

        intent = new Intent(PatientActivity.this, PatientAssistance.class);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        food = findViewById(R.id.food);
        water = findViewById(R.id.water);
        bathroom = findViewById(R.id.bathroom);
        pain = findViewById(R.id.pain);
        sleep = findViewById(R.id.sleep);
        call = findViewById(R.id.call);
        caption = findViewById(R.id.caption);
        updatePosition = true;

        food.setOnClickListener((v) ->
        {
            updatePosition = true;
            Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are hungry.", false));
            intent.putExtra("Action", "hungry");
            Robot.getInstance().goTo("nurses station");

        });

        water.setOnClickListener((v) ->
        {
            updatePosition = true;
            Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like water.", false));
            intent.putExtra("Action", "water");
            Robot.getInstance().goTo("nurses station");

        });

        bathroom.setOnClickListener((v) ->
        {
            updatePosition = true;
            Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know you would like to use the restroom.", false));
            intent.putExtra("Action", "restroom");
            Robot.getInstance().goTo("nurses station");
        });

        pain.setOnClickListener((v) ->
        {
            updatePosition = true;
            Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are in pain.", false));
            intent.putExtra("Action", "pain");
            Robot.getInstance().goTo("nurses station");
        });

        sleep.setOnClickListener((v) ->
        {
            updatePosition = true;
            Robot.getInstance().speak(TtsRequest.create("I will go find your nurse and let her know that you are sleepy.", false));
            intent.putExtra("Action", "sleepy");
            Robot.getInstance().goTo("nurses station");
        });

        call.setOnClickListener((v) ->
        {
            intent = new Intent(this, CallActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String status, int i, String s2) {
        switch (status) {
            case "going":
                updatePosition = false;
                caption.setVisibility(View.INVISIBLE);
                food.setVisibility(View.INVISIBLE);
                water.setVisibility(View.INVISIBLE);
                bathroom.setVisibility(View.INVISIBLE);
                pain.setVisibility(View.INVISIBLE);
                sleep.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                caption.setText("I am on my way to deliver a message ");
                caption.setVisibility(View.VISIBLE);
                break;
            case "complete":
                if (currentPosition != null) {
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    startActivity(intent);
                }
                else {
                    Log.e("PatientActivity", "Current position is null");
                    Robot.getInstance().speak(TtsRequest.create("Your request cannot be completed. Please try tapping on my head to move me around, then try again. ", false));

                }

                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("Your request could not be completed. Please try again.", false));
                break;
        }

    }

    @Override
    public void onCurrentPositionChanged(@NonNull Position position) {
        if (updatePosition) {
            currentPosition = position;
            Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
        }
    }

    @Override
    public void onRobotReady(boolean b) {
        if (b) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
        }
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
}


