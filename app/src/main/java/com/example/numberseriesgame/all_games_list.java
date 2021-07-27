package com.example.numberseriesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class all_games_list extends AppCompatActivity {
    ListView lv;

    ArrayList<Atributes> data;

    Database dp_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_games_list);
        lv=findViewById(R.id.lv);

        dp_game = new Database(this);

       data=new ArrayList<>();
        data=dp_game.getAlldata();
       Game_customAdapter adapter=new Game_customAdapter(this,R.layout.listview_design,data);
       lv.setAdapter(adapter);
    }
}
