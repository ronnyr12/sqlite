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

public class SplashScreen extends AppCompatActivity implements View.OnClickListener {
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
        btn_admin.setOnClickListener(this);

        Utils.createTables(db);
        Utils.addDefault_Pokemons(db);
        Utils.addDefault_Trainers(db);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(imageView == v){
            clicked++;
            if(clicked == 7){
                et_admin.setVisibility(View.VISIBLE);
                btn_admin.setVisibility(View.VISIBLE);
            }
        }
        if(btn_admin == v){
            String id = et_admin.getText().toString();
            if(id.equals(Utils.ADMIN_ID)){
                Intent intent = new Intent(SplashScreen.this,
                        CatchPokemon_Screen.class);
                intent.putExtra(Utils.INTENT_KEY_TRAINER_ID, id);
                startActivity(intent);
            }
        }
        if(btnEnter == v){
            startActivity(new Intent(SplashScreen.this,
                    MainActivity.class));
        }
    }
}