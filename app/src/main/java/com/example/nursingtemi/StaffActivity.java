package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.Objects;

public class StaffActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Button delivery = findViewById(R.id.delivery);
        Button vitals = findViewById(R.id.vitals);
        Button pharm = findViewById(R.id.pharmacy);
        Button nurse = findViewById(R.id.nurse);
        Button data = findViewById(R.id.data);
        Button food = findViewById(R.id.meal);
        ImageView backButton = findViewById(R.id.backButton);

        data.setOnClickListener((v) -> {
            Intent obj = new Intent(this, RecordedVitalsActivity.class);
            startActivity(obj);
        });

        // selecting delivery
        delivery.setOnClickListener((v) -> {
            delivery.setVisibility(View.GONE);
            vitals.setVisibility(View.GONE);
            data.setVisibility(View.GONE);
            pharm.setVisibility(View.VISIBLE);
            nurse.setVisibility(View.VISIBLE);
            food.setVisibility(View.VISIBLE);

            backButton.setOnClickListener((y) -> {
                Intent obj = new Intent(this, StaffActivity.class);
                startActivity(obj);
            });
        });

        nurse.setOnClickListener((v) -> {
            Intent obj = new Intent(this, DeliveryActivityNurse.class);
            startActivity(obj);
        });

        pharm.setOnClickListener((v) -> {
            Intent obj = new Intent(this, DeliveryActivity.class);
            startActivity(obj);
        });

        food.setOnClickListener((v) -> {
            Intent obj = new Intent(this, FoodActivity.class);
            startActivity(obj);
        });

        // selecting vitals
        vitals.setOnClickListener((v) -> {
            Intent obj = new Intent(this, Vitals.class);
            startActivity(obj);
        });

        backButton.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });
    }

}
