package com.example.mylistview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Trainer_Screen extends AppCompatActivity {
    TextView textView_name;
    TextView tv_phone;
    TextView tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_screen);

        textView_name = findViewById(R.id.textView_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_id = findViewById(R.id.tv_id);

        Intent intent = getIntent();
        String name = intent.getStringExtra(UtilsTrainer.INTENT_KEY_TRAINER_NAME);
        String phone = intent.getStringExtra(UtilsTrainer.INTENT_KEY_TRAINER_PHONE);
        int num = intent.getIntExtra(UtilsTrainer.INTENT_KEY_TRAINER_ID, 0);
        String id = "" + num;

        textView_name.setText(textView_name.getText() + name);
        tv_phone.setText(tv_phone.getText() + phone);
        tv_id.setText(tv_id.getText() + id);

    }
}