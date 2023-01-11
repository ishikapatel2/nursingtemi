package com.example.nursingtemi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nursingtemi.classes.Medicine;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String MEDICINE_BRAND = "MEDICINE_BRAND";
    public static final String MEDICINE_TABLE = "MEDICINE_TABLE";
    public static final String MEDICINE_CONCENTRATION = "MEDICINE_CONCENTRATION";
    public static final String GENERIC_NAME = "GENERIC_NAME";
    public static final String MEDICINE_BARCODE = "MEDICINE_BARCODE";
    public static final String MEDICINE_QR = "MEDICINE_QR";
    public static final String MEDICINE_FORM = "MEDICINE_FORM";
    public static final String ID = "ID";


    public DatabaseHelper(@Nullable Context context, @Nullable String name,  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + " " + MEDICINE_TABLE + " " + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MEDICINE_BRAND + " TEXT, " + MEDICINE_CONCENTRATION + " " + " TEXT, " + " " + MEDICINE_FORM + " " + " TEXT, "
                + " " + GENERIC_NAME + " TEXT, " + MEDICINE_BARCODE + " TEXT, " + MEDICINE_QR + " " + "TEXT) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean add (Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MEDICINE_BRAND, medicine.getBrand());
        cv.put(MEDICINE_CONCENTRATION, medicine.getConcentration());
        cv.put(GENERIC_NAME, medicine.getName());

        long insert = db.insert(MEDICINE_TABLE,null,cv);
            if (insert == -1){
                return false;
            }
            else{
                return true;
            }
    }
}
