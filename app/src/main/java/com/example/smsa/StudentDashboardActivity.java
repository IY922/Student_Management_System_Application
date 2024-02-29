package com.example.smsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);



        Button studentDetailsButton = findViewById(R.id.StudentDetails);
        Button examTimeTableButton = findViewById(R.id.btnViewExamTimeTable);
        Button circularNoticeButton = findViewById(R.id.btnViewCircularNotice);
        Button collegeEventsButton = findViewById(R.id.btnViewCollegeEvents);

        studentDetailsButton.setBackgroundColor(getResources().getColor(R.color.button));
        examTimeTableButton.setBackgroundColor(getResources().getColor(R.color.button));
        circularNoticeButton.setBackgroundColor(getResources().getColor(R.color.button));
        collegeEventsButton.setBackgroundColor(getResources().getColor(R.color.button));
















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

        // Get the student name and USN from the intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String studentName = extras.getString("studentName");
            String studentUSN = extras.getString("studentUSN");
            String studentSem = extras.getString("studentSem");

            // Set the student name in the TextView
            TextView textStudentName = findViewById(R.id.textStudentName);
            textStudentName.setText(studentName);

            // Set the student USN in another TextView
            TextView textStudentUSN = findViewById(R.id.textStudentUSN);
            textStudentUSN.setText(studentUSN);

//            Button studentDetailsButton = findViewById(R.id.StudentDetails);
            studentDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start StudentDetailActivity and pass the student USN as an intent extra
                    Intent intent = new Intent(StudentDashboardActivity.this, StudentDetailActivity.class);
                    intent.putExtra("usn", studentUSN);
                    startActivity(intent);
                }
            });


            Button btnViewExamTimeTable = findViewById(R.id.btnViewExamTimeTable);
            btnViewExamTimeTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start ExamTimeActivity and pass the student USN as an intent extra
                    Intent intent = new Intent(StudentDashboardActivity.this, ExamTimeActivity.class);
                    intent.putExtra("usn", studentUSN);
                    intent.putExtra("sem", studentSem);

                    startActivity(intent);
                }
            });

            Button btnCircularNotice = findViewById(R.id.btnViewCircularNotice);
            btnCircularNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start CircularActivity
                    Intent intent = new Intent(StudentDashboardActivity.this, CircularActivityDisplay.class);
                    startActivity(intent);
                }
            });

//            Button collegeEventsButton = findViewById(R.id.btnViewCollegeEvents);
            collegeEventsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the CollegeEventActivity
                    Intent intent = new Intent(StudentDashboardActivity.this, CollegeEventActivity.class);
                    startActivity(intent);
                }
            });


        }

        // Rest of your code...
    }




    @Override
    public void onBackPressed() {
        finish();
    }
}
