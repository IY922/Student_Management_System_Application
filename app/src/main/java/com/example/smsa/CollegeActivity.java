package com.example.smsa;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CollegeActivity extends AppCompatActivity {

    private EditText editTextEventName, editTextEventDescription;
    private Button btnUploadEvent;
    private CollegeDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);



        Button btnUploadEvent = findViewById(R.id.btnUploadEvent);

        btnUploadEvent.setBackgroundColor(getResources().getColor(R.color.button));


















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

        editTextEventName = findViewById(R.id.editTextEventName);
        editTextEventDescription = findViewById(R.id.editTextEventDescription);
        btnUploadEvent = findViewById(R.id.btnUploadEvent);
        dbHelper = new CollegeDbHelper(this);

        btnUploadEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = editTextEventName.getText().toString().trim();
                String eventDescription = editTextEventDescription.getText().toString().trim();

                // Validate if both event name and description are not empty
                if (!eventName.isEmpty() && !eventDescription.isEmpty()) {
                    boolean success = dbHelper.insertEventDetails(eventName, eventDescription);
                    if (success) {
                        Toast.makeText(CollegeActivity.this, "Event uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CollegeActivity.this, "Failed to upload event", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CollegeActivity.this, "Please enter event name and description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish(); // Close the current activity and go back to the previous activity
    }
}
