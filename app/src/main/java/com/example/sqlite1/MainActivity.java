package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void INSERT(View view) {
        startActivity(new Intent(MainActivity.this, Insert.class));
    }

    public void UPDATE(View view) {
        startActivity(new Intent(MainActivity.this, Update.class));
    }

    public void DELETE(View view) {
        startActivity(new Intent(MainActivity.this, Delete.class));
    }

    public void SEARCH(View view) {
        startActivity(new Intent(MainActivity.this, Search.class));
    }

    public void DISPLAY_ALL(View view) {
        startActivity(new Intent(MainActivity.this, DisplayAll.class));
    }
}