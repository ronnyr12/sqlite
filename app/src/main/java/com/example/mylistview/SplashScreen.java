package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {
    Button btnEnter, btn_admin;
    SQLiteDatabase db;
    ImageView imageView;
    int clicked = 0;
    EditText et_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        et_admin = findViewById(R.id.et_admin);
        et_admin.setVisibility(View.INVISIBLE);
        btn_admin = findViewById(R.id.btn_admin);
        btn_admin.setVisibility(View.INVISIBLE);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Utils.createTables(db);
        Utils.addDefault_Pokemons(db);
        Utils.addDefault_Trainers(db);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked++;
                if(clicked == 7){
                    et_admin.setVisibility(View.VISIBLE);

                }

                startActivity(new Intent(getApplicationContext(), CatchPokemon_Screen.class));
            }
        });
        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this,
                        MainActivity.class));
            }
        });
    }














}