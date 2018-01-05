package com.chess.util;

import java.io.Serializable;

public class DataTransferObject implements Serializable{
    private Move move;
    private int playerId;
    private Status status;

    public DataTransferObject() {
    }

    public DataTransferObject(Move move, int playerId) {
        this.move = move;
        this.playerId = playerId;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
