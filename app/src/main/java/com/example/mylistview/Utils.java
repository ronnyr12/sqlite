package com.example.mylistview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils {
    final static String INTENT_KEY_POKEMON_NAME = "pokemon_name";


    final static String DATABASE_NAME = "db_pokemon_app";

    final static String TABLE_NAME_POKEMON = "tbl_pokemon";
    final static String TABLE_POKEMON_COL_NAME = "name";
    final static String TABLE_POKEMON_COL_POWER = "power";
    final static String TABLE_POKEMON_COL_TYPE = "type";

    public static void createTables(SQLiteDatabase db){

        //db.execSQL("create table if not exists tbl_pokemon(name text, et_power integer, et_type text)");

        db.execSQL("create table if not exists "+Utils.TABLE_NAME_POKEMON+
                " ("+Utils.TABLE_POKEMON_COL_NAME+" text, "+Utils.TABLE_POKEMON_COL_POWER+" integer, "+Utils.TABLE_POKEMON_COL_TYPE+" text)");
    }

    public static void addALLToDb(SQLiteDatabase db){

        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + Utils.TABLE_NAME_POKEMON, null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        if(empty){
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            Pokemon pk0 = new Pokemon("Charmander", 1400, "Fire");
            Pokemon pk1 = new Pokemon("Pikachu", 2350, "Electric");
            Pokemon pk2 = new Pokemon("Charizard", 2700, "Fire");
            Pokemon pk3 = new Pokemon("Eevee", 1800, "Normal");
            Pokemon pk4 = new Pokemon("Snorlax", 2100, "Normal");
            Pokemon pk5 = new Pokemon("Squirtle", 1600, "Water");
            Pokemon pk6 = new Pokemon("Bulbasaur", 800, "Grass");
            Pokemon pk7 = new Pokemon("Lucario", 1700, "Wind");
            Pokemon pk8 = new Pokemon("Jigglypuff", 500, "Fairy");
            Pokemon pk9 = new Pokemon("Ditto", 400, "Fairy");

            pokemons.add(pk0);
            pokemons.add(pk1);
            pokemons.add(pk2);
            pokemons.add(pk3);
            pokemons.add(pk4);
            pokemons.add(pk5);
            pokemons.add(pk6);
            pokemons.add(pk7);
            pokemons.add(pk8);
            pokemons.add(pk9);

            for (Pokemon p: pokemons) {
                db.execSQL("insert into tbl_pokemon values('"+p.getName()+"',"+p.getPower()+",'"+p.getType()+"')");
            }
        }

    }
    public static void insertAll(SQLiteDatabase db){

        db.execSQL("delete from " + Utils.TABLE_NAME_POKEMON);

        ArrayList<Pokemon> pokemons = new ArrayList<>();

        Pokemon pk0 = new Pokemon("Charmander", 1400, "Fire");
        Pokemon pk1 = new Pokemon("Pikachu", 2350, "Electric");
        Pokemon pk2 = new Pokemon("Charizard", 2700, "Fire");
        Pokemon pk3 = new Pokemon("Eevee", 1800, "Normal");
        Pokemon pk4 = new Pokemon("Snorlax", 2100, "Normal");
        Pokemon pk5 = new Pokemon("Squirtle", 1600, "Water");
        Pokemon pk6 = new Pokemon("Bulbasaur", 800, "Grass");
        Pokemon pk7 = new Pokemon("Lucario", 1700, "Wind");
        Pokemon pk8 = new Pokemon("Jigglypuff", 500, "Fairy");
        Pokemon pk9 = new Pokemon("Ditto", 400, "Fairy");

        pokemons.add(pk0);
        pokemons.add(pk1);
        pokemons.add(pk2);
        pokemons.add(pk3);
        pokemons.add(pk4);
        pokemons.add(pk5);
        pokemons.add(pk6);
        pokemons.add(pk7);
        pokemons.add(pk8);
        pokemons.add(pk9);

        for (Pokemon p: pokemons) {
            db.execSQL("insert into tbl_pokemon values('"+p.getName()+"',"+p.getPower()+",'"+p.getType()+"')");
        }
    }
}
