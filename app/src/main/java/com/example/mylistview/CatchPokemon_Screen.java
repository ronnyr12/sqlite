package com.example.mylistview;

import android.content.Intent;
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
    ArrayList<Pokemon> partials_pokemon;
    ArrayList<String> pokemons_names;
    Intent intent;
    SQLiteDatabase db;
    String trainerId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        intent = getIntent();
        trainerId = intent.getStringExtra(Utils.INTENT_KEY_TRAINER_ID);

        partials_pokemon = new ArrayList<>();
        pokemons_names = new ArrayList<>();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        spinner_pokemon = findViewById(R.id.spinner_pokemon);

        //extract the pokemons names from the database
        Cursor cursor = db.rawQuery("select * from " +
                Utils.TABLE_NAME_POKEMON, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int pid = cursor.getInt(0);
            Pokemon tmp = new Pokemon(name, pid);
            partials_pokemon.add(tmp);

            pokemons_names.add(name);   //for the spinner we required to supply a names list
        }

        //spinner to make it work, yes? - Creating the ArrayAdapter instance having the pokemon names list
        ArrayAdapter aa = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,pokemons_names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_pokemon.setAdapter(aa);
        spinner_pokemon.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        db.execSQL("insert into "+Utils.TABLE_NAME_CATCHED_POKEMON+" values('"+trainerId+"', " +
                "'"+partials_pokemon.get(position).getPid()+"')");
        Log.d("debbug",
                String.valueOf(partials_pokemon.get(position).getPid()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}