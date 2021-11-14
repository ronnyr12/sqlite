package com.example.mylistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddPokemon_Screen extends AppCompatActivity {
    EditText et_name, et_type, et_power;
    Button btn_save, btn_back;
    SQLiteDatabase db_pokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon_screen);

        init();
    }

    /**
     * custom method - initialize values of our activity
     */
    private void init() {
        db_pokemon = openOrCreateDatabase(Utils.DATABASE_NAME,
            MODE_PRIVATE, null);

        et_name = findViewById(R.id.et_name);
        et_type = findViewById(R.id.et_type);
        et_power = findViewById(R.id.et_power);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(v -> {
            String name = et_name.getText().toString();
            int power = Integer.parseInt(et_power.getText().toString());
            String type = et_type.getText().toString();

            db_pokemon.execSQL("insert into tbl_pokemon values('" +name+ "'," +power+ ",'" +type+ "')");

            startActivity(new Intent(AddPokemon_Screen.this, MainActivityPokemon.class));
        });

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPokemon_Screen.this, MainActivityPokemon.class));
            }
        });
    }
}