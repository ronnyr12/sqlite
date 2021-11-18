package com.example.mylistview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CatchPokemon_Screen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner_pokemon;
    ArrayList<String> pokemon_names;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        pokemon_names = new ArrayList<>();
        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        spinner_pokemon = findViewById(R.id.spinner_pokemon);

        //extract the pokemons names from the database
        Cursor cursor = db.rawQuery("select * from " +
                Utils.TABLE_NAME_POKEMON, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            pokemon_names.add(name);
        }

        //spinner to make it work, yes? - Creating the ArrayAdapter instance having the pokemon names list
        ArrayAdapter aa = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,pokemon_names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_pokemon.setAdapter(aa);
        spinner_pokemon.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("debbug",pokemon_names.get(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}