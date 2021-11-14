package com.example.mylistview;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils {
    final static String INTENT_KEY_POKEMON_NAME = "pokemon_name";


    final static String DATABASE_NAME = "db_pokemon_app2";

    final static String TABLE_NAME_POKEMON = "tbl_pokemon";
    final static String TABLE_POKEMON_COL_PID = "pid";
    final static String TABLE_POKEMON_COL_NAME = "name";
    final static String TABLE_POKEMON_COL_POWER = "power";
    final static String TABLE_POKEMON_COL_TYPE = "type";


    final static String TABLE_NAME_TRAINER = "tbl_trainer";
    final static String TABLE_TRAINER_COL_NAME = "name";
    final static String TABLE_TRAINER_COL_PHONE = "phone";
    final static String TABLE_TRAINER_COL_ID = "id";


    final static String INTENT_KEY_TRAINER_NAME = "name";
    final static String INTENT_KEY_TRAINER_PHONE = "phone";
    final static String INTENT_KEY_TRAINER_ID = "id";

    public static void createTables(SQLiteDatabase db){

        //db.execSQL("create table if not exists tbl_pokemon(name text, power integer, type text)");

        db.execSQL("create table if not exists "+Utils.TABLE_NAME_POKEMON+
                " ("+TABLE_POKEMON_COL_PID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+Utils.TABLE_POKEMON_COL_NAME+" text, "+Utils.TABLE_POKEMON_COL_POWER+" integer, "+Utils.TABLE_POKEMON_COL_TYPE+" text)");

        db.execSQL("create table if not exists "
                + TABLE_NAME_TRAINER +
                "(" + TABLE_POKEMON_COL_PID + " Integer PRIMARY KEY AUTOINCREMENT, " +
                TABLE_TRAINER_COL_NAME + " text, " +
                TABLE_TRAINER_COL_PHONE + " integer, " +
                TABLE_TRAINER_COL_ID + " text)");

    }
    public static void addDefault_Trainers(SQLiteDatabase db_pokemon) {
        db_pokemon.execSQL("delete from " + TABLE_NAME_TRAINER);

        Trainer tr1 = new Trainer("Ashe", "0532565412", 15262);

        Trainer tr2 = new Trainer("Murad", "0585556627", 554556445);

        Trainer tr3 = new Trainer("No one", "0582121212", 2121212);

        Trainer tr4 = new Trainer("Someone", "0541234567", 123456789);

        Trainer tr5 = new Trainer("Dr.  Who", "0524545486", 562021);

        ArrayList<Trainer> trainerList = new ArrayList<>();
        trainerList.add(tr1);
        trainerList.add(tr2);
        trainerList.add(tr3);
        trainerList.add(tr4);
        trainerList.add(tr5);

        for (Trainer tr : trainerList) {
            db_pokemon.execSQL("insert into tbl_trainer values('" + tr.getName() + "', '" + tr.getPhone() + "','" + tr.getId() + "')");
        }
    }
    public static void addDefault_Pokemons(SQLiteDatabase db){
        db.execSQL("delete from "+Utils.TABLE_NAME_POKEMON);

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
        Pokemon pk6 = new Pokemon("riyachu",
                5500, "electricity");
        ArrayList<Pokemon> pokemonList =
                new ArrayList<>();

        pokemonList.add(pk1);
        pokemonList.add(pk2);
        pokemonList.add(pk3);
        pokemonList.add(pk4);
        pokemonList.add(pk5);
        pokemonList.add(pk6);

        for (Pokemon p: pokemonList) {
            db.execSQL("insert into tbl_pokemon values(null, '"+p.getName()+"',"+p.getPower()+",'"+p.getType()+"')");
        }
    }
}
