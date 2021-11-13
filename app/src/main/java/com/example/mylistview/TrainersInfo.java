package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TrainersInfo extends AppCompatActivity {
    Button btn_back;
    TextView tv_name, tv_id, tv_phone;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers_info);

        db = openOrCreateDatabase(Utils.DATABASE_TRAINERS_NAME,
                MODE_PRIVATE, null);

        getSupportActionBar().hide(); //<< this
        Intent intent = getIntent();

        String trainersName = intent.getStringExtra("trainers_name");
        int trainersId = intent.getIntExtra("trainers_id", 0);
        String trainersPhone = intent.getStringExtra("trainers_phone");
        tv_name = findViewById(R.id.textViewName2);
        tv_id = findViewById(R.id.textViewId2);
        tv_phone = findViewById(R.id.textViewPhone2);
        btn_back = findViewById(R.id.btn_back);
        tv_id.setText(""+trainersId);
        tv_name.setText(trainersName);
        tv_phone.setText(trainersPhone);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrainersInfo.this,
                        TrainersActivity.class));
            }
        });

    }
}