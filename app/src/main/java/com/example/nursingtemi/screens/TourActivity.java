package com.example.nursingtemi.screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nursingtemi.R;
import com.example.nursingtemi.classes.TourLocation;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import java.util.Objects;

public class TourActivity extends AppCompatActivity implements OnRobotReadyListener {

    //Attributes
    private static int curLoc;
    private  TourLocation[] locations
    = {

            new TourLocation("VR Station", "vr station","Space for 2 patient simulators. Can be used for home health, long-term or acute care. Equipped with hospital furniture, patient bed-side monitor, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
            new TourLocation("Graduate Student Station", "graduate student station","6 outpatient clinic exam rooms surrounding a central learning lab equipped with multi-media presentation resources. Each exam room is furnished with an exam table and physical assessment equipment. The entire space includes cameras, speakers and microphones that are part of the video/audio media system."),
            new TourLocation("Dr Carter's Desk", "dr carter's desk","Flexible, interactive environment where presentations are provided on multiple large screens and live demonstrations along with opportunities to practice skills. Patient simulators, skills-task trainers and medical equipment is set-up according to scheduled activities."),


            // soon, make a table on SQLite that keeps track of the rooms

            /*
            new TourLocation("Control room 365", "control room 365",
                    "Used by the Simulation Team to provide simulation-based activities."),
            new TourLocation("Simulation room 366", "simulation room 366",
                    "Space for 2 patient simulators. Can be used for home health, long-term or acute care. Equipped with hospital furniture, patient bed-side monitor, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
            new TourLocation("Simulation room 368", "simulation room 368",
                    "Simulated hospital room equipped with hospital furniture, patient bed-side monitor, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
            new TourLocation("Simulation room 369", "simulation room 369"
            ,"Simulated hospital room equipped with hospital furniture, patient bed-side monitor, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
            new TourLocation("Control Room 370", "control room 370",
                    "Used by the Simulation Team to provide simulation-based activities."),
            new TourLocation("Simulation Room 371", "simulation room 371",
                    "Simulated hospital room equipped with hospital furniture, patient bed-side monitor, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
            new TourLocation("Debriefing 372", "debriefing 372",
                    "Comfortable safe space to observe other students during simulations and to discuss what was learned to apply to care of actual patients in the future."),
            new TourLocation("Debriefing 373", "debriefing 373",
                    "Comfortable safe space to observe other students during simulations and to discuss what was learned to apply to care of actual patients in the future."),
            new TourLocation("Debriefing 374", "debriefing 374",
                    "Comfortable safe space to observe other students during simulations and to discuss what was learned to apply to care of actual patients in the future."),
            new TourLocation("Control Room 375", "control room 375",
                    "Used by the Simulation Team to provide simulation-based activities."),
            new TourLocation("Simulation Room 376", "simulation room 376",
                    "Simulated hospital room equipped with hospital furniture, patient bed-side monitor, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
            new TourLocation("Learning Lab 301", "learning lab 301",
                    "Flexible, interactive environment where presentations are provided on multiple large screens and live demonstrations along with opportunities to practice skills. Patient simulators, skills-task trainers and medical equipment is set-up according to scheduled activities."),
            new TourLocation("Learning Lab 302", "learning lab 302",
                    "Flexible, interactive environment where presentations are provided on multiple large screens and live demonstrations along with opportunities to practice skills. Patient simulators, skills-task trainers and medical equipment is set-up according to scheduled activities."),
            new TourLocation("Interactive lab 351","interactive lab 351",
                    "6 outpatient clinic exam rooms surrounding a central learning lab equipped with multi-media presentation resources. Each exam room is furnished with an exam table and physical assessment equipment. The entire space includes cameras, speakers and microphones that are part of the video/audio media system."),
            new TourLocation("Offices", "offices", "Display Text"),
            new TourLocation("Skills lab 334", "skills lab 334","8 bed skills lab equipped with hospital furniture, simulated medical headwall, medical equipment, simulated EHR and bar-code scanner, and video/audio media system."),
                */
                 };

    private static final String HOME_BASE_LOCATION = "home base";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_tour);

        TextView text = findViewById(R.id.description);
        TextView locationText = findViewById(R.id.locationText);
        Button continueButton = findViewById(R.id.continueButton);
        ImageView backButton = findViewById(R.id.backButton);

        locationText.setText("Location: " + locations[curLoc].getTitle());
        text.setText(locations[curLoc].getMessage());


        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        continueButton.setOnClickListener((v)->{
            if (curLoc == locations.length){
                Robot.getInstance().speak(TtsRequest.create("Thank you for participating in the tour of the hospital. Returning to home base", false));
                Robot.getInstance().goTo(HOME_BASE_LOCATION);
            }
            else {
                Toast.makeText(this, locations[curLoc].getLocation(), Toast.LENGTH_LONG).show();
                Robot.getInstance().goTo(locations[curLoc].getLocation());
                locationText.setText("Location: " + locations[curLoc].getTitle());
                text.setText(locations[curLoc].getMessage());
                curLoc++;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {

        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Please follow me around the premiscense.", false));
            Robot.getInstance().goTo(locations[curLoc].getLocation());
           curLoc++;
        }
    }

}