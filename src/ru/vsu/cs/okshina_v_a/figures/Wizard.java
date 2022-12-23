package ru.vsu.cs.okshina_v_a.figures;

import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.enums.ID;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Move;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import javax.swing.*;
import java.util.ArrayList;

public class Wizard extends Piece {
    private ImageIcon icon;

    public Wizard(COLOUR colour, Coordinate OGcoord) {
        super(ID.WIZARD, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BWizard.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WWizard.png");
    }

    public Wizard(Wizard original) {
        super(original);
    }

    @Override
    public Wizard makeCopy() {
        return new Wizard(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontKnight(pieces,this);
        ArrayList<Coordinate> right = Move.backKnight(pieces,this);
        ArrayList<Coordinate> back = Move.rightKnight(pieces,this);
        ArrayList<Coordinate> left = Move.leftKnight(pieces,this);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }
}