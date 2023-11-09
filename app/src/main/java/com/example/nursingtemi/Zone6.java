package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

import java.util.Objects;

public class Zone6 extends AppCompatActivity {

    private Button room301;
    private Button room302;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone6);

        room301 = findViewById(R.id.room301);
        room302 = findViewById(R.id.room302);


        room301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 301!",false));
                // fix name
                Robot.getInstance().goTo("learning lab 301");
            }
        });

        room302.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 302!",false));
                // fix name
                Robot.getInstance().goTo("learning lab 302");
            }
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });
    }
}