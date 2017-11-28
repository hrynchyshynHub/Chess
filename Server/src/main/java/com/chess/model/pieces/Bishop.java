package com.chess.model.pieces;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.util.Color;
import com.chess.util.Move;

import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public class Bishop extends Piece{

    public Bishop(Color color, String id, boolean available) {
        super(color, id, available);
    }

    @Override
    public Cell move(Board board, Cell destinationCell) {
        return null;
    }

    @Override
    public String toString(){
        return "Bishop";
    }
}
