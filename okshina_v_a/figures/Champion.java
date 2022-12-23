package ru.vsu.cs.okshina_v_a.figures;

import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.enums.ID;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Move;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import javax.swing.*;
import java.util.ArrayList;

public class Champion extends Piece {
    private ImageIcon icon;

    public Champion(COLOUR colour, Coordinate OGcoord) {
        super(ID.CHAMPION, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BChampion.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WChampion.png");
    }

    public Champion(Champion original) {
        super(original);
    }

    @Override
    public Champion makeCopy() {
        return new Champion(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontFree(pieces,this, 2);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this, 2);
        ArrayList<Coordinate> back = Move.backFree(pieces,this, 2);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this, 2);
        ArrayList<Coordinate> frontRDig = Move.frontRDigFree(pieces, this, 2);
        ArrayList<Coordinate> backRDig = Move.backRDigFree(pieces, this, 2);
        ArrayList<Coordinate> backLDig = Move.backLDigFree(pieces, this, 2);
        ArrayList<Coordinate> frontLDig = Move.frontLDigFree(pieces, this, 2);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        front.addAll(frontRDig);

        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }
}