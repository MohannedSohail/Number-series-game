package com.example.numberseriesgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Register extends AppCompatActivity {
    ImageView imv_user;
    EditText ed_fullname,ed_email,ed_username,ed_passward,ed_repassward,ed_date;
    Spinner sp_country;
    RadioButton rb_male,rb_female;
    Button btn_save;



    Uri imgv;

    public static final int REQ_COde=1;


    DatePickerDialog.OnDateSetListener Date;

    SharedPreferences data;
    SharedPreferences.Editor re_data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imv_user=findViewById(R.id.areg_imv_user);
        ed_fullname=findViewById(R.id.areg_ed_fullname);
        ed_email=findViewById(R.id.areg_ed_email);
        ed_username=findViewById(R.id.areg_ed_username);
        ed_passward=findViewById(R.id.areg_ed_pass);
        ed_repassward=findViewById(R.id.areg_ed_repass);
        sp_country=findViewById(R.id.areg_sp_country);
        ed_date=findViewById(R.id.areg_ed_date);
        rb_male=findViewById(R.id.areg_rb_male);
        rb_female=findViewById(R.id.areg_rb_female);
        btn_save=findViewById(R.id.areg_btn_save);


        data=getSharedPreferences("register",MODE_PRIVATE);
        re_data=data.edit();



        imv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img=new Intent(Intent.ACTION_PICK);
                img.setType("image/*");
            startActivityForResult(img,REQ_COde);


            }
        });



        Calendar calender=Calendar.getInstance();
        final int year=calender.get(Calendar.YEAR);
        final int month=calender.get(Calendar.MONTH);
        final int day=calender.get(Calendar.DAY_OF_MONTH);

        ed_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    DatePickerDialog datePickerDialog=new DatePickerDialog(Register.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
        Date,year,month,day);
    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    datePickerDialog.show();

            }
        });

        Date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                String date=day+"/"+month+"/"+year;
                ed_date.setText(date);

            }
        };





        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String full_name=ed_fullname.getText().toString();
                String email=ed_email.getText().toString();
                String user_name=ed_username.getText().toString();
                String passward=ed_passward.getText().toString();
                String repassward=ed_repassward.getText().toString();
                String country=sp_country.getSelectedItem().toString();
                String date=ed_date.getText().toString();



                /*عشان اقدر احسب عمر اللاعب*/
                Calendar cal=Calendar.getInstance();
                final int year=cal.get(Calendar.YEAR);
                String[] d= ed_date.getText().toString().split("/");
                 int old=Integer.parseInt(d[2]);

                 /*عملية حساب عمر اللاعب*/
               int age=year-old;
          re_data.putInt("age",age);




                String gender="Male";
                if(rb_female.isChecked())
                    gender="Female";

                String imv="";
                if(imgv!=null)
                    imv=imgv.toString();

                re_data.putString("Image",imv);


/*عملية فخص المدخلات وتخزينها في ال shared prefefences*/

     if (!full_name.isEmpty()){
       re_data.putString("full name", full_name);
        if (!email.isEmpty()){
          re_data.putString("email", email);
            if(!user_name.isEmpty()){
            re_data.putString("user_name", user_name);
                if (!passward.isEmpty() && !repassward.isEmpty() && repassward.equals(passward)){
                re_data.putString("passward",passward);
                if (!country.isEmpty()){
                     re_data.putString("country",country);
                     if (!date.isEmpty()){
                        re_data.putString("date",date);
                           if (!gender.isEmpty()){
                               re_data.putString("gender",gender);
                               re_data.apply();

                                Intent save=new Intent(getBaseContext(),MainActivity.class);
                                save.putExtra("username",user_name);
                                 save.putExtra("passw",passward);
                                 setResult(1,save);
                                       finish();


                                        }else Toast.makeText(getBaseContext(),"field gender is empty ",Toast.LENGTH_SHORT).show();

                                    }else Toast.makeText(getBaseContext(),"field date is empty",Toast.LENGTH_SHORT).show();

                                }else Toast.makeText(getBaseContext(),"field country is empty ",Toast.LENGTH_SHORT).show();

                            }else Toast.makeText(getBaseContext(),"field repassward is empty ",Toast.LENGTH_SHORT).show();

                        }else Toast.makeText(getBaseContext(),"field user is empty ",Toast.LENGTH_SHORT).show();

                    }else Toast.makeText(getBaseContext(),"field email is empty ",Toast.LENGTH_SHORT).show();

                }else {AlertDialog.Builder ad=new AlertDialog.Builder(Register.this);
         ad.setTitle("alert");
         ad.setIcon(R.drawable.camera);
         ad.setMessage("your component is Empty");
         ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });
         AlertDialog dialog=ad.create();
         dialog.setCanceledOnTouchOutside(false);
         dialog.show();
     }/*Toast.makeText(getBaseContext(),"field fullname is empty ",Toast.LENGTH_SHORT).show();*/





            }
        });

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder dia=new AlertDialog.Builder(this);
        dia.setTitle("Alert");
        dia.setMessage("Do you want to Back");
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
              Register.super.onBackPressed();

            }
        });
        AlertDialog di=dia.create();
        di.setCanceledOnTouchOutside(false);
        di.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_COde && resultCode==RESULT_OK){

            imgv=data.getData();
            imv_user.setImageURI(imgv);



        }

    }
}
