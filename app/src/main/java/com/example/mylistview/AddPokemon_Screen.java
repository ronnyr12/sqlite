package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPokemon_Screen extends AppCompatActivity {

    EditText et_name, et_type, et_power;
    Button btn_save;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon_screen);

        et_name = findViewById(R.id.et_name);
        et_type = findViewById(R.id.et_type);
        et_power = findViewById(R.id.et_power);
        btn_save = findViewById(R.id.btn_save);
        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                int power = Integer.parseInt(et_power.getText().toString());
                String type = et_type.getText().toString();

                String values = "('" + name + "', " + power + ", '" + type + "')";
                db.execSQL("insert into tbl_pokemon values" + values);
                startActivity(new Intent(AddPokemon_Screen.this, MainActivity.class));
            }
        });




    }
}