package com.example.nursingtemi;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import java.util.Objects;

public class TourActivity extends AppCompatActivity implements OnRobotReadyListener {

    //Attributes
    private static int curLoc;
    private final TourLocation[] locations = {


            // soon, make a table on SQLite that keeps track of the rooms
            new TourLocation("Control room 365", "control room 365"),
            new TourLocation("Simulation room 366", "simulation room 366"),
            new TourLocation("Simulation room 368", "simulation room 368"),
            new TourLocation("Simulation room 369", "simulation room 369"),
            new TourLocation("Control Room 370", "control room 370"),
            new TourLocation("Simulation Room 371", "simulation room 371"),
            new TourLocation("Debriefing 372", "debriefing 372"),
            new TourLocation("Debriefing 373", "debriefing 373"),
            new TourLocation("Debriefing 374", "debriefing 374"),
            new TourLocation("Control Room 375", "control room 375"),
            new TourLocation("Simulation Room 376", "simulation room 376"),
            new TourLocation("Learning Lab 301", "learning lab 301"),
            new TourLocation("Learning Lab 302", "learning lab 302"),
            new TourLocation("Interactive lab 351","interactive lab 351"),
            new TourLocation("Offices", "offices"),
            new TourLocation("Skills lab 334", "skills lab 334"),

    };
    private static final String HOME_BASE_LOCATION = "home base";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_tour);

        Button continueButton = findViewById(R.id.continueButton);
        TextView text = findViewById(R.id.description);
        ImageView backButton = findViewById(R.id.backButton);

        text.setText(locations[curLoc].getLocation());

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