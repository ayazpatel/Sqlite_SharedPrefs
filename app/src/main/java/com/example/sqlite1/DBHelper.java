package com.example.sqlite1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {

    static class Constants {
        public static final String TABLE_NAME = "Person";

        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String COURSE = "course";
        public static final String DATE = "date";

        public static final String CREATE_QUERY =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NAME + " TEXT," +
                        AGE + " INTEGER," +
                        COURSE + " TEXT," +
                        DATE + " TEXT);";
        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String SEARCH_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE _id = "; // Concat Id
        public static final String DISPLAY_QUERY = "SELECT * FROM " + TABLE_NAME;
        public static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME + " WHERE _id = "; // Concat Id
        public static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + " (name, age, course, date) VALUES ("; // 'name', 18, 'MCA', 'dd/MM/yyyy');

    }

    public DBHelper(@Nullable Context context) {
        super(context, Constants.TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(Constants.CREATE_QUERY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(Constants.DROP_QUERY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
