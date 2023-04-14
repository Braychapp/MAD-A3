package com.example.tripplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


// Following along from https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/
public class DBHandler extends SQLiteOpenHelper{

    // Defining constant for logging tag
    private static final String TAG = "DBHandler";

    // Defining constants for database
    public static final String  DB_NAME = "tripPlanner.db";
    public static final int     DB_VERSION = 1;

    // Defining column names for tables


    public static final String ID_COL = "id";           // name of the column id
    public static final String START_COL = "starting";  // name of starting location column
    public static final String END_COL = "ending";      // name of ending location column
    public static final String DATE_COL = "date";       // name of date column
    public static final String METHOD_COL = "method";   // name of transportation method column


    // Defining the names of the tables
    //public static final String  TABLE_NAME_DATE = "dateTable";
    //public static final String  TABLE_NAME_TRANSPORT = "transportTable";
    public static final String  TABLE_NAME_FINAL = "finalTable";

    // Create a constructor for the class
    public DBHandler (Context context) {
         super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Create a table with column names and data types
        String query = "CREATE TABLE " + TABLE_NAME_FINAL + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + START_COL + " TEXT,"
                + END_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + METHOD_COL + " TEXT)";

        Log.d(TAG, "Creating table for database");

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // This is called when we want to check if the table already exists

        Log.d(TAG, "Upgrading db from version" + oldVersion + "to" + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FINAL);
        onCreate(db);
    }

    // Method to add a new trip to the database
    // Taken mostly from https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/
    public void addNewTrip (String tripStart, String tripEnd, String tripDate, String tripMethod){

        // Creating an SQLite database variable to write into
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues variable to add to database table
        ContentValues values = new ContentValues();

        // Getting the values in key-value pair
        values.put(START_COL, tripStart);
        values.put(END_COL, tripEnd);
        values.put(DATE_COL, tripDate);
        values.put(METHOD_COL, tripMethod);

        // All the values are added, pass them into the database
        db.insert(TABLE_NAME_FINAL, null, values);

        // Close the database now
        db.close();
    }

    // Method to read all the trips in the database
    // Taken mostly from https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/
    public ArrayList<TripModal> readTrips()
    {
        // get a variable for a readable database
        SQLiteDatabase db = this.getReadableDatabase();

        // create a cursor to read from the database
        Cursor cursorTrips = db.rawQuery("SELECT * FROM " + TABLE_NAME_FINAL, null);

        // creating a new array list
        ArrayList<TripModal> tripModalArrayList = new ArrayList<>();

        // move the cursor to the first position of the array
        if (cursorTrips.moveToFirst()){
            do {
                // add data to array list from cursor
                tripModalArrayList.add(new TripModal(
                        cursorTrips.getString(1),
                        cursorTrips.getString(2),
                        cursorTrips.getString(3),
                        cursorTrips.getString(4)));

                // move to the next cursor
            } while (cursorTrips.moveToNext());

        }
        // close the cursor
        cursorTrips.close();

        // return array list holding data
        return tripModalArrayList;
    }
}
