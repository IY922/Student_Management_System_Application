package com.example.smsa;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CircularDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "circular.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CIRCULAR = "circular";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    public CircularDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_CIRCULAR + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CIRCULAR);
        onCreate(db);
    }

    public boolean insertCircularDetails(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_CIRCULAR, null, values);
        return result != -1;
    }

    public List<CircularDetails> getAllCircularDetails() {
        List<CircularDetails> circularDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns to retrieve from the table
        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION
        };

        // Perform the query
        Cursor cursor = db.query(TABLE_CIRCULAR, columns, null, null, null, null, null);

        // Iterate through the cursor to retrieve data
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int id = cursor.isNull(idIndex) ? -1 : cursor.getInt(idIndex);

            int eventNameIndex = cursor.getColumnIndex(COLUMN_NAME);
            String eventName = cursor.isNull(eventNameIndex) ? "" : cursor.getString(eventNameIndex);

            int eventDescriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            String eventDescription = cursor.isNull(eventDescriptionIndex) ? "" : cursor.getString(eventDescriptionIndex);

            CircularDetails circularDetails = new CircularDetails(id, eventName, eventDescription);
            circularDetailsList.add(circularDetails);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return circularDetailsList;
    }


}
