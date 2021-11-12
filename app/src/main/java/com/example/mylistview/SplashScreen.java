package com.example.mylistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Button btnEnter;
    SQLiteDatabase db_pokemon;
    SQLiteDatabase db_trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db_pokemon = openOrCreateDatabase(UtilsPokemon.DATABASE_NAME,
                MODE_PRIVATE, null);

        UtilsPokemon.createTables(db_pokemon);
        UtilsPokemon.addALLToDb(db_pokemon);

        db_trainer = openOrCreateDatabase(UtilsTrainer.DATABASE_NAME,
                MODE_PRIVATE, null);

        UtilsTrainer.createTables(db_trainer);
        UtilsTrainer.addALLToDb(db_trainer);


        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(v -> startActivity(new Intent(SplashScreen.this, ChooseScreen.class)));

    }
 }