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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.OnViewDragListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

public class MapActivity extends AppCompatActivity  implements OnRobotReadyListener, OnGoToLocationStatusChangedListener, OnCurrentPositionChangedListener {
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
    Icon exit1;
    Icon exit2;
    Icon exit3;
    PhotoView photoView;
    ImageView closeButton;
    Icon backButton;
    TextView info;
    CardView infoCardView;
    Button goButton;

    private Position currentPosition;
    private boolean updatePosition = true;

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
        exit1 = new Icon(findViewById(R.id.exit1), 260, 490);
        exit2 = new Icon(findViewById(R.id.exit2), 1453, 485);
        exit3 = new Icon(findViewById(R.id.exit3), 1478, 320);

        info = findViewById(R.id.info);

        infoCardView = findViewById(R.id.infoCardView);
        goButton = findViewById(R.id.goButton);
        closeButton = findViewById(R.id.closeButton);

        Robot.getInstance().speak(TtsRequest.create("Tap on any of the icons on the map to get more information or to navigate to your desired emergency feature.",false));

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
                setInitialIconPosition(exit1);
                setInitialIconPosition(exit2);
                setInitialIconPosition(exit3);

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
                updateIconPosition(exit1);
                updateIconPosition(exit2);
                updateIconPosition(exit3);

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
                updateIconPosition(exit1);
                updateIconPosition(exit2);
                updateIconPosition(exit3);
            }
        });

        backButton.getImageView().setOnClickListener(v -> {
            Intent intent = new Intent(this, SafetyProcedures.class);
            startActivity(intent);
        });

        exit1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This is the west emergency exit located near the nurses station.");
                Robot.getInstance().speak(TtsRequest.create("This is the emergency exit near the double doors. Click 'go,' and I will take you there.",false));


                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("west emergency exit");
                    }
                });
            }
        });

        exit3.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This is the first east side emergency exit located by the restrooms.");
                Robot.getInstance().speak(TtsRequest.create("This is the emergency exit near the restrooms. Click 'go,' and I will take you there.",false));

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("east emergency exit 1");
                    }
                });
            }
        });

        exit2.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This is the second east side emergency exit located near the skills labs.");
                Robot.getInstance().speak(TtsRequest.create("This is the emergency exit near the learning labs. Click 'go,' and I will take you there.",false));
                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("east emergency exit 2");
                    }
                });
            }
        });



        fe1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");
                Robot.getInstance().speak(TtsRequest.create("This is the fire extinguisher near the lavender lounge. Click 'go,' and I will take you there.",false));

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("fire extinguisher 4");
                    }
                });
            }
        });

        fe2.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");
                Robot.getInstance().speak(TtsRequest.create("This is the fire extinguisher between the debriefing room and the double doors. Click 'go,' and I will take you there.",false));

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("fire extinguisher 3");
                    }
                });
            }
        });

        fe3.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");
                Robot.getInstance().speak(TtsRequest.create("This is the fire extinguisher near the learning lab. Click 'go,' and I will take you there.",false));

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int[] location = new int[2];
                v.getLocationOnScreen(location); // gets the location of the icon on the screen

                int x = location[0];
                int y = location[1];

                showCardViewAtPosition(x, y, v);

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("fire extinguisher 2");
                    }
                });
            }
        });

        fe4.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Use fire extinguishers on small (waste basket size) fires only if safe to do so. Instructions on how to use it are attached to it.");
                Robot.getInstance().speak(TtsRequest.create("This is the fire extinguisher near the restrooms. Click 'go,' and I will take you there.",false));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("fire extinguisher 1");
                    }
                });
            }
        });

        db.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This button will notify the police department when pressed. It is located underneath the counter.");
                Robot.getInstance().speak(TtsRequest.create("This button is under the counter at the nurse's desk by the offices. Click 'go,' and I will take you there.",false));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("duress button");
                    }
                });
            }
        });

        book1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This physical booklet is in a binder and contains all of the safety and emergency information. This can also be accessed via the QR code if you click the back arrow.");
                Robot.getInstance().speak(TtsRequest.create("This is the incident procedures booklet located inside the simulation office room 363. Click 'go,' and I will take you there.",false));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("incident booklet 2");
                    }
                });
            }
        });

        book2.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This physical booklet is in a binder and contains all of the safety and emergency information. This can also be accessed via the QR code if you click the back arrow.");
                Robot.getInstance().speak(TtsRequest.create("This is the incident procedures booklet located by the nurse's desk by the offices. Click 'go,' and I will take you there.",false));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("incident booklet 3");
                    }
                });
            }
        });

        book3.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This physical booklet is in a binder and contains all of the safety and emergency information. This can also be accessed via the QR code if you click the back arrow.");
                Robot.getInstance().speak(TtsRequest.create("This is the incident procedures booklet located in learning lounge. Click 'go,' and I will take you there.",false));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("incident booklet 1");
                    }
                });
            }
        });

        bag1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This bag is like a first aid kit and contains necessary equipment for serious injuries such as gun shot wounds.");
                Robot.getInstance().speak(TtsRequest.create("This go bag is located by the nurse's desk by the offices on the wall. Click 'go,' and I will take you there.",false));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("go bag");
                    }
                });
            }
        });

        aed1.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("This is used for people who have sudden cardiac arrest.");
                Robot.getInstance().speak(TtsRequest.create("The AED is located by the elevators. Click 'go,' and I will take you there.",false));


                int[] location = new int[2];
                v.getLocationOnScreen(location);

                Drawable background = ContextCompat.getDrawable(MapActivity.this, R.drawable.card_border);
                infoCardView.setBackground(background);

                int x = location[0]; // The x-coordinate of the tapped view
                int y = location[1]; // The y-coordinate of the tapped view
                showCardViewAtPosition(x, y, v); // Pass the view itself for width calculation

                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Robot.getInstance().goTo("aed");
                    }
                });
            }
        });


        closeButton.setOnClickListener(v -> closeCardView());
    }

    private void playVideo() {
        VideoView videoView = findViewById(R.id.videoView);
        Button closeVideoButton = findViewById(R.id.closeVideoButton);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_map;
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
    public void onCurrentPositionChanged(Position position) {
        if (updatePosition) {
            currentPosition = position;
            //Log.d("PositionUpdate", "X: " + currentPosition.getX() + ", Y: " + currentPosition.getY() + ", Yaw: " + currentPosition.getYaw());
        }
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
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        Intent intent;
        switch (status) {
            case "going":
                updatePosition = false;
                Robot.getInstance().speak(TtsRequest.create("Follow me!", false));

                break;
            case "complete":
                if (currentPosition != null) {
                    intent = new Intent(MapActivity.this, MainActivity.class);

                    intent.putExtra("positionX", currentPosition.getX());
                    intent.putExtra("positionY", currentPosition.getY());
                    intent.putExtra("positionYaw", currentPosition.getYaw());
                    intent.putExtra("positionTiltAngle", currentPosition.getTiltAngle());
                    startActivity(intent);
                }
                else {
                    Log.e("MapActivity", "Current position is null");
                }
                updatePosition = true;
                break;
            case "abort":
                updatePosition = true;
                Robot.getInstance().speak(TtsRequest.create("I am having trouble reaching my destination," +
                        " please try again or call 911 if this is a life threatening emergency.", false));
                break;
        }
    }
}