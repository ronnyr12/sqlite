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

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);
        Intent intent = getIntent();
        column = String.valueOf(intent.getIntExtra("column", 0));
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_NAME_POKEMON + " where rowid=" + column, null );
        cursor.moveToFirst();

        et_name.setText(cursor.getString(cursor.getColumnIndex("name")));
        et_type.setText(cursor.getString(cursor.getColumnIndex("type")));
        et_power.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("power"))));
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
                    startActivity(i);

                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.delete){
            db.delete(Utils.TABLE_NAME_POKEMON, "rowid=?", new String[]{column});
            Intent intent = new Intent(EditScreen.this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}