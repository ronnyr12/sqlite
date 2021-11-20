package com.example.mylistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditScreen extends AppCompatActivity {

    Button btn_submit;
    EditText et_name, et_type, et_power;
    ImageView img;
    SQLiteDatabase db;
    String column;
    Context c;
    String table_name;
    TextView title;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);
        et_name = findViewById(R.id.name);
        et_type = findViewById(R.id.type);
        et_power = findViewById(R.id.power);
        btn_submit = findViewById(R.id.edit);
        img = findViewById(R.id.img);
        title = findViewById(R.id.title);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        Intent intent = getIntent();
        column = intent.getStringExtra("column");
        System.out.println(column);
        System.out.println(column);
        table_name = intent.getStringExtra("tbl_name");

        if(table_name.equals("tbl_pokemon")){
            Cursor cursor = db.rawQuery("select * from tbl_pokemon where id = " + column , null);
            cursor.moveToFirst();
            et_name.setText(cursor.getString(cursor.getColumnIndex("name")));
            et_type.setText(cursor.getString(cursor.getColumnIndex("type")));
            et_power.setText(String.valueOf(cursor.getString(cursor.getColumnIndex("power"))));
            c = getApplicationContext();
            if(c.getResources().getIdentifier(et_name.getText().toString().toLowerCase(), "drawable", c.getPackageName()) != 0){
                img.setImageResource(c.getResources().getIdentifier(et_name.getText().toString().toLowerCase(), "drawable", c.getPackageName()));
            }
            else{
                img.setImageResource(R.drawable.poca_ball);
            }


            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(et_name.getText().toString().isEmpty() || et_type.getText().toString().isEmpty() || et_power.getText().toString().isEmpty()){
                        Toast.makeText(EditScreen.this, "Fields empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ContentValues cv = new ContentValues();
                        cv.put("name", et_name.getText().toString());
                        cv.put("power", Integer.parseInt(et_power.getText().toString()));
                        cv.put("type", et_type.getText().toString());

                        db.update(Utils.TABLE_NAME_POKEMON, cv, "rowid=?",new String[]{column});
                        Intent i = new Intent(EditScreen.this, MainActivity.class);
                        i.putExtra("tbl_name", table_name);
                        startActivity(i);

                    }
                }
            });
        }

        else{
            title.setText("Trainer Editor");
            System.out.print(column);
            Cursor cursor = db.rawQuery("select * from " + table_name + " where id=" + column, null );
            cursor.moveToFirst();
            et_power.setEnabled(false);

            et_name.setText(cursor.getString(cursor.getColumnIndex("name")));
            et_type.setText(cursor.getString(cursor.getColumnIndex("phone")));
            et_power.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));

            et_type.setHint("Phone");
            et_power.setHint("ID");
            c = getApplicationContext();
            img.setImageResource(R.drawable.trainer);


            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(et_name.getText().toString().isEmpty() || et_type.getText().toString().isEmpty() || et_power.getText().toString().isEmpty()){
                        Toast.makeText(EditScreen.this, "Fields empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ContentValues cv = new ContentValues();
                        cv.put("name", et_name.getText().toString());
                        cv.put("id", Integer.parseInt(et_power.getText().toString()));
                        cv.put("phone", et_type.getText().toString());

                        db.update(table_name, cv, "rowid=?",new String[]{column});
                        Intent i = new Intent(EditScreen.this, MainActivity.class);
                        i.putExtra("tbl_name", table_name);
                        startActivity(i);

                    }
                }
            });
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.delete){

            db.delete(table_name, "rowid=?", new String[]{column});
            db.execSQL("vacuum"); //resets rowids
            Intent intent = new Intent(EditScreen.this, MainActivity.class);
            intent.putExtra("tbl_name", table_name);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(EditScreen.this, MainActivity.class);
        i.putExtra("tbl_name", table_name);
        startActivity(i);
    }
}