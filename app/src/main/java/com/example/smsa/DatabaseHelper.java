package com.example.smsa;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDB";
    private static final String TABLE_NAME = "Students";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_USN = "usn";
    private static final String COL_SEM = "sem";
    private static final String COL_BRANCH = "branch";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_USN + " TEXT, " +
                COL_SEM + " TEXT, " +
                COL_BRANCH + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addStudent(String studentId, String name, String sem, String branch, String usn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ID, studentId);
        values.put(COL_NAME, name);
        values.put(COL_SEM, sem);
        values.put(COL_BRANCH, branch);
        values.put(COL_USN, usn);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result;
    }

    public boolean isStudentExists(String studentId, String usn) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? OR " + COL_USN + " = ?";
            cursor = db.rawQuery(query, new String[]{studentId, usn});
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public boolean isUsnExists(String usn) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID};
        String selection = COL_USN + " = ?";
        String[] selectionArgs = {usn};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();

        return exists;
    }

    public List<Student> getStudentsBySem(String sem) {
        List<Student> studentList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID, COL_NAME, COL_USN, COL_SEM, COL_BRANCH};
        String selection = COL_SEM + " = ?";
        String[] selectionArgs = {sem};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COL_ID);
                int nameIndex = cursor.getColumnIndex(COL_NAME);
                int usnIndex = cursor.getColumnIndex(COL_USN);
                int semIndex = cursor.getColumnIndex(COL_SEM);
                int branchIndex = cursor.getColumnIndex(COL_BRANCH);

                String id = (idIndex != -1) ? cursor.getString(idIndex) : "";
                String name = (nameIndex != -1) ? cursor.getString(nameIndex) : "";
                String usn = (usnIndex != -1) ? cursor.getString(usnIndex) : "";
                String semester = (semIndex != -1) ? cursor.getString(semIndex) : "";
                String branch = (branchIndex != -1) ? cursor.getString(branchIndex) : "";

                Student student = new Student(id, name, usn, semester, branch);
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return studentList;
    }

    public List<Student> getStudentsByBranch(String branch) {
        List<Student> studentList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID, COL_NAME, COL_USN, COL_SEM, COL_BRANCH};
        String selection = COL_BRANCH + " = ?";
        String[] selectionArgs = {branch};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COL_ID);
                int nameIndex = cursor.getColumnIndex(COL_NAME);
                int usnIndex = cursor.getColumnIndex(COL_USN);
                int semIndex = cursor.getColumnIndex(COL_SEM);
                int branchIndex = cursor.getColumnIndex(COL_BRANCH);

                String id = (idIndex != -1) ? cursor.getString(idIndex) : "";
                String name = (nameIndex != -1) ? cursor.getString(nameIndex) : "";
                String usn = (usnIndex != -1) ? cursor.getString(usnIndex) : "";
                String semester = (semIndex != -1) ? cursor.getString(semIndex) : "";
                String studentBranch = (branchIndex != -1) ? cursor.getString(branchIndex) : "";

                Student student = new Student(id, name, usn, semester, studentBranch);
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return studentList;
    }

    public List<Student> getStudentsBySemAndBranch(String sem, String branch) {
        List<Student> studentList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID, COL_NAME, COL_USN, COL_SEM, COL_BRANCH};
        String selection = COL_SEM + " = ? AND " + COL_BRANCH + " = ?";
        String[] selectionArgs = {sem, branch};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COL_ID);
                int nameIndex = cursor.getColumnIndex(COL_NAME);
                int usnIndex = cursor.getColumnIndex(COL_USN);
                int semIndex = cursor.getColumnIndex(COL_SEM);
                int branchIndex = cursor.getColumnIndex(COL_BRANCH);

                String id = (idIndex != -1) ? cursor.getString(idIndex) : "";
                String name = (nameIndex != -1) ? cursor.getString(nameIndex) : "";
                String usn = (usnIndex != -1) ? cursor.getString(usnIndex) : "";
                String semester = (semIndex != -1) ? cursor.getString(semIndex) : "";
                String studentBranch = (branchIndex != -1) ? cursor.getString(branchIndex) : "";

                Student student = new Student(id, name, usn, semester, studentBranch);
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return studentList;
    }

    public Student getStudentByUsn(String usn) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COL_ID, COL_NAME, COL_USN, COL_SEM, COL_BRANCH};
        String selection = COL_USN + " = ?";
        String[] selectionArgs = {usn};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        Student student = null;

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COL_ID);
            int nameIndex = cursor.getColumnIndex(COL_NAME);
            int usnIndex = cursor.getColumnIndex(COL_USN);
            int semIndex = cursor.getColumnIndex(COL_SEM);
            int branchIndex = cursor.getColumnIndex(COL_BRANCH);

            String id = (idIndex != -1) ? cursor.getString(idIndex) : "";
            String name = (nameIndex != -1) ? cursor.getString(nameIndex) : "";
            String semester = (semIndex != -1) ? cursor.getString(semIndex) : "";
            String branch = (branchIndex != -1) ? cursor.getString(branchIndex) : "";

            student = new Student(id, name, usn, semester, branch);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return student;
    }
    public int updateStudentDetails(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, student.getName());
        values.put(COL_SEM, student.getSem());
        values.put(COL_BRANCH, student.getBranch());

        String whereClause = COL_USN + " = ?";
        String[] whereArgs = {student.getUsn()};

        int rowsAffected = db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();

        return rowsAffected;
    }

    public boolean isStudentExistsByNameAndUSN(String name, String usn) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ? AND " + COL_USN + " = ?";
            cursor = db.rawQuery(query, new String[]{name, usn});
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public String getStudentSemester(String studentUSN) {
        String semester = "";  // Default value indicating an error or no semester found

        SQLiteDatabase db = this.getReadableDatabase();

        // Query the database to retrieve the student's semester based on their USN
        Cursor cursor = db.rawQuery("SELECT semester FROM students WHERE usn = ?", new String[]{studentUSN});

        if (cursor != null && cursor.moveToFirst()) {
            int semesterIndex = cursor.getColumnIndex("semester");
            if (semesterIndex != -1) {
                // Retrieve the semester value from the cursor
                semester = cursor.getString(semesterIndex);
            }
        }

        // Close the cursor and database connection
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return semester;
    }







}