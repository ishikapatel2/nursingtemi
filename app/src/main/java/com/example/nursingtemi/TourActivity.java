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

            //new TourLocation("VR Station", "vr station"),
            //new TourLocation("Graduate Student Station", "grad desk"),
            //new TourLocation("Dr. Carter's Desk", "carter desk"),

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




            /*
            new TourLocation("Corridor C361", "corridor C361"),
            new TourLocation("Corridor C301", "corridor C301"),
            new TourLocation("Corridor C300G", "corridor C300G"),
            new TourLocation("Corridor C329", "corridor C329"),

            new TourLocation("Conference Room 321", "conf room 321"),
            new TourLocation("Open Study 303", "open study 303"),
            new TourLocation("Open Study 335", "open study 335"),
            new TourLocation("Exam Rooms", "exam rooms"),
            new TourLocation("Offices", "offices"),
            new TourLocation("Sim Office", "sim office"),

            Skills lab 334
            Interactive lab 351
            Offices
            Conferrence room 321
            Learning lab 301
            Learning lab 302
            Debriefing 374
            Debriefing 373
            Debriefing 372
            Control room 375
            Simulation room 376
            Simulation 371

            new TourLocation("Debriefing 373", "debriefing 373"),
            new TourLocation("Debriefing 374", "debriefing 374"),
            new TourLocation("Skills lab 334", "skills lab 334"),
            new TourLocation("Simulation room 366", "simulation room 366"),
            new TourLocation("Simulation room 368", "simulation room 368"),
            new TourLocation("Simulation room 369", "simulation room 369"),
            new TourLocation("Simulation Room 371", "simulation room 371"),

            new TourLocation("Control room 365", "control room 365"),
            new TourLocation("Control room 367", "control room 367"),
            new TourLocation("Control Room 370", "ctrl room 370"),
            c

            new TourLocation("Corridor C361", "corridor C361"),
            new TourLocation("Corridor C301", "corridor C301"),
            new TourLocation("Corridor C300G", "corridor C300G"),
            new TourLocation("Corridor C329", "corridor C329"),

            new TourLocation("Conference Room 321", "conf room 321"),
            new TourLocation("Open Study 303", "open study 303"),
            new TourLocation("Open Study 335", "open study 335"),
            new TourLocation("Exam Rooms", "exam rooms"),
            new TourLocation("Offices", "offices"),
            new TourLocation("Sim Office", "sim office"),
           // new TourLocation("Stair 2", "stair 2"),
         //   new TourLocation("Open Interaction", "open interaction"),
         //   new TourLocation("Restrooms", "restrooms"),
         //   new TourLocation("Elevators", "elevators"),
            new TourLocation("Medication Room 360", "medication room"),
            new TourLocation("Nurses Station 361", "nurses station"),

            new TourLocation("Workroom 329", "workroom 329"),
            new TourLocation("Workroom 330", "workroom 330"),
            new TourLocation("Compressor", "compressor"),
            new TourLocation("Storage 352", "storage 352"),
            new TourLocation("Storage 311", "storage 311"),
            new TourLocation("Storage 310", "storage 310"),
            new TourLocation("Janitor/Storage 315", "janitor/storage"),
            new TourLocation("Learning Lab 302", "learning lab 302"),
            new TourLocation("Learning Lab 301", "learning lab 301"),
            new TourLocation("Lavender Lounge 333", "lavender lounge"),
            new TourLocation("Learning Lounge 304", "learning lounge"),

            Skills lab 334
            Interactive lab 351
            Offices
            Conferrence room 321
            Learning lab 301
            Learning lab 302
            Debriefing 374
            Debriefing 373
            Debriefing 372
            Control room 375
            Simulation room 376
            Simulation 371
*/
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