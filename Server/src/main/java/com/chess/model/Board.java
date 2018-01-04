package com.chess.model;

import com.chess.model.pieces.*;
import com.chess.saver.GameSaver;
import com.chess.util.Color;
import com.chess.util.Move;
import com.chess.util.Player;

import java.util.*;

/**
 * Created by ivan.hrynchyshyn on 15.11.2017.
 */

public class Board{
   private Cell[][] cells = new Cell[8][8];
   private Player whitePlayer;
   private Player blackPlayer;
   private boolean isWin;
   private Queue<Move> moves = new PriorityQueue<>();
   private GameSaver saver;
   Map<Piece, Cell> piecesOnBoard = new HashMap<>();

   private void initializeBoard() {
      boolean isWhite = true;
      Color color = Color.WHITE;
      for (int i = 7; i >= 0; i--) {
         for (int j = 0; j < 8; j++) {
            isWhite = !isWhite;
            if( j == 7){
               isWhite = !isWhite;
            }
            if(isWhite){
                  color = Color.BLACK;
               }else{
                  color = Color.WHITE;
               }
               cells[i][j] = new Cell(i, (char) (j + 97), color);
               System.out.print('[' + cells[i][j].getId() + ' ' + cells[i][j].getColor() + ']');
         }
         System.out.println();
      }

   }

   public void initializatePieces(Color color){
      Rock rock1 = new Rock(color);
      Rock rock2 = new Rock(color);
      Knight knight1 = new Knight(color);
      Knight knight2 = new Knight(color);
      Bishop bishop1 = new Bishop(color);
      Bishop bishop2 = new Bishop(color);
      King king = new King(color);
      Queen queen = new Queen(color);
      List<Piece> pieces = new ArrayList<>();
      pieces.add(rock1);
      pieces.add(rock2);
      pieces.add(knight1);
      pieces.add(knight2);
      pieces.add(bishop1);
      pieces.add(bishop2);
      pieces.add(king);
      pieces.add(queen);
      List<Piece> pawns = new ArrayList<>();
      for(int i = 0; i < 8; i++){
         pawns.add(new Pawn(color));
      }
      pieces.addAll(pawns);
      for(int chessCount = 0; chessCount < 16 ; chessCount++ ){
            initializatePiece(pieces.get(chessCount));
      }
   }

   public void initializatePiece(Piece piece){
      Cell cell = getCellById(piece.getDefaultCellStack().pop().getId());
      cell.setPiece(piece);
      piece.setCurrentCell(cell);
      piecesOnBoard.put(piece, cell);
   }

   public  void showBoard(){
      for(int i =0; i < 8 ; i++){
         for(int j =0; j< 8 ; j++){
            if(cells[i][j].getPiece() != null){
               System.out.print("[" + cells[i][j].getId() + " " + cells[i][j].getColor() + " = " + cells[i][j].getPiece().toString() +  "]");
            }
         }
         System.out.println();
      }
   }

   public Cell getCellById(String id){
      Cell foundCell = null;
      for(int i = 0; i < 8; i++){
         for(int j =0 ; j < 8; j++){
            if(cells[i][j].getId().equalsIgnoreCase(id)) foundCell = cells[i][j];
         }
      }
      return foundCell;
   }

   public List<Piece> getAvailablePieces(Color color){
      List<Piece> availablePieces = new ArrayList<>();
      for(int i = 0; i < 8; i++){
         for(int j =0 ; j < 8; j++){
            if(cells[i][j].getPiece() != null && cells[i][j].getPiece().isAvailable() && cells[i][j].getPiece().getColor() == color){
               availablePieces.add(cells[i][j].getPiece());
            }
         }
      }
      return availablePieces;
   }

   public static void main(String[] args) {
      Board board = new Board();
      board.initializeBoard();
      board.initializatePieces(Color.WHITE);
      board.initializatePieces(Color.BLACK);
      board.showBoard();
//       get id from Client
      Piece selectedPiece = board.getCellById("2b").getPiece();
      System.out.println(board.getAvailablePieces(Color.BLACK));
      System.out.println("Available cell to move \n" + selectedPiece.getAvailableCellsToMove(board));
      System.out.println("Move from 2b to 3b \n" + selectedPiece.move(board, board.getCellById("3b")));
      System.out.println("Available cell to move \n" + selectedPiece.getAvailableCellsToMove(board));
      System.out.println("Move from 3b to 4b \n" + selectedPiece.move(board, board.getCellById("4b")));
      System.out.println("Available cell to move \n" + selectedPiece.getAvailableCellsToMove(board));
      System.out.println("Move from 4b to 5b \n" + selectedPiece.move(board,  board.getCellById("5b")));
      System.out.println("Available cell to move \n" + selectedPiece.getAvailableCellsToMove(board));
      System.out.println("Move from 5b to 6b \n" + selectedPiece.move(board,  board.getCellById("6b")));
      System.out.println("Available cell to move \n" + selectedPiece.getAvailableCellsToMove(board));
      System.out.println("Move from 6b to 7b \n" + selectedPiece.move(board,  board.getCellById("7b")));
      System.out.println(board.getAvailablePieces(Color.BLACK));


   }
}
