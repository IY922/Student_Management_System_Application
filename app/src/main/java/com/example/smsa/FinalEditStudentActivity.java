package com.example.smsa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class FinalEditStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextSem, editTextBranch;
    private TextView textViewStudentId, textViewUsn;
    private Button btnUpload;
    private DatabaseHelper databaseHelper;
    private Student student;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_edit_student);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btnUpload = findViewById(R.id.btnUpload);

        btnUpload.setBackgroundColor(getResources().getColor(R.color.button));














        // Initialize views
        textViewStudentId = findViewById(R.id.textViewStudentId);
        textViewUsn = findViewById(R.id.textViewUsn);
        editTextName = findViewById(R.id.editTextName);
        editTextSem = findViewById(R.id.editTextSem);
        editTextBranch = findViewById(R.id.editTextBranch);
        btnUpload = findViewById(R.id.btnUpload);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Get the USN value from the intent
        String usn = getIntent().getStringExtra("usn");

        // Retrieve the student details from the database
        student = databaseHelper.getStudentByUsn(usn);

        // Set the student details in the UI elements
        textViewStudentId.setText("Student ID: " + student.getId());
        textViewUsn.setText("USN: " + student.getUsn());
        editTextName.setText(student.getName());
        editTextSem.setText(student.getSem());
        editTextBranch.setText(student.getBranch());

        // Add a back button in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the click listener for the back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Set the click listener for the "Upload" button
        // Set the click listener for the "Upload" button
        // Set the click listener for the "Upload" button
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the edited details from the UI elements
                String name = editTextName.getText().toString().trim().toUpperCase();
                String sem = editTextSem.getText().toString().trim().toUpperCase();
                String branch = editTextBranch.getText().toString().trim().toUpperCase();

                // Check for null values
                if (name.isEmpty() || sem.isEmpty() || branch.isEmpty()) {
                    Toast.makeText(FinalEditStudentActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update the student details in the database
                student.setName(name);
                student.setSem(sem);
                student.setBranch(branch);
                databaseHelper.updateStudentDetails(student);

                // Show a success message
                Toast.makeText(FinalEditStudentActivity.this, "Student details updated", Toast.LENGTH_SHORT).show();

                // Return back to the student edit activity
                onBackPressed();
            }
        });




    }
}
