package com.example.sudoku.view;

import com.example.sudoku.model.BoxData;
import com.example.sudoku.model.SudokuManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static com.example.sudoku.model.SudokuUtilities.*;

public class GridView extends TilePane {

    private final Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private SudokuManager sudokuManager;
    private final Controller controller;

    public GridView(SudokuManager sudokuManager, Controller controller) {
        super();
        this.sudokuManager = sudokuManager;
        this.controller = controller;
        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        initNumberTiles();

        TilePane numberPane = makeNumberPane();

        this.getChildren().add(numberPane);
        this.setMaxWidth(50);

    }

    // called by constructor (only)
    private void initNumberTiles() {
        BoxData[][] boxData = sudokuManager.getBoardArray();

        Font fontStart = Font.font("Monospaced", FontWeight.BOLD, 20);
        Font fontGuess = Font.font("Monospaced", FontWeight.NORMAL, 20);

        String number = "";

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if(boxData[row][col].getStartValue() == 0){
                    number = "";
                } else {
                    number = String.valueOf(boxData[row][col].getStartValue()); // data from model
                }
                Label tile = new Label(number); // data from model
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                if(number.equals("")){
                    tile.setFont(fontGuess);
                } else {
                    tile.setFont(fontStart);
                }
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }

    private TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SECTIONS_PER_ROW);
        root.setPrefRows(SECTIONS_PER_ROW);
        root.setStyle(
                "-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SECTIONS_PER_ROW][SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SECTION_SIZE);
                section.setPrefRows(SECTION_SIZE);
                section.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < SECTION_SIZE; row++) {
                    for (int col = 0; col < SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SECTION_SIZE + row][scol * SECTION_SIZE + col]);
                    }
                }

                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }
        return root;
    }

    public void updateTileFont(){
        BoxData[][] boxData = sudokuManager.getBoardArray();
        Font fontStart = Font.font("Monospaced", FontWeight.BOLD, 20);
        Font fontGuess = Font.font("Monospaced", FontWeight.NORMAL, 20);
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if(boxData[row][col].getShowAtStart() == 0){
                    numberTiles[row][col].setFont(fontGuess);
                } else {
                    numberTiles[row][col].setFont(fontStart);
                }

            }
        }
    }

    public void updateGridView(){
        BoxData[][] boxData = sudokuManager.getBoardArray();
        String number = "";
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if(boxData[row][col].getValueToShow() == 0){
                    number = "";
                } else {
                    number = String.valueOf(boxData[row][col].getValueToShow());
                }
                numberTiles[row][col].setText(number);

            }
        }
    }

    private final EventHandler<MouseEvent> tileClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    if (event.getSource() == numberTiles[row][col]) {
                        controller.makeGuess(row, col);
                        // we got the row and column - now call the appropriate controller method
                        return;
                    }
                }
            }
        }
    };

    public void setSudokuManager(SudokuManager sudokuManager){ this.sudokuManager = sudokuManager; }

}
