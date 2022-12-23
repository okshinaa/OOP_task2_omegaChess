package ru.vsu.cs.okshina_v_a.rules;

import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.enums.ID;
import ru.vsu.cs.okshina_v_a.figures.Bishop;
import ru.vsu.cs.okshina_v_a.figures.King;
import ru.vsu.cs.okshina_v_a.figures.Pawn;
import ru.vsu.cs.okshina_v_a.game.Boards;

import java.util.*;

public class Pieces {
    private HashMap<Coordinate, Piece> pieces;
    private HashMap<Coordinate, Piece> previousPieces;
    private boolean isCapture;
    private boolean isGUIGame;
    private ArrayList<HashMap<Coordinate,Piece>> gameProgress = new ArrayList<>();

    public Pieces() {
        pieces = Boards.getChessBoard();
        previousPieces = copyHashMap(pieces);
        gameProgress.add(copyHashMap(pieces));
        updatePotentials();
    }

    public Pieces(HashMap<Coordinate, Piece> newBoard) {
        pieces = newBoard;
        previousPieces = copyHashMap(pieces);
        gameProgress.add(copyHashMap(pieces));
        updatePotentials();
    }

    public Pieces (Pieces original) {
        this.pieces = copyHashMap(original.getPieces());
        this.previousPieces = original.previousPieces;
        this.isCapture = original.isCapture;
        this.isGUIGame = original.isGUIGame;
        this.gameProgress = copyArrayHash(original.getGameProgress());
    }

    private HashMap<Coordinate, Piece> copyHashMap (HashMap<Coordinate, Piece> original) {
        HashMap<Coordinate, Piece> copyMap = new HashMap<>();
        for (Coordinate key : original.keySet()) {
            Coordinate newKey = new Coordinate(key);
            Piece newPiece = original.get(key).makeCopy();
            copyMap.put(newKey,newPiece);
        }

        return copyMap;
    }

    private ArrayList<HashMap<Coordinate,Piece>> copyArrayHash (ArrayList<HashMap<Coordinate,Piece>> original) {
        ArrayList<HashMap<Coordinate,Piece>> copyList = new ArrayList<>();
        for (HashMap<Coordinate,Piece> game : original) {
            copyList.add(copyHashMap(game));
        }

        return copyList;
    }

    public HashMap<Coordinate, Piece> getPieces() {
        return pieces;
    }

    public void setPieces(HashMap<Coordinate,Piece> pieces) {this.pieces = pieces;}

    public boolean getIsCapture() {
        return isCapture;
    }

    public void setIsCapture(boolean captureStatus) {
        this.isCapture = captureStatus;
    }

    public HashMap<Coordinate, Piece> getPreviousPieces() {
        return previousPieces;
    }

    public void setPreviousPieces(HashMap<Coordinate, Piece> previousPieces) {
        this.previousPieces = copyHashMap(previousPieces);
    }

    public ArrayList<HashMap<Coordinate, Piece>> getGameProgress() {
        return gameProgress;
    }

    public void setGUIGame (boolean GUIStatus) {
        isGUIGame = GUIStatus;
    }

    public void addPiece(Coordinate coordinate, Piece piece) {
        pieces.put(coordinate,piece);
    }

    public Coordinate findPiece(Piece piece) {

        Objects.requireNonNull(piece, "Provide an existing piece. It can't be null.");

        for (Coordinate key : pieces.keySet()) {
            if (pieces.get(key).equals(piece))
                return key;
        }
        System.err.println(piece.getName().toFullString() +" not found.");
        return Coordinate.emptyCoordinate;
    }

    public Coordinate findKing(COLOUR colour) {
        for (Coordinate key : pieces.keySet()) {
            Piece potentialKing = pieces.get(key);
            if (potentialKing.getName().equals(ID.KING) && (potentialKing.getColour() == colour))
                return key;
        }
        String pieceNotInBoard = "King not found. Assuming it isn't in board. Empty coordinate provided.";
        System.err.println(pieceNotInBoard);
        return Coordinate.emptyCoordinate;
    }

    public Piece getPiece(Coordinate coordinate) {

        Objects.requireNonNull(coordinate, "Provide an existing coordinate. It can't be null.");

        for (Coordinate key : pieces.keySet()) {
            if (key.equals(coordinate))
                return pieces.get(key);
        }
        System.err.println("There is no piece in this coordinate. Empty piece provided.");
        return Piece.emptyPiece;
    }

