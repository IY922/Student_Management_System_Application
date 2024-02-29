package com.example.smsa;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextUSN;
    private Button buttonLogin;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setBackgroundColor(getResources().getColor(R.color.button));

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

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextUSN = findViewById(R.id.editTextUSN);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Set click listener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Handle the back button press
        finish();
    }

    private void login() {
        String name = editTextName.getText().toString().trim().toUpperCase();
        String usn = editTextUSN.getText().toString().trim().toUpperCase();

        if (name.isEmpty() || usn.isEmpty()) {
            Toast.makeText(this, "Please enter name and USN", Toast.LENGTH_SHORT).show();
        } else {
            Student student = dbHelper.getStudentByUsn(usn);

            if (student != null) {
                // Display the student details in the textViewResult
                String sem = student.getSem().toUpperCase();

                // Login successful
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

                // Create intent to start the StudentDashboardActivity
                Intent intent = new Intent(StudentActivity.this, StudentDashboardActivity.class);

                // Pass student name, USN, branch, and sem as extras
                intent.putExtra("studentName", name);
                intent.putExtra("studentUSN", usn);
                intent.putExtra("studentSem", sem);

                startActivity(intent);
                finish();
            } else {
                // Invalid login
                Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
