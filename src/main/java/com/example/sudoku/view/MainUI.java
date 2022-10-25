package com.example.sudoku.view;

import com.example.sudoku.model.SudokuManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// NB! To run this code, create a proper JavaFX project
public class MainUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        SudokuManager sudokuManager = new SudokuManager();
        sudokuManager.createBoard();

        SudokuView view = new SudokuView(sudokuManager); // creates the controller and gridview object
        //GridView gridView = new GridView(sudokuManager); // inside controller now

        MenuBar menubar = view.getMenuBar();

        //view.setCenter(gridView); // controller has this method now

        // we need a VBox to put the menu bar at the top of the window
        VBox root = new VBox(menubar, view);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

        primaryStage.setTitle("Sudoku");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}