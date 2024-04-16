package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.UserInfo;

import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.listeners.OnTelepresenceEventChangedListener;
import com.robotemi.sdk.model.CallEventModel;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.ArrayList;
import java.util.List;

public class CallActivity extends AppCompatActivity implements OnRobotReadyListener, ContactsAdapter.OnItemClickListener, OnTelepresenceEventChangedListener, OnCurrentPositionChangedListener {

    RecyclerView contactsList;
    private Position currentPosition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PatientActivity.class);
            startActivity(intent);
        });

        contactsList = findViewById(R.id.contacts);
        Robot robot = Robot.getInstance();
        ArrayList<UserInfo> allUsers = (ArrayList<UserInfo>) robot.getAllContact();
        ArrayList<UserInfo> contacts = new ArrayList<UserInfo>();

        for (UserInfo user : allUsers) {
            if (user.getName().equals("Hintonje")) {
                contacts.add(user);

            }

        }

        contactsList.setLayoutManager(new LinearLayoutManager(this));
        contactsList.addItemDecoration(new SpaceDecorator(20)); // Adds 20dp of vertical space

        ContactsAdapter adapter = new ContactsAdapter(this, contacts, this);
        contactsList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(UserInfo contact) {
        Robot.getInstance().startTelepresence(contact.getName(), contact.getUserId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
        Robot.getInstance().addOnTelepresenceEventChangedListener(this);
        Robot.getInstance().addOnCurrentPositionChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
        Robot.getInstance().removeOnTelepresenceEventChangedListener(this);
        Robot.getInstance().removeOnCurrentPositionChangedListener(this);
    }

    @Override
    public void onRobotReady(boolean b) {

    }

    @Override
    public void onCurrentPositionChanged(Position position) {
        currentPosition = position;
        //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
    }

    @Override
    public void onTelepresenceEventChanged(CallEventModel callEventModel) {
        if (callEventModel.getType() == CallEventModel.TYPE_OUTGOING && callEventModel.getState() == CallEventModel.STATE_ENDED) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}