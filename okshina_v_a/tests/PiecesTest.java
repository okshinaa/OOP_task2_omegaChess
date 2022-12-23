package ru.vsu.cs.okshina_v_a.tests;

import org.junit.Test;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import java.util.*;

import static org.junit.Assert.*;


public class PiecesTest {

    Pieces p = new Pieces();
    HashMap<Coordinate, Piece> board = p.getPieces();

    @Test
    public void testCorrectPieces() {

        Coordinate whiteQueen = new Coordinate('E',1);
        Coordinate blackQueenKnight = new Coordinate('B',10);
        Coordinate whiteKingRook = new Coordinate('H',1);
        Coordinate blackPawnMiddle = new Coordinate('E',9);

        assertEquals(board.get(whiteQueen).getPieceID(), "*Q*White*e*");
        assertEquals(board.get(blackQueenKnight).getPieceID(), "*R*Black*b*");
        assertEquals(board.get(whiteKingRook).getPieceID(), "*N*White*h*");
        assertEquals(board.get(blackPawnMiddle).getPieceID(), "**Black*e*");
    }

    @Test
    public void notInBoard() {
        assertNull(board.get(new Coordinate('F', 5)));
        assertNull(board.get(new Coordinate('K', 9)));
    }
}