package com.chess.model.pieces;

import com.chess.model.Cell;
import com.chess.util.Color;
import com.chess.util.Move;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
@Getter @Setter
public abstract class Piece {

    private Color color;
    private String id;
    private Cell currentCell;
    private boolean available; // not dead
    protected List<Cell> availavleCellsToMove = new ArrayList<>();


    public Piece(Color color, String id, boolean available) {
        this.color = color;
        this.id = id;
        this.available = available;
    }

    /**
     * @return destination Cell

     */
    public abstract Cell move(Cell state[][], Move move); /**state is board state in current time; */


}
