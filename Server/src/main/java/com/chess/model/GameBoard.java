package com.chess.model;

import com.chess.model.pieces.Piece;
import com.chess.util.Color;
import com.chess.util.Move;

import java.util.List;

public interface GameBoard {
     void initializeBoard();
     void initializatePieces(Color color);
     void showBoard();
     Cell getCellById(String id);
     List<Piece> getAvailablePieces(Color color);
     boolean move(Move move);
}
