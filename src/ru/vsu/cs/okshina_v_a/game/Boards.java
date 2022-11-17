package ru.vsu.cs.okshina_v_a.game;

import ru.vsu.cs.okshina_v_a.enums.BOARD;
import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.figures.*;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;
import java.util.HashMap;

public class Boards {
    public static HashMap<Coordinate, Piece> getChessBoard() {
        HashMap <Coordinate, Piece> pieces = new HashMap<>();

        int pawnBRank = 9;
        int bRank = 10;
        int pawnWRank = 2;
        int wRank = 1;

        Pawn pawnBa = new Pawn(COLOUR.B, new Coordinate('a', pawnBRank));
        Pawn pawnBb = new Pawn(COLOUR.B, new Coordinate('b', pawnBRank));
        Pawn pawnBc = new Pawn(COLOUR.B, new Coordinate('c', pawnBRank));
        Pawn pawnBd = new Pawn(COLOUR.B, new Coordinate('d', pawnBRank));
        Pawn pawnBe = new Pawn(COLOUR.B, new Coordinate('e', pawnBRank));
        Pawn pawnBf = new Pawn(COLOUR.B, new Coordinate('f', pawnBRank));
        Pawn pawnBg = new Pawn(COLOUR.B, new Coordinate('g', pawnBRank));
        Pawn pawnBh = new Pawn(COLOUR.B, new Coordinate('h', pawnBRank));
        Pawn pawnBi = new Pawn(COLOUR.B, new Coordinate('i', pawnBRank));
        Pawn pawnBj = new Pawn(COLOUR.B, new Coordinate('j', pawnBRank));

        Champion championBa = new Champion(COLOUR.B, new Coordinate('a', bRank));
        Champion championBj = new Champion(COLOUR.B, new Coordinate('j', bRank));

        Rook rookBb = new Rook(COLOUR.B, new Coordinate('b', bRank));
        Rook rookBi = new Rook(COLOUR.B, new Coordinate('i', bRank));

        Knight knightBc = new Knight(COLOUR.B, new Coordinate('c', bRank));
        Knight knightBh = new Knight(COLOUR.B, new Coordinate('h', bRank));

        Bishop bishopBd = new Bishop(COLOUR.B, new Coordinate('d', bRank));
        Bishop bishopBg = new Bishop(COLOUR.B, new Coordinate('g', bRank));

        Queen queenB = new Queen(COLOUR.B, new Coordinate('e', bRank));

        King kingB = new King(COLOUR.B, new Coordinate('f', bRank));

        Pawn pawnWa = new Pawn(COLOUR.W, new Coordinate('a', pawnWRank));
        Pawn pawnWb = new Pawn(COLOUR.W, new Coordinate('b', pawnWRank));
        Pawn pawnWc = new Pawn(COLOUR.W, new Coordinate('c', pawnWRank));
        Pawn pawnWd = new Pawn(COLOUR.W, new Coordinate('d', pawnWRank));
        Pawn pawnWe = new Pawn(COLOUR.W, new Coordinate('e', pawnWRank));
        Pawn pawnWf = new Pawn(COLOUR.W, new Coordinate('f', pawnWRank));
        Pawn pawnWg = new Pawn(COLOUR.W, new Coordinate('g', pawnWRank));
        Pawn pawnWh = new Pawn(COLOUR.W, new Coordinate('h', pawnWRank));
        Pawn pawnWi = new Pawn(COLOUR.W, new Coordinate('i', pawnWRank));
        Pawn pawnWj = new Pawn(COLOUR.W, new Coordinate('j', pawnWRank));

        Champion championWa = new Champion(COLOUR.W, new Coordinate('a', wRank));
        Champion championWj = new Champion(COLOUR.W, new Coordinate('j', wRank));

        Rook rookWb = new Rook(COLOUR.W, new Coordinate('b', wRank));
        Rook rookWi = new Rook(COLOUR.W, new Coordinate('i', wRank));

        Knight knightWc = new Knight(COLOUR.W, new Coordinate('c', wRank));
        Knight knightWh = new Knight(COLOUR.W, new Coordinate('h', wRank));

        Bishop bishopWd = new Bishop(COLOUR.W, new Coordinate('d', wRank));
        Bishop bishopWg = new Bishop(COLOUR.W, new Coordinate('g', wRank));

        Queen queenW = new Queen(COLOUR.W, new Coordinate('e', wRank));

        King kingW = new King(COLOUR.W, new Coordinate('f', wRank));

        pieces.put(pawnBa.getCoords(), pawnBa);
        pieces.put(pawnBb.getCoords(), pawnBb);
        pieces.put(pawnBc.getCoords(), pawnBc);
        pieces.put(pawnBd.getCoords(), pawnBd);
        pieces.put(pawnBe.getCoords(), pawnBe);
        pieces.put(pawnBf.getCoords(), pawnBf);
        pieces.put(pawnBg.getCoords(), pawnBg);
        pieces.put(pawnBh.getCoords(), pawnBh);
        pieces.put(pawnBi.getCoords(), pawnBi);
        pieces.put(pawnBj.getCoords(), pawnBj);

        pieces.put(championBa.getCoords(), championBa);
        pieces.put(championBj.getCoords(), championBj);

        pieces.put(rookBb.getCoords(), rookBb);
        pieces.put(rookBi.getCoords(), rookBi);

        pieces.put(knightBc.getCoords(), knightBc);
        pieces.put(knightBh.getCoords(), knightBh);

        pieces.put(bishopBd.getCoords(), bishopBd);
        pieces.put(bishopBg.getCoords(), bishopBg);

        pieces.put(queenB.getCoords(), queenB);

        pieces.put(kingB.getCoords(), kingB);

        pieces.put(pawnWa.getCoords(), pawnWa);
        pieces.put(pawnWb.getCoords(), pawnWb);
        pieces.put(pawnWc.getCoords(), pawnWc);
        pieces.put(pawnWd.getCoords(), pawnWd);
        pieces.put(pawnWe.getCoords(), pawnWe);
        pieces.put(pawnWf.getCoords(), pawnWf);
        pieces.put(pawnWg.getCoords(), pawnWg);
        pieces.put(pawnWh.getCoords(), pawnWh);
        pieces.put(pawnWi.getCoords(), pawnWi);
        pieces.put(pawnWj.getCoords(), pawnWj);

        pieces.put(championWa.getCoords(), championWa);
        pieces.put(championWj.getCoords(), championWj);

        pieces.put(rookWb.getCoords(), rookWb);
        pieces.put(rookWi.getCoords(), rookWi);

        pieces.put(knightWc.getCoords(), knightWc);
        pieces.put(knightWh.getCoords(), knightWh);

        pieces.put(bishopWd.getCoords(), bishopWd);
        pieces.put(bishopWg.getCoords(), bishopWg);

        pieces.put(queenW.getCoords(), queenW);

        pieces.put(kingW.getCoords(), kingW);

        return pieces;
    }

