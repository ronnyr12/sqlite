package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class EditScreen extends AppCompatActivity {
    Switch swtchdark;
    ConstraintLayout edit_layout;
    RadioGroup rg_type;
    RadioButton rb_mind, rb_elec, rb_water;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        getSupportActionBar().hide(); //<< this
        rg_type = findViewById(R.id.rg_type);
        edit_layout = findViewById(R.id.edit_layout);
        swtchdark = findViewById(R.id.swtch_dark);
        Boolean switchState = swtchdark.isChecked();
        if(switchState)
            edit_layout.setBackgroundColor(Color.BLACK);
        else
            edit_layout.setBackgroundColor(Color.WHITE);


    }
}