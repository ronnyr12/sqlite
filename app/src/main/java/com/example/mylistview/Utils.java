package com.example.mylistview;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils {
    final static String DATABASE_NAME = "db_pokemon_app";
    final static String TABLE_NAME_POKEMON = "tbl_pokemon";
    final static String TABLE_POKEMON_COL_NAME = "name";
    final static String TABLE_POKEMON_COL_POWER = "power";
    final static String TABLE_POKEMON_COL_TYPE = "type";

    final static String INTENT_KEY_POKEMON_NAME = "pokemon_name";

    public static void createTables(SQLiteDatabase db){
        // TODO: 07.11.2021 implement create database ;
        db.execSQL("create table if not exists "
                + Utils.TABLE_NAME_POKEMON +
                "(" + Utils.TABLE_POKEMON_COL_NAME + " text, " + Utils.TABLE_POKEMON_COL_POWER + " integer, " + Utils.TABLE_POKEMON_COL_TYPE + " text)");
    }
    public static void addALLToDb(SQLiteDatabase db) {

        Pokemon pk1 = new Pokemon("giglipuf",
                500, "mind");

        Pokemon pk2 = new Pokemon("psyduck",
                1500, "mind");

        Pokemon pk3 = new Pokemon("aggron",
                2000, "rock");

        Pokemon pk4 = new Pokemon("picachu",
                3500, "electricity");

        Pokemon pk5 = new Pokemon("riyachu",
                5500, "electricity");

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pk1);
        pokemonList.add(pk2);
        pokemonList.add(pk3);
        pokemonList.add(pk4);
        pokemonList.add(pk5);

        for ( Pokemon p : pokemonList ) {
            db.execSQL("insert into tbl_pokemon values('"+p.getName()+"', '"+p.getPower()+"','"+p.getType()+"')");
        //  db.execSQL("insert into tbl_pokemon values('"+p.getName()+"', 100,'"+p.getType()+"')");

        }


        /*
            foreach as a simple for loop

            for (int i = 0; i < pokemonList.size(); i++) {
            Pokemon p = pokemonList.get(i);
            //add desire code
           }
         */

    }
}
