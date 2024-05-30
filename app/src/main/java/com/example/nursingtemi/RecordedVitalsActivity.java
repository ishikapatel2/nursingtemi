package com.example.nursingtemi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.system.StructUtsname;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.VideoView;

import java.util.List;

public class RecordedVitalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_vitals);

        ImageView tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener((v) ->{
            playVideo();
        });

        ImageView backButton = findViewById(R.id.backButton);
        ListView vitalsListView = findViewById(R.id.vitalsListView);

        // gets the list of all vitals
        List<Record> vitals = DatabaseHelper.getInstance(getApplicationContext()).getAllVitals();

        View headerView = getLayoutInflater().inflate(R.layout.list_header_vital, vitalsListView, false);
        vitalsListView.addHeaderView(headerView, null, false);

        VitalsAdapter adapter = new VitalsAdapter(this, vitals);
        vitalsListView.setAdapter(adapter);

        // Searching the database
        SearchView searchView = findViewById(R.id.searchView);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    adapter.resetData();
                } else {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });


        vitalsListView.setOnItemLongClickListener((parent, view, position, id) -> {

            Log.d("VitalsAdapter", "Deleting this position: " + (position-1));
            // Prompt for confirmation, then delete
            new AlertDialog.Builder(RecordedVitalsActivity.this)
                    .setTitle("Delete Record")
                    .setMessage("Are you sure you want to delete this record?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        adapter.removeItem(position-1);
                        //vitals.remove(position);
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });

        backButton.setOnClickListener((y) -> {
            Intent obj = new Intent(this, StaffActivity.class);
            startActivity(obj);
        });

    }

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_recordedvitals;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setVisibility(View.VISIBLE);
        closeVideoButton.setVisibility(View.VISIBLE);




        closeVideoButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);
            closeVideoButton.setVisibility(View.GONE);


        });
    }
}