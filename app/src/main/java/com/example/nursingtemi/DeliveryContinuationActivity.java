package com.example.nursingtemi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryContinuationActivity extends AppCompatActivity implements OnRobotReadyListener {

    private final TourLocation[] locations = {
            new TourLocation("Control room 365", "control room 365"),
            new TourLocation("Control room 367", "control room 367"),
            new TourLocation("Control Room 370", "control room 370"),
            new TourLocation("Conference Room 321", "conference room 321"),
            new TourLocation("Control Room 375", "control room 375"),
            new TourLocation("Debriefing 372", "debriefing 372"),
            new TourLocation("Debriefing 373", "debriefing 373"),
            new TourLocation("Debriefing 374", "debriefing 374"),
            new TourLocation("Interactive lab 351","interactive lab 351"),
            new TourLocation("Learning Lab 301", "learning lab 301"),
            new TourLocation("Learning Lab 302", "learning lab 302"),
            new TourLocation("Simulation room 363", "simulation room 363"),
            new TourLocation("Simulation room 364", "simulation room 364"),
            new TourLocation("Simulation room 366", "simulation room 366"),
            new TourLocation("Simulation room 368", "simulation room 368"),
            new TourLocation("Simulation room 369", "simulation room 369"),
            new TourLocation("Simulation Room 371", "simulation room 371"),
            new TourLocation("Simulation Room 376", "simulation room 376"),
            new TourLocation("Skills lab 334", "skills lab 334"),
            new TourLocation("Meeting Room 333", "meeting room 333"),
            new TourLocation("Office 326", "office 326"),
            new TourLocation("Office 322", "office 322"),
            new TourLocation("Office 328", "office 328"),
            new TourLocation("Office 323", "office 323"),
            new TourLocation("Office 331", "office 331"),
            new TourLocation("Office 324", "office 324"),
            new TourLocation("Office 325", "office 325"),
            new TourLocation("Office 330", "office 330"),
            new TourLocation("Office 327", "office 327"),
    };

    DeliveryItem deliveryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation);

        deliveryItem = (DeliveryItem) getIntent().getSerializableExtra("item");
        //Robot.getInstance().speak(TtsRequest.create(deliveryItem.getQuantity(), false));
        Spinner spinner = findViewById(R.id.spin);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        adapter.add(" Select which zone you would like to deliver"); // Add the prompt or title
        adapter.add(" Zone 1");
        adapter.add(" Zone 2");
        adapter.add(" Zone 3");
        adapter.add(" Zone 4");
        adapter.add(" Zone 5");
        adapter.add(" Zone 6");

        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ImageView map = findViewById(R.id.zoomable_image);
        ScaleGestureDetector scaleGestureDetector;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String zone = parent.getItemAtPosition(position).toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(25);

                if (zone == " Zone 1") {
                    Intent object = new Intent(DeliveryContinuationActivity.this, Zone1.class);
                    object.putExtra("item", deliveryItem);
                    startActivity(object);
                }
                if (zone == " Zone 2") {
                    Intent object = new Intent(DeliveryContinuationActivity.this, Zone2.class);
                    object.putExtra("item", deliveryItem);
                    startActivity(object);
                }
                if (zone == " Zone 3") {
                    Intent object = new Intent(DeliveryContinuationActivity.this, Zone3.class);
                    object.putExtra("item", deliveryItem);
                    startActivity(object);
                }
                if (zone == " Zone 4") {
                    Intent object = new Intent(DeliveryContinuationActivity.this, Zone4.class);
                    object.putExtra("item", deliveryItem);
                    startActivity(object);
                }
                if (zone == " Zone 5") {
                    Intent object = new Intent(DeliveryContinuationActivity.this, Zone5.class);
                    object.putExtra("item", deliveryItem);
                    startActivity(object);
                }
                if (zone == " Zone 6") {
                    Intent object = new Intent(DeliveryContinuationActivity.this, Zone6.class);
                    object.putExtra("item", deliveryItem);
                    startActivity(object);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
            }
        });


        //String selectedLocation = (String) parent.getItemAtPosition(position).toString();
        //Robot.getInstance().goTo(selectedLocation);
        //Toast.makeText(DeliveryContinuationActivity.this, "Recording in Progress. Please do not touch.", Toast.LENGTH_SHORT).show();

        //Intent intent = new Intent(DeliveryContinuationActivity.this, ConfirmMessageActivity.class);
        //startActivity(intent);
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, DeliveryActivity.class);
            startActivity(obj);
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
            Robot.getInstance().speak(TtsRequest.create("Where would you like for me to go", false));
        }
    }

}