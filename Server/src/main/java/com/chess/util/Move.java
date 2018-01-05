package com.chess.util;

import com.chess.model.Cell;

import java.io.Serializable;


/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

public class Move implements Serializable{
    private Cell source;
    private Cell destination;

    public Move() {
    }

    public Move(Cell source, Cell destination) {
        this.source = source;
        this.destination = destination;
    }

    public Cell getSource() {
        return source;
    }

    public void setSource(Cell source) {
        this.source = source;
    }

    public Cell getDestination() {
        return destination;
    }

    public void setDestination(Cell destination) {
        this.destination = destination;
    }
}
