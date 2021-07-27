package com.example.numberseriesgame;

public class Util {
    public static Question generteQuestion(){


        String [][] x=new String[3][3];

int startNumber=(int) (Math.random()*10)+1;

int incStartNumber=(int) (Math.random()*5)+1;
int StredNumber;
int number=-1;

    for (int i=0;i< x.length;i++){
        for (int j=0;j< x[i].length;j++){

            StredNumber=startNumber+incStartNumber;
            if (i==1 && j==1){

                x[i][j]="??";
                number=StredNumber;

            }else x[i][j]=StredNumber+"";

            incStartNumber+=2;
            startNumber=StredNumber;

        }

    }

        return new Question(x,number);
    }



}
