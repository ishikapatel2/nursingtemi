package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class BeginningActivity extends AppCompatActivity implements OnRobotReadyListener, OnCurrentPositionChangedListener {

    private Position currentPosition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_beginning);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener((v) ->{
            progressBar.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            loadActivity(MainActivity.class);
            loadActivity(MapActivity.class);
        });
    }

   public void loadActivity(Class a){
       Intent intent = new Intent(this,a);
       if (currentPosition == null) {
           intent.putExtra("positionY", 0.0f);
           intent.putExtra("positionYaw", 0.0f);
           intent.putExtra("positionTiltAngle", 0.0f);
           intent.putExtra("positionTiltAngle", 0);      }
       else {
           intent.putExtra("positionX", currentPosition.getX());
           intent.putExtra("positionY", currentPosition.getY());
           intent.putExtra("positionYaw", currentPosition.getYaw());
           intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
       }
       startActivity(intent);
   }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
        Robot.getInstance().addOnCurrentPositionChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
        Robot.getInstance().removeOnCurrentPositionChangedListener(this);
    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        currentPosition = position;
        //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
        }
    }

}