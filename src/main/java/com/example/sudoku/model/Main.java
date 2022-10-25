package com.example.sudoku.model;

import java.util.*;
import static com.example.sudoku.model.SudokuUtilities.SudokuLevel.*;
public class Main {
    public static void main(String []args) {

        SudokuManager testManager = new SudokuManager();
        testManager.createBoard();
        System.out.println(testManager.toString());
        testManager.setDifficulty(SudokuUtilities.SudokuLevel.HARD);
        System.out.println(testManager.toString());
        Scanner sc = new Scanner(System.in);
        BoxData[][]  board;
        while(!testManager.gameIsOver()){
            System.out.println("input guess");
            int guess = sc.nextInt();
            System.out.println("input row");
            int row = sc.nextInt();
            System.out.println("input col");
            int col = sc.nextInt();
            testManager.setGuess(guess);
            testManager.makeGuess(row, col);
            board = testManager.getBoardArray();
            String info = "";
            for(int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                        info += board[i][j].getValueToShow();
                }
                info += "\n";
            }
            System.out.println(info);
        }

    }
}