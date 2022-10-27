package com.example.sudoku.model;

import java.io.*;

public class SudokuFileIO {
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
}
