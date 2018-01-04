package com.chess.saver.impl;

import com.chess.model.Board;
import com.chess.saver.GameSaver;
import com.google.inject.Singleton;

@Singleton
public class FileSaver implements GameSaver{

    @Override
    public boolean save(Board board) {
        System.out.println("File saver....");
        return false;
    }
}
