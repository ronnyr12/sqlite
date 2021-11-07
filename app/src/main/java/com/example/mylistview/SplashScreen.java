package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    Button btnEnter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        Utils.createTables(db);
        Utils.addALLToDb(db);

        btnEnter = findViewById(R.id.enter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this,
                        MainActivity.class));
            }
        });
    }

}