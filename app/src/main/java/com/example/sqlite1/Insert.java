package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Insert extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    EditText etdName, edtAge, edtDate;
    Spinner sp;
    DatePickerDialog datePickerDialog;
    Button btnSubmit;
    ArrayAdapter<String> arrayAdapter;
    String[] courses;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etdName = findViewById(R.id.edtName_insert);
        edtAge = findViewById(R.id.edtAge_insert);
        sp = findViewById(R.id.spinner_insert);
        edtDate = findViewById(R.id.edtDate_insert);
        btnSubmit = findViewById(R.id.btnSubmit_insert);

//        courses = getResources().getStringArray(R.array.courses);
        courses = new String[] {"MCA", "BCA"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courses);
        sp.setAdapter(arrayAdapter);

        dbHelper = new DBHelper(this);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year); // Month starts from 0
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Inserting Result");
        builder.setPositiveButton(
                "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }
        );
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = dbHelper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put("name", etdName.getText().toString());
                cv.put("age", Integer.parseInt(edtAge.getText().toString()));
                cv.put("course", sp.getSelectedItem().toString());
                cv.put("date", edtDate.getText().toString());

                long l = sqLiteDatabase.insert(DBHelper.Constants.TABLE_NAME, null, cv);
                if (l != -1) {
                    builder.setMessage("Success");
                    alertDialog = builder.create();
                    alertDialog.show();
                    Toast.makeText(Insert.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    clearActivity();
                } else {
                    builder.setMessage("Failed");
                    alertDialog = builder.create();
                    alertDialog.show();
                    Toast.makeText(Insert.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }

//                sqLiteDatabase.close();

            }
        });

    }

    public void clearActivity() {
        etdName.setText("");
        edtAge.setText("");
    }
}