    public HashMap<Coordinate, Piece> getColourPieces(COLOUR colour) {
        HashMap<Coordinate,Piece> colours = new HashMap<>();
        for (Coordinate key : pieces.keySet()) {
            Piece piece = pieces.get(key);
            if (piece.getColour() == colour)
                colours.put(key,piece);
        }
        return colours;
    }

    public HashSet<Coordinate> allColouredPotentials (COLOUR colour) {
        HashSet<Coordinate> allMoves = new HashSet<>();
        HashMap<Coordinate, Piece> allColoured = getColourPieces(colour);
        for (Piece piece : allColoured.values()){
            allMoves.addAll(piece.getPotentialMoves());
        }
        return allMoves;
    }

    public HashSet<Coordinate> allColouredRaws (COLOUR colour) {
        HashSet<Coordinate> allMoves = new HashSet<>();
        HashMap<Coordinate, Piece> allColoured = getColourPieces(colour);
        for (Piece piece : allColoured.values()){
            allMoves.addAll(piece.getRawMoves(this));
        }
        return allMoves;
    }

    public void updatePreviousMovePawns () {
        for (Piece potentialPawn : pieces.values()){
            if (potentialPawn.getName() == ID.PAWN) {
                Pawn pawn = (Pawn) potentialPawn;
                pawn.setPreviousCoordinate(pawn.getCoords());
            }
        }
    }

    public boolean pieceInSameFile (Piece piece) {
        if (piece.getName() == ID.KING)
            return false;

        HashMap <Coordinate, Piece> coloured = getColourPieces(piece.getColour());
        for (Piece value : coloured.values()) {
            if (value.getName() == piece.getName() && value.getFile() == piece.getFile() && !value.equals(piece))
                return true;
        }
        return false;
    }

    public boolean pieceInSameRank (Piece piece) {

        if (piece.getName() == ID.KING)
            return false;

        HashMap <Coordinate, Piece> coloured = getColourPieces(piece.getColour());
        for (Piece value : coloured.values()) {
            if (value.getName() == piece.getName() && value.getRank() == piece.getRank() && !value.equals(piece))
                return true;
        }
        return false;
    }

    public boolean pieceToSameCoordinate (Coordinate coordinate, Piece piece) {
        assert piece.getPotentialMoves().contains(coordinate);

        if (piece.getName() == ID.KING)
            return false;

        HashMap <Coordinate, Piece> coloured = getColourPieces(piece.getColour());
        for (Piece value : coloured.values()) {
            if (value.getName() == piece.getName() && value.getPotentialMoves().contains(coordinate) && !value.equals(piece))
                return true;
        }
        return false;
    }

    public boolean isCheck(COLOUR colour) {
        Coordinate kingPosition = findKing(colour);

        if (kingPosition.equals(Coordinate.emptyCoordinate))
            throw new IllegalArgumentException("There is no king in the board. This is an illegal game!");

        HashSet<Coordinate> dangerMoves = allColouredPotentials(COLOUR.not(colour));
        return (dangerMoves.contains(kingPosition));
    }

    public boolean isMate(COLOUR colour) {
        HashSet<Coordinate> allMoves = allColouredPotentials(colour);
        return isCheck(colour) && (allMoves.size() == 0);
    }

    public boolean isDraw() {

        int n = gameProgress.size();

        boolean twoKings = !findKing(COLOUR.B).equals(Coordinate.emptyCoordinate) && !findKing(COLOUR.W).equals(Coordinate.emptyCoordinate);

        if (getPieces().size() == 2)
            return twoKings;
        else if (getPieces().size() == 3) {
            int counter = 0;
            for (Piece piece : this.getPieces().values()) {
                if (piece.getName() == ID.BISHOP || piece.getName() == ID.KNIGHT)
                    counter++;
            }
            return twoKings && counter == 1;
        }
        else if (getPieces().size() == 4) {
            int counterB = 0;
            Bishop bishopB = null;
            int counterW = 0;
            Bishop bishopW = null;
            for (Piece piece : this.getPieces().values()) {
                if (piece.getName() == ID.BISHOP) {
                    if (piece.getColour() == COLOUR.B) {
                        bishopB = (Bishop) piece;
                        counterB++;
                    }
                    else {
                        bishopW = (Bishop) piece;
                        counterW++;
                    }
                }
            }

            boolean sameColourBishops = counterB == 1 &&
                    counterW == 1 &&
                    bishopB.getOGcoord().getFile() != bishopW.getOGcoord().getFile();

            return twoKings && sameColourBishops;
        }
        else if (n >= 3){
            for (HashMap<Coordinate, Piece> currentGame : gameProgress) {
                int counter = 0;
                for (HashMap<Coordinate, Piece> checkGame : gameProgress) {
                    if (currentGame.equals(checkGame)) {
                        counter++;
                    }
                }
                if (counter == 3)
                    return true;
            }

        }

        return false;
    }

