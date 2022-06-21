package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeliveryActivity extends AppCompatActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        animationBackground();
        deliverStation();

        confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener( (v) -> {
            confirmButton.setVisibility(View.INVISIBLE);
            continueProcess();

        });

        animationBackground();

    }



    public void continueProcess(){
        Robot.getInstance().setVolume(3);
        Robot.getInstance().speak(TtsRequest.create("Select the location you want me to deliver to.", false));

    }
    public void deliverStation(){
        Robot.getInstance().setVolume(3);
        Robot.getInstance().speak(TtsRequest.create("Place your item on my pad. Then place Confirm to Continue.", false));

    }


    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener((OnRobotReadyListener) this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener((OnRobotReadyListener) this);
    }

    public void animationBackground(){
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }
}