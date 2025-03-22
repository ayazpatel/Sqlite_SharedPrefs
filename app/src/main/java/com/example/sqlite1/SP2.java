package com.example.sqlite1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SP2 extends AppCompatActivity {
    TextView txtName, txtAge;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp2);

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "No Name Found");
        String age = sharedPreferences.getString("age", "No Age Found");

        txtName.setText("Name: " + name);
        txtAge.setText("Age: " + age);
    }
}
