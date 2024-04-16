package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
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

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnRobotReadyListener {
    private static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        // tour
        Button tourButton = findViewById(R.id.tourButton);

        Drawable originalDrawable = getResources().getDrawable(R.drawable.tour_icon);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        int width = 120;
        int height = 120;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        Drawable resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        tourButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        tourButton.setOnClickListener((v) -> openActivity(TourActivity.class));

        // safety
        Button safetyButton = findViewById(R.id.safetyButton);

        originalDrawable = getResources().getDrawable(R.drawable.emergency_icon);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);

        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        safetyButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        safetyButton.setOnClickListener((v) -> openActivity(SafetyProcedures.class));

        Button patientButton = findViewById(R.id.patients);
        originalDrawable = getResources().getDrawable(R.drawable.patient);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        width = 130;
        height = 130;
        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);

        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        patientButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        patientButton.setOnClickListener((v)-> openActivity(PatientActivity.class));



        // staff
        Button staffButton = findViewById(R.id.staffButton);

        originalDrawable = getResources().getDrawable(R.drawable.staff_icon);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        staffButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);

        staffButton.setOnClickListener((v)-> openActivity(StaffActivity.class));

        // survey
        Button surveyButton = findViewById(R.id.surveyButton);

        originalDrawable = getResources().getDrawable(R.drawable.survey);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        surveyButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);
        surveyButton.setOnClickListener((v)-> openActivity(SurveyActivity.class));


        // exit
        Button exitButton = findViewById(R.id.exitButton);


        originalDrawable = getResources().getDrawable(R.drawable.exit_icon);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);
        resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        exitButton.setCompoundDrawablesWithIntrinsicBounds(resizedDrawable, null, null, null);

        exitButton.setOnClickListener((v) ->{
            finishAffinity();
            System.exit(0);
        });
    }

    public void openActivity(Class a){
        Intent obj = new Intent(this,a);
        startActivity(obj);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
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













