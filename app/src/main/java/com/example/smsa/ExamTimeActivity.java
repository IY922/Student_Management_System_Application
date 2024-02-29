package com.example.smsa;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExamTimeActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ExamDbHelper examDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_time);

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


        examDbHelper = new ExamDbHelper(this);

        // Retrieve the student's USN from the intent extras
        Intent intent = getIntent();
        String studentUSN = intent.getStringExtra("usn");

        String semester = intent.getStringExtra("sem");

        ExamDbHelper dbHelper = new ExamDbHelper(ExamTimeActivity.this);
        List<ExamDetails> examDetailsList = dbHelper.getExamDetailsBySemester(semester);

        TextView textViewExamDetails = findViewById(R.id.textViewExamDetails);
        String examDetailsText = "";

        for (ExamDetails examDetails : examDetailsList) {
            examDetailsText += "Exam Name: " + examDetails.getExamName() + "\n";
            examDetailsText += "Semester: " + examDetails.getSem() + "\n";
            examDetailsText += "Branch: " + examDetails.getBranch() + "\n";
            examDetailsText += "Subject 1: " + examDetails.getSubject1Name() + " - " + examDetails.getSubject1Timetable() + "\n";
            examDetailsText += "Subject 2: " + examDetails.getSubject2Name() + " - " + examDetails.getSubject2Timetable() + "\n";
            examDetailsText += "Subject 3: " + examDetails.getSubject3Name() + " - " + examDetails.getSubject3Timetable() + "\n";
            examDetailsText += "Subject 4: " + examDetails.getSubject4Name() + " - " + examDetails.getSubject4Timetable() + "\n";
            examDetailsText += "\n";
        }

        textViewExamDetails.setText(examDetailsText);


//
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}