package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditScreen extends AppCompatActivity {
    Switch swtchdark;
    ConstraintLayout edit_layout;
    RadioGroup rg_type;
    RadioButton radioButton;
    Button btn_submit;
    TextView tv_edit_name;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        getSupportActionBar().hide(); //<< this
        Intent intent = getIntent();

        String pokemonName = intent.getStringExtra(Utils.INTENT_KEY_POKEMON_NAME);

        rg_type = findViewById(R.id.rg_type);
        tv_edit_name = findViewById(R.id.tv_edit_name);
        tv_edit_name.setText(pokemonName);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = rg_type.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = findViewById(selectedId);

                Toast.makeText(EditScreen.this,
                        radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
        edit_layout = findViewById(R.id.edit_layout);
        swtchdark = findViewById(R.id.swtch_dark);
        swtchdark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean switchState = swtchdark.isChecked();
                if(switchState)
                    edit_layout.setBackgroundColor(Color.DKGRAY);
                else
                    edit_layout.setBackgroundColor(Color.WHITE);
            }
        });



    }
}