package com.example.sudoku.model;

public class BoxData {

    private final int correctValue;
    private final int showAtStart;
    private int userInputValue;

    public BoxData(int correctValue, int show) {
        this.correctValue = correctValue;
        this.showAtStart = show;
        userInputValue = 0;
    } //test

    public BoxData(int correctValue, int show, int userInputValue) {
        this.correctValue = correctValue;
        this.showAtStart = show;
        this.userInputValue = userInputValue;
    }

    public int getCorrectValue() {
        return correctValue;
    }

    public int getValueToShow(){
        if(showAtStart == 1) return correctValue;
        return userInputValue;
    }

    public int getStartValue(){
        if(showAtStart == 1) return correctValue;
        return 0;
    }

    public int getShowAtStart(){ return showAtStart; }

    public int getUserInputValue(){ return userInputValue; }

    public void setUserInputValue(int userInputValue) throws IllegalArgumentException {
        this.userInputValue = userInputValue;
    }

    @Override
    public String toString() {
        return  "correctValue=" + correctValue +
                "\n showAtStart=" + showAtStart +
                "\n userInputValue=" + userInputValue;
    }
}
