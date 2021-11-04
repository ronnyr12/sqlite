package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemonList;
    ListView lv;
    PokemonAdapter adapter;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

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

        ArrayList<Pokemon> pokemons = new ArrayList<>();
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



        lv = findViewById(R.id.lv_pokemon);
        adapter = new PokemonAdapter(pokemons,
                MainActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon tmp = pokemons.get(position);
                Intent intent = new Intent(MainActivity.this, EditScreen.class);
                intent.putExtra(Utils.INTENT_KEY_POKEMON_NAME, tmp.getName());
                startActivity(intent);
            }
        });
    }
}