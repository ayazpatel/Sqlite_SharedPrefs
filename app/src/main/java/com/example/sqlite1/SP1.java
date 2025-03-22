package com.example.sqlite1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SP1 extends AppCompatActivity {
    EditText edtName, edtAge;
    Button btnSave, btnGoToSP2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp1);

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        btnSave = findViewById(R.id.btnSave);
        btnGoToSP2 = findViewById(R.id.btnGoToSP2);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String age = edtAge.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("age", age);
                editor.apply(); // Save data

                edtName.setText("");
                edtAge.setText("");
            }
        });

        btnGoToSP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SP1.this, SP2.class));
            }
        });
    }
}
