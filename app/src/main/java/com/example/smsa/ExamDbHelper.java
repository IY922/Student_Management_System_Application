package com.example.smsa;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ExamDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "exam.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EXAM = "exam";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EXAM_NAME = "exam_name";
    private static final String COLUMN_SEM = "sem";
    private static final String COLUMN_BRANCH = "branch";
    private static final String COLUMN_SUBJECT1_NAME = "subject1_name";
    private static final String COLUMN_SUBJECT1_TIMETABLE = "subject1_timetable";
    private static final String COLUMN_SUBJECT2_NAME = "subject2_name";
    private static final String COLUMN_SUBJECT2_TIMETABLE = "subject2_timetable";
    private static final String COLUMN_SUBJECT3_NAME = "subject3_name";
    private static final String COLUMN_SUBJECT3_TIMETABLE = "subject3_timetable";
    private static final String COLUMN_SUBJECT4_NAME = "subject4_name";
    private static final String COLUMN_SUBJECT4_TIMETABLE = "subject4_timetable";

    public ExamDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_EXAM + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXAM_NAME + " TEXT, " +
                COLUMN_SEM + " TEXT, " +
                COLUMN_BRANCH + " TEXT, " +
                COLUMN_SUBJECT1_NAME + " TEXT, " +
                COLUMN_SUBJECT1_TIMETABLE + " TEXT, " +
                COLUMN_SUBJECT2_NAME + " TEXT, " +
                COLUMN_SUBJECT2_TIMETABLE + " TEXT, " +
                COLUMN_SUBJECT3_NAME + " TEXT, " +
                COLUMN_SUBJECT3_TIMETABLE + " TEXT, " +
                COLUMN_SUBJECT4_NAME + " TEXT, " +
                COLUMN_SUBJECT4_TIMETABLE + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        onCreate(db);
    }
    public boolean insertExamDetails(ExamDetails examDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXAM_NAME, examDetails.getExamName());
        values.put(COLUMN_SEM, examDetails.getSem());
        values.put(COLUMN_BRANCH, examDetails.getBranch());
        values.put(COLUMN_SUBJECT1_NAME, examDetails.getSubject1Name());
        values.put(COLUMN_SUBJECT1_TIMETABLE, examDetails.getSubject1Timetable());
        values.put(COLUMN_SUBJECT2_NAME, examDetails.getSubject2Name());
        values.put(COLUMN_SUBJECT2_TIMETABLE, examDetails.getSubject2Timetable());
        values.put(COLUMN_SUBJECT3_NAME, examDetails.getSubject3Name());
        values.put(COLUMN_SUBJECT3_TIMETABLE, examDetails.getSubject3Timetable());
        values.put(COLUMN_SUBJECT4_NAME, examDetails.getSubject4Name());
        values.put(COLUMN_SUBJECT4_TIMETABLE, examDetails.getSubject4Timetable());

        long result = db.insert(TABLE_EXAM, null, values);
        return result != -1;
    }

    public List<ExamDetails> getExamDetailsBySemester(String semester) {
        List<ExamDetails> examDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns to retrieve from the table
        String[] columns = {
                COLUMN_EXAM_NAME,
                COLUMN_SEM,
                COLUMN_BRANCH,
                COLUMN_SUBJECT1_NAME,
                COLUMN_SUBJECT1_TIMETABLE,
                COLUMN_SUBJECT2_NAME,
                COLUMN_SUBJECT2_TIMETABLE,
                COLUMN_SUBJECT3_NAME,
                COLUMN_SUBJECT3_TIMETABLE,
                COLUMN_SUBJECT4_NAME,
                COLUMN_SUBJECT4_TIMETABLE
        };

        // Define the selection criteria
        String selection = COLUMN_SEM + " = ?";
        String[] selectionArgs = {semester};

        // Query the table based on the selection criteria
        Cursor cursor = db.query(
                TABLE_EXAM,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Iterate over the cursor and retrieve the exam details
        if (cursor.moveToFirst()) {
            do {
                String examName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXAM_NAME));
                String sem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SEM));
                String branch = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BRANCH));
                String subject1Name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT1_NAME));
                String subject1Timetable = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT1_TIMETABLE));
                String subject2Name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT2_NAME));
                String subject2Timetable = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT2_TIMETABLE));
                String subject3Name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT3_NAME));
                String subject3Timetable = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT3_TIMETABLE));
                String subject4Name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT4_NAME));
                String subject4Timetable = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT4_TIMETABLE));

                ExamDetails examDetails = new ExamDetails(examName, sem, branch, subject1Name, subject1Timetable,
                        subject2Name, subject2Timetable, subject3Name, subject3Timetable, subject4Name, subject4Timetable);

                examDetailsList.add(examDetails);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();

        return examDetailsList;
    }










    // ... Other code in the ExamDbHelper class ...
}