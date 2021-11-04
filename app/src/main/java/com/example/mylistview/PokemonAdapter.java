package com.example.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter {
    ArrayList<Pokemon> pokemons;
    Context context;

    public PokemonAdapter(ArrayList<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pokemon tmp = pokemons.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.row_list_pokemon, null);

        TextView tv_type = convertView.findViewById(R.id.type);
        TextView tv_name = convertView.findViewById(R.id.name);
        TextView tv_power = convertView.findViewById(R.id.power);
        ImageView pic = convertView.findViewById(R.id.picture);

        tv_type.setText(tmp.getType());
        tv_name.setText(tmp.getName());
        tv_power.setText(String.valueOf(tmp.getPower()));
        Context c = context.getApplicationContext();
        pic.setImageResource(c.getResources().getIdentifier(tv_name.getText().toString().toLowerCase(), "drawable", c.getPackageName()));

        return convertView;
    }
}
