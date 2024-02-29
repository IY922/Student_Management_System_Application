package com.example.smsa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smsa.Student;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {

    private EditText editTextUsn;
    private EditText editTextSem;
    private EditText editTextBranch;
    private Button btnSearchByUsn;
    private Button btnSearchByCriteria;
    private Button btnNext;

    private DatabaseHelper databaseHelper;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);


        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setBackgroundColor(getResources().getColor(R.color.button));

        Button btnSearchByUsn = findViewById(R.id.btnSearchByUsn);

        btnSearchByUsn.setBackgroundColor(getResources().getColor(R.color.button));

        Button btnSearchByCriteria = findViewById(R.id.btnSearchByCriteria);

        btnSearchByCriteria.setBackgroundColor(getResources().getColor(R.color.button));




















        // Initialize the TextView
        textViewResult = findViewById(R.id.textViewResult);

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
        editTextUsn = findViewById(R.id.editTextUsn);
        editTextSem = findViewById(R.id.editTextSem);
        editTextBranch = findViewById(R.id.editTextBranch);
        btnSearchByUsn = findViewById(R.id.btnSearchByUsn);
        btnSearchByCriteria = findViewById(R.id.btnSearchByCriteria);
        btnNext = findViewById(R.id.btnNext);

        // Create an instance of the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set click listener for Search by USN button
        btnSearchByUsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = editTextUsn.getText().toString().trim().toUpperCase();

                if (!usn.isEmpty()) {
                    // Check if the USN exists in the database
                    if (databaseHelper.isUsnExists(usn)) {
                        // Retrieve the student details from the database
                        Student student = databaseHelper.getStudentByUsn(usn);

                        // Display the student details in the textViewResult
                        String result = "Id: " + student.getId() + "\n";
                        result += "Name: " + student.getName() + "\n";
                        result += "USN: " + student.getUsn() + "\n";
                        result += "Semester: " + student.getSem() + "\n";
                        result += "Branch: " + student.getBranch();

                        textViewResult.setText(result);
                    } else {
                        // Show a message if the USN doesn't exist in the database
                        Toast.makeText(EditStudentActivity.this, "No student found with the provided USN", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show a message if USN is not provided
                    Toast.makeText(EditStudentActivity.this, "Provide a USN", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set click listener for Search by Criteria button
        btnSearchByCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sem = editTextSem.getText().toString().trim().toUpperCase();
                String branch = editTextBranch.getText().toString().trim().toUpperCase();

                if (!sem.isEmpty() && !branch.isEmpty()) {
                    // Both semester and branch are provided
                    List<Student> students = databaseHelper.getStudentsBySemAndBranch(sem, branch);
                    // Perform the desired action with the retrieved students list (e.g., display details)
                    if (students.isEmpty()) {
                        // No matching students found
                        // Display an appropriate message or handle the case
                        textViewResult.setText("No students found with the provided semester and branch.");
                    } else {
                        // Matching students found
                        StringBuilder resultBuilder = new StringBuilder();
                        for (Student student : students) {
                            // Append student details to the result string
                            resultBuilder.append("ID: ").append(student.getId()).append("\n");
                            resultBuilder.append("Name: ").append(student.getName()).append("\n");
                            resultBuilder.append("USN: ").append(student.getUsn()).append("\n");
                            resultBuilder.append("Semester: ").append(student.getSem()).append("\n");
                            resultBuilder.append("Branch: ").append(student.getBranch()).append("\n\n");
                        }
                        textViewResult.setText(resultBuilder.toString());
                    }
                } else if (!sem.isEmpty()) {
                    // Only semester is provided
                    List<Student> students = databaseHelper.getStudentsBySem(sem);
                    // Perform the desired action with the retrieved students list (e.g., display details)
                    if (students.isEmpty()) {
                        // No matching students found
                        // Display an appropriate message or handle the case
                        textViewResult.setText("No students found with the provided semester.");
                    } else {
                        // Matching students found
                        StringBuilder resultBuilder = new StringBuilder();
                        for (Student student : students) {
                            // Append student details to the result string
                            resultBuilder.append("ID: ").append(student.getId()).append("\n");
                            resultBuilder.append("Name: ").append(student.getName()).append("\n");
                            resultBuilder.append("USN: ").append(student.getUsn()).append("\n");
                            resultBuilder.append("Semester: ").append(student.getSem()).append("\n");
                            resultBuilder.append("Branch: ").append(student.getBranch()).append("\n\n");
                        }
                        textViewResult.setText(resultBuilder.toString());
                    }
                } else if (!branch.isEmpty()) {
                    // Only branch is provided
                    List<Student> students = databaseHelper.getStudentsByBranch(branch);
                    // Perform the desired action with the retrieved students list (e.g., display details)
                    if (students.isEmpty()) {
                        // No matching students found
                        // Display an appropriate message or handle the case
                        textViewResult.setText("No students found with the provided branch.");
                    } else {
                        // Matching students found
                        StringBuilder resultBuilder = new StringBuilder();
                        for (Student student : students) {
                            // Append student details to the result string
                            resultBuilder.append("ID: ").append(student.getId()).append("\n");
                            resultBuilder.append("Name: ").append(student.getName()).append("\n");
                            resultBuilder.append("USN: ").append(student.getUsn()).append("\n");
                            resultBuilder.append("Semester: ").append(student.getSem()).append("\n");
                            resultBuilder.append("Branch: ").append(student.getBranch()).append("\n\n");
                        }
                        textViewResult.setText(resultBuilder.toString());
                    }
                } else {
                    // Neither semester nor branch is provided
                    // Display an appropriate message or handle the error case
                    textViewResult.setText("Please provide a semester, a branch, or both.");
                }
            }
        });






        // Set click listener for Next button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = editTextUsn.getText().toString().trim().toUpperCase();

                if (!usn.isEmpty()) {
                    // Check if the USN exists in the database
                    if (databaseHelper.isUsnExists(usn)) {
                        // Create an Intent to navigate to the FinalEditStudentActivity
                        Intent intent = new Intent(EditStudentActivity.this, FinalEditStudentActivity.class);
                        // Pass the USN value to the next activity
                        intent.putExtra("usn", usn);
                        // Start the next activity
                        startActivity(intent);
                        finish();
                    } else {
                        // Show a message if the USN doesn't exist in the database
                        Toast.makeText(EditStudentActivity.this, "No student found with the provided USN", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show a message if USN is not provided
                    Toast.makeText(EditStudentActivity.this, "Provide a USN", Toast.LENGTH_SHORT).show();
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
