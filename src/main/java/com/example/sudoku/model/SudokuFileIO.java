package com.example.sudoku.model;

import java.io.*;


/**
 * This class represents file interaction for the Sudoku board
 * It is used to read to file and to write to file
 * It makes it possible to save Sudoku board to file and to load a Sudoku board from file
 */
public class SudokuFileIO {
    /**
     * Writes data from a Sudoku board to file
     *
     * @param file The file to be written to
     * @param data Object with board data
     */
    public static void serializeToFile(File file, SudokuManager data) throws IOException {
        ObjectOutputStream oos = null;

        try{
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(data);
        }  finally {
            if(oos != null){
                oos.close();
            }
        }
    }


    /**
     * Reads data from a Sudoku board to file
     *
     * @param file The file to read from
     */
    public static SudokuManager deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        SudokuManager sudokuManager;
        ObjectInputStream ois = null;

        try {
            FileInputStream fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);
            sudokuManager = (SudokuManager) ois.readObject();
        } finally {
            if(ois != null){
                ois.close();

            }
        }
        return sudokuManager;
    }

    private SudokuFileIO() {}
}
