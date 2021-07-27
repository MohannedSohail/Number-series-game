package com.example.numberseriesgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    EditText ed_name,ed_passward;
    Button btn_login,btn_register;
    CheckBox check_remember;


    SharedPreferences data;
    SharedPreferences.Editor re_data;




    public static final int Register_REQ_COde=2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        data=getSharedPreferences("register",MODE_PRIVATE);
        re_data=data.edit();

        iv=findViewById(R.id.am_iv_user);
        ed_name=findViewById(R.id.am_ed_name);
        ed_passward=findViewById(R.id.am_ed_passward);
        btn_login=findViewById(R.id.am_btn_login);
        btn_register=findViewById(R.id.am_btn_reg);
        check_remember=findViewById(R.id.am_check_remember);

       Boolean shared= data.getBoolean("check",false);

        if (shared==true){
            Intent check=new Intent(MainActivity.this,Game.class);
            startActivity(check);
        }



       check_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){

                   re_data.putBoolean("check",true);
                   re_data.apply();

               }else{
                   re_data.putBoolean("check",false);
                   re_data.apply();
               }
           }
       });



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),Register.class);

                startActivityForResult(intent,Register_REQ_COde);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String names= data.getString("user_name","");
             String passw= data.getString("passward","");
             String rename= ed_name.getText().toString();
             String pas= ed_passward.getText().toString();
             if (rename.equals(names) && pas.equals(passw) ){
                 if (!rename.isEmpty() && !pas.isEmpty()){

                 Intent DD=new Intent(MainActivity.this,Game.class);
                 startActivity(DD);}

             } else Toast.makeText(getBaseContext(),"Empty field",Toast.LENGTH_LONG).show();



            }
        });

    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder dia=new AlertDialog.Builder(this);
        dia.setTitle("Alert");
        dia.setMessage("Do you want to Exit");
        dia.setIcon(R.drawable.emotion);
        dia.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        dia.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               MainActivity.super.onBackPressed();
               /* MainActivity.this.finish();*/    /*نفس عمل الكود الي فوق*/
            }
        });
        AlertDialog di=dia.create();
        di.setCanceledOnTouchOutside(false);
        di.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Register_REQ_COde && resultCode==1){

            String name=data.getStringExtra("username");
            String passward=data.getStringExtra("passw");
            ed_name.setText(name);
            ed_passward.setText(passward);





        }

    }



}
