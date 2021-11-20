package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.CacheRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener {

    Button btn_pk, btn_tr, admin;
    SQLiteDatabase db;
    ImageView trainer;
    int clicked;
    EditText et_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Utils.createTables(db);
        Utils.addALLToDb(db);

        clicked = 0;
        btn_pk = findViewById(R.id.pokemons);
        btn_tr = findViewById(R.id.trainers);
        trainer = findViewById(R.id.trainer_img);
        admin = findViewById(R.id.admin);
        et_id = findViewById(R.id.trainer_id);
        et_id.setVisibility(View.INVISIBLE);
        admin.setVisibility(View.INVISIBLE);


        btn_pk.setOnClickListener(this);
        btn_tr.setOnClickListener(this);
        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this, CatchPokemon_Screen.class));
            }
        });

        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                if(clicked == 7){
                    et_id.setVisibility(View.VISIBLE);
                    admin.setVisibility(View.VISIBLE);
                }
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                Intent i;
                boolean right = false;
                Cursor cursor = db.rawQuery("select id  from tbl_trainer ",null);
                while(cursor.moveToNext()){
                    if(id.equals(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))))) {
                        i = new Intent(SplashScreen.this, CatchPokemon_Screen.class);
                        i.putExtra("id", id);
                        startActivity(i);
                        right = true;
                    }
                }
                if(!right) Toast.makeText(SplashScreen.this, "Wrong id", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        switch (view.getId()){
            case R.id.pokemons:
                intent.putExtra("tbl_name", "tbl_pokemon");
                break;
            case R.id.trainers:
                intent.putExtra("tbl_name", "tbl_trainer");
                break;
        }
        startActivity(intent);
    }
}