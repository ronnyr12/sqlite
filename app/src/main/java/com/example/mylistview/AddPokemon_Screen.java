package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AddPokemon_Screen extends AppCompatActivity {
    EditText et_name, et_power, et_type;
    Button btn_save;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon_screen);

        init();
    }
    public void init(){
        db = openOrCreateDatabase(Utils.DATABASE_NAME,
            MODE_PRIVATE, null);

        et_name = findViewById(R.id.et_name);
        et_power = findViewById(R.id.et_name);
        et_type = findViewById(R.id.et_name);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(v -> {
            Log.d()

        });

    }
}