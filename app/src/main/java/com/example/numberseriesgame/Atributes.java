package com.example.numberseriesgame;

public class Atributes {
    int score;
    String name;
    int age;
    String date;

    public Atributes(int score, String name, int age, String date) {
        this.score = score;
        this.name = name;
        this.age = age;
        this.date = date;
    }

    public Atributes(int score, String name, String date) {
        this.score = score;
        this.name = name;
        this.date = date;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
