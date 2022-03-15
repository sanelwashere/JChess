package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

import com.chess.engine.pieces.Piece;



public abstract class Tile {
    
    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        //Schachbrett hat 64 Felder
        for(int i = 0; i<BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        // Map = Container, nachdem die emptyTileMap erstellt wurde, soll sie unveränderlich sein
        return ImmutableMap.copyOf(emptyTileMap);
    }

    //einzige Methode zur Tile-Erstellung
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    private Tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }


    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    // Unterklasse leeres Feld
    public static final class EmptyTile extends Tile{

        private EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }

    // Unterklasse besetztes Feld
    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;
        
        private OccupiedTile(int tileCoordinate, final Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
