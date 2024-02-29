package com.example.smsa;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smsa.ExamDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExamActivity extends AppCompatActivity {

    private EditText editTextExamName, editTextSem, editTextBranch;
    private EditText editTextSubject1Name, editTextSubject1Timetable;
    private EditText editTextSubject2Name, editTextSubject2Timetable;
    private EditText editTextSubject3Name, editTextSubject3Timetable;
    private EditText editTextSubject4Name, editTextSubject4Timetable;
    private Button btnUpload;

    private ExamDbHelper dbHelper;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);


        Button btnUpload = findViewById(R.id.btnUpload);

        btnUpload.setBackgroundColor(getResources().getColor(R.color.button));





















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

        // Initialize EditText fields
        editTextExamName = findViewById(R.id.editTextExamName);
        editTextSem = findViewById(R.id.editTextSem);
        editTextBranch = findViewById(R.id.editTextBranch);
        editTextSubject1Name = findViewById(R.id.editTextSubject1Name);
        editTextSubject1Timetable = findViewById(R.id.editTextSubject1Timetable);
        editTextSubject2Name = findViewById(R.id.editTextSubject2Name);
        editTextSubject2Timetable = findViewById(R.id.editTextSubject2Timetable);
        editTextSubject3Name = findViewById(R.id.editTextSubject3Name);
        editTextSubject3Timetable = findViewById(R.id.editTextSubject3Timetable);
        editTextSubject4Name = findViewById(R.id.editTextSubject4Name);
        editTextSubject4Timetable = findViewById(R.id.editTextSubject4Timetable);
        btnUpload = findViewById(R.id.btnUpload);

        dbHelper = new ExamDbHelper(this);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Set click listeners for the date picker fields
        editTextSubject1Timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextSubject1Timetable);
            }
        });

        editTextSubject2Timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextSubject2Timetable);
            }
        });

        editTextSubject3Timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextSubject3Timetable);
            }
        });

        editTextSubject4Timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextSubject4Timetable);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered exam details
                String examName = editTextExamName.getText().toString().trim();
                String sem = editTextSem.getText().toString().trim().toUpperCase();
                String branch = editTextBranch.getText().toString().trim().toUpperCase();
                String subject1Name = editTextSubject1Name.getText().toString().trim();
                String subject1Timetable = editTextSubject1Timetable.getText().toString().trim();
                String subject2Name = editTextSubject2Name.getText().toString().trim();
                String subject2Timetable = editTextSubject2Timetable.getText().toString().trim();
                String subject3Name = editTextSubject3Name.getText().toString().trim();
                String subject3Timetable = editTextSubject3Timetable.getText().toString().trim();
                String subject4Name = editTextSubject4Name.getText().toString().trim();
                String subject4Timetable = editTextSubject4Timetable.getText().toString().trim();

                // Validate if any field is empty
                if (examName.isEmpty() || sem.isEmpty() || branch.isEmpty()
                        || subject1Name.isEmpty() || subject1Timetable.isEmpty()
                        || subject2Name.isEmpty() || subject2Timetable.isEmpty()
                        || subject3Name.isEmpty() || subject3Timetable.isEmpty()
                        || subject4Name.isEmpty() || subject4Timetable.isEmpty()) {
                    Toast.makeText(ExamActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the exam details to the database
                    ExamDbHelper dbHelper = new ExamDbHelper(ExamActivity.this);
                    ExamDetails examDetails = new ExamDetails(examName, sem, branch, subject1Name, subject1Timetable,
                            subject2Name, subject2Timetable, subject3Name, subject3Timetable,
                            subject4Name, subject4Timetable);
                    boolean success = dbHelper.insertExamDetails(examDetails);


                    if (success) {
                        Toast.makeText(ExamActivity.this, "Exam details uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ExamActivity.this, "Failed to upload exam details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                editText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ExamActivity.this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {

        finish(); // Optional, if you want to finish the current activity after navigation
    }
}
