package com.example.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityTrainer extends AppCompatActivity {
    TextView tv_trainer;
    ListView lv_trainer;
    TrainerAdapter trainerAdapter;

    SQLiteDatabase db_trainer;
    ArrayList<Trainer> trainerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trainer);

        tv_trainer = findViewById(R.id.tv_trainer);

        db_trainer = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        trainerList = new ArrayList<>();

        //cursor selects objects from the table in database and add them to arrayList
        Cursor cursor = db_trainer.rawQuery("select * from " +
                Utils.TABLE_NAME_TRAINER, null);
        while ( cursor.moveToNext() ){
            String name = cursor.getString(0);
            String phone = cursor.getString(1);
            int id = cursor.getInt(2);

            Trainer trainer = new Trainer(name, phone, id);
            trainerList.add(trainer);
        }

        lv_trainer = findViewById(R.id.lv_trainer);
        trainerAdapter = new TrainerAdapter(trainerList, MainActivityTrainer.this);
        lv_trainer.setAdapter(trainerAdapter);
        lv_trainer.setOnItemClickListener((parent, view, position, id) -> {
            Trainer tmp = trainerList.get(position);
            Intent intent = new Intent(MainActivityTrainer.this, Trainer_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_TRAINER_NAME, tmp.getName());
            intent.putExtra(Utils.INTENT_KEY_TRAINER_PHONE, tmp.getPhone());
            intent.putExtra(Utils.INTENT_KEY_TRAINER_ID, tmp.getId());
            startActivity(intent);
        });
    }
}