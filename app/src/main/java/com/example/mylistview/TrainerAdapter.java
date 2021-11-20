package com.example.mylistview;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainerAdapter extends BaseAdapter {
    ArrayList<Trainer> trainers;
    Context context;
    SQLiteDatabase db;

    public TrainerAdapter(ArrayList<Trainer> trainers, Context context, SQLiteDatabase db){
        this.trainers = trainers;
        this.context = context;
        this.db = db;
    }

    @Override
    public int getCount() {
        return trainers.size();
    }

    @Override
    public Trainer getItem(int position) {
        return trainers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trainer tmp = trainers.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.row_list, null);

        TextView tv_name = convertView.findViewById(R.id.name);
        TextView tv_id = convertView.findViewById(R.id.type);
        TextView tv_phone = convertView.findViewById(R.id.power);
        ImageView img = convertView.findViewById(R.id.picture);

        tv_name.setText(tmp.getName());
        tv_phone.setText(tmp.getPhone());

        Cursor cursor = db.rawQuery("select * from tbl_trainer where name='" + tmp.getName() + "'", null);
        cursor.moveToFirst();
        tv_id.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
        img.setImageResource(R.drawable.trainer);

        return convertView;
    }
}
