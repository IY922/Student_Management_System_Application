package com.example.smsa;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {



    private Button btnDepartmentLogin;
    private Button btnStudentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnDepartmentLogin = findViewById(R.id.btnDepartmentLogin);
        Button btnStudentLogin = findViewById(R.id.btnStudentLogin);

        btnDepartmentLogin.setBackgroundColor(getResources().getColor(R.color.button));
        btnStudentLogin.setBackgroundColor(getResources().getColor(R.color.button));













        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDepartmentLogin = findViewById(R.id.btnDepartmentLogin);
        btnDepartmentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AdminActivity
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        btnStudentLogin = findViewById(R.id.btnStudentLogin);
        btnStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentActivity
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });
    }
}
