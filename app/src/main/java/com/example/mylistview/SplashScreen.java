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
        btnEnter = findViewById(R.id.enter);
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

    }

    public void addALLToDb(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        Pokemon pk0 = new Pokemon("Charmander", 1400, "Fire");
        Pokemon pk1 = new Pokemon("Pikachu", 2350, "Electric");
        Pokemon pk2 = new Pokemon("Charizard", 2700, "Fire");
        Pokemon pk3 = new Pokemon("Eevee", 1800, "Normal");
        Pokemon pk4 = new Pokemon("Snorlax", 2100, "Normal");
        Pokemon pk5 = new Pokemon("Squirtle", 1600, "Water");
        Pokemon pk6 = new Pokemon("Bulbasaur", 800, "Grass");
        Pokemon pk7 = new Pokemon("Lucario", 1700, "Wind");
        Pokemon pk8 = new Pokemon("Jigglypuff", 500, "Fairy");
        Pokemon pk9 = new Pokemon("Ditto", 400, "Fairy");

        pokemons.add(pk0);
        pokemons.add(pk1);
        pokemons.add(pk2);
        pokemons.add(pk3);
        pokemons.add(pk4);
        pokemons.add(pk5);
        pokemons.add(pk6);
        pokemons.add(pk7);
        pokemons.add(pk8);
        pokemons.add(pk9);


    }

}