package com.example.mylistview;

import android.content.Context;
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

    public TrainerAdapter(ArrayList<Trainer> trainers, Context context){
        this.trainers = trainers;
        this.context = context;
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
        tv_id.setText(String.valueOf(tmp.getId()));
        tv_phone.setText(tmp.getPhone());
        img.setImageResource(R.drawable.trainer);

        return convertView;
    }
}
