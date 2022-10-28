package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryContinuationActivity extends AppCompatActivity implements OnRobotReadyListener {

    private final TourLocation[] locationList = {
            new TourLocation("Control room 365", "control room 365"),
            new TourLocation("Control Room 370", "control room 370"),
            new TourLocation("Control Room 375", "control room 375"),
            new TourLocation("Debriefing 372", "debriefing 372"),
            new TourLocation("Debriefing 373", "debriefing 373"),
            new TourLocation("Debriefing 374", "debriefing 374"),
            new TourLocation("Interactive lab 351","interactive lab 351"),
            new TourLocation("Learning Lab 301", "learning lab 301"),
            new TourLocation("Learning Lab 302", "learning lab 302"),
            new TourLocation("Offices", "offices"),
            new TourLocation("Simulation room 366", "simulation room 366"),
            new TourLocation("Simulation room 368", "simulation room 368"),
            new TourLocation("Simulation room 369", "simulation room 369"),
            new TourLocation("Simulation Room 371", "simulation room 371"),
            new TourLocation("Simulation Room 376", "simulation room 376"),
            new TourLocation("Skills lab 334", "skills lab 334"),
    };

    private ImageView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation);

        Button zone1 = findViewById(R.id.zone1);
        mapView = findViewById(R.id.mapView);
        zone1.setOnClickListener((v) -> {
            mapView.setImageDrawable(getResources().getDrawable(R.drawable.zone1));
        });

        Button zone2 = findViewById(R.id.zone2);
        zone2.setOnClickListener((v) -> {
            mapView.setImageDrawable(getResources().getDrawable(R.drawable.zone2));
        });

        Button zone3 = findViewById(R.id.zone3);
        zone3.setOnClickListener((v) -> {
            mapView.setImageDrawable(getResources().getDrawable(R.drawable.zone3));
        });

        Button zone4 = findViewById(R.id.zone4);
        zone4.setOnClickListener((v) -> {
            mapView.setImageDrawable(getResources().getDrawable(R.drawable.zone4));
        });

        Button zone5 = findViewById(R.id.zone5);
        zone5.setOnClickListener((v) -> {
            mapView.setImageDrawable(getResources().getDrawable(R.drawable.zone5));
        });

        Button zone6 = findViewById(R.id.zone6);
        zone6.setOnClickListener((v) -> {
            mapView.setImageDrawable(getResources().getDrawable(R.drawable.zone6));
        });






        /*
        ListView locations = findViewById(R.id.listView);
        DeliveryItem item = (DeliveryItem) getIntent().getSerializableExtra("item");
        List<String> locationLists = new ArrayList<>();
        Log.d("Starting to add", "tour locations");

        for (TourLocation tourLocation : locationList) {
            locationLists.add(tourLocation.getTitle());
            Log.d("Added tour location", tourLocation.getTitle());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,locationLists);
        Log.d("item count", String.valueOf(arrayAdapter.getCount()));
        Log.d("array items", arrayAdapter.toString());
        locations.setAdapter(arrayAdapter);

        Robot.getInstance( ).setVolume(3);
        Robot.getInstance().speak(TtsRequest.create("Where would you like for me to go", false));

        locations.setOnItemClickListener((adapterView, view, i, l) -> {
            Robot.getInstance().goTo(locationList[i].getLocation());
            locations.setVisibility(View.INVISIBLE);
            Robot.getInstance().setVolume(3);

            Intent intent = new Intent(this,ConfirmMessageActivity.class);
            intent.putExtra("item",item);
            startActivity(intent);

            //TODO Talk to Gavin about wanting to Temi to talk right after it reaches the location to deliver
            //Robot.getInstance().speak(TtsRequest.create("Hello, we were ordered to deliver " + item.getQuantity() + " items of " + item.getItem() + " to this location.", false));
        });

         */
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