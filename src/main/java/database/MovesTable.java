package database;

import java.util.Vector;

import model.Move;

public enum MovesTable {
    INSTANCE;
    
    public final Vector<Move> Moves = new Vector<>();
}