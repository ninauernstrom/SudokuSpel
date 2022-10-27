package com.example.sudoku.view;

import com.example.sudoku.model.SudokuManager;
import com.example.sudoku.model.SudokuUtilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;

public class SudokuView extends BorderPane {

    private Button checkButton;
    private Button hintButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button cButton;
    private MenuBar menuBar;

    private SudokuManager sudokuManager;

    private GridView gridView;

    SudokuView(SudokuManager sudokuManager) {
        super();
        //createUiComponents();
        this.sudokuManager = sudokuManager;
        Controller controller = new Controller(this, sudokuManager);
        gridView = new GridView(sudokuManager, controller);
        createMenuBar(controller);
        createUiComponents();
        addEventHandlers(controller);
        controller.setGridView();
        this.setCenter(gridView);

    }

    private void createUiComponents() {
        checkButton = new Button("Check");
        hintButton = new Button("Hint");
        VBox vboxLeft = new VBox(checkButton, hintButton);
        vboxLeft.setAlignment(Pos.CENTER);
        this.setLeft(vboxLeft);
        oneButton = new Button("1");
        twoButton = new Button("2");
        threeButton = new Button("3");
        fourButton = new Button("4");
        fiveButton = new Button("5");
        sixButton = new Button("6");
        sevenButton = new Button("7");
        eightButton = new Button("8");
        nineButton = new Button("9");
        cButton = new Button("C");
        VBox vboxRight = new VBox(oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, cButton);
        vboxRight.setAlignment(Pos.CENTER);
        this.setRight(vboxRight);

    }

    private void createMenuBar(Controller controller) {
        Menu fileMenu = new Menu("File");
        MenuItem loadItem = new MenuItem("Load game");
        MenuItem saveItem = new MenuItem("Save game");
        MenuItem exitItem = new MenuItem("Exit");
        EventHandler<ActionEvent> exitHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleExit(); // save data and exit
            }
        };
        exitItem.addEventHandler(ActionEvent.ACTION, exitHandler);
        fileMenu.getItems().add(loadItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(exitItem);

        Menu gameMenu = new Menu("Game");
        Menu difficulty = new Menu("Set difficulty");
        MenuItem easyItem = new MenuItem("Easy");
        EventHandler<ActionEvent> difficultyHandlerEasy = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.setDifficulty(SudokuUtilities.SudokuLevel.EASY);
            }
        };
        easyItem.addEventHandler(ActionEvent.ACTION, difficultyHandlerEasy);
        MenuItem mediumItem = new MenuItem("Medium");
        EventHandler<ActionEvent> difficultyHandlerMedium = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.setDifficulty(SudokuUtilities.SudokuLevel.MEDIUM);
            }
        };
        mediumItem.addEventHandler(ActionEvent.ACTION, difficultyHandlerMedium);
        MenuItem hardItem = new MenuItem("Hard");
        EventHandler<ActionEvent> difficultyHandlerHard = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.setDifficulty(SudokuUtilities.SudokuLevel.HARD);
            }
        };
        hardItem.addEventHandler(ActionEvent.ACTION, difficultyHandlerHard);

        MenuItem startItem = new MenuItem("Start new game");
        EventHandler<ActionEvent> startHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.startNewGame();
            }
        };
        startItem.addEventHandler(ActionEvent.ACTION, startHandler);

        gameMenu.getItems().add(startItem);
        gameMenu.getItems().add(difficulty);
        difficulty.getItems().addAll(easyItem,mediumItem,hardItem);

        Menu helpMenu = new Menu("Help");
        MenuItem clearItem = new MenuItem("Clear");
        EventHandler<ActionEvent> clearHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.clearUserInput();
            }
        };
        clearItem.addEventHandler(ActionEvent.ACTION, clearHandler);

        MenuItem checkIfCorrectItem = new MenuItem("Check if correct");

        EventHandler<ActionEvent> checkMenuHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert a1 = new Alert(AlertType.NONE,
                        "Your inputs are so far correct",ButtonType.OK);
                Alert a2 = new Alert(AlertType.NONE,
                        "Your inputs are so far not correct",ButtonType.OK);
                if(sudokuManager.checkIfCorrect()) {
                    a1.show();
                } else {
                    a2.show();
                }
            }
        };
        checkIfCorrectItem.addEventHandler(ActionEvent.ACTION, checkMenuHandler);

        MenuItem rulesItem = new MenuItem("Rules");
        EventHandler<ActionEvent> helpHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert a1 = new Alert(AlertType.NONE,
                        "The object of the puzzle is to fill the remaining squares, using all the numbers 1–9 " +
                                "exactly once in each row, column, and the nine 3 × 3 subgrids",ButtonType.OK);
                a1.show();
            }
        };
        rulesItem.addEventHandler(ActionEvent.ACTION, helpHandler);

        helpMenu.getItems().add(clearItem);
        helpMenu.getItems().add(checkIfCorrectItem);
        helpMenu.getItems().add(rulesItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    private void addEventHandlers(Controller controller){
        EventHandler<ActionEvent> numberButtonHandler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                int number = Integer.parseInt(((Button)actionEvent.getSource()).getText());
                controller.setGuess(number);
            }
        };
        oneButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        twoButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        threeButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        fourButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        fiveButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        sixButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        sevenButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        eightButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        nineButton.addEventHandler(ActionEvent.ACTION, numberButtonHandler);
        EventHandler<ActionEvent> cButtonHandler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.setGuess(0); // can only clear labels which user have entered
            }
        };
        cButton.addEventHandler(ActionEvent.ACTION, cButtonHandler);

        EventHandler<ActionEvent> checkButtonHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert a1 = new Alert(AlertType.NONE,
                        "Your inputs are so far correct",ButtonType.OK);
                Alert a2 = new Alert(AlertType.NONE,
                        "Your inputs are so far not correct",ButtonType.OK);
                if(sudokuManager.checkIfCorrect()) {
                    a1.show();
                } else {
                    a2.show();
                }
            }
        };
        checkButton.addEventHandler(ActionEvent.ACTION, checkButtonHandler);

        EventHandler<ActionEvent> hintButtonHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.hint();
            }
        };
        hintButton.addEventHandler(ActionEvent.ACTION, hintButtonHandler);
    }

    // Main.start needs a reference to the menu bar
    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    public GridView getGridView(){ return gridView; }


}
