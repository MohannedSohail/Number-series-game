package com.example.numberseriesgame;

public class Question {
    private String [][] data;
    private int   hiddenNumber;

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public int getHiddenNumber() {
        return hiddenNumber;
    }

    public void setHiddenNumber(int hiddenNumber) {
        this.hiddenNumber = hiddenNumber;
    }

    public Question(String[][] data, int hiddenNumber) {
        this.data = data;
        this.hiddenNumber = hiddenNumber;

    }
}
