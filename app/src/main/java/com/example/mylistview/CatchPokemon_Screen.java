package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CatchPokemon_Screen extends AppCompatActivity {
    Spinner spinner_pokemon;
    SQLiteDatabase db;
    ArrayList<String> pokemon_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        pokemon_names = new ArrayList<>();
        spinner_pokemon = findViewById(R.id.spinner_pokemon);
        pokemon_names.clear();
        Cursor cursor = db.rawQuery("select name from tbl_pokemon", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            pokemon_names.add(name);
        }
        for(String i : pokemon_names){
            System.out.println(i);
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemon_names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pokemon.setAdapter(arrayAdapter);

        spinner_pokemon.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
                Toast.makeText( getApplicationContext(),  pokemon_names.get( position ), Toast.LENGTH_LONG ).show();
            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {

            }
        } );
    }

}