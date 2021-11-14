package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class CatchPokemon_Screen extends AppCompatActivity {
    Spinner spinner_pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        spinner_pokemon = findViewById(R.id.spinner_pokemon);
    }
}