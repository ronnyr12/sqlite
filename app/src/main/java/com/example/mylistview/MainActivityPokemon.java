package com.example.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivityPokemon extends AppCompatActivity {
    TextView tv_pokemon;
    ListView lv_pokemon;
    PokemonAdapter pokemonAdapter;

    SQLiteDatabase db_pokemon;
    ArrayList<Pokemon> pokemonList;
    FloatingActionButton fab;
    int pid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pokemon);

        tv_pokemon = findViewById(R.id.tv_pokemon);

        db_pokemon = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);
        pokemonList = new ArrayList<>();

        //cursor selects objects from the table in database and add them to arrayList
        Cursor cursor = db_pokemon.rawQuery("select * from " + Utils.TABLE_NAME_POKEMON, null);
        while(cursor.moveToNext()){
            pid = cursor.getInt( 0 ) ;
            String name = cursor.getString(1);
            int power = cursor.getInt(2);
            String type = cursor.getString(3);

            Toast.makeText( getApplicationContext(), String.valueOf(cursor.getInt( 0 )) +"/"+ name +"/"+ String.valueOf( power ) +"/"+ type, Toast.LENGTH_SHORT ).show();
            Pokemon pokemon = new Pokemon(name, power, type);
            pokemon.setPid(pid);
            pokemonList.add( pokemon );
        }

        lv_pokemon = findViewById(R.id.lv_pokemon);
        pokemonAdapter = new PokemonAdapter(pokemonList, MainActivityPokemon.this);
        lv_pokemon.setAdapter(pokemonAdapter);
        lv_pokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon tmp = pokemonList.get(position);
                Intent intent = new Intent(MainActivityPokemon.this, EditScreen.class);
                intent.putExtra(Utils.INTENT_KEY_POKEMON_NAME, tmp.getName());
                intent.putExtra(Utils.INTENT_KEY_POKEMON_TYPE, tmp.getType());
                intent.putExtra(Utils.INTENT_KEY_POKEMON_PID, tmp.getPid());

                Toast.makeText(getApplicationContext(), ""+pid, Toast.LENGTH_LONG ).show();
                startActivity(intent);
            }
        });

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityPokemon.this, AddPokemon_Screen.class));
            }
        });
    }
}