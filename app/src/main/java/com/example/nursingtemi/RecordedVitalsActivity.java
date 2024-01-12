package com.example.nursingtemi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

public class RecordedVitalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_vitals);

        ImageView backButton = findViewById(R.id.backButton);
        ListView vitalsListView = findViewById(R.id.vitalsListView);

        // gets the list of all vitals
        List<String> vitals = DatabaseHelper.getInstance(getApplicationContext()).getAllVitals();

        View headerView = getLayoutInflater().inflate(R.layout.list_header_vital, vitalsListView, false);
        vitalsListView.addHeaderView(headerView, null, false);

        VitalsAdapter adapter = new VitalsAdapter(this, vitals);
        vitalsListView.setAdapter(adapter);

        // Searching the database

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()) {
                    adapter.resetData();
                }
                else {// Reset the adapter's data
                    adapter.getFilter().filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    adapter.resetData();
                } else {
                    adapter.getFilter().filter(newText);
                }
                return false;
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

    public void refreshListView() {
        ListView vitalsListView = findViewById(R.id.vitalsListView);

        List<String> vitals = DatabaseHelper.getInstance(getApplicationContext()).getAllVitals();
        VitalsAdapter adapter = new VitalsAdapter(this, vitals);
        vitalsListView.setAdapter(adapter);
    }




}