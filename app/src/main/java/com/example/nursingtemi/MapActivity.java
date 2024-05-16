package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.OnViewDragListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

public class MapActivity extends AppCompatActivity  implements OnRobotReadyListener {
    Icon fe1;
    Icon fe2;
    Icon fe3;
    Icon fe4;
    Icon fe5;
    Icon db;
    Icon db2;
    Icon book1;
    Icon book2;
    Icon book3;
    Icon book4;
    Icon bag1;
    Icon bag2;
    Icon aed1;
    Icon aed2;
    PhotoView photoView;
    ImageView closeButton;
    Icon backButton;
    TextView info;
    CardView infoCardView;
    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.emergencymap);

        fe1 = new Icon(findViewById(R.id.fe1), 380, 530);
        fe2 = new Icon(findViewById(R.id.fe2), 723, 365);
        fe3 = new Icon(findViewById(R.id.fe3), 1185, 460);
        fe4 = new Icon(findViewById(R.id.fe4), 1402, 243);
        fe5 = new Icon(findViewById(R.id.fe5), 225, 856);
        db = new Icon(findViewById(R.id.db), 470, 680);
        db2 = new Icon(findViewById(R.id.db2), 35, 858);
        db2 = new Icon(findViewById(R.id.db2), 35, 858);
        book1 = new Icon(findViewById(R.id.book1), 240, 385);
        book2 = new Icon(findViewById(R.id.book2), 470, 610);
        book3 = new Icon(findViewById(R.id.book3), 845, 297);
        book4 = new Icon(findViewById(R.id.book4), 440, 858);
        bag1 = new Icon(findViewById(R.id.bag1), 420, 680);
        bag2 = new Icon(findViewById(R.id.bag2), 882, 860);
        aed1 = new Icon(findViewById(R.id.aed1), 1082, 395);
        aed2 = new Icon(findViewById(R.id.aed2), 1058, 860);
        backButton = new Icon(findViewById(R.id.backButton), 10, 50);

        info = findViewById(R.id.info);

        infoCardView = findViewById(R.id.infoCardView);
        goButton = findViewById(R.id.goButton);
        closeButton = findViewById(R.id.closeButton);

        Robot.getInstance().speak(TtsRequest.create("Tap on any of the icons on the map to get more information or to navigate to each emergency feature.",false));

        photoView.post(new Runnable() {
            @Override
            public void run() {
                setInitialIconPosition(fe1);
                setInitialIconPosition(fe2);
                setInitialIconPosition(fe3);
                setInitialIconPosition(fe4);
                setInitialIconPosition(fe5);
                setInitialIconPosition(db);
                setInitialIconPosition(db2);
                setInitialIconPosition(book1);
                setInitialIconPosition(book2);
                setInitialIconPosition(book3);
                setInitialIconPosition(book4);
                setInitialIconPosition(bag1);
                setInitialIconPosition(bag2);
                setInitialIconPosition(aed1);
                setInitialIconPosition(aed2);
                setInitialIconPosition(backButton);

                updateIconPosition(fe1);
                updateIconPosition(fe2);
                updateIconPosition(fe3);
                updateIconPosition(fe4);
                updateIconPosition(fe5);
                updateIconPosition(db);
                updateIconPosition(db2);
                updateIconPosition(book1);
                updateIconPosition(book2);
                updateIconPosition(book3);
                updateIconPosition(book4);
                updateIconPosition(bag1);
                updateIconPosition(bag2);
                updateIconPosition(aed1);
                updateIconPosition(aed2);
                updateIconPosition(backButton);
            }
        });

        photoView.setOnMatrixChangeListener(new OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rect) {

                updateIconPosition(fe1);
                updateIconPosition(fe2);
                updateIconPosition(fe3);
                updateIconPosition(fe4);
                updateIconPosition(fe5);
                updateIconPosition(db);
                updateIconPosition(db2);
                updateIconPosition(book1);
                updateIconPosition(book2);
                updateIconPosition(book3);
                updateIconPosition(book4);
                updateIconPosition(bag1);
                updateIconPosition(bag2);
                updateIconPosition(aed1);
                updateIconPosition(aed2);
                updateIconPosition(backButton);

            }
        });

        backButton.getImageView().setOnClickListener(v -> {
            Intent intent = new Intent(this, SafetyProcedures.class);
            startActivity(intent);
        });

        fe1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);
            }
        });

        fe2.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);
            }
        });

        fe3.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);
            }
        });

        fe4.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });

        db.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This button will notify the police department when pressed. It is located underneath the counter.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });

        book1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This physical booklet is in a binder and contains all of the safety and emergency information. This can also be accessed via the QR code if you click the back arrow.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });

        book2.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This physical booklet is in a binder and contains all of the safety and emergency information. This can also be accessed via the QR code if you click the back arrow.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });

        book3.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This physical booklet is in a binder and contains all of the safety and emergency information. This can also be accessed via the QR code if you click the back arrow.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });

        bag1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This bag is like a first aid kit and contains necessary equipment for serious injuries such as gun shot wounds.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });

        aed1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This is used for people who have sudden cardiac arrest.");

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation
            }
        });


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assuming you have a method to send the robot to a specific location
                Robot.getInstance().goTo("Home Base");
            }
        });

        closeButton.setOnClickListener(v -> closeCardView());
    }

    private void showCardViewAtPosition(int x, int y, View tappedView) {
        // Get location and size of the PhotoView
        int[] photoViewLocation = new int[2];
        photoView.getLocationOnScreen(photoViewLocation);
        int photoViewWidth = photoView.getWidth();
        int photoViewHeight = photoView.getHeight();
        int photoViewRight = photoViewLocation[0] + photoViewWidth;
        int photoViewBottom = photoViewLocation[1] + photoViewHeight;

        // Measure CardView to know its dimensions
        infoCardView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int cardWidth = infoCardView.getMeasuredWidth();
        int cardHeight = infoCardView.getMeasuredHeight();

        // Determine horizontal position: try right first, or left if it would go out of bounds
        int newX = x + tappedView.getWidth();
        if (newX + cardWidth > photoViewRight) {
            newX = x - cardWidth; // Place left of the tap if right would exceed
        }

        // Determine vertical position: try below first, or above if it would go out of bounds
        int newY = y + tappedView.getHeight(); // Position below the tapped view initially
        if (newY + cardHeight > photoViewBottom) {
            newY = y - cardHeight; // Place above the tap if below would exceed
            if (newY < photoViewLocation[1]) {
                newY = photoViewLocation[1]; // Ensure the card doesn't go above the PhotoView
            }
        }

        // Ensure the CardView never goes above the top of the PhotoView and not below the bottom
        newY = Math.max(newY, photoViewLocation[1]); // Adjust to not go above the top
        newY = Math.min(newY, photoViewBottom - cardHeight); // Adjust to not go below the bottom

        // Apply positioning to the CardView
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = newX;
        params.topMargin = newY;
        infoCardView.setLayoutParams(params);
        infoCardView.setVisibility(View.VISIBLE);
    }



    private void closeCardView() {
        if (infoCardView.getVisibility() == View.VISIBLE) {
            infoCardView.setVisibility(View.GONE);
            photoView.setZoomable(true); // Re-enable zoom interactions
        }
    }

    private void setInitialIconPosition(Icon icon) {
        // Calculate initial absolute positions based on PhotoView's size and scale
        float pxWidth = photoView.getWidth();
        float pxHeight = photoView.getHeight();
        float drawableWidth = photoView.getDrawable().getIntrinsicWidth();
        float drawableHeight = photoView.getDrawable().getIntrinsicHeight();
        float scale = Math.min(pxWidth / drawableWidth, pxHeight / drawableHeight);

        float adjustedX = pxWidth * icon.getX();
        float adjustedY = pxHeight * icon.getY();

        // Set the initial position for the icon
        ImageView temp = icon.getImageView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) temp.getLayoutParams();
        params.leftMargin = (int) (adjustedX - (temp.getWidth() / 2) * scale);
        params.topMargin = (int) (adjustedY - (temp.getHeight() / 2) * scale);
        temp.setLayoutParams(params);
        temp.setVisibility(View.VISIBLE);
    }

    private void updateIconPosition(Icon icon) {
        // Retrieve the current matrix values from the PhotoView
        float[] matrixValues = new float[9];
        photoView.getImageMatrix().getValues(matrixValues);
        float scaleX = matrixValues[Matrix.MSCALE_X];
        float scaleY = matrixValues[Matrix.MSCALE_Y];
        float transX = matrixValues[Matrix.MTRANS_X];
        float transY = matrixValues[Matrix.MTRANS_Y];

        // Calculate new size for the icon based on scale
        int newWidth = (int) (48 * scaleX);
        int newHeight = (int) (48 * scaleY);

        // Calculate new position for the icon to make it stay in position relative to the image
        float adjustedX = icon.getX() * scaleX + transX;
        float adjustedY = icon.getY() * scaleY + transY;

        ImageView temp = icon.getImageView();
        // Update icon position and size
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) temp.getLayoutParams();
        params.width = newWidth;
        params.height = newHeight;
        params.leftMargin = (int) adjustedX - newWidth / 2;
        params.topMargin = (int) adjustedY - newHeight / 2;

        temp.setLayoutParams(params);
    }
    @Override
    public void onRobotReady(boolean b) {

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
}