package com.example.mylistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseScreen extends AppCompatActivity {

    private Button btn_pokemons;
    private Button btn_trainers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        btn_pokemons = findViewById(R.id.btn_pokemons);
        btn_pokemons.setOnClickListener(v -> {
            startActivity(new Intent(ChooseScreen.this, MainActivityPokemon.class));
        });

        btn_trainers = findViewById(R.id.btn_trainers);
        btn_trainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseScreen.this, MainActivityTrainer.class);
                startActivity(intent);
            }
        });
    }
}