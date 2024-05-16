package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnRobotReadyListener, OnCurrentPositionChangedListener {
    private static int count = 0;
    private Position currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);
        currentPosition = new Position(x, y, yaw, angle);

        // tour
        Button tourButton = findViewById(R.id.tourButton);


        Drawable originalDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.tour_icon);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        int width = 120;
        int height = 120;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        Drawable resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        tourButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        tourButton.setOnClickListener((v) -> openActivity(TourActivity.class));

        // safety
        Button safetyButton = findViewById(R.id.safetyButton);

        originalDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.emergency_icon);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);

        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        safetyButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        safetyButton.setOnClickListener((v) -> openActivity(SafetyProcedures.class));

        Button patientButton = findViewById(R.id.patients);
        originalDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.patient);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        width = 130;
        height = 130;
        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);

        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        patientButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        patientButton.setOnClickListener((v)-> openActivity(PatientActivity.class));



        // staff
        Button staffButton = findViewById(R.id.staffButton);

        originalDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.staff_icon);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        staffButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);

        staffButton.setOnClickListener((v)-> openActivity(StaffActivity.class));

        // survey
        Button surveyButton = findViewById(R.id.surveyButton);

        originalDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.survey);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        surveyButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        surveyButton.setOnClickListener((v)-> openActivity(SurveyActivity.class));


        // exit
        Button exitButton = findViewById(R.id.exitButton);


        originalDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.exit_icon);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        exitButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);

        exitButton.setOnClickListener((v) ->{
            finishAffinity();
            System.exit(0);
        });
    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        currentPosition = position;
        //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());

    }

    public void openActivity(Class a){
        Intent intent = new Intent(this,a);
        intent.putExtra("positionX", currentPosition.getX());
        intent.putExtra("positionY", currentPosition.getY());
        intent.putExtra("positionYaw", currentPosition.getYaw());
        intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
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
    protected void onResume() {
        super.onResume();
        if (count == 0) {
            TtsRequest ttsRequest = TtsRequest.create("How may I assist you today?", false);
            Robot.getInstance().speak(ttsRequest);
            count++;
        }

    }

    @Override
    public void onRobotReady(boolean b) {

    }
}













