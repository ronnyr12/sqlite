package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class EditScreen extends AppCompatActivity {
    Switch swtchdark;
    ConstraintLayout edit_layout;
    RadioGroup rg_type;
    RadioButton rb_mind, rb_elec, rb_water;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        getSupportActionBar().hide(); //<< this

        rg_type = findViewById(R.id.rg_type);
        rb_elec = findViewById(R.id.rb_electricity);
        rb_mind = findViewById(R.id.rb_mind);
        rb_water = findViewById(R.id.rb_water);

        btn_submit = findViewById(R.id.btn_submit);
        edit_layout = findViewById(R.id.edit_layout);
        swtchdark = findViewById(R.id.swtch_dark);
        Boolean switchState = swtchdark.isChecked();
        if(switchState)
            edit_layout.setBackgroundColor(Color.BLACK);
        else
            edit_layout.setBackgroundColor(Color.WHITE);


    }
}