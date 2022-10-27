package com.example.sudoku.view;


import com.example.sudoku.model.SudokuManager;
import com.example.sudoku.model.SudokuUtilities;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Controller {
    private com.example.sudoku.view.SudokuView view;
    private SudokuManager sudokuManager;
    private com.example.sudoku.view.GridView gridView;

    public Controller(com.example.sudoku.view.SudokuView view, SudokuManager sudokuManager) {
        this.view = view;
        this.sudokuManager = sudokuManager;
    }

    public void startNewGame() {
        sudokuManager.createBoard();
        gridView.updateTileFont();
        gridView.updateGridView();
    }

    public void setDifficulty(SudokuUtilities.SudokuLevel difficulty) {
        sudokuManager.setDifficulty(difficulty);
        gridView.updateTileFont();
        gridView.updateGridView();
    }

    public void clearUserInput() {
        sudokuManager.clearBoardArray();
        gridView.updateGridView();
    }

    public void setGridView(){
        gridView = view.getGridView();
    }

    public void setGuess(int guess){
        sudokuManager.setGuess(guess);
    }

    private void gameIsOver(){
        if(sudokuManager.checkIfCorrect()){
            Alert a = new Alert(Alert.AlertType.NONE,
                    "Game is over. Your inputs are correct. Good job!", ButtonType.OK);
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.NONE,
                    "Game is over. Your inputs are incorrect, Try again!",ButtonType.OK);
            a.show();
        }
    }

    public void makeGuess(int row, int col){
        sudokuManager.makeGuess(row, col);
        gridView.updateGridView();
        if (sudokuManager.gameIsOver()){
            gameIsOver();
        }
    }

    public void hint() {
        sudokuManager.randomHint();
        gridView.updateGridView();
        if (sudokuManager.gameIsOver()){
            gameIsOver();
        }
    }

    public void handleExit() {
        System.exit(0);
    }
}
