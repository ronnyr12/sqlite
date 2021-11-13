package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TrainersActivity extends AppCompatActivity {

    ArrayList<Trainers> trainersList;
    ListView lv;
    TrainersADapter adapter;
    Button buttonback;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers);
        db = openOrCreateDatabase(Utils.DATABASE_TRAINERS_NAME,
                MODE_PRIVATE, null);
        trainersList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_TRAINERS_NAME, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            int id = cursor.getInt(1);
            String phone = cursor.getString(2);
            Trainers trainers = new Trainers(name, id, phone);
            trainersList.add(trainers);
        }

        buttonback = findViewById(R.id.buttonback);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrainersActivity.this,
                        SplashScreen.class));
            }
        });

        lv = findViewById(R.id.lv_trainers);
        adapter = new TrainersADapter(trainersList,
                TrainersActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trainers tmp = trainersList.get(position);
                Intent intent = new Intent(TrainersActivity.this, TrainersInfo.class);
                intent.putExtra("trainers_name", tmp.getName());
                intent.putExtra("trainers_id", tmp.getId());
                intent.putExtra("trainers_phone", tmp.getPhone());
                startActivity(intent);
            }
        });
    }
}