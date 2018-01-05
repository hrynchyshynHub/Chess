package com.chess.config;

import com.chess.model.Board;
import com.chess.model.GameBoard;
import com.chess.saver.GameSaver;
import com.chess.saver.impl.FileSaver;
import com.google.inject.AbstractModule;

public class AppInjector  extends AbstractModule{

    @Override
    protected void configure() {
        bind(GameSaver.class).to(FileSaver.class);
        bind(GameBoard.class).to(Board.class);
    }
}
