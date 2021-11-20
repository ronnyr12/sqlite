package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CatchPokemon_Screen extends AppCompatActivity {
    Spinner spinner_pokemon;
    SQLiteDatabase db;
    ArrayList<String> pokemon_names;
    TextView welcome;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        pokemon_names = new ArrayList<>();
        spinner_pokemon = findViewById(R.id.spinner_pokemon);
        welcome = findViewById(R.id.welcome);
        pokemon_names.clear();
        Intent in = getIntent();
        int id = Integer.parseInt(in.getStringExtra("id"));

        Cursor cursor1 = db.rawQuery("select * from tbl_trainer where id =" + id, null);
        cursor1.moveToFirst();

        String trainer = cursor1.getString(cursor1.getColumnIndex("name"));
        welcome.setText("Welcome " + trainer);

        Cursor cursor = db.rawQuery("select name from tbl_pokemon", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            pokemon_names.add(name);
        }

        button = findViewById(R.id.btn_catch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pokemon = spinner_pokemon.getSelectedItem().toString();
                Cursor c = db.rawQuery("select * from tbl_pokemon where name = '" + pokemon + "'", null);
                c.moveToFirst();
                int pid = c.getInt(c.getColumnIndex("id"));

                db.execSQL("insert into " + Utils.TABLE_NAME_CAUGHT + " values(" + id + "," + pid +")");
                Toast.makeText(CatchPokemon_Screen.this, pokemon + " has been caught by " + trainer, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CatchPokemon_Screen.this, SplashScreen.class));

            }
        });


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemon_names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pokemon.setAdapter(arrayAdapter);

        spinner_pokemon.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {

            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {

            }
        } );
    }

}