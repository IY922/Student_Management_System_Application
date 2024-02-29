package com.example.smsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView textStudentName;
    private TextView textStudentUSN;
    private TextView textStudentSemester;
    private TextView textStudentBranch;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set click listener for the back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Initialize TextViews
        textStudentName = findViewById(R.id.textStudentName);
        textStudentUSN = findViewById(R.id.textStudentUSN);
        textStudentSemester = findViewById(R.id.textStudentSemester);
        textStudentBranch = findViewById(R.id.textStudentBranch);

        // Get the student USN from the intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String studentUSN = extras.getString("usn");

            // Retrieve the student details from the database
            Student student = dbHelper.getStudentByUsn(studentUSN);

            if (student != null) {
                // Set the student details in the TextViews
                textStudentName.setText("Name: " + student.getName());
                textStudentUSN.setText("USN: " + student.getUsn());
                textStudentSemester.setText("Semester: " + student.getSem());
                textStudentBranch.setText("Branch: " + student.getBranch());
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
