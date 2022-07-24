package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity implements OnRobotReadyListener{

    private Button confirmButton;
    private ListView locations;
    private List<String> locationLists;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delivery);

        confirmButton = findViewById(R.id.confirm_button);
        locations = findViewById(R.id.locationList);
        backButton = findViewById(R.id.backButton);
        locations.setVisibility(View.INVISIBLE);

        animationBackground();

        backButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        confirmButton.setOnClickListener((v) ->{
            Intent obj = new Intent(this,DeliveryContinuationActivity.class);
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
            Robot.getInstance().speak(TtsRequest.create("Place the item on me, then press confirm to continue", false));
        }
    }

    public void continueProcess(){
        confirmButton.setVisibility(View.INVISIBLE);
        locationLists = new ArrayList<>();
        locationLists.add("VR Station");
        locationLists.add("Graduate Student Station");
        locationLists.add("Dr. Carter's Desk");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,locationLists);
        locations.setAdapter(arrayAdapter);

        locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(DeliveryActivity.this,"Clicked item " + i + locationLists.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        Robot.getInstance().setVolume(3);
        Robot.getInstance().speak(TtsRequest.create("Where would you like for me to go", false));

    }
    public void animationBackground()
    {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

    }
}