package com.example.numberseriesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Settings extends AppCompatActivity {
    Button btn_showall, btn_showlast, btn_change, btn_history;
    Database dp_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dp_game = new Database(this);

        setContentView(R.layout.activity_settings);
        btn_change = findViewById(R.id.aset_btn_change);
        btn_history = findViewById(R.id.aset_btn_history);
        btn_showall = findViewById(R.id.aset_btn_show_all);
        btn_showlast = findViewById(R.id.aset_btn_show_last);


        btn_showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        Intent intent=new Intent(getBaseContext(),all_games_list.class);
        startActivity(intent);

            }
        });

        btn_showlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Atributes> atribute = dp_game.getAlldata();
                String date1 = "";
                for (Atributes d : atribute) {
                   date1 = d.getDate();
                }
                if (date1.equals("")) {
                    Toast.makeText(Settings.this, "Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), date1, Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),change_passward.class);
                startActivity(intent);


            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        dp_game.delete_tb();

        Toast.makeText(getBaseContext(),"Your Record game is Deleted ",Toast.LENGTH_LONG).show();




            }
        });

    }
}
