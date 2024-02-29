package com.example.smsa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class CircularActivityDisplay extends AppCompatActivity {

    private CircularDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_display);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new CircularDbHelper(this);

        TextView textCircularData = findViewById(R.id.textCircularData);
        textCircularData.setText(getCircularData());
    }

    private String getCircularData() {
        List<CircularDetails> circularDetailsList = dbHelper.getAllCircularDetails();

        StringBuilder circularData = new StringBuilder();

        for (CircularDetails circularDetails : circularDetailsList) {
            circularData.append("Event Name: ").append(circularDetails.getName()).append("\n");
            circularData.append("Event Description: ").append(circularDetails.getDescription()).append("\n\n");
        }

        return circularData.toString();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
