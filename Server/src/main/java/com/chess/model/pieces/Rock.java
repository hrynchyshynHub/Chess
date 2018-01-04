package com.chess.model.pieces;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.util.Color;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public class Rock extends Piece {
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Rock(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1a"));
            getDefaultCellStack().push(new Cell("1h"));
        }else{
            getDefaultCellStack().push(new Cell("8h"));
            getDefaultCellStack().push(new Cell("8a"));
        }
    }

    @Override
    public Cell move(Board board, Cell destinationCell) {
        return null;
    }

    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        return null;
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }


    @Override
    public String toString() {
        return "Rock";
    }
}
