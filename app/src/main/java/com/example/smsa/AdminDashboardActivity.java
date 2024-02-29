package com.example.smsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button btnAddStudent = findViewById(R.id.btnAddStudent);
        Button btnEditStudent = findViewById(R.id.btnEditStudent);
        Button btnExamTimeTable = findViewById(R.id.btnExamTimeTable);
        Button btnCircularNotice = findViewById(R.id.btnCircularNotice);
        Button btnCollegeEvents = findViewById(R.id.btnCollegeEvents);



        btnAddStudent.setBackgroundColor(getResources().getColor(R.color.button));


        btnEditStudent.setBackgroundColor(getResources().getColor(R.color.button));

        btnExamTimeTable.setBackgroundColor(getResources().getColor(R.color.button));

        btnAddStudent.setBackgroundColor(getResources().getColor(R.color.button));

        btnCircularNotice.setBackgroundColor(getResources().getColor(R.color.button));

        btnCollegeEvents.setBackgroundColor(getResources().getColor(R.color.button));









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

        // Set click listeners for the functionality buttons























        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Add Student" button click
                Intent intent = new Intent(AdminDashboardActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Edit Student button
        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, EditStudentActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Exam Timetable button
        btnExamTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });

        btnCircularNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CircularActivity
                Intent intent = new Intent(AdminDashboardActivity.this, CircularActivity.class);
                startActivity(intent);
                                                 }
        });

        btnCollegeEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, CollegeActivity.class);
                startActivity(intent);
            }
        });





        // Implement click listeners for other functionality buttons

    }

    // Override onBackPressed to navigate back to the AdminActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminDashboardActivity.this, AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish(); // Optional, if you want to finish the current activity after navigation
    }



}