    public static HashMap<Coordinate, Piece> getTestBoard() {
        HashMap <Coordinate, Piece> pieces = new HashMap<>();

        int pawnBRank = 9;
        int bRank = 10;
        int pawnWRank = 2;
        int wRank = 1;

        Pawn pawnB = new Pawn(COLOUR.B, new Coordinate('g', pawnBRank));

        Rook rookB = new Rook(COLOUR.B, new Coordinate('b', bRank));

        Knight knightB = new Knight(COLOUR.B, new Coordinate('h', 10));

        Queen queenB = new Queen(COLOUR.B, new Coordinate('e', bRank));

        Pawn pawnWe = new Pawn(COLOUR.W, new Coordinate('e', pawnWRank));
        Pawn pawnWh = new Pawn(COLOUR.W, new Coordinate('h', pawnWRank));

        Rook rookWa = new Rook(COLOUR.W, new Coordinate('b', wRank));
        Rook rookWh = new Rook(COLOUR.W, new Coordinate('i', wRank));

        Bishop bishopW = new Bishop(COLOUR.W, new Coordinate('d', 4));

        Queen queenW = new Queen(COLOUR.W, new Coordinate('g', 3));

        King kingW = new King(COLOUR.W, new Coordinate('f', wRank));

        pieces.put(pawnB.getCoords(), pawnB);

        pieces.put(rookB.getCoords(), rookB);

        pieces.put(knightB.getCoords(), knightB);

        pieces.put(queenB.getCoords(), queenB);

        pieces.put(pawnWe.getCoords(), pawnWe);
        pieces.put(pawnWh.getCoords(), pawnWh);

        pieces.put(rookWa.getCoords(), rookWa);
        pieces.put(rookWh.getCoords(), rookWh);

        pieces.put(bishopW.getCoords(), bishopW);

        pieces.put(queenW.getCoords(), queenW);

        pieces.put(kingW.getCoords(), kingW);

        return pieces;
    }

    public static HashMap<Coordinate, Piece> getCheckIngBoard() {
        HashMap <Coordinate, Piece> pieces = new HashMap<>();

        int pawnBRank = 9;
        int bRank = 10;
        int pawnWRank = 2;
        int wRank = 1;

        Pawn pawnB = new Pawn(COLOUR.B, new Coordinate('g', pawnBRank));

        Rook rookBh = new Rook(COLOUR.B, new Coordinate('b', bRank));
        Rook rookB = new Rook(COLOUR.B, new Coordinate('i', bRank));

        Knight knightB = new Knight(COLOUR.B, new Coordinate('h', 10));

        Queen queenB = new Queen(COLOUR.B, new Coordinate('e', bRank));

        King kingB = new King(COLOUR.B, new Coordinate('f', bRank));

        Pawn pawnWe = new Pawn(COLOUR.W, new Coordinate('e', pawnWRank));
        Pawn pawnWh = new Pawn(COLOUR.W, new Coordinate('h', pawnWRank));

        Rook rookWa = new Rook(COLOUR.W, new Coordinate('b', wRank));
        Rook rookWh = new Rook(COLOUR.W, new Coordinate('i', wRank));

        Bishop bishopW = new Bishop(COLOUR.W, new Coordinate('f', 3));

        Queen queenW = new Queen(COLOUR.W, new Coordinate('g', 3));

        King kingW = new King(COLOUR.W, new Coordinate('f', wRank));

        pieces.put(pawnB.getCoords(), pawnB);

        pieces.put(rookB.getCoords(), rookB);
        pieces.put(rookBh.getCoords(),rookBh);

        pieces.put(knightB.getCoords(), knightB);

        pieces.put(queenB.getCoords(), queenB);

        pieces.put(kingB.getCoords(),kingB);

        pieces.put(pawnWe.getCoords(), pawnWe);
        pieces.put(pawnWh.getCoords(), pawnWh);

        pieces.put(rookWa.getCoords(), rookWa);
        pieces.put(rookWh.getCoords(), rookWh);

        pieces.put(bishopW.getCoords(), bishopW);

        pieces.put(queenW.getCoords(), queenW);

        pieces.put(kingW.getCoords(), kingW);

        return pieces;
    }

