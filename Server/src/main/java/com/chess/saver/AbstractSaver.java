package com.chess.saver;

import com.chess.model.Board;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public abstract class AbstractSaver {
    public abstract boolean save(Board board);
}
