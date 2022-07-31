package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryContinuationActivity extends AppCompatActivity implements OnRobotReadyListener {

    private final TourLocation[] locationList = {
            new TourLocation("VR Station", "vr station"),
            new TourLocation("Graduate Student Station", "grad desk"),
            new TourLocation("Dr. Carter's Desk", "carter desk")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation);
        ListView locations = findViewById(R.id.listView);
        List<String> locationLists = new ArrayList<>();

        for (TourLocation tourLocation : locationList) {
            locationLists.add(tourLocation.getTitle());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,locationLists);
        locations.setAdapter(arrayAdapter);

        locations.setOnItemClickListener((adapterView, view, i, l) -> {
            Robot.getInstance().setVolume(3);
            Robot.getInstance().goTo(locationList[i].getLocation());
            Robot.getInstance().speak(TtsRequest.create("Made it to the delivery destination.", false));
        });
        Robot.getInstance().setVolume(3);
        Robot.getInstance().speak(TtsRequest.create("Where would you like for me to go", false));
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
            Robot.getInstance().speak(TtsRequest.create("Where would you like for me to go", false));
        }
    }

}