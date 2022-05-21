package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button tourButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tourButton = findViewById(R.id.tourButton);
        tourButton.setOnClickListener((v) -> {
            openActivity(TourActivity.class);
        });

    }


    public void openActivity(Class a){
        Intent obj = new Intent(this,a);
        startActivity(obj);
    }
}