package com.example.sudoku.model;

import java.io.Serializable;
/**
 * This class represents data for a number box on a Sudoku board.
 * It is used to store three types of data for each box
 * the correct value,
 * if the box will be shown on start 1 represents yes, 0 no,
 * and the user input value.
 */
public class BoxData implements Serializable {

    private final int correctValue;
    private final int showAtStart;
    private int userInputValue;
    /**
     * Constructs a new BoxData with specified correctValue and show.
     *
     * @param correctValue The correct value for this box
     * @param show If the box is supposed to be shown at start
     */
    public BoxData(int correctValue, int show) {
        this.correctValue = correctValue;
        this.showAtStart = show;
        userInputValue = 0;
    }
    /**
     * Constructs a new BoxData with specified correctValue and show.
     *
     * @param correctValue The correct value for this box
     * @param show If the box is supposed to be shown at start
     * @param userInputValue The user input value
     */
    public BoxData(int correctValue, int show, int userInputValue) {
        this.correctValue = correctValue;
        this.showAtStart = show;
        this.userInputValue = userInputValue;
    }
    /**
     * Returns the correctValue of the BoxData.
     * @return the correctValue
     */
    public int getCorrectValue() {
        return correctValue;
    }

    /**
     * Returns the value to be shown on the board
     * @return userInputValue
     */
    public int getValueToShow(){
        if(showAtStart == 1) return correctValue;
        return userInputValue;
    }

    /**
     * Returns the correct value if box is supposed to be shown at start else 0
     * @return correctValue or 0
     */
    public int getStartValue(){
        if(showAtStart == 1) return correctValue;
        return 0;
    }

    /**
     * Returns the showAtStart value
     * @return showAtStart
     */
    public int getShowAtStart(){ return showAtStart; }

    /**
     * Returns the userInputValue value
     * @return userInputValue
     */
    public int getUserInputValue(){ return userInputValue; }

    /**
     * Sets the userInputValue for a BoxData
     *
     * @param userInputValue the value to be set
     */
    public void setUserInputValue(int userInputValue) throws     IllegalArgumentException {
        this.userInputValue = userInputValue;
    }

    /**
     * Converts data members in BoxData to a string to present the BoxData
     *
     * @return string of data members
     */
    @Override
    public String toString() {
        return  "correctValue=" + correctValue +
                "\n showAtStart=" + showAtStart +
                "\n userInputValue=" + userInputValue;
    }
}
