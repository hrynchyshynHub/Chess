package com.chess.util;

import java.io.Serializable;

public class DataTransferObject implements Serializable{
    private Move move;
    private Player player;
    private int status;

    public DataTransferObject() {
    }

    public DataTransferObject(Move move, Player player) {
        this.move = move;
        this.player = player;
    }
    public DataTransferObject(Move move, Player player, int status) {
        this.move = move;
        this.player = player;
        this.status = status;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataTransferObject{" +
                "move=" + move +
                ", player=" + player +
                ", status=" + status +
                '}';
    }
}
