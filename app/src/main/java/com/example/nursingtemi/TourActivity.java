package com.example.nursingtemi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import org.w3c.dom.Text;

import java.util.Objects;

public class TourActivity extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {

    private int curLoc;
    private TextView description;
    private TextView locationName;
    private TextView locationText;
    private Position currentPosition;

    private final TourLocation[] locations = {
            new TourLocation("debriefing 372", "This is one of three of our " +
                    "debriefing rooms. It is a comfortable safe space to observe other " +
                    "students during simulations and discuss what they learned to apply " +
                    "to care of real patients in the future.", "Debriefing Room"),
            new TourLocation("simulation room 376", "This is one of four of our simulated " +
                    "hospital room equipped with hospital furniture, patient bedside monitor, " +
                    "simulated medical headwall, medical equipment, simulated EHR and barcode scanner, " +
                    "and video and audio media system.", "Simulation Room"),
            new TourLocation("control room 370", "Here we have one of four of our " +
                    "control rooms which is used by our simulation team to provide simulation-based " +
                    "activities.", "Control Room"),
            new TourLocation("nurses station", "The nurse's station provides " +
                    "an immersive, simulated hospital environment. It is equipped with a " +
                    "video and audio media system. It also contains supply carts for student use " +
                    "during simulations.", "Nurse's Station"),
            new TourLocation("simulation room 366", "This is our simulation room " +
                    "equipped for 2 patients. This room can be used for home health, long-term or acute care. " +
                    "It is furnished with hospital furniture, patient bedside monitor, simulated medical headwall, " +
                    "medical equipment, simulated EHR and barcode scanner, and video and audio media system.", "Simulation Room"),
            new TourLocation("simulation room 364", "This simulation room " +
                    "is also made for 2 patients. It can be used for labor and delivery simulations " +
                    "in addition to having the equipment all other simulation rooms contain.", "Simulation Room"),
            new TourLocation("lavender lounge", "This is what we call our lavender lounge. We use this " +
                    "as part of education activities and relaxation and renewal. Here, students are encouraged to use " +
                    "mindfullness and centering techniques as they approach patient care simulations. We also have a larger area " +
                    "by the elevators for the same purpose.", "Lavender Lounge"),
            new TourLocation("skills lab 334", "This is our skills lab which contains 8 " +
                    "beds equipped with hospital furniture, patient bedside monitor, simulated medical headwall, " +
                    "medical equipment, simulated EHR and barcode scanner, and video and audio media system.", "Skills Lab"),
            new TourLocation("office 328", "These are our staff offices. We also have a conference room down " +
                    "the hall to the right.", "Offices"),
            new TourLocation("learning lab 351","Here we have one of our learning labs. This lab has 6 " +
                    "outpatient clinic exam rooms surrounding a central learning lab equipped with multi-media presentation " +
                    "resources. Each exam room is furnished with an exam table and physical assessment equipment. The entire " +
                    "space includes cameras, speakers, and microphones that are part of the video and audio media system.", "Learning Lab"),
            new TourLocation("learning lab 301","Here we have two more learning labs. These spaces provide a " +
                    "flexible, interactive environment where presentations are provided on multiple large screens and live demonstrations " +
                    "along with opportunities to practice skills. Patient simulators, skills-task trainers and medical equipment is " +
                    "set up according to scheduled activities.", "Learning Lab")
    };

    private static final String HOME_BASE_LOCATION = "home base";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_tour);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);
        currentPosition = new Position(x, y, yaw, angle);

        curLoc = 0;
        description = findViewById(R.id.description);
        locationName = findViewById(R.id.locationName);
        locationText = findViewById(R.id.location);

        locationName.setVisibility(View.INVISIBLE);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });


        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener((v)->{
            if (curLoc > locations.length){
                Robot.getInstance().speak(TtsRequest.create("Thank you for touring the Gilbert Simulation Center! I will now return to home base.", false));
                Robot.getInstance().goTo(HOME_BASE_LOCATION);
            }
            else {
                Robot.getInstance().speak(TtsRequest.create("On our way to the" + locations[curLoc].getLocationName(), false));
//                Toast.makeText(this, locations[curLoc].getLocation(), Toast.LENGTH_LONG).show();
                Robot.getInstance().goTo(locations[curLoc].getLocation());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
        Robot.getInstance().addOnGoToLocationStatusChangedListener(this);
        Robot.getInstance().addOnCurrentPositionChangedListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
        Robot.getInstance().removeOnGoToLocationStatusChangedListener(this);
        Robot.getInstance().removeOnCurrentPositionChangedListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
            locationText.setVisibility(View.INVISIBLE);
            description.setText("Let's take a tour! Please click continue, then follow me around the premises.");
            description.setTextSize(90);
            Robot.getInstance().speak(TtsRequest.create("Let's take a tour! Please click continue, then follow me around the premises.", false));
        }
    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        currentPosition = position;
        //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());

    }

    @Override
    public void onGoToLocationStatusChanged(String location, @NonNull String status, int i, String s2) {

        switch (status) {
            case "going":
                locationName.setVisibility(View.INVISIBLE);
                locationText.setVisibility(View.INVISIBLE);
                description.setText("We are now on our way to " + locations[curLoc].getLocationName());
                description.setTextSize(90);
                break;
            case "complete":
                Robot.getInstance().speak(TtsRequest.create(locations[curLoc].getMessage(), false));
                locationName.setVisibility(View.VISIBLE);
                locationText.setVisibility(View.VISIBLE);
                locationName.setText(locations[curLoc].getLocationName());
                description.setText(locations[curLoc].getMessage());
                description.setTextSize(70);
                curLoc++;


                if (curLoc >= locations.length) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());

                    startActivity(intent);
                    break;
                }
                else {
                    break;
                }
            case "abort":
                Robot.getInstance().speak(TtsRequest.create("I am having trouble giving you a tour. " +
                        "Please get someone to help me.", false));
                break;
        }
    }
}