package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

import java.util.Objects;

public class Zone3 extends AppCompatActivity {

    private Button room371;
    private Button room360;
    private Button room375;
    private Button room372;
    private Button room373;
    private Button room374;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone3);

        room371 = findViewById(R.id.room371);
        room360 = findViewById(R.id.room360);
        room375 = findViewById(R.id.room375);
        room372 = findViewById(R.id.room372);
        room373 = findViewById(R.id.room373);
        room374 = findViewById(R.id.room374);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, DeliveryContinuationActivity.class);
            startActivity(obj);
        });

        room371.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 371!",false));
                Robot.getInstance().goTo("simulation room 371");
            }
        });
        room360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 360!",false));
                // fix name
                Robot.getInstance().goTo("control room 370");
            }
        });

        room375.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 375!",false));
                Robot.getInstance().goTo("control room 375");
            }
        });

        room372.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 372!",false));
                Robot.getInstance().goTo("debriefing 372");
            }
        });

        room373.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 373!",false));
                Robot.getInstance().goTo("debriefing 373");
            }
        });

        room374.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Robot.getInstance().speak(TtsRequest.create("Alrighty. I am about to deliver your resource right now to room 374!",false));
                Robot.getInstance().goTo("debriefing 374");
            }
        });
    }
}