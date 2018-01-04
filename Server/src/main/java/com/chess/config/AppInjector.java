package com.chess.config;

import com.chess.saver.GameSaver;
import com.chess.saver.impl.FileSaver;
import com.google.inject.AbstractModule;

public class AppInjector  extends AbstractModule{

    @Override
    protected void configure() {
        bind(GameSaver.class).to(FileSaver.class);
    }
}
