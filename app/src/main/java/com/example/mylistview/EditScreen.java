package com.example.mylistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EditScreen extends AppCompatActivity {
    Switch switchdark;
    ConstraintLayout edit_layout;

    RadioGroup rg_type;

    RadioButton rb_mind;
    RadioButton rb_electricity;
    RadioButton rb_water;

    RadioButton radioButton;
    Button btn_submit;
    TextView tv_edit_name, tv_row_id, row_id;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);
        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        getSupportActionBar().hide(); //<< this

        Intent intent = getIntent();

        String pokemonName = intent.getStringExtra(Utils.INTENT_KEY_POKEMON_NAME);
        String pokemonType = intent.getStringExtra(Utils.INTENT_KEY_POKEMON_TYPE);
        int pid = intent.getIntExtra( "pid", 0);

        rg_type = findViewById(R.id.rg_type);
        tv_edit_name = findViewById( R.id.tv_edit_name );
        btn_submit = findViewById( R.id.btn_submit );
        tv_row_id = findViewById( R.id.tv_row_id );
        row_id = findViewById( R.id.row_id );

        tv_edit_name.setText(pokemonName);
        row_id.setText( ""+pid );
        //setTypeRButtonChecked(pokemonType);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = rg_type.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = findViewById(selectedId);
                System.out.println(selectedId);
                Toast.makeText(EditScreen.this,
                        radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
        edit_layout = findViewById(R.id.edit_layout);
        switchdark = findViewById(R.id.switch_dark);
        switchdark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean switchState = switchdark.isChecked();
                if(switchState)
                    edit_layout.setBackgroundColor(Color.DKGRAY);
                else
                    edit_layout.setBackgroundColor(Color.WHITE);
            }
        });
    }

    public void setTypeRButtonChecked(String pokemonType){
        rb_mind = findViewById(R.id.rb_mind);
        rb_electricity = findViewById(R.id.rb_electricity);
        rb_water = findViewById(R.id.rb_water);

        if(pokemonType == "mind"){
            rb_mind.setChecked(true);
        }
        if(pokemonType == "electricity"){
            rb_electricity.setChecked(true);
        }
        if(pokemonType == "water"){
            rb_water.setChecked(true);
        }
    }
}