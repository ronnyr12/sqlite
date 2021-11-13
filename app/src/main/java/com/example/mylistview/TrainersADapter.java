package com.example.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainersADapter extends BaseAdapter {
    ArrayList<Trainers> trainers;
    Context context;

    public TrainersADapter(ArrayList<Trainers> trainers, Context context) {
        this.trainers = trainers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trainers.size();
    }

    @Override
    public Trainers getItem(int position) {
        return trainers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trainers tmp = trainers.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.row_list_trainers, null);

        TextView tv_id = convertView.findViewById(R.id.tv_type);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_phone = convertView.findViewById(R.id.tv_power);

        tv_id.setText(String.valueOf(tmp.getId()));
        tv_name.setText(tmp.getName());
        tv_phone.setText(tmp.getPhone());

        return convertView;
    }
}
