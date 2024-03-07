package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.Objects;

public class ConfirmMessageActivity extends AppCompatActivity implements OnRobotReadyListener
{

    private EditText name;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_confirm_message);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        float yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        int angle = getIntent().getIntExtra("positionTiltAngle", 0);

        String deliveryType = getIntent().getStringExtra("deliveryType");

        Position previousLocation = new Position(x, y, yaw, angle);

        ImageView fingerprintScanner = findViewById(R.id.fingerprintScanner);
        TextView message = findViewById(R.id.textMessage);
        Button homeMenu = findViewById(R.id.homeMenu);
        Button homeBase = findViewById(R.id.homeBase);
        Button prevLoc = findViewById(R.id.prevLoc);

        confirm = findViewById(R.id.confirm_button);

        name = findViewById(R.id.name);

        homeMenu.setVisibility(View.INVISIBLE);
        homeBase.setVisibility(View.INVISIBLE);
        prevLoc.setVisibility(View.INVISIBLE);


        if ("Food".equals(deliveryType)) {
            fingerprintScanner.setVisibility(View.GONE);
            Robot.getInstance().speak(TtsRequest.create("Knock knock, this is Temi. I am here with your food", false));
            message.setText("Please confirm you received your food by entering your full name below.");
            Robot.getInstance().speak(TtsRequest.create("Please confirm you received your food by entering your name below. Select confirm to continue.", false));
            name.setVisibility(View.VISIBLE);
        }
        else {
            Robot.getInstance().speak(TtsRequest.create("Knock knock, Temi here. Your order of is here.", false));
            message.setText("Please confirm this order by scanning your finger");
            Robot.getInstance().speak(TtsRequest.create("Please confirm this order by scanning your finger", false));
        }

        message.setVisibility(View.VISIBLE);

        fingerprintScanner.setOnLongClickListener(view -> {
            Robot.getInstance().speak(TtsRequest.create("Order confirmed. You may continue.", false));

            message.setText("Please continue using the following options:");
            homeMenu.setVisibility(View.VISIBLE);
            homeBase.setVisibility(View.VISIBLE);
            prevLoc.setVisibility(View.VISIBLE);
            fingerprintScanner.setVisibility(View.GONE);
            return true;
        });

        confirm.setOnClickListener((v)-> {
            if (!emptyCredentials(name)) {
                Robot.getInstance().speak(TtsRequest.create("Thank you. If you no longer need any more assistance, " +
                        "please send me back to my previous location. Enjoy your meal! ", false));
                message.setText("Please click on one of the following options to send me back.");
                homeMenu.setVisibility(View.VISIBLE);
                homeBase.setVisibility(View.VISIBLE);
                prevLoc.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.GONE);
            }
        });

        homeMenu.setOnClickListener((v)->{

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            // Send robot back to the previous location
            Robot.getInstance().goToPosition(previousLocation);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        homeBase.setOnClickListener(v -> {
            // Send robot to its home base
            Robot.getInstance().goTo("home base");
        });
    }

    private boolean emptyCredentials(EditText patientName) {
        if  (patientName.getText().toString().isEmpty()) {
            patientName.setError("Please fill this field");
            return true;
        }
        return false;
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
        }
    }
}