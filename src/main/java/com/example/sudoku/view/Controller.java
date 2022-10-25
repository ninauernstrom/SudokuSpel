package com.example.sudoku.view;


import com.example.sudoku.model.SudokuManager;
import com.example.sudoku.model.SudokuUtilities;

public class Controller {
    private com.example.sudoku.view.SudokuView view;
    private SudokuManager sudokuManager;
    private com.example.sudoku.view.GridView gridView;

    public Controller(com.example.sudoku.view.SudokuView view, SudokuManager sudokuManager) {
        this.view = view;
        this.sudokuManager = sudokuManager;
    }

    public Controller(com.example.sudoku.view.GridView gridView, SudokuManager sudokuManager){
        this.gridView = gridView;
        this.sudokuManager = sudokuManager;
    }
    public void startNewGame(com.example.sudoku.view.GridView gridView) {
        sudokuManager.createBoard();
        gridView.updateGridView();
    }

    public void setDifficulty(SudokuUtilities.SudokuLevel difficulty, com.example.sudoku.view.GridView gridView) {
        sudokuManager.setDifficulty(difficulty);
        gridView.updateGridView();
    }

    public void setGuess(int guess){
        sudokuManager.setGuess(guess);
    }

    public void makeGuess(int row, int col){
        sudokuManager.makeGuess(row, col);
        gridView.updateGridView();
    }

    public void handleExit() {
        System.exit(0);
    }
}
