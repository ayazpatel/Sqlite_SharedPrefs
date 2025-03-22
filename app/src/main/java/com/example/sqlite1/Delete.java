package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {
    EditText edtDeleteId;
    Button btnDelete;
    DBHelper dbHelper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        edtDeleteId = findViewById(R.id.edtDeleteId);
        btnDelete = findViewById(R.id.btnDelete);
        dbHelper = new DBHelper(this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = dbHelper.getWritableDatabase();
                int id = Integer.parseInt(edtDeleteId.getText().toString());

                int b = database.delete(DBHelper.Constants.TABLE_NAME, "_id = " + id, null);
                if(b>0) {
                    Toast.makeText(Delete.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Delete.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}