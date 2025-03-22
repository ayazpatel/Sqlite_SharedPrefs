package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Update extends AppCompatActivity {
    Spinner spinnerId1;
    EditText edtAge_update;
    Button btnUpdate;
    SQLiteDatabase database;
    DBHelper dbHelper;
    ArrayAdapter<Integer> spinnerAdapter;
    ArrayList<Integer> spinnerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        spinnerId1 = findViewById(R.id.spinnerId1);
        edtAge_update = findViewById(R.id.edtAge_update);
        btnUpdate = findViewById(R.id.btnUpdate);

        spinnerData = new ArrayList<Integer>();
        dbHelper = new DBHelper(this);

        LoadSpinnerData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int rowId = spinnerData.get(spinnerId1.getSelectedItemPosition());

                database = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("age", Integer.parseInt(edtAge_update.getText().toString()));

                long l = database.update(DBHelper.Constants.TABLE_NAME, cv, "_id = " + rowId, null);
                if (l>0) {
                    Toast.makeText(Update.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Update.this, "Failed", Toast.LENGTH_SHORT).show();
                }
//                database.close();
            }
        });


        spinnerId1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int Id = spinnerData.get(i);
                database = dbHelper.getReadableDatabase();
                Cursor cursor = database.rawQuery(DBHelper.Constants.DISPLAY_QUERY, null);
                cursor.moveToFirst();
                if (cursor!=null) {
                    int age = cursor.getInt(2);
                    edtAge_update.setText(String.valueOf(age));
                } else {
                    edtAge_update.setText("");
                }
//                database.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                edtAge_update.setText("");
            }
        });
    }

    private void LoadSpinnerData() {
        database = dbHelper.getReadableDatabase();

        Cursor res = database.rawQuery(DBHelper.Constants.DISPLAY_QUERY, null);
        if (res.getCount() == 0) {
            Toast.makeText(this, "No Records Found For Entry", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                spinnerData.add(res.getInt(0));
                res.moveToNext();
            }
        }
        spinnerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, spinnerData);
        spinnerId1.setAdapter(spinnerAdapter);
//        database.close();
    }
}