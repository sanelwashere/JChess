package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;

public class Pawn extends Piece{

    // "Richtungen", in die der Bauer sich bewegen kann
    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16};

    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

            final List<Move> legalMoves = new ArrayList<>();

            for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
                final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
                if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    continue;
                }
                if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    // TEMPORARY
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }else if(currentCandidateOffset == 16 && this.isFirstMove() &&
                (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())){
                    final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                    if( !board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                        // TEMPORARY
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));   
                        }
                }
            }
            return legalMoves;
    }
    
}
