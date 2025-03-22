package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DisplayAll extends AppCompatActivity {
    SimpleCursorAdapter simpleCursorAdapter;
    ListView listView;
    DBHelper dbHelper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        try {
            listView = findViewById(R.id.lvRecords);
            dbHelper = new DBHelper(this);
            database = dbHelper.getReadableDatabase();

            Cursor result = database.rawQuery(DBHelper.Constants.DISPLAY_QUERY, null);
            String[] from = new String[] {
                    result.getColumnName(1), //name
                    result.getColumnName(2), //age
                    result.getColumnName(3), //course
                    result.getColumnName(4) //date
            };
            int[] to = new int[] {
                    R.id.tvName,
                    R.id.tvAge,
                    R.id.tvCourse,
                    R.id.tvDate
            };

            simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item, result, from, to, 0);
            listView.setAdapter(simpleCursorAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}