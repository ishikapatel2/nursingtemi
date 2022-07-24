package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.UiAutomation;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import java.util.ArrayList;
import java.util.List;

public class DeliveryContinuationActivity extends AppCompatActivity implements OnRobotReadyListener {


    private ListView locations;
    private List<String> locationLists;
    private final TourLocation[] locationList = {
            new TourLocation("VR Station", "vr station"),
            new TourLocation("Graduate Student Station", "grad desk"),
            new TourLocation("Dr. Carter's Desk", "carter desk")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delivery_continuation);
        locations = findViewById(R.id.listView);

        locationLists = new ArrayList<>();

        for (int i = 0; i < locationList.length; i++){
            locationLists.add(locationList[i].getTitle());
        }
        //locationLists.add(locationList[0]);
        //locationLists.add("Graduate Student Station");
        //locationLists.add("Dr. Carter's Desk");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,locationLists);
        locations.setAdapter(arrayAdapter);

        locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  Robot.getInstance().goTo(locationLists.get(i).toString());
                Robot.getInstance().setVolume(3);
                Robot.getInstance().goTo(locationList[i].getLocation());
               // Robot.getInstance().speak(TtsRequest.create("Made it to the delivery destination. Happy sunday, motherfucker", false));

                //Toast.makeText(DeliveryContinuationActivity.this,"Clicked item " + i + locationLists.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
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


    public void continueProcess(){


    }
}