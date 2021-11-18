package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_Screen extends AppCompatActivity {

    EditText et_name, et_type, et_power;
    Button btn_save;
    ImageView img;
    SQLiteDatabase db;
    String table_name;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Intent intent = getIntent();
        table_name = intent.getStringExtra("tbl_name");

        et_name = findViewById(R.id.name);
        et_type = findViewById(R.id.type);
        et_power = findViewById(R.id.power);
        btn_save = findViewById(R.id.save);
        img = findViewById(R.id.img);
        title = findViewById(R.id.title);

        if(table_name.equals("tbl_trainer")){
            title.setText("Add Trainer");
            img.setImageResource(R.drawable.trainer);
            et_type.setHint("Phone");
            et_power.setHint("ID");
        }
        else{
            img.setImageResource(R.drawable.poca_ball);
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                int power = Integer.parseInt(et_power.getText().toString());
                String type = et_type.getText().toString();

                String values = "('" + name + "', " + power + ", '" + type + "')";
                db.execSQL("insert into " + table_name + " values" + values);
                Intent i = new Intent(Add_Screen.this, MainActivity.class);
                i.putExtra("tbl_name", table_name);
                startActivity(i);
            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Add_Screen.this, MainActivity.class);
        i.putExtra("tbl_name", table_name);
        startActivity(i);
    }
}