    public static HashMap<Coordinate, Piece> getPromotingBoard() {
        HashMap <Coordinate, Piece> pieces = new HashMap<>();

        int pawnBRank = 9;
        int bRank = 10;
        int pawnWRank = 2;
        int wRank = 1;

        Pawn pawnW = new Pawn(COLOUR.W, new Coordinate('g', pawnBRank));
        Pawn pawnB = new Pawn(COLOUR.B, new Coordinate('b',pawnWRank));

        Rook rookBh = new Rook(COLOUR.B, new Coordinate('i', bRank));
        Rook rookB = new Rook(COLOUR.B, new Coordinate('b', bRank));

        Knight knightB = new Knight(COLOUR.B, new Coordinate('h', 10));

        Queen queenB = new Queen(COLOUR.B, new Coordinate('e', bRank));

        King kingB = new King(COLOUR.B, new Coordinate('f', bRank));

        Pawn pawnWe = new Pawn(COLOUR.W, new Coordinate('e', pawnWRank));
        Pawn pawnWh = new Pawn(COLOUR.W, new Coordinate('h', pawnWRank));

        Rook rookWa = new Rook(COLOUR.W, new Coordinate('b', wRank));
        Rook rookWh = new Rook(COLOUR.W, new Coordinate('i', wRank));

        Bishop bishopW = new Bishop(COLOUR.W, new Coordinate('f', 3));

        Queen queenW = new Queen(COLOUR.W, new Coordinate('g', 3));

        King kingW = new King(COLOUR.W, new Coordinate('f', wRank));

        pieces.put(pawnW.getCoords(), pawnW);
        pieces.put(pawnB.getCoords(), pawnB);

        pieces.put(rookB.getCoords(), rookB);
        pieces.put(rookBh.getCoords(),rookBh);

        pieces.put(knightB.getCoords(), knightB);

        pieces.put(queenB.getCoords(), queenB);

        pieces.put(kingB.getCoords(),kingB);

        pieces.put(pawnWe.getCoords(), pawnWe);
        pieces.put(pawnWh.getCoords(), pawnWh);

        pieces.put(rookWa.getCoords(), rookWa);
        pieces.put(rookWh.getCoords(), rookWh);

        pieces.put(bishopW.getCoords(), bishopW);

        pieces.put(queenW.getCoords(), queenW);

        pieces.put(kingW.getCoords(), kingW);

        return pieces;
    }

    public static String fancySeparator() {
        String unit = "|====";

        String str = "  " +
                unit.repeat(10) +
                "|";
        return str;
    }

    public static String spacer (int n,String type) {
        if (type.equals("L"))
            return n + " ";
        else
            return " " + n;
    }

    public static String fancyColumnIndex() {
        StringBuilder str = new StringBuilder();
        str.append("   ");
        for (char file = 'a'; file <= 'j'; file++) {
            str.append(" ").append(file).append("   ");
        }

        return str.toString();
    }

    public static String displayBoard(Pieces pieces) {
        int dimRank = BOARD.FIRST_RANK.getRankVal();
        char dimFile = BOARD.FIRST_FILE.getFileVal();
        char lastFile = BOARD.LAST_FILE.getFileVal();

        StringBuilder str = new StringBuilder();

        str.append(fancyColumnIndex()).append("\n");
        str.append(fancySeparator()).append("\n");
        for (int rank = 10; rank >= dimRank; rank--) {
            str.append(spacer(rank,"L")).append("|");
            for (char file = dimFile; file <= lastFile; file++) {
                Coordinate coord = new Coordinate(file,rank);
                str.append((pieces.getPieces().get(coord) != null)
                        ? (" " + pieces.getPieces().get(coord).toBoardString() + " |")
                        : "    |");
            }
            str.append(spacer(rank,"R")).append("\n");
            str.append(fancySeparator()).append("\n");
        }
        str.append(fancyColumnIndex()).append("\n");

        return str.toString();
    }
}