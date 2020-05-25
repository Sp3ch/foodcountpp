package ru.krivorukie.foodcountpp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GGF_DB {
    // TODO IF YOU UPDATE THE DB STRUCTURE: realize all the DB changes here to make any version become the version you neeв
    // classer -- the variable to cast the SQLiteOpenHelper methods on
    public static void patch(SQLiteDatabase db, MySQLDB classer){ // void to call from onUpgrade of DBs if update is required


        classer.onCreate(db);
    };


    public boolean addProduct(){

        return true;
    }
    public boolean setNavRailSettings(int[] settings, int amount){

        return true;
    }
    public  String[] getNavRailSettings(Context context){//TODO parse

        MySQLDB dbHelper = new MySQLDB(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int amount;
        String data ;
        String[] projection = {
                "value"
        };
        Cursor cursor = db.rawQuery("SELECT value FROM listing_settings", projection); // TODO figure out how to get the cell by name
        data = cursor.getString(0);
        //amount = ...
        cursor.close();
        for(String i: data.split(" "))
            Toast.makeText(context, i, Toast.LENGTH_LONG).show();

        return  data.split(" ");
    }






    //DB itself
    //
    //
    //
    //
    //
    //

    public class MySQLDB extends SQLiteOpenHelper {

        private static final String ggf_db="GGF_DB";
        private static final int version=1;
        public MySQLDB(Context context){
            super(context,ggf_db,null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
// all app settings, stored as "settings category name - > large string of settings content" [ hard code square pants ) ]
            String listingSettings = "CREATE TABLE listing_settings(" +
                    "id  INTEGER PRIMARY KEY, " +
                    "value TEXT NOT NULL)";
            db.execSQL(listingSettings);

//TODO realize keys adjustment in

// the table of all prods user has saved on the device. The keys are to be adjusted so they match the proper keys on the global server
            String productsTotal="CREATE TABLE products_total(" +
                    "id  INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "weight INTEGER, " +
                    "price INTEGER, " +
                    "proteins INTEGER, " +
                    "fats INTEGER, " +
                    "carbohydrates INTEGER," +
                    "UNIQUE (id) " +
                    ")";
            db.execSQL(productsTotal);

            // total products user has
            String carts = "CREATE TABLE carts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "time TEXT NOT NULL, " +
                    "contains TEXT" +
                    ")";
            db.execSQL(carts);

            String current_carts = "CREATE TABLE current_carts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "time TEXT NOT NULL, " +
                    "contains TEXT," +
                    "expires TEXT," +
                    "FOREIGN KEY (id) REFERENCES carts (id) " +
                    ")";
            db.execSQL(current_carts);
        };



        @Override
        public void onUpgrade(SQLiteDatabase db,int i,int ii){
            patch(db, this);
        }
    };
}



