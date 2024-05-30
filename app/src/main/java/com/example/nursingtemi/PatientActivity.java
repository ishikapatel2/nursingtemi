package com.example.nursingtemi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

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

public class PatientActivity extends AppCompatActivity implements OnRobotReadyListener, OnCurrentPositionChangedListener {

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

        ImageView tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener((v) ->{
            playVideo();
        });

        intent = new Intent(PatientActivity.this, NotifyNurseActivity.class);

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
            intent.putExtra("Action", "hungry");
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });

        water.setOnClickListener((v) ->
        {
            updatePosition = true;
            intent.putExtra("Action", "water");
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });

        bathroom.setOnClickListener((v) ->
        {
            updatePosition = true;
            intent.putExtra("Action", "restroom");
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });

        pain.setOnClickListener((v) ->
        {
            updatePosition = true;
            intent.putExtra("Action", "pain");
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });

        sleep.setOnClickListener((v) ->
        {
            updatePosition = true;
            intent.putExtra("Action", "sleepy");
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });

        call.setOnClickListener((v) ->
        {
            intent = new Intent(this, CallActivity.class);
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });
    }

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_patient;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);




        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);


        });
    }

    @Override
    public void onCurrentPositionChanged(@NonNull Position position) {
        if (updatePosition) {
            currentPosition = position;
            //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
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
        Robot.getInstance().addOnCurrentPositionChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
        Robot.getInstance().removeOnCurrentPositionChangedListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}


