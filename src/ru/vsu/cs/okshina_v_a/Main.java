package ru.vsu.cs.okshina_v_a;

import ru.vsu.cs.okshina_v_a.GUI.GUIBoard;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to myChess. Enter \"GUI\" to play on a Graphical User Interface. Alternatively, enter \"TBI\" to play on the terminal.");

        Pieces pieces = new Pieces();

        boolean correctType = false;

        while (!correctType) {

            String gameType = sc.next();

            if (gameType.equals("GUI")) {
                pieces.setGUIGame(true);
                new GUIBoard(pieces);
                correctType = true;
            }
            else if (gameType.equals("TBI")) {
                pieces.setGUIGame(false);
                ConsoleBoard.gameLoop(pieces);
                correctType = true;
            }
            else
                System.out.println("Incorrect game format. Try again.");
        }
    }
}