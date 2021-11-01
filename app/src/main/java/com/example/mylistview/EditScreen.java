package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class EditScreen extends AppCompatActivity {
    Switch swtchdark;
    ConstraintLayout edit_layout;
    RadioGroup rg_type;
    RadioButton radioButton;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        getSupportActionBar().hide(); //<< this

        rg_type = findViewById(R.id.rg_type);


        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = rg_type.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = findViewById(selectedId);

                Toast.makeText(EditScreen.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        edit_layout = findViewById(R.id.edit_layout);
        swtchdark = findViewById(R.id.swtch_dark);
        Boolean switchState = swtchdark.isChecked();
        if(switchState)
            edit_layout.setBackgroundColor(Color.BLACK);
        else
            edit_layout.setBackgroundColor(Color.WHITE);


    }
}