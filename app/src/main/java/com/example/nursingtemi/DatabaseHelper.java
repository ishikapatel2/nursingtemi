package com.example.nursingtemi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "VitalsDatabase";
    private static final int DATABASE_VERSION = 2;
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Static method to get the singleton instance of the class
    public static synchronized DatabaseHelper getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE vitals (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "patient TEXT," +
                "heart_rate TEXT," +
                "temperature TEXT," +
                "blood_pressure TEXT," +
                "oxygen_saturation TEXT" + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d("DB_UPGRADE", "Upgrading database from version " + oldVersion + " to " + newVersion);
        if (oldVersion < 2) {
            // Upgrade logic from version 1 to 2
            db.execSQL("ALTER TABLE vitals ADD COLUMN patient TEXT");
        }
    }

    public int getDatabaseVersion() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.getVersion();
    }

    public void deleteRecord(Record r) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("vitals", "id=?", new String[]{String.valueOf(r.getId())});
        db.close();
    }

    public List<String> getAllVitals() {
        List<String> vitalsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("vitals", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String patientName = cursor.getString(cursor.getColumnIndexOrThrow("patient"));
                String heartRate = cursor.getString(cursor.getColumnIndexOrThrow("heart_rate"));
                String temperature = cursor.getString(cursor.getColumnIndexOrThrow("temperature"));
                String bloodPressure = cursor.getString(cursor.getColumnIndexOrThrow("blood_pressure"));
                String oxygenSaturation = cursor.getString(cursor.getColumnIndexOrThrow("oxygen_saturation"));

                String vitalRecord = "Patient: " + patientName +
                        ", Heart Rate: " + heartRate +
                        ", Temperature: " + temperature +
                        ", Blood Pressure: " + bloodPressure +
                        ", Oxygen Saturation: " + oxygenSaturation +
                        ", ID: " + id;

                Log.d("DatabaseHelper", "Retrieved record: " + vitalRecord);
                vitalsList.add(vitalRecord);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vitalsList;
    }
}
