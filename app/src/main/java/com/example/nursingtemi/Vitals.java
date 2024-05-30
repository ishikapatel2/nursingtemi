package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.robotemi.sdk.Robot;

import org.w3c.dom.Text;

public class Vitals extends AppCompatActivity {

    private EditText temp_text;
    private EditText hr_text;
    private EditText o2_sat_text;
    private EditText bp_test;
    private EditText patient_text;
    private Button submit_button;
    private TextView title;
    private TextView patient;
    private TextView o2;
    private TextView hr2;
    private TextView temp;
    private TextView bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

         temp_text = findViewById(R.id.temp_text);
         hr_text = findViewById(R.id.hr_text);
         o2_sat_text = findViewById(R.id.o2_sat_text);
         bp_test = findViewById(R.id.bp_text);
         patient_text = findViewById(R.id.patient_text);
         submit_button = findViewById(R.id.submit_button);
         title = findViewById(R.id.title);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        ImageView tutorial = findViewById(R.id.tutorial);;

        tutorial.setOnClickListener((v) ->{
            playVideo();
        });

         patient = findViewById(R.id.patient_name);
         o2 = findViewById(R.id.o2);
         hr2 = findViewById(R.id.hr2);
         temp = findViewById(R.id.temp);
         bp = findViewById(R.id.bp);
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

                submit_button.setVisibility(View.GONE);

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

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_vitals;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);

        temp_text.setVisibility(View.INVISIBLE);
        bp_test.setVisibility(View.INVISIBLE);


        hr_text.setVisibility(View.INVISIBLE);
        o2_sat_text.setVisibility(View.INVISIBLE);
        patient_text.setVisibility(View.INVISIBLE);
        submit_button.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        patient.setVisibility(View.INVISIBLE);
        o2.setVisibility(View.INVISIBLE);
        bp.setVisibility(View.INVISIBLE);
        hr2.setVisibility(View.INVISIBLE);
        temp.setVisibility(View.INVISIBLE);

        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);
            temp_text.setVisibility(View.VISIBLE);
            hr_text.setVisibility(View.VISIBLE);
            o2_sat_text.setVisibility(View.VISIBLE);
            bp_test.setVisibility(View.VISIBLE);
            patient_text.setVisibility(View.VISIBLE);
            submit_button.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            patient.setVisibility(View.VISIBLE);
            o2.setVisibility(View.VISIBLE);
            bp.setVisibility(View.VISIBLE);
            hr2.setVisibility(View.VISIBLE);
            temp.setVisibility(View.VISIBLE);

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