    public boolean isStalemate(COLOUR colour) {
        HashSet<Coordinate> allMoves = allColouredPotentials(COLOUR.not(colour));
        return allMoves.size() == 0 && !isCheck(COLOUR.not(colour));
    }

    public void pieceMove (Coordinate coordinate, Piece piece) {
        Coordinate pieceCoord = findPiece(piece);
        addPiece(coordinate, piece);
        piece.setCoords(coordinate);
        piece.setHasMoved();
        pieces.remove(pieceCoord);
    }

    public void makeMove (Coordinate coordinate, Piece piece) {

        if (piece.isValidMove(coordinate, piece.getColour())) {
            setPreviousPieces(this.getPieces());
            isCapture = Move.tileFull(this, coordinate) && Move.isNotTileColour(this,coordinate, piece.getColour());
            if (piece.getName() == ID.KING) {
                King castleKing = (King) piece;
                if (castleKing.canCastleQueen(this) && coordinate.equals(castleKing.getCastleCoordKingQ()) && !isCheck(castleKing.getColour())) {
                    assert !findPiece(castleKing.getRookQueen()).equals(Coordinate.emptyCoordinate);
                    pieceMove(coordinate,castleKing);
                    pieceMove(castleKing.getRookQueen().getCastleCoordRook(),castleKing.getRookQueen());
                }
                else if (castleKing.canCastleKing(this) && coordinate.equals(castleKing.getCastleCoordKingK()) && !isCheck(castleKing.getColour())) {
                    assert !findPiece(castleKing.getRookKing()).equals(Coordinate.emptyCoordinate);
                    pieceMove(coordinate,castleKing);
                    pieceMove(castleKing.getRookKing().getCastleCoordRook(),castleKing.getRookKing());
                }
                else {
                    pieceMove(coordinate, castleKing);
                }
            }
            else if (piece.getName() == ID.PAWN) {
                Pawn pawn = (Pawn) piece;

                updatePreviousMovePawns();
                if (Math.abs(coordinate.getRank() - pawn.getRank()) == 2)
                    pawn.setHasMovedTwo();

                if (pawn.canPromoteBlack(coordinate) || pawn.canPromoteWhite(coordinate)) {
                    Piece toPromote;

                    if (isGUIGame) {
                        pawn.GUIPromotionQuery(coordinate);
                        toPromote = pawn.getPromotedPiece();
                    }
                    else {
                        toPromote = pawn.promotionQuery(coordinate);
                    }
                    Coordinate pieceCoord = findPiece(piece);
                    addPiece(coordinate, toPromote);
                    pieces.remove(pieceCoord);
                }
                else if (pawn.getEnPassantLeft()) {
                    Coordinate left = Move.leftFree(this,pawn,1).get(0);
                    setIsCapture(true);
                    pieces.remove(left);
                    pieceMove(coordinate,pawn);
                }
                else if (pawn.getEnPassantRight()) {
                    Coordinate right = Move.rightFree(this,pawn,1).get(0);
                    setIsCapture(true);
                    pieces.remove(right);
                    pieceMove(coordinate,pawn);
                }
                else {
                    pieceMove(coordinate, pawn);
                }
            }
            else {
                pieceMove(coordinate, piece);
            }
        }
        else
            System.err.println(piece.getName().toFullString() + " to " + coordinate.toString() + " is an invalid move.");

        gameProgress.add(copyHashMap(pieces));
        updatePotentials();

    }

    public void updatePotentials() {

        for (Piece value : pieces.values()) {
            value.clearMoves();
            value.updatePotentialMoves(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        pieces.forEach((coord, piece) -> str.append(piece.getPieceID())
                .append(" is at ")
                .append(coord.toString())
                .append("\n"));

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces1 = (Pieces) o;
        return Objects.equals(pieces, pieces1.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}