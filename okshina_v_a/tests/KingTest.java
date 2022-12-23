package ru.vsu.cs.okshina_v_a.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.cs.okshina_v_a.figures.King;
import ru.vsu.cs.okshina_v_a.figures.Rook;
import ru.vsu.cs.okshina_v_a.game.Boards;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class KingTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.err;

    @Before
    public void setUpStreams() {
        System.setErr(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setErr(originalOut);
    }

    @Test
    public void movingRooks() {
        Pieces p = new Pieces(Boards.getCheckIngBoard());
        King kingW = (King) p.getPiece(new Coordinate('f',1));
        King kingB = (King) p.getPiece(new Coordinate('f',10));
        Rook rookWa = (Rook) p.getPiece(new Coordinate('b', 1));
        Rook rookWh = (Rook) p.getPiece(new Coordinate('i', 1));
        Rook rookBh = (Rook) p.getPiece(new Coordinate('i', 10));
        Rook rookB = (Rook) p.getPiece(new Coordinate('b', 10));
        p.makeMove(new Coordinate('a',1),rookB);
        assertFalse(kingW.canCastleQueen(p));
    }

    @Test
    public void testMoves() {
        Pieces p = new Pieces(Boards.getCheckIngBoard());

        King kingB = (King) p.getPiece(new Coordinate('f',10));
        HashSet<Coordinate> kingBMoves = new HashSet<>(Arrays.asList(new Coordinate('f',9), new Coordinate('g',10), new Coordinate('e',9)));
        assertEquals(kingBMoves, kingB.getPotentialMoves());
    }
}
