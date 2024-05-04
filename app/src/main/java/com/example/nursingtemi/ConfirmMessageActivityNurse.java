package com.example.nursingtemi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import org.w3c.dom.Text;

import java.util.Objects;

public class ConfirmMessageActivityNurse extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener
{
    private Button homeMenu;
    private Button prevLoc;
    private Button homeBase;
    private TextView message;
    private ImageView recording;
    private float yaw;
    private int angle;

    private String sender;
    private String item;
    private String quantity;

    private TextView senderName;
    private TextView quantityAmount;
    private TextView itemName;
    private TextView labelNurse;
    private TextView labelitem;
    private TextView labelquantity;

    private Position currentPosition;
    private boolean updatePosition = true;
    private boolean delivery = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_confirm_message_nurse);

        // Reconstruct the Position object (as previously described)
        float x = getIntent().getFloatExtra("positionX", 0.0f);
        float y = getIntent().getFloatExtra("positionY", 0.0f);
        yaw = getIntent().getFloatExtra("positionYaw", 0.0f);
        angle = getIntent().getIntExtra("positionTiltAngle", 0);

        Position previousLocation = new Position(x, y, yaw, angle);

        // initializing current position but should update here
        currentPosition = previousLocation;

        sender = getIntent().getStringExtra("sender");
        item = getIntent().getStringExtra("item");
        quantity = getIntent().getStringExtra("quantity");

        //Log.d("DeliveryData", "Sender: " + sender + ", Item: " + item + ", Quantity: " + quantity);

        labelNurse = findViewById(R.id.labelNurse);
        labelquantity = findViewById(R.id.quantitylabel);
        labelitem = findViewById(R.id.itemlabel);
        senderName = findViewById(R.id.senderName);
        itemName = findViewById(R.id.itemName);
        quantityAmount = findViewById(R.id.quantityAmount);

        senderName.setText(sender);
        itemName.setText(item);
        quantityAmount.setText(quantity);

        message = findViewById(R.id.textMessage);
        recording = findViewById(R.id.recording);
        homeMenu = findViewById(R.id.homeMenu);
        homeBase = findViewById(R.id.homeBase);
        prevLoc = findViewById(R.id.prevLoc);

        Robot.getInstance().speak(TtsRequest.create("Hi there, I am here to deliver an order that was requested by " + sender + ". " +
                "Please complete the following order and send me back when the order is ready.", false));

        homeMenu.setVisibility(View.VISIBLE);
        homeBase.setVisibility(View.VISIBLE);
        prevLoc.setVisibility(View.VISIBLE);

        homeMenu.setOnClickListener((v)->{
            delivery = false;
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("positionX", currentPosition.getX());
            intent.putExtra("positionY", currentPosition.getY());
            intent.putExtra("positionYaw", currentPosition.getYaw());
            intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
            startActivity(intent);
        });

        prevLoc.setOnClickListener(v -> {
            Robot.getInstance().speak(TtsRequest.create("I am now going back to the patient room to deliver the" + item, false));

            // Send robot back to the previous location
            Robot.getInstance().goToPosition(previousLocation);
        });

        homeBase.setOnClickListener(v -> {
            delivery = false;
            // Send robot to its home base
            Robot.getInstance().goTo("home base");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
        Robot.getInstance().addOnGoToLocationStatusChangedListener(this);
        Robot.getInstance().addOnCurrentPositionChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
        Robot.getInstance().removeOnGoToLocationStatusChangedListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
        }
    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        if (updatePosition) {
            currentPosition = position;
            //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
        }
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        switch (status) {
            case "going":
                if (delivery == false) {
                    updatePosition = true;
                }
                else {
                    updatePosition = false;
                }
                homeMenu.setVisibility(View.INVISIBLE);
                prevLoc.setVisibility(View.INVISIBLE);
                homeBase.setVisibility(View.INVISIBLE);
                senderName.setVisibility(View.INVISIBLE);
                itemName.setVisibility(View.INVISIBLE);
                quantityAmount.setVisibility(View.INVISIBLE);
                labelNurse.setVisibility(View.INVISIBLE);
                labelquantity.setVisibility(View.INVISIBLE);
                labelitem.setVisibility(View.INVISIBLE);
                recording.setVisibility(View.VISIBLE);
                message.setText("For security and monitoring: \n" +
                        "Recording in Progress. ");
                break;
            case "complete":
                message.setVisibility(View.GONE);
                Intent intent = new Intent(this, ConfirmMessageActivity.class);
                intent.putExtra("positionX", currentPosition.getX());
                intent.putExtra("positionY", currentPosition.getY());
                intent.putExtra("positionYaw", currentPosition.getYaw());
                intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                startActivity(intent);
                updatePosition = true;

                break;
            case "abort":
                Robot.getInstance().speak(TtsRequest.create("I am experiencing problems.", false));
                updatePosition = true;
                break;
        }
    }
}