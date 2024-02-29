package com.example.smsa;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextStudentId, editTextName, editTextSem, editTextBranch, editTextUsn;
    private Button btnUpload;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        Button btnUpload = findViewById(R.id.btnUpload);

        btnUpload.setBackgroundColor(getResources().getColor(R.color.button));















        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        // Set click listener for the back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize views
        editTextStudentId = findViewById(R.id.editTextStudentId);
        editTextName = findViewById(R.id.editTextName);
        editTextSem = findViewById(R.id.editTextSem);
        editTextBranch = findViewById(R.id.editTextBranch);
        editTextUsn = findViewById(R.id.editTextUsn);
        btnUpload = findViewById(R.id.btnUpload);

        // Create an instance of the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set click listener for upload button
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered student details
                String studentId = editTextStudentId.getText().toString().trim().toUpperCase();
                String name = editTextName.getText().toString().trim().toUpperCase();
                String sem = editTextSem.getText().toString().trim().toUpperCase();
                String branch = editTextBranch.getText().toString().trim().toUpperCase();
                String usn = editTextUsn.getText().toString().trim().toUpperCase();

                // Validate the entered details (You can add your own validation logic)
                if (studentId.isEmpty() || name.isEmpty() || sem.isEmpty() || branch.isEmpty() || usn.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check for duplicate entries
                if (databaseHelper.isStudentExists(studentId, usn)) {
                    Toast.makeText(AddStudentActivity.this, "Student with the same ID or USN already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the student details in the database
                long result = databaseHelper.addStudent(studentId, name, sem, branch, usn);
                databaseHelper.close();

                if (result != -1) {
                    // Show a success message
                    Toast.makeText(AddStudentActivity.this, "Student details uploaded successfully", Toast.LENGTH_SHORT).show();

                    // Clear the input fields
                    editTextStudentId.setText("");
                    editTextName.setText("");
                    editTextSem.setText("");
                    editTextBranch.setText("");
                    editTextUsn.setText("");
                    onBackPressed();
                } else {
                    // Show an error message if the insertion failed
                    Toast.makeText(AddStudentActivity.this, "Failed to upload student details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Override onBackPressed to navigate back to the AdminDashboardActivity
    @Override
    public void onBackPressed() {
        finish(); // Optional, if you want to finish the current activity after navigation
    }
}
