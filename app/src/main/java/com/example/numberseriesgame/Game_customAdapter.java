package com.example.numberseriesgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Game_customAdapter extends BaseAdapter {
    TextView tv_name;
    TextView tv_date;
    TextView tv_score;
    Context c;
    ArrayList<Atributes> data;
    int design;

    public Game_customAdapter(Context c, int design,ArrayList<Atributes> data){
        this.c=c;
        this.design=design;
        this.data=data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Atributes getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if(v==null){
            v= LayoutInflater.from(c).inflate(design,null,false);

             tv_name = v.findViewById(R.id.des_tv_fullname);
             tv_date = v.findViewById(R.id.des_tv_date);
             tv_score = v.findViewById(R.id.des_tv_score);
    }

        Atributes a=getItem(position);
        tv_name.setText(a.getName());
        tv_date.setText(a.getDate());
        tv_score.setText(a.getScore()+"");


        return v;
}}
