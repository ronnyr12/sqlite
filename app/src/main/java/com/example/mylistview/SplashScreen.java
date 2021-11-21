package com.example.mylistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Button btnEnter;
    SQLiteDatabase db;
    ImageView imageView;
    EditText et_admin;
    Button btn_admin;
    int clicked=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_admin = findViewById(R.id.et_admin);
        et_admin.setVisibility(View.INVISIBLE);
        btn_admin = findViewById(R.id.btn_admin);
        btn_admin.setVisibility(View.INVISIBLE);
        btn_admin.setOnClickListener(v -> {
            String id = et_admin.getText().toString();
            if(id == Utils.ADMIN_ID){
                Intent intent = new Intent(SplashScreen.this, CatchPokemon_Screen.class);
                intent.putExtra(Utils.INTENT_KEY_TRAINER_ID, id);
                startActivity(intent);
                et_admin.setText( "Enter the admin id" );
            }
        });
        Utils.createTables(db);
        Utils.addDefault_Pokemons(db);
        Utils.addDefault_Trainers(db);

        et_admin = findViewById(R.id.et_admin);
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked++;
                if(clicked == 7){
                    et_admin.setVisibility(View.VISIBLE);
                    btn_admin.setVisibility( View.VISIBLE );
                }
            }
        });
        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(v -> startActivity(new Intent(SplashScreen.this, ChooseScreen.class)));

    }
 }