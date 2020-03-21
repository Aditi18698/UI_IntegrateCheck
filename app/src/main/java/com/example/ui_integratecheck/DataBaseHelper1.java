package com.example.ui_integratecheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper1 extends SQLiteOpenHelper {
    public static final String Database_Name = "user.db";
    public static final String Table_Name="user";
    public static final String dbBT_no = "BT_no";
    public static final String dbvehicle = "vehicle";
    public static final String dbvehicle_no = "vehicle_no";
    public static final String dbtank = "tank";
    public static       String Query = "";

    public DataBaseHelper1(@Nullable Context context) {
        super(context, Database_Name, null,1);
    }

    /*public void createTable(String table_Name ){

        Log.d("Tag", " Values in DB:1 "+table_Name);
        Query="create table "+table_Name+" ( BT_no text PRIMARY KEY , vehicle text , vehicle_no text , tank text )";
    }

     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Tag", " Values in DB: 2");
        db.execSQL("create table "+Table_Name+" ( BT_no text PRIMARY KEY , vehicle text , vehicle_no text , tank text )");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public Boolean addVehicle(String BT_no,String vehicle,String vehicle_no,String tank){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues= new ContentValues();

        contantValues.put(dbBT_no,BT_no);
        contantValues.put(dbvehicle,vehicle);
        contantValues.put(dbvehicle_no,vehicle_no);
        contantValues.put(dbtank,tank);

        long result = db.insert(Table_Name,null,contantValues);

        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getVehicleData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name,null);
        return res;
    }
}
