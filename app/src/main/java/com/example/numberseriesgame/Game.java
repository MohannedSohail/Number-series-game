package com.example.numberseriesgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Game extends AppCompatActivity {
    TextView tv_score, tv_gamer, tv_age, tv_num1, tv_num2, tv_num3, tv_num4, tv_num5, tv_num6, tv_num7, tv_num8, tv_num9;
    EditText ed_enternumber;
    Button btn_check, btn_newgame;


    SharedPreferences data;
    SharedPreferences.Editor re_data;

    Database dp_game;

    MediaPlayer mw;
    MediaPlayer ml;
    int hiddennum;

    View T;
    View F;
    TextView tv1;
    TextView tv2;
    Toast tr;
    Toast tf;


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ( (MenuBuilder)menu).setOptionalIconsVisible(true);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Settings:
                Intent intent = new Intent(getBaseContext(), Settings.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                dp_game.delete_tb();


                re_data.remove("full name");
                re_data.remove("email");
                re_data.remove("user_name");
                re_data.remove("passward");
                re_data.remove("country");
                re_data.remove("date");
                re_data.remove("gender");
                re_data.remove("full name");
                re_data.commit();
                re_data.clear();
                re_data.commit();
                getApplicationContext().getSharedPreferences("register", MODE_PRIVATE).edit().clear().commit();

                Intent dl=new Intent(getBaseContext(),MainActivity.class);
                startActivity(dl);

                return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*ال custom toast ,  الصوت*/
        T= LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_toast,null,false);
        tv1=T.findViewById(R.id.tv_true);
        tv1.setText("The number entered is correct \n");
        tr=new Toast(getBaseContext());


        F= LayoutInflater.from(getBaseContext()).inflate(R.layout.cutom_toast1,null,false);
        tv2=F.findViewById(R.id.tv);
        tv2.setText("The entered number is wrong \n");
        tf=new Toast(getBaseContext());



        dp_game = new Database(this);

        data = getSharedPreferences("register", MODE_PRIVATE);
        re_data = data.edit();

        tv_score = findViewById(R.id.agame_tv_score);
        tv_gamer = findViewById(R.id.agame_tv_gamer);
        tv_age = findViewById(R.id.agame_tv_age);
        tv_num1 = findViewById(R.id.agame_tv_num1);
        tv_num2 = findViewById(R.id.agame_tv_num2);
        tv_num3 = findViewById(R.id.agame_tv_num3);
        tv_num4 = findViewById(R.id.agame_tv_num4);
        tv_num5 = findViewById(R.id.agame_tv_num5);
        tv_num6 = findViewById(R.id.agame_tv_num6);
        tv_num7 = findViewById(R.id.agame_tv_num7);
        tv_num8 = findViewById(R.id.agame_tv_num8);
        tv_num9 = findViewById(R.id.agame_tv_num9);
        ed_enternumber = findViewById(R.id.agame_ed_enter_num);
        btn_check = findViewById(R.id.agame_btn_check);
        btn_newgame = findViewById(R.id.agame_btn_newgame);


     tv_score.setText("0");


        Question q = Util.generteQuestion();

        hiddennum = q.getHiddenNumber();
        String[][] d = q.getData();
        tv_num1.setText(d[0][0]);
        tv_num2.setText(d[0][1]);
        tv_num3.setText(d[0][2]);
        tv_num4.setText(d[1][0]);
        tv_num5.setText(d[1][1]);
        tv_num6.setText(d[1][2]);
        tv_num7.setText(d[2][0]);
        tv_num8.setText(d[2][1]);
        tv_num9.setText(d[2][2]);




        /*ارجاع الاسم والعمر*/
        String name = data.getString("full name", null);
        int user_age = data.getInt("age", 0);
        tv_gamer.setText(name);
        tv_age.setText(user_age + "");


        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int entnum = Integer.parseInt(ed_enternumber.getText().toString());

                if (entnum == hiddennum) {

                    tv_score.setText("30");

/*استخدام ال custom Toast*/
                    tr.setView(T);
                    tr.setDuration(Toast.LENGTH_LONG);
                    tr.show();

                    mw = MediaPlayer.create(getBaseContext(), R.raw.win);
                    mw.start();
                } else {


                    tf.setView(F);
                    tf.setDuration(Toast.LENGTH_LONG);
                    tf.show();

                    tv_score.setText("-10");

                    ml = MediaPlayer.create(getBaseContext(), R.raw.lose);
                    ml.start();
                }

                Calendar dt = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
                 String date = format.format(dt.getTime());

                int score = Integer.parseInt(tv_score.getText().toString());

                String name = tv_gamer.getText().toString();

                Atributes b = new Atributes(score, name, date);
                //استعمال ال database
                dp_game.addition(b);



            }
        });


        btn_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_score.setText("0");
                Question q = Util.generteQuestion();


               hiddennum = q.getHiddenNumber();
                String[][] d = q.getData();
                tv_num1.setText(d[0][0]);
                tv_num2.setText(d[0][1]);
                tv_num3.setText(d[0][2]);
                tv_num4.setText(d[1][0]);
                tv_num5.setText(d[1][1]);
                tv_num6.setText(d[1][2]);
                tv_num7.setText(d[2][0]);
                tv_num8.setText(d[2][1]);
                tv_num9.setText(d[2][2]);





            }
        });
    }

}
