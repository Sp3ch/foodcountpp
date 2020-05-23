package ru.krivorukie.foodcountpp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

public class MySQL{



    public boolean addProduct(){

        return true;
    }
    public boolean setNavRailSettings(int[] settings, int amount){

        return true;
    }
    public String[] getNavRailSettings(Context context){//TODO parse

        MySQLDB dbHelper = new MySQLDB(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int amount; String[] settings; String iterator=" ";
        String data ;
        String[] projection = {
                "value"
        };
        Cursor cursor = db.rawQuery("SELECT value FROM listing_settings", projection); // TODO figure out how to get the cell by name
        data = cursor.getString(0);
        //amount = ...
        settings = new int[amount];
        cursor.close();
        return settings;
    }
}

    public class MySQLDB extends SQLiteOpenHelper {

        private static final String GGF_DB="GGF_DB";
        private static final int version=0;

        public MySQLDB(Context context){
            super(context,GGF_DB,null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){

            String listingSettings = "CREATE TABLE listing_settings(id  INTEGER PRIMARY KEY, value TEXT)";
            db.execSQL(listingSettings);

            String productsTotal="CREATE TABLE products_total(id  INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, weight INTEGER, price INTEGER, proteins INTEGER, fats INTEGER, carbohydrates INTEGER)";
            db.execSQL(productsTotal);
        };
        @Override
        public void onUpgrade(SQLiteDatabase db,int i,int ii){
            onCreate(db);
        }



};

