package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemonList;
    ListView lv;
    PokemonAdapter adapter;

    SQLiteDatabase db;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);
        pokemonList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_NAME_POKEMON, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            int power = cursor.getInt(1);
            String type = cursor.getString(2);

            Pokemon pokemon = new Pokemon(name, power, type);
            pokemonList.add(pokemon);
        }


        lv = findViewById(R.id.lv_pokemon);
        adapter = new PokemonAdapter(pokemonList, MainActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon tmp = pokemonList.get(position);
                Intent intent = new Intent(MainActivity.this, EditScreen.class);
                intent.putExtra(Utils.INTENT_KEY_POKEMON_NAME, tmp.getName());
                intent.putExtra(Utils.INTENT_KEY_POKEMON_TYPE, tmp.getType());
                startActivity(intent);
            }
        });

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddPokemon_Screen.class));
            }
        });
    }
}