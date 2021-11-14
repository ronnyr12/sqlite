package com.example.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CatchPokemon_Screen extends AppCompatActivity {
    Spinner spinner_pokemon;
    ArrayList<Pokemon> partialPokemons;
    ArrayList<String> pokemonNames;
    SQLiteDatabase db;
    String trainerId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        Intent intent = getIntent();
        String adminId = intent.getStringExtra(Utils.ADMIN_ID);
        Toast.makeText(getApplicationContext(), adminId, Toast.LENGTH_SHORT).show();
        spinner_pokemon = findViewById(R.id.spinner_pokemon);

        partialPokemons = new ArrayList<>();
        pokemonNames = new ArrayList<>();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        //extract the pokemons names from the database
        Cursor cursor = db.rawQuery( "select * from " + Utils.TABLE_NAME_POKEMON, null );
        while( cursor.moveToNext() ){
            String name = cursor.getString( 1 );
            int pid = cursor.getInt( 0 );
            Pokemon tmp = new Pokemon( name, pid );
            partialPokemons.add(tmp);
            pokemonNames.add(name);
        }
        //spinner to make it work, yes? - Creating the ArrayAdapter instance having the pokemon names list
        ArrayAdapter arrayAdapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item, pokemonNames );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spinner_pokemon.setAdapter(arrayAdapter);
        spinner_pokemon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), pokemonNames.get(position), Toast.LENGTH_LONG).show();
                db.execSQL("insert into "+Utils.TABLE_CAUGHT_POKEMON_NAME+" values('"+trainerid+"')");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}