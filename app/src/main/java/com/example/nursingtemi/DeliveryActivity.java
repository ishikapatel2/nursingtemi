package com.example.nursingtemi;

import androidx.appcompat.app.AppCompatActivity;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.Objects;

public class DeliveryActivity extends AppCompatActivity implements OnRobotReadyListener
{

    // below variable is for our data base name
    private static final String DB_NAME = "temidb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "meds";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our brand name column
    private static final String BRAND_COL = "synonyms/Brand";

    // below variable id for our concentration name column.
    private static final String CONCENTRATION_COL = "concentration";

    // below variable for our form description column.
    private static final String FORM_COL = "form";

    // below variable is for our generic tracks column.
    private static final String GENERIC_COL = "generic-name";

    // private variable is for our ehr barcode column.
    private static final String EHR_COL = "barcode";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        handleServer();
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_delivery);

        Button confirmButton = findViewById(R.id.confirm_button);
        EditText item = findViewById(R.id.item);
        EditText quantity = findViewById(R.id.quantity);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, MainActivity.class);
            startActivity(obj);
        });

        confirmButton.setOnClickListener((v) ->
        {
            if (!emptyCredentials(item,quantity))
            {
                DeliveryItem deliveryItem = new DeliveryItem(item.getText().toString(),quantity.getText().toString());

                Intent obj = new Intent(this,DeliveryContinuationActivity.class);
                obj.putExtra("item", deliveryItem);
                startActivity(obj);
            }

        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Robot.getInstance().addOnRobotReadyListener(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Robot.getInstance().removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady)
    {
        if (isReady)
        {
            Robot.getInstance().hideTopBar();
            Robot.getInstance().setVolume(3);
            Robot.getInstance().speak(TtsRequest.create("Place the item on me, then press confirm to continue", false));
        }
    }


    public void handleServer () {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BRAND_COL + " TEXT,"
                + CONCENTRATION_COL + " TEXT,"
                + FORM_COL + " TEXT,"
                + GENERIC_COL + " TEXT,"
                + EHR_COL + "TEXT)";


    }

    /*
     * Checks if any fields are empty before confirming the delivery
     */
    public boolean emptyCredentials(EditText item, EditText quantity)
    {
        if  ( (item.getText().toString().isEmpty()) && (quantity.getText().toString().isEmpty()) )
        {
                item.setError("Please fill this field");
                quantity.setError("Please fill this field");

                return true;
        }
        else
        {

            if (item.getText().toString().isEmpty())
            {
                item.setError("Please fill this field");

                return true;
            }

            if (quantity.getText().toString().isEmpty())
            {
                quantity.setError("Please fill this field");

                return true;
            }
        }

        return false;
    }
}