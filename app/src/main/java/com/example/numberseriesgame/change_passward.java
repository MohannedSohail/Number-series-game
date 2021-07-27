package com.example.numberseriesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class change_passward extends AppCompatActivity {
    Button btn_save;
    EditText ed_old_passward,ed_new_passward,ed_re_passward;

    SharedPreferences data;
    SharedPreferences.Editor re_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data=getSharedPreferences("register",MODE_PRIVATE);
        re_data=data.edit();

        setContentView(R.layout.activity_change_passward);
        btn_save=findViewById(R.id.change_btn_save);
        ed_old_passward=findViewById(R.id.change_ed_oldpassward);
        ed_new_passward=findViewById(R.id.change_ed_newpassward);
        ed_re_passward=findViewById(R.id.change_ed_renewpassward);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String old=ed_old_passward.getText().toString();
                String passward=ed_new_passward.getText().toString();
                String repassward=ed_re_passward.getText().toString();

                if (!passward.isEmpty() && !repassward.isEmpty() && repassward.equals(passward)){
                    re_data.putString("passward",passward);
                    Toast.makeText(getBaseContext(),"Your passward is changed ",Toast.LENGTH_SHORT).show();
                }

              else Toast.makeText(getBaseContext(),"wrong repassward",Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(getBaseContext(),Settings.class);
                startActivity(intent);
        }});
    }}

