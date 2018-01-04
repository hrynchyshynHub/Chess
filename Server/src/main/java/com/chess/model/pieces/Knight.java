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
public class Knight extends Piece {
    private static Deque<Cell> defaultCellStack = new ArrayDeque<>();

    public Knight(Color color) {
        super(color);
        if(color == Color.WHITE){
            getDefaultCellStack().push(new Cell("1b"));
            getDefaultCellStack().push(new Cell("1g"));
        }else{
            getDefaultCellStack().push(new Cell("8b"));
            getDefaultCellStack().push(new Cell("8g"));
        }
    }

    public Deque<Cell> getDefaultCellStack() {
        return defaultCellStack;
    }

    public void setDefaultCellStack(Deque<Cell> defaultCellStack) {
        this.defaultCellStack = defaultCellStack;
    }

    @Override
    public Cell move(Board board, Cell destinationCell) {
        return null;
    }

    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
