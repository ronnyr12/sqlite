package com.example.mylistview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CatchPokemon_Screen extends AppCompatActivity {
    Spinner spinner_pokemon;
    ArrayList<String> pokemonNames;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        spinner_pokemon = findViewById(R.id.spinner_pokemon);

        pokemonNames = new ArrayList<>();
        db = openOrCreateDatabase( Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery( "select * from " + Utils.TABLE_NAME_POKEMON, null );
        while( cursor.moveToNext() ){
            String name = cursor.getString( 1 );
            pokemonNames.add( name);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item, pokemonNames );
        arrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinner_pokemon.setOnItemClickListener( ( parent, view, position, id ) -> {
            Toast.makeText( getApplicationContext(), pokemonNames.get(position), Toast.LENGTH_LONG ).show();
        } );
    }
}