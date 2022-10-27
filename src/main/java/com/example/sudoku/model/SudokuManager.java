package com.example.sudoku.model;


import static com.example.sudoku.model.SudokuUtilities.SudokuLevel.*;
import static com.example.sudoku.model.SudokuUtilities.generateSudokuMatrix;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represents data for a 9x9 Sudoku board
 * It is used to data for every box on the board,
 * boardArray a 2d array of BoxData
 * difficulty represents one of three difficulties, easy, medium, hard
 * guess stores the current player guess for a box
 */
public class SudokuManager implements Serializable{

    private BoxData[][] boardArray;
    private SudokuUtilities.SudokuLevel difficulty;
    private int guess;

    /**
     * Constructs a new SudokuManager with a 9x9 2d array of BoxData
     * and sets difficulty the easy, our default value
     * guess value is set to 0
     */
    public SudokuManager() {
        boardArray = new BoxData[9][9];
        difficulty = SudokuUtilities.SudokuLevel.EASY;
        guess = 0;
    }

    /**
     * method to reset the Sudoku board
     * creates new 2d array with BoxData
     * @return createBoard()
     */
    public BoxData[][] reset(){
        boardArray = new BoxData[9][9];
        return createBoard();
    }
    /**
     * method to set the Sudoku board difficulty
     * @return reset()
     */
    public BoxData[][] setDifficulty(SudokuUtilities.SudokuLevel difficulty){
        this.difficulty = difficulty;
        return reset();
    }
    /**
     * method to set the guess value
     * @return guess
     */
    public void setGuess(int guess){ this.guess = guess; }

    /**
     * method to get the guess value
     * @return guess
     */
    public int getGuess(){ return guess; }

    /**
     * method to get make guess for a BoxData
     * uses gameIsOver method to check that there is a BoxData which can be guesed upon
     */
    public void makeGuess(int row, int column){
        if(!gameIsOver()){
            boardArray[row][column].setUserInputValue(guess);
        }

    }
    /**
     * method to check if game is over
     * does this by checking if there exists boxData on the board which are 0
     * 0 represents an empty tile on the Sudoku board
     * if yes return false if no return true
     * @return boolean
     */
    public boolean gameIsOver(){
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(boardArray[i][j].getValueToShow() == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * method to create a Sudoku board
     * does this by looping each of the BoxData objects of boardArray
     * gets data for each BoxData from SudokuUtilities.generateSudokuMatrix(difficulty)
     * @return boardArray
     */
    public BoxData[][] createBoard() {
        int[][][] fullBoard  = SudokuUtilities.generateSudokuMatrix(difficulty);
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int show = 0; // represents if correct value should be shown at start
                if(fullBoard[i][j][0] != 0) show = 1;
                boardArray[i][j] = new BoxData(fullBoard[i][j][1], show); //[row][col][0] represents the initial values, zero representing an empty cell.
            }
        }
        return boardArray;
    }

    /**
     * method to copy and a array of the current SudokuBoard
     * @return BoxData[][] copy
     */
    public BoxData[][] getBoardArray(){
        BoxData[][] copy = new BoxData[9][9];
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = new BoxData(boardArray[i][j].getCorrectValue(),
                        boardArray[i][j].getShowAtStart(),
                        boardArray[i][j].getUserInputValue());
            }
        }
        return copy;
    }

    /**
     * method clear all user inputs on the Sudoku board
     * @return boardArray
     */
    public BoxData[][] clearBoardArray(){
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(boardArray[i][j].getUserInputValue()!=0) {
                    boardArray[i][j].setUserInputValue(0);
                }
            }
        }
        return boardArray;
    }

    /**
     * method to check if user inputs are correct
     * @return boolean
     */
    public Boolean checkIfCorrect(){
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(boardArray[i][j].getUserInputValue() != 0) {
                    if (boardArray[i][j].getUserInputValue() != boardArray[i][j].getCorrectValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * method to give player a hint of a random box on the Sudoku board
     * @return boardArray
     */
    public BoxData[][] randomHint() {
        if(gameIsOver()) {
            return boardArray;
        }
        int[] availTiles = new int[81];
        int amount = 0;
        int[] row = new int[81];
        int[] col = new int[81];
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(boardArray[i][j].getUserInputValue() == 0 && boardArray[i][j].getShowAtStart() == 0) {
                    availTiles[amount] = boardArray[i][j].getCorrectValue();
                    row[amount] = i;
                    col[amount] = j;
                    amount++;
                }
            }
        }
        Random rn = new Random();
        int randomTileIndex = rn.nextInt(amount);
        boardArray[row[randomTileIndex]][col[randomTileIndex]].setUserInputValue(availTiles[randomTileIndex]);

        return boardArray;
    }

    /**
     * Converts data members in SudokuManager to a string to present the SudokuManager
     *
     * @return string of data members
     */
    @Override
    public String toString() {
        String info = "initial board: " + "\n";
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                info += boardArray[i][j].getStartValue();
            }
            info += "\n";
        }
        info += "solution board: " + "\n";
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                info += boardArray[i][j].getCorrectValue();  // [row][col][0] represents the initial values, zero representing an empty cell.
                // [row][col][1] represents the solution.
            }
            info += "\n";
        }
        return info;
    }
}
