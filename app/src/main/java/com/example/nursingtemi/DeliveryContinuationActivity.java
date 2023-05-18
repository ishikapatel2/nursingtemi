package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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


    // soon, make a table on SQLite, which has every single room
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



    private String[] locations = {"Location 1", "Location 2", "Location 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation);

        ListView listView = findViewById(R.id.listview);
        List<String> list = new ArrayList<>();
        list.add("Room 363");
        list.add("Room 364");
        list.add("Room 365");
        list.add("Room 366");
        list.add("Room 367");
        list.add("Room 368");
        list.add("Room 369");
        list.add("Room 370");
        list.add("Room 371");
        list.add("Room 372");
        list.add("Room 373");
        list.add("Room 374");
        list.add("Room 375");
        list.add("Room 376");
        list.add("Room 360");
        list.add("Room 334");
        list.add("Room 333");
        list.add("Room 374");
        list.add("Room 329");
        list.add("Room 328");
        list.add("Room 327");
        list.add("Room 326");
        list.add("Room 325");
        list.add("Room 324");
        list.add("Room 323");
        list.add("Room 322");
        list.add("Room 321");
        list.add("Room 329");
        list.add("Room 330");
        list.add("Room 331");
        list.add("Room 351");
        list.add("Room 301");
        list.add("Room 302");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the selected location from the list of mapped locations
                TourLocation selectedLocation = locationList[position];

                // Instructs Temi to go to the selected location
                Robot.getInstance().goTo(selectedLocation.getLocation());

                

            }
        });



    }







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