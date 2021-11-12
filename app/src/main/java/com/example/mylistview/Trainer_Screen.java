package com.example.mylistview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Trainer_Screen extends AppCompatActivity {
    TextView textView_name;
    TextView tv_phone;
    TextView tv_id;

    TextView name;
    TextView phone;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_screen);

        textView_name = findViewById(R.id.textView_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_id = findViewById(R.id.tv_id);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        id = findViewById(R.id.id);

        Intent intent = getIntent();
        String trainerName = intent.getStringExtra(UtilsTrainer.INTENT_KEY_TRAINER_NAME);
        String trainerPhone = intent.getStringExtra(UtilsTrainer.INTENT_KEY_TRAINER_PHONE);
        int num = intent.getIntExtra(UtilsTrainer.INTENT_KEY_TRAINER_ID, 0);
        String trainerId = "" + num;

        name.setText(trainerName);
        phone.setText(trainerPhone);
        id.setText(trainerId);
    }
}