package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import java.util.Objects;

public class DeliveryContFoodActivity extends AppCompatActivity implements OnRobotReadyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery_continuation);

        Spinner spinner = findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        adapter.add(" Select which zone you would like to deliver");
        adapter.add(" Zone 1");
        adapter.add(" Zone 2");
        adapter.add(" Zone 3");
        adapter.add(" Zone 4");
        adapter.add(" Zone 5");
        adapter.add(" Zone 6");
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String zone = parent.getItemAtPosition(position).toString();
                //((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                //((TextView) parent.getChildAt(0)).setTextSize(25);

                if (zone == " Zone 1") {
                    Intent object = new Intent(DeliveryContFoodActivity.this, Zone1.class);
                    startActivity(object);
                }
                if (zone == " Zone 2") {
                    Intent object = new Intent(DeliveryContFoodActivity.this, Zone2.class);

                    startActivity(object);
                }
                if (zone == " Zone 3") {
                    Intent object = new Intent(DeliveryContFoodActivity.this, Zone3.class);

                    startActivity(object);
                }
                if (zone == " Zone 4") {
                    Intent object = new Intent(DeliveryContFoodActivity.this, Zone4.class);

                    startActivity(object);
                }
                if (zone == " Zone 5") {
                    Intent object = new Intent(DeliveryContFoodActivity.this, Zone5.class);

                    startActivity(object);
                }
                if (zone == " Zone 6") {
                    Intent object = new Intent(DeliveryContFoodActivity.this, Zone6.class);

                    startActivity(object);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
            }
        });

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