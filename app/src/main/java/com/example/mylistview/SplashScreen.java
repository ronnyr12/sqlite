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

        createDatabase();
        addALLToDb();
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


        db.execSQL("create table if not exists tbl_pokemon(name text, power integer, type text)");

       /* db.execSQL("create table if not exists "+Utils.TABLE_NAME_POKEMON+
                " ("+Utils.TABLE_POKEMON_COL_NAME+" text, "+Utils.TABLE_POKEMON_COL_POWER+" integer, "+Utils.TABLE_POKEMON_COL_TYPE+" text)");
    */


    }

    public void addALLToDb(){
        ArrayList<Pokemon>pokemonList =
                new ArrayList<>();

        Pokemon pk1 = new Pokemon("giglipuf",
                        500, "mind");

        Pokemon pk2 = new Pokemon("psyduck",
                1500, "mind");

        Pokemon pk3 = new Pokemon("aggron",
                2000, "rock");

        Pokemon pk4 = new Pokemon("picachu",
                3500, "electricity");

        Pokemon pk5 = new Pokemon("riyachu",
                5500, "electricity");

        pokemonList = new ArrayList<Pokemon>();
        pokemonList.add(pk1);
        pokemonList.add(pk2);
        pokemonList.add(pk3);
        pokemonList.add(pk4);
        pokemonList.add(pk5);

        for (Pokemon p: pokemonList) {
            //db.execSQL("insert into tbl_pokemon values(picahu, 3000,mind)");

            db.execSQL("insert into tbl_pokemon values('p.getName()', 'p.getPower()','p.getType()')");
        }

        /*
            foreach as a simple for loop

            for (int i = 0; i < pokemonList.size(); i++) {
            Pokemon p = pokemonList.get(i);
            //add desire code
           }
         */

    }

}