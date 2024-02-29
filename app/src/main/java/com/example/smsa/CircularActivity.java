package com.example.smsa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CircularActivity extends AppCompatActivity {

    private EditText editTextCircularName, editTextCircularDescription;
    private Button btnUploadCircular;
    private CircularDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

        Button btnUploadCircular = findViewById(R.id.btnUploadCircular);

        btnUploadCircular.setBackgroundColor(getResources().getColor(R.color.button));

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

        editTextCircularName = findViewById(R.id.editTextCircularName);
        editTextCircularDescription = findViewById(R.id.editTextCircularDescription);
        btnUploadCircular = findViewById(R.id.btnUploadCircular);
        dbHelper = new CircularDbHelper(this);

        btnUploadCircular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String circularName = editTextCircularName.getText().toString().trim();
                String circularDescription = editTextCircularDescription.getText().toString().trim();
                // Validate if both circular name and description are not empty
                if (!circularName.isEmpty() && !circularDescription.isEmpty()) {
                    boolean success = dbHelper.insertCircularDetails(circularName, circularDescription);
                    if (success) {
                        Toast.makeText(CircularActivity.this, "Circular uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CircularActivity.this, "Failed to upload circular", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CircularActivity.this, "Please enter circular name and description", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back button click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
            }
        });
    }
}
