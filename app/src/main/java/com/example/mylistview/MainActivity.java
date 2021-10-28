package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemonList;
    ListView lv;
    PokemonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pokemon pk1 = new Pokemon("giglipuf", 500, "mind");
        Pokemon pk2 = new Pokemon("psyduck", 1500, "mind");
        Pokemon pk3 = new Pokemon("aggron", 2000, "rock");
        Pokemon pk4 = new Pokemon("picachu", 3500, "electricity");
        Pokemon pk5 = new Pokemon("riyachu", 5500, "electricity");

        pokemonList = new ArrayList<Pokemon>();
        pokemonList.add(pk1);
        pokemonList.add(pk2);
        pokemonList.add(pk3);
        pokemonList.add(pk4);
        pokemonList.add(pk5);

        lv = findViewById(R.id.lv_pokemon);
        adapter = new PokemonAdapter(pokemonList,
                MainActivity.this);
        lv.setAdapter(adapter);

    }
}