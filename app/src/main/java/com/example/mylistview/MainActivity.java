package com.example.mylistview;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SQLiteDatabase db;
    FloatingActionButton fab;
    String table_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);
        fab = findViewById(R.id.floatingActionButton);
        lv = findViewById(R.id.lv_pokemon);

        Intent received = getIntent();
        table_name = received.getStringExtra("tbl_name");

        if (table_name.equals("tbl_pokemon")) showPokemons();

        else showTrainers();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Add_Screen.class);
                i.putExtra("tbl_name", table_name);
                startActivity(i);
            }
        });

    }

    private void showTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        TrainerAdapter adapter;

        Cursor cursor = db.rawQuery("select * from " + "tbl_trainer", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            int id = cursor.getInt(2);
            String phone = cursor.getString(1);
            Trainer tmp = new Trainer(name, phone);
            trainers.add(tmp);
        }

        adapter = new TrainerAdapter(trainers, MainActivity.this, db);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trainer tmp = trainers.get(position);
                Intent intent = new Intent(MainActivity.this, EditScreen.class);
                Cursor cursor1 = db.rawQuery("select * from tbl_trainer where name='" + tmp.getName() + "'" , null);
                cursor1.moveToFirst();
                System.out.println(String.valueOf(cursor1.getInt(cursor1.getColumnIndex("id"))));
                intent.putExtra("column", String.valueOf(cursor1.getInt(cursor1.getColumnIndex("id"))));
                intent.putExtra("tbl_name", table_name);
                startActivity(intent);
            }
        });
    }

    private void showPokemons() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        PokemonAdapter adapter;

        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_NAME_POKEMON, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int power = cursor.getInt(cursor.getColumnIndex("power"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            Pokemon pokemon = new Pokemon(name, power, type);
            pokemons.add(pokemon);
        }

        adapter = new PokemonAdapter(pokemons, MainActivity.this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon tmp = pokemons.get(position);
                Intent intent = new Intent(MainActivity.this, EditScreen.class);
                Cursor cursor1 = db.rawQuery("select * from tbl_pokemon where name='" + tmp.getName() + "'" , null);
                cursor1.moveToFirst();
                intent.putExtra("column", String.valueOf(cursor1.getInt(cursor1.getColumnIndex("id"))));
                intent.putExtra("tbl_name", table_name);
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MainActivity.this, SplashScreen.class);
        startActivity(i);
    }
}