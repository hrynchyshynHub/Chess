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
                return destinationCell;
            }
        }
        return currentCell;
    }

    @Override
    public List<String> getAvailableCellsToMove(Board board) {
        availableCellsToMove.clear();
        setCurrentCell(new Cell("5d"));
        Cell currentCell = getCurrentCell();
        int xPos = currentCell.getX();
        char yPos = currentCell.getY();

        final int gMoveStepHight = 2;
        final int gMoveStepLow = 1;

        String cell1 = new String(""+ (xPos + gMoveStepHight) + "" + (char)(yPos + gMoveStepLow));
        String cell2 = new String(""+ (xPos + gMoveStepHight) + "" + (char)(yPos - gMoveStepLow));
        String cell3 = new String(""+ (xPos + gMoveStepLow) + "" + (char)(yPos + gMoveStepHight));
        String cell4 = new String(""+ (xPos + gMoveStepLow) + "" + (char)(yPos - gMoveStepHight));
        String cell5 = new String(""+ (xPos - gMoveStepHight) + "" + (char)(yPos + gMoveStepLow));
        String cell6 = new String(""+ (xPos - gMoveStepHight) + "" + (char)(yPos - gMoveStepLow));
        String cell7 = new String(""+ (xPos - gMoveStepLow) + "" + (char)(yPos + gMoveStepHight));
        String cell8 = new String(""+ (xPos - gMoveStepLow) + "" + (char)(yPos - gMoveStepHight));

        availableCellsToMove.add(cell1);
        availableCellsToMove.add(cell2);
        availableCellsToMove.add(cell3);
        availableCellsToMove.add(cell4);
        availableCellsToMove.add(cell5);
        availableCellsToMove.add(cell6);
        availableCellsToMove.add(cell7);
        availableCellsToMove.add(cell8);
        return availableCellsToMove;
    }

    @Override
    public String toString() {
        return "Knight";
    }

    public static void main(String[] args) {
        Board b =new Board();
        Knight k = new Knight(Color.WHITE);
        k.getAvailableCellsToMove(b);
    }
}
