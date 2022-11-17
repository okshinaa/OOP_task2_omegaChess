package ru.vsu.cs.okshina_v_a.game;

import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.enums.ID;
import ru.vsu.cs.okshina_v_a.figures.King;
import ru.vsu.cs.okshina_v_a.figures.Pawn;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class ChessIO {
    private static final String errorSave = "$$$";

    public static String moveString (Pieces pieces, Coordinate coordinate, Piece piece) {
        boolean isCastle = false;

        StringBuilder str = new StringBuilder();
        Pieces previousBoard = new Pieces(pieces.getPreviousPieces());
        Coordinate previousCoordinate = previousBoard.findPiece(piece);
        Piece previousPiece = previousBoard.getPiece(previousCoordinate);

        if (piece.getName() != ID.KING) {
            str.append(piece.getName().toString());
        }
        else {
            King king = (King) piece;
            King previousKing = (King) previousPiece;

            if (coordinate.equals(king.getCastleCoordKingQ()) && previousKing.canCastleQueen(previousBoard)) {
                str.append("O-O-O");
                isCastle = true;
            }
            else if (coordinate.equals(king.getCastleCoordKingK()) && previousKing.canCastleKing(previousBoard)) {
                str.append("O-O");
                isCastle = true;
            }
            else
                str.append(piece.getName().toString());
        }

        str.append(removeAmbiguous(previousBoard, coordinate, previousPiece));

        if (pieces.getIsCapture()) {
            if (piece.getName() == ID.PAWN) {
                assert piece instanceof Pawn;
                Pawn pawn = (Pawn) piece;
                str.append(pawn.getPreviousCoordinate().getFile()).append("x");
            }
            else
                str.append("x");
        }

        if (!isCastle) {
            str.append(coordinate.toString());
        }

        if (piece.getName() == ID.PAWN) {
            Pawn pawn = (Pawn) piece;
            if (pawn.canPromoteBlack(coordinate) || pawn.canPromoteWhite(coordinate))
                str.append("=").append(pawn.getPromotedPiece().getName().toString());
        }

        if (pieces.isMate(COLOUR.not(piece.getColour())))
            str.append("#");
        else if (pieces.isCheck(COLOUR.not(piece.getColour())))
            str.append("+");

        return str.toString();
    }

    public static String removeAmbiguous (Pieces pieces, Coordinate coordinate, Piece piece) {
        if (pieces.pieceToSameCoordinate(coordinate, piece)) {
            if (pieces.pieceInSameRank(piece))
                return piece.getFile() + "";
            else if (pieces.pieceInSameFile(piece))
                return piece.getRank() + "";
            else
                return "";
        }
        else
            return "";
    }

    public static Path fileQuery(Scanner test_in) {
        System.out.print("Enter file path: ");
        String filePath = test_in.nextLine();
        return Paths.get(filePath);
    }

    public static String toTxt (String filePath) {
        filePath = filePath.replaceAll("\\s","");

        if (filePath.length() == 0)
            return errorSave;

        int periodCheck = filePath.lastIndexOf(".");

        if (periodCheck == -1)
            return filePath + ".txt";
        else if (filePath.substring(periodCheck).length() == 5)
            return filePath;
        else
            return errorSave;
    }

   public static boolean isErrorSave (String potentialFile) {
        return errorSave.equals(potentialFile);
    }
    public static boolean saveGame(String game, Path saveFile) {
        Objects.requireNonNull(game,"You can't have a null game!");
        Objects.requireNonNull(saveFile,"You can't save a game to a null path!");

        String path = String.valueOf(saveFile);
        File gameFile = new File(path);
        if (!gameFile.exists()) {
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(game);
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}