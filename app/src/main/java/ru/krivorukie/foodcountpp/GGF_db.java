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

public class GGF_db {
    // TODO IF YOU UPDATE THE DB STRUCTURE: realize all the DB changes here to make any version become the version you need
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
    public  String[] getNavRailSettings(Context context){

        MySQLDB dbHelper = new MySQLDB(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int amount;
        String data ;
        String[] projection = {"value"};
        Cursor cursor = db.rawQuery("SELECT value FROM listing_settings", projection);
        data = cursor.getString(0);
        //amount = ...
        cursor.close();
        for(String i: data.split(" "))
        Toast.makeText(context, i, Toast.LENGTH_LONG).show();

        return  data.split(" ");
    }

// TODO figure out how to get the cell by name




    // DB itself
    // tables:
    // [ listing_settings ] -- all the settings requiring to remember linear manifold of parameters (presented as splitable String)
    // [ item_settings ] -- settings that need only one parameter to keep (string, as it has to keep numbers and texts at the same time)
    // [ products_total ] --
    // [ carts ] --
    // [ current_carts ] --
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
            db.execSQL(productsTotal);   //TODO realize keys adjustment when internet connection appears so they don't mismatch with global id's

// carts that user saved (all, including scheduled)
// field "contains" is string in format of id's listing divided by " "
            String carts = "CREATE TABLE carts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "time TEXT NOT NULL, " +
                    "contains TEXT" +
                    ")";
            db.execSQL(carts);


// carts that are in progress (scheduled)
// field "contains" is string in format of id's listing divided by " "
            String current_carts = "CREATE TABLE current_carts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "time TEXT NOT NULL, " +
                    "contains TEXT," +
                    "expires TEXT," +
                    "FOREIGN KEY (id) REFERENCES carts (id) " +
                    ")";
            db.execSQL(current_carts);


// products that user recently bought, all have the expiry date
            String fridge = "CREATE TABLE fridge(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "expires TEXT," +
                    "FOREIGN KEY (id) REFERENCES carts (id) " +
                    ")";
            db.execSQL(fridge);
        };



        @Override
        public void onUpgrade(SQLiteDatabase db,int i,int ii){
            patch(db, this);
        }
    };
}



