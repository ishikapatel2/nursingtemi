package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotemi.sdk.Robot;

public class Vitals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        EditText temp_text = findViewById(R.id.temp_text);
        EditText hr_text = findViewById(R.id.hr_text);
        EditText o2_sat_text = findViewById(R.id.o2_sat_text);
        EditText bp_test = findViewById(R.id.bp_text);
        EditText patient_text = findViewById(R.id.patient_text);
        Button submit_button = findViewById(R.id.submit_button);
        TextView title = findViewById(R.id.title);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        TextView patient = findViewById(R.id.patient_name);
        TextView o2 = findViewById(R.id.o2);
        TextView hr2 = findViewById(R.id.hr2);
        TextView temp = findViewById(R.id.hr2);
        TextView bp = findViewById(R.id.hr2);
        Button homeBase = findViewById(R.id.homeBase);
        Button mainMenu = findViewById(R.id.main);

        // when the confirm button is pressed
        submit_button.setOnClickListener((v) ->
        {
            // makes sure all information has been given before confirming delivery
            if (!emptyCredentials(patient_text, temp_text, hr_text, o2_sat_text, bp_test))
            {
                String patientName = patient_text.getText().toString();
                String heartRate = hr_text.getText().toString();
                String temperature = temp_text.getText().toString();
                String bloodPressure = bp_test.getText().toString();
                String oxygenSaturation = o2_sat_text.getText().toString();

                insertData(patientName, heartRate, temperature, bloodPressure, oxygenSaturation);

                title.setText("Vitals are complete!");

                // Reposition TextView to be centered in ConstraintLayout
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(title.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
                constraintSet.connect(title.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(title.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
                constraintSet.connect(title.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
                constraintSet.applyTo(constraintLayout);

//                temp_text.setVisibility(View.GONE);
//                hr_text.setVisibility(View.GONE);
//                o2_sat_text.setVisibility(View.GONE);
//                bp_test.setVisibility(View.GONE);
                submit_button.setVisibility(View.GONE);
//                o2.setVisibility(View.GONE);
//                hr2.setVisibility(View.GONE);
//                temp.setVisibility(View.INVISIBLE);
//                bp.setVisibility(View.INVISIBLE);

                homeBase.setVisibility(View.VISIBLE);
                mainMenu.setVisibility(View.VISIBLE);
            }

        });

        // back button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        mainMenu.setOnClickListener((v) -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        // return to home base
        homeBase.setOnClickListener(v -> {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
            // Send robot to its home base
            Robot.getInstance().goTo("home base");
        });
    }


    /*
     *  Add vitals to the database on submit
     */
    public void insertData(String patientName, String heartRate, String temperature, String bloodPressure, String oxygenSaturation) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("patient", patientName);
        values.put("heart_rate", heartRate);
        values.put("temperature", temperature);
        values.put("blood_pressure", bloodPressure);
        values.put("oxygen_saturation", oxygenSaturation);

        db.insert("vitals", null, values);
        db.close();
    }

    /*
     * Checks if any vital fields are empty
     */
    public boolean emptyCredentials(EditText patient_text, EditText hr_text, EditText temp_text, EditText o2_sat_text, EditText bp_test)
    {
        boolean check = false;

        if  (patient_text.getText().toString().isEmpty()) {
            patient_text.setError("Please complete this field");
            check = true;
        }

        if  (hr_text.getText().toString().isEmpty()) {
            hr_text.setError("Please complete this field");
            check = true;
        }

        if (temp_text.getText().toString().isEmpty()) {
            temp_text.setError("Please complete this field");
            check = true;
        }

        if (o2_sat_text.getText().toString().isEmpty()) {
            o2_sat_text.setError("Please complete this field");
            check = true;
        }

        if (bp_test.getText().toString().isEmpty()) {
            bp_test.setError("Please complete this field");
            check = true;
        }
        return check;
    }
}