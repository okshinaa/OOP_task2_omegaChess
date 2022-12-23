package ru.vsu.cs.okshina_v_a.tests;

import org.junit.Test;
import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.enums.ID;
import ru.vsu.cs.okshina_v_a.figures.Pawn;
import ru.vsu.cs.okshina_v_a.game.Boards;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PawnTest {

    Pieces p = new Pieces(Boards.getPromotingBoard());

    @Test
    public void noPromotion() {
        Pawn pawn = (Pawn) p.getPiece(new Coordinate('h',2));
        p.makeMove(new Coordinate('h',4),pawn);
        assertEquals(ID.PAWN, pawn.getName());
        assertEquals("[h5]", pawn.getPotentialMoves().toString());
    }

    @Test
    public void promoteAndCheck() {
        Pawn pawn = (Pawn) p.getPiece(new Coordinate('g',9));
        ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
        System.setIn(in);
        p.makeMove(new Coordinate('g',10),pawn);
        assertTrue(p.isCheck(COLOUR.B));
        System.setIn(System.in);
    }

    @Test
    public void promoteNoCheck() {
        Pawn pawn = (Pawn) p.getPiece(new Coordinate('g',9));
        ByteArrayInputStream in = new ByteArrayInputStream("N".getBytes());
        System.setIn(in);
        p.makeMove(new Coordinate('g',10),pawn);
        assertFalse(p.isCheck(COLOUR.B));
        System.setIn(System.in);
    }

    @Test
    public void enPassantSituations() {
        Pieces p = new Pieces(Boards.getCheckIngBoard());
        Pawn pawnWh = (Pawn) p.getPiece(new Coordinate('h',2));
        Pawn pawnWe = (Pawn) p.getPiece(new Coordinate('e',2));
        Pawn pawnB = (Pawn) p.getPiece(new Coordinate('g',9));
        p.makeMove(new Coordinate('h',3),pawnWh);
        assertFalse(pawnWh.getHasMovedTwo());
        assertTrue(pawnWh.getHasMoved());
        assertEquals(new Coordinate('h',2),pawnWh.getPreviousCoordinate());
        p.makeMove(new Coordinate('e',4), pawnWe);
        assertTrue(pawnWe.getHasMovedTwo());
        assertTrue(pawnWe.getHasMoved());
        assertEquals(new Coordinate('e',2),pawnWe.getPreviousCoordinate());
        p.makeMove(new Coordinate('e',5), pawnWe);
        assertEquals(new Coordinate('e',4),pawnWe.getPreviousCoordinate());
        assertFalse(pawnB.getHasMovedTwo());
        assertFalse(pawnB.getHasMoved());
        assertEquals(new Coordinate('g',9),pawnB.getPreviousCoordinate());
    }
}