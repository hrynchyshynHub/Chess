package com.chess.model;

import com.chess.model.pieces.*;
import com.chess.util.Color;
import com.chess.util.Move;
import com.chess.util.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by ivan.hrynchyshyn on 15.11.2017.
 */
public class Board {
   private Cell[][] cells = new Cell[8][8];
   private Player whitePlayer;
   private Player blackPlayer;
   private boolean isWin;
   private Queue<Move> moves = new PriorityQueue<>();

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


      initializePieces(1,2,Color.WHITE);
      initializePieces(8,7,Color.BLACK);


   }

   private void initializePieces(int mainRow, int pawnIndex, Color color){
      Rock w_rock1 = new Rock(color, mainRow + "a", true);
      getCellById(mainRow + "a").setPiece(w_rock1);
      w_rock1.setCurrentCell(getCellById(mainRow + "a"));

      Rock w_rock2 = new Rock(color, mainRow + "h", true);
      getCellById(mainRow +"h").setPiece(w_rock2);
      w_rock2.setCurrentCell(getCellById(mainRow + "h"));

      Knight w_knight1 = new Knight(color, mainRow + "b", true);
      getCellById(mainRow +"b").setPiece(w_knight1);
      w_knight1.setCurrentCell(getCellById(mainRow +"b"));

      Knight w_knight2 = new Knight(color, mainRow + "g", true);
      getCellById(mainRow +"g").setPiece(w_knight2);
      w_knight2.setCurrentCell(getCellById(mainRow +"g"));

      Bishop w_bishop1 = new Bishop(color, mainRow + "c", true);
      getCellById(mainRow +"c").setPiece(w_bishop1);
      w_bishop1.setCurrentCell(getCellById(mainRow +"c"));

      Bishop w_bishop2 = new Bishop(color, mainRow + "f", true);
      getCellById(mainRow +"f").setPiece(w_bishop2);
      w_bishop2.setCurrentCell(getCellById(mainRow +"f"));

      King king = new King(color, mainRow + "e", true);
      getCellById(mainRow +"e").setPiece(king);
      king.setCurrentCell(getCellById(mainRow +"e"));

      Queen queen = new Queen(color, mainRow + "d", true);
      getCellById(mainRow +"d").setPiece(queen);
      queen.setCurrentCell(getCellById(mainRow +"d"));

      for(int i = 0; i < 8 ; i++){
         char ascii = (char)(i + 97);
         Pawn pawn = new Pawn(color, "" + pawnIndex + ascii, true );
         getCellById(""+pawnIndex + ascii).setPiece(pawn);
         pawn.setCurrentCell(getCellById(""+pawnIndex + ascii));
      }

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
   private Cell getCellById(String id){
      Cell foundCell = null;
      for(int i = 0; i < 8; i++){
         for(int j =0 ; j < 8; j++){
            if(cells[i][j].getId().equalsIgnoreCase(id)) foundCell = cells[i][j];
         }
      }
      return foundCell;
   }



   public static void main(String[] args) {
      Board board = new Board();
      board.initializeBoard();
      System.out.println("\n\n\n\n");
      board.showBoard();

      // get id from Client
      Piece selectedPiece = board.getCellById("2a").getPiece();
      if(selectedPiece.isAvailable()){
         List<Cell> posibleMoves = selectedPiece.getAvailavleCellsToMove();
         Cell selctedCell = board.getCellById("3a");
         if(selctedCell.getPiece() == null) {
            selctedCell.setPiece(selectedPiece);
         }
         else{
            selctedCell.getPiece().setAvailable(false);
            selctedCell.setPiece(selectedPiece);
         }
      }

   }
}
