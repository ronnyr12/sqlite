package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {
    Button btnEnter;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this,
                        MainActivity.class));
            }
        });
    }

    public void createDatabase(){
        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);


        db.execSQL("create table if not exists tbl_pokemon(name text, power integer, type text)");

       /* db.execSQL("create table if not exists "+Utils.TABLE_NAME_POKEMON+
                " ("+Utils.TABLE_POKEMON_COL_NAME+" text, "+Utils.TABLE_POKEMON_COL_POWER+" integer, "+Utils.TABLE_POKEMON_COL_TYPE+" text)");
    */


    }

}