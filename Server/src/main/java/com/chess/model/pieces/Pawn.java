package com.chess.model.pieces;

import com.chess.model.Board;
import com.chess.model.Cell;
import com.chess.util.Color;
import com.chess.util.Move;

import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
public class Pawn extends Piece {
    private boolean isFirstMove = true;

    public Pawn(Color color, String id, boolean available) {
        super(color, id, available);
    }

    @Override
    public Cell move(Board board, Cell destinationCell) {
        List<String> availableCellToMove = getAvailableCellsToMove(board);
        for (String id : availableCellToMove) {
            if (destinationCell.getId().equalsIgnoreCase(id)) {
                this.setCurrentCell(destinationCell);
                Piece piece = board.getCellById(id).getPiece();
                if (piece != null) {
                    System.out.println("old piece = " + piece);
                    piece.setAvailable(false);                       // old figure dead
                }
                board.getCellById(id).setPiece(this);
                isFirstMove = false;
                return destinationCell;
            }
        }
        return currentCell;
    }


    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();
        if (getColor() == Color.WHITE) {
            xPos++;
            availableCellsToMove.add(new String(xPos + "" + yPos));
            if (board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos + 1)));
            }
            if (board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos - 1)));
            }
            if (isFirstMove) {
                availableCellsToMove.add(new String(++xPos + "" + yPos));
            }
        } else {
            xPos--;
            availableCellsToMove.add(new String(xPos + "" + yPos));
            if (board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos + 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos + 1)));
            }
            if (board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece() != null && board.getCellById(new String(xPos + "" + (char) (yPos - 1))).getPiece().getColor() != getColor()) {
                availableCellsToMove.add(new String(xPos + "" + (char) (yPos - 1)));
            }

            if (isFirstMove) {
                availableCellsToMove.add(new String(--xPos + "" + yPos));
            }
        }
        return availableCellsToMove;
    }

    @Override
    public String toString() {
        return "Pawn " + getColor();
    }
}
