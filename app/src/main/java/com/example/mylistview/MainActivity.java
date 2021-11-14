package com.example.mylistview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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


        updateList();

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,
                        AddPokemon_Screen.class));
            }
        });

    }

    private void updateList() {

        pokemonList.clear();
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_NAME_POKEMON, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            int power = cursor.getInt(1);
            String type = cursor.getString(2);
            Pokemon pokemon = new Pokemon(name, power, type);
            pokemonList.add(pokemon);
        }


        lv = findViewById(R.id.lv_pokemon);
        adapter = new PokemonAdapter(pokemonList,
                MainActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon tmp = pokemonList.get(position);
                Intent intent = new Intent(MainActivity.this, EditScreen.class);
                intent.putExtra(Utils.INTENT_KEY_POKEMON_NAME, tmp.getName());
                intent.putExtra("column", position+1);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.reset){
            Utils.insertAll(db);
            startActivity(getIntent());
        }
        return true;
    }
}