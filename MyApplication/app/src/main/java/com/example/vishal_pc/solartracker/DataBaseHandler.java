package com.example.vishal_pc.solartracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by VISHAL-PC on 5/15/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper{

    private final ArrayList<Values> noteList= new ArrayList<>();

    public DataBaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_VALUES_TABLE="CREATE TABLE "+Constants.TABLE_NAME+
                "("+Constants.KEY_ID+" INTEGER PRIMARY KEY, "+Constants.S1_NAME+
                " TEXT, "+Constants.S2_NAME+" TEXT, "+Constants.S3_NAME+
                " TEXT, "+Constants.S4_NAME+" TEXT, "+Constants.M1_NAME+
                " TEXT, "+Constants.M2_NAME+" TEXT, "+Constants.SOLAR_NAME+" TEXT, "+Constants.DATE_NAME+
                " LONG);";
        db.execSQL(CREATE_VALUES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);

        //To create new one.
        onCreate(db);

    }


    public void addValues(Values val)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(Constants.S1_NAME,val.getS1());
        values.put(Constants.S2_NAME,val.getS2());
        values.put(Constants.S3_NAME,val.getS3());
        values.put(Constants.S4_NAME,val.getS4());
        values.put(Constants.M1_NAME,val.getM1());
        values.put(Constants.M2_NAME,val.getM2());
        values.put(Constants.SOLAR_NAME,val.getSolar());
        values.put(Constants.DATE_NAME, java.lang.System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME,null,values);

        db.close();
    }

    public ArrayList<Values> getValues()
    {
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor= db.query(Constants.TABLE_NAME,new String[]{Constants.KEY_ID,
                        Constants.S1_NAME,Constants.S2_NAME,Constants.S3_NAME,
                Constants.S4_NAME,Constants.M1_NAME,Constants.M2_NAME,Constants.SOLAR_NAME,Constants.DATE_NAME},
                null,null,null,null, Constants.DATE_NAME+" DESC");

        if(cursor.moveToFirst())
        {
            do {
                Values val=new Values();
                val.setS1(cursor.getString(cursor.getColumnIndex(Constants.S1_NAME)));
                val.setS2(cursor.getString(cursor.getColumnIndex(Constants.S2_NAME)));
                val.setS3(cursor.getString(cursor.getColumnIndex(Constants.S3_NAME)));
                val.setS4(cursor.getString(cursor.getColumnIndex(Constants.S4_NAME)));
                val.setM1(cursor.getString(cursor.getColumnIndex(Constants.M1_NAME)));
                val.setM2(cursor.getString(cursor.getColumnIndex(Constants.M2_NAME)));
                val.setSolar(cursor.getString(cursor.getColumnIndex(Constants.SOLAR_NAME)));
                val.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                java.text.DateFormat dateformat= java.text.DateFormat.getDateInstance();
                String dateData = dateformat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.DATE_NAME))).getTime());

                val.setRecordDate(dateData);

                noteList.add(val);

            }while(cursor.moveToNext());
        }
        return noteList;
    }

}
