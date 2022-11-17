package ru.vsu.cs.okshina_v_a.rules;

import ru.vsu.cs.okshina_v_a.enums.BOARD;

import java.util.Objects;

public class Coordinate {
    public char file;
    public int rank;
    public static Coordinate emptyCoordinate = new Coordinate((char) 0,0);

    public Coordinate (char file, int rank) {

        this.file = Character.toLowerCase(file);
        this.rank = rank;
    }

    public Coordinate (Coordinate original) {
        file = original.file;
        rank = original.rank;
    }

    public Coordinate (String coordinate) {

        if (coordinate.length() == 2 && Character.isLetter(coordinate.charAt(0)) && Character.isDigit(coordinate.charAt(1))) {
            file = Character.toLowerCase(coordinate.charAt(0));
            rank = Character.getNumericValue(coordinate.charAt(1));
        }
        else {
            System.out.println("Invalid coordinate format. Empty coordinate provided.");
            file = 0;
            rank = 0;
        }
    }

    public Coordinate() {
        file = 0;
        rank = 0;
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public static boolean inBoard (Coordinate coord) {
        char coordFile = coord.getFile();
        int coordRank = coord.getRank();
        return (coordFile >= BOARD.FIRST_FILE.getFileVal()
                && coordFile <= BOARD.LAST_FILE.getFileVal()
                && coordRank >= BOARD.FIRST_RANK.getRankVal()
                && coordRank <= BOARD.LAST_RANK.getRankVal());
    }

    @Override
    public String toString() {
        return file + "" + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return file == that.file &&
                rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}