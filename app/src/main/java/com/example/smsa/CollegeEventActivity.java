package com.example.smsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CollegeEventActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CollegeEventAdapter adapter;
    private List<CollegeEvent> eventList;
    private CollegeDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_event);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewColleges);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = new ArrayList<>();
        adapter = new CollegeEventAdapter(eventList);
        recyclerView.setAdapter(adapter);

        // Initialize the database helper
        dbHelper = new CollegeDbHelper(this);

        // Load the event details from the database
        loadEventDetails();
    }

    private void loadEventDetails() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                CollegeDbHelper.COLUMN_EVENT_NAME,
                CollegeDbHelper.COLUMN_EVENT_DESCRIPTION
        };

        Cursor cursor = db.query(
                CollegeDbHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        eventList.clear();

        while (cursor.moveToNext()) {
            String eventName = cursor.getString(cursor.getColumnIndexOrThrow(CollegeDbHelper.COLUMN_EVENT_NAME));
            String eventDescription = cursor.getString(cursor.getColumnIndexOrThrow(CollegeDbHelper.COLUMN_EVENT_DESCRIPTION));

            CollegeEvent event = new CollegeEvent(eventName, eventDescription);
            eventList.add(event);
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
