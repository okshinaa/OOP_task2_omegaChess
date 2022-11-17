package ru.vsu.cs.okshina_v_a.figures;

import ru.vsu.cs.okshina_v_a.enums.BOARD;
import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.enums.ID;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Move;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import javax.swing.*;
import java.util.*;

public class King extends Piece {
    private Coordinate castleCoordKingK;
    private Coordinate castleCoordKingQ;
    private Coordinate transitionCoordKingK;
    private Coordinate transitionCoordKingQ;
    private Rook rookKing;
    private Rook rookQueen;
    private ImageIcon icon;

    public King(COLOUR colour, Coordinate OGcoord) {
        super(ID.KING, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BKing.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WKing.png");
    }

    public King(King original) {
        super(original);
    }
    public Coordinate getCastleCoordKingK() {
        return castleCoordKingK;
    }

    public Coordinate getCastleCoordKingQ() {
        return castleCoordKingQ;
    }

    public Coordinate getTransitionCoordKingK() {
        return transitionCoordKingK;
    }

    public Coordinate getTransitionCoordKingQ() {
        return transitionCoordKingQ;
    }

    public Rook getRookKing() {
        return rookKing;
    }

    public Rook getRookQueen() {
        return rookQueen;
    }

    public boolean canCastleKing (Pieces pieces) {

        if (pieces.isCheck(getColour()))
            return false;

        HashMap<Coordinate, Piece> colouredPieces = pieces.getColourPieces(getColour());

        for (Piece value : colouredPieces.values()) {
            if (value.getName() == ID.ROOK && value.getFile() == BOARD.LAST_FILE.getFileVal())
                rookKing = (Rook) value;
        }

        int distanceRookKing = 2;
        ArrayList<Coordinate> castleCoords;

        if (getColour() == COLOUR.B)
            castleCoords = Move.leftFree(pieces, this, dimension);
        else
            castleCoords = Move.rightFree(pieces, this, dimension);

        boolean isSpace = castleCoords.size() == distanceRookKing;

        boolean canCastle = rookKing != null &&
                !rookKing.getHasMoved() &&
                !getHasMoved() &&
                isSpace;

        if (canCastle) {
            castleCoordKingK = castleCoords.get(1);
            transitionCoordKingK = castleCoords.get(0);
            rookKing.setCastleCoordRook(castleCoords.get(0));
            return true;
        }
        return false;
    }

    public boolean canCastleQueen (Pieces pieces) {
        if (pieces.isCheck(getColour()))
            return false;

        HashMap<Coordinate,Piece> colouredPieces = pieces.getColourPieces(getColour());

        for (Piece value : colouredPieces.values()) {
            if (value.getName() == ID.ROOK && value.getFile() == BOARD.FIRST_FILE.getFileVal())
                rookQueen = (Rook) value;
        }

        int distanceRookQueen = 3;
        ArrayList<Coordinate> castleCoords;

        if (getColour() == COLOUR.W) {
            castleCoords = Move.leftFree(pieces, this, dimension);
        }
        else {
            castleCoords = Move.rightFree(pieces, this, dimension);
        }

        boolean isSpace = castleCoords.size() == distanceRookQueen;


        boolean canCastle = rookQueen != null &&
                !rookQueen.getHasMoved() &&
                !getHasMoved() &&
                isSpace;

        if (canCastle) {
            castleCoordKingQ = castleCoords.get(1);
            transitionCoordKingQ = castleCoords.get(0);
            rookQueen.setCastleCoordRook(castleCoords.get(0));
            return true;
        }
        return false;
    }

    @Override
    public King makeCopy() {
        return new King(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontFree(pieces,this,single);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,single);
        ArrayList<Coordinate> back = Move.backFree(pieces,this,single);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,single);
        ArrayList<Coordinate> frontRDig = Move.frontRDigFree(pieces, this,single);
        ArrayList<Coordinate> backRDig = Move.backRDigFree(pieces, this, single);
        ArrayList<Coordinate> backLDig = Move.backLDigFree(pieces, this,single);
        ArrayList<Coordinate> frontLDig = Move.frontLDigFree(pieces, this, single);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        front.addAll(frontRDig);

        if (canCastleKing(pieces))
            front.add(castleCoordKingK);
        if (canCastleQueen(pieces))
            front.add(castleCoordKingQ);


        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }
}