package com.example.mylistview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils {
    final static String INTENT_KEY_POKEMON_NAME = "pokemon_name";
    final static String ADMIN_ID = "15262";



    final static String DATABASE_NAME = "db_pokemon_app";
    final static String TABLE_NAME_POKEMON = "tbl_pokemon";
    final static String TABLE_NAME_CAUGHT = "tbl_caught";
    final static String TABLE_POKEMON_COL_NAME = "name";
    final static String TABLE_POKEMON_COL_POWER = "power";
    final static String TABLE_POKEMON_COL_TYPE = "type";

    public static void createTables(SQLiteDatabase db){

        db.execSQL("create table if not exists tbl_pokemon(id integer primary key autoincrement, name text, power integer, type text)");

        db.execSQL("create table if not exists tbl_trainer(id integer primary key autoincrement, name text, phone text)");

        db.execSQL("create table if not exists " + Utils.TABLE_NAME_CAUGHT + "(id integer, pid integer)");
    }

    public static void addALLToDb(SQLiteDatabase db){

        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + Utils.TABLE_NAME_POKEMON, null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }


        if(empty) {
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

            for (Pokemon p : pokemons) {
                db.execSQL("insert into tbl_pokemon('name', power, 'type') values('" + p.getName() + "'," + p.getPower() + ",'" + p.getType() + "')");
            }
        }

        boolean trainers_empty = true;
        cur = db.rawQuery("SELECT COUNT(*) FROM " + "tbl_trainer", null);
        if (cur != null && cur.moveToFirst()) {
            trainers_empty = (cur.getInt (0) == 0);
        }

        if(trainers_empty){
            ArrayList<Trainer> trainers = new ArrayList<>();

            Trainer t0 = new Trainer("Ashe", "100000");
            Trainer t1 = new Trainer("Muradik", "111111");
            Trainer t2 = new Trainer("Roni", "222222");
            Trainer t3 = new Trainer("Rafael", "333333");

            trainers.add(t0);
            trainers.add(t1);
            trainers.add(t2);
            trainers.add(t3);

            for(Trainer t : trainers){
                db.execSQL("insert into tbl_trainer('name', 'phone') values('"+t.getName()+"',"+t.getPhone()+")");
            }
        }

            cur.close();
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
            db.execSQL("insert into tbl_pokemon('name', 'power', 'type') values('"+p.getName()+"',"+p.getPower()+",'"+p.getType()+"')");
        }

        db.execSQL("delete from tbl_trainer");
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "tbl_trainer" + "'");
        ArrayList<Trainer> trainers = new ArrayList<>();

        Trainer t0 = new Trainer("Ashe", "100000");
        Trainer t1 = new Trainer("Muradik", "111111");
        Trainer t2 = new Trainer("Roni", "222222");
        Trainer t3 = new Trainer("Rafael", "333333");
        trainers.add(t0);
        trainers.add(t1);
        trainers.add(t2);
        trainers.add(t3);

        for(Trainer t : trainers){
            db.execSQL("insert into tbl_trainer('name', 'phone') values('"+t.getName()+"',"+t.getPhone()+")");
        }
    }
}
