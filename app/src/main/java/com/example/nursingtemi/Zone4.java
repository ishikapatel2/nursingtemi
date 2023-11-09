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

public class Zone4 extends AppCompatActivity {

    private Button room334;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone4);

        room334 = findViewById(R.id.room334);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room334.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 334!",false));
                // fix name
                Robot.getInstance().goTo("skills lab 334");
            }
        });

    }
}