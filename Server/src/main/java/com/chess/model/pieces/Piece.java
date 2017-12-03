package com.chess.model.pieces;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.util.Color;
import com.chess.util.Move;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
@Getter
@Setter
@ToString
public abstract class Piece {

    private Color color;
    private String id;
    protected Cell currentCell;
    protected Cell defaultCell;
    private boolean available; // not dead
    protected List<String> availableCellsToMove = new ArrayList<>();


    public Piece(Color color, String id, boolean available) {
        this.color = color;
        this.id = id;
        this.available = available;
    }

    /**
     * @return destination Cell

     */
    public abstract Cell move(Board board, Cell destinationCell); /**state is board state in current time; */
    public  List<String> getAvailableCellsToMove(Board board){
        return null;
    }; /**state is board state in current time; */


}
