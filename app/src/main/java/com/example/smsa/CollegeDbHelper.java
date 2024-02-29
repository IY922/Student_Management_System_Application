package com.example.smsa;



import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CollegeDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "college_events.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "events";
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_EVENT_DESCRIPTION = "event_description";

    public CollegeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EVENT_NAME + " TEXT, " +
                COLUMN_EVENT_DESCRIPTION + " TEXT" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertEventDetails(String eventName, String eventDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, eventName);
        values.put(COLUMN_EVENT_DESCRIPTION, eventDescription);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }




    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

}
