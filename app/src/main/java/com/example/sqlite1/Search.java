package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    EditText edtSearchId;
    Button btnSearch;
    TextView tvSearchResult;
    DBHelper dbHelper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtSearchId = findViewById(R.id.edtSearchId);
        btnSearch = findViewById(R.id.btnSearch);
        tvSearchResult = findViewById(R.id.tvSearchResult);
        dbHelper = new DBHelper(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtSearchId.getText().toString().trim();

                if (id.isEmpty()) {
                    Toast.makeText(Search.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                database = dbHelper.getReadableDatabase();
                Cursor cursor = database.rawQuery(DBHelper.Constants.SEARCH_QUERY + id, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(Search.this, "Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    tvSearchResult.setText("");
                    while (cursor.isAfterLast() == false) {
                        tvSearchResult.append(cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getInt(2) + " " + cursor.getString(3) + " " + cursor.getString(4));
                        cursor.moveToNext();
                    }
                }
            }
            // database.close()
        });


    }
}