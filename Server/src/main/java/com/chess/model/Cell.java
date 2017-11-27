package com.chess.model;

import com.chess.model.pieces.Piece;
import com.chess.util.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
@AllArgsConstructor
@Getter
@Setter
public class Cell {
    private boolean isPosibleDestination;    /**available for next move*/
    private Piece piece;
    private int x;
    private char y;
    private boolean isSelected = false;
    private boolean isCheck = false;
    private Color color;
    private String id;

    public Cell(int x, char y, Color color) {
        this.x = ++x;
        this.y = y;
        this.color = color;
        this.id = x + "" + y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "piece=" + piece +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }

    public String getId(){
        return id;
    }

}
