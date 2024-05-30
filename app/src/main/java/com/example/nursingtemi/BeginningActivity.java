package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class BeginningActivity extends AppCompatActivity implements OnRobotReadyListener, OnCurrentPositionChangedListener {

    private Position currentPosition = null;
    ImageView tutorial;
    ImageView logo;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_beginning);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        logo = findViewById(R.id.logo);

        startButton = findViewById(R.id.startButton);

        tutorial = findViewById(R.id.tutorial);

        tutorial.setOnClickListener((v) ->{
            startButton.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.INVISIBLE);
            playVideo();
        });



        startButton.setOnClickListener((v) ->{
            progressBar.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loadActivity(MainActivity.class);
        });
    }

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_beginning;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);

        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
            logo.setVisibility(View.VISIBLE);
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