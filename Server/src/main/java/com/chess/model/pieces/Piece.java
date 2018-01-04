package com.chess.model.pieces;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.util.Color;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

public abstract class Piece {

    private Color color;
    protected Cell currentCell;
    private boolean available; // not dead
    protected List<String> availableCellsToMove = new ArrayList<>();

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<String> getAvailableCellsToMove() {
        return availableCellsToMove;
    }
    public void setAvailableCellsToMove(List<String> availableCellsToMove) {
        this.availableCellsToMove = availableCellsToMove;
    }
    /**
     * @return destination Cell

     */
    public abstract List<String> getAvailableCellsToMove(Board board); /**state is board state in current time; */


    public abstract Cell move(Board board, Cell destinationCell); /**state is board state in current time; */

    public abstract Deque<Cell> getDefaultCellStack();

}
