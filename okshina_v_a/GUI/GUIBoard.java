package ru.vsu.cs.okshina_v_a.GUI;

import ru.vsu.cs.okshina_v_a.enums.BOARD;
import ru.vsu.cs.okshina_v_a.enums.COLOUR;
import ru.vsu.cs.okshina_v_a.game.ChessIO;
import ru.vsu.cs.okshina_v_a.rules.Coordinate;
import ru.vsu.cs.okshina_v_a.rules.Piece;
import ru.vsu.cs.okshina_v_a.rules.Pieces;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.HashSet;

public class GUIBoard extends JFrame {
    private final Pieces pieces;
    private final int dimension = BOARD.LAST_RANK.getRankVal();
    private final int firstRank = BOARD.FIRST_RANK.getRankVal();
    private final char firstFile = BOARD.FIRST_FILE.getFileVal();
    private final char lastFile = BOARD.LAST_FILE.getFileVal();
    private final char charFile = (char) (firstFile - 1);
    private static COLOUR turn = COLOUR.W;
    private final JTextPane movePane = new JTextPane();
    private final JTextPane matePane = new JTextPane();
    private final JButton[][] board = new JButton[dimension][dimension];
    private final JButton saveButton = new JButton("Save Game");
    private final Color brown = new Color(150, 75, 0);
    private final Color pastel = new Color(255, 222, 173);
    private final Color intermediate = new Color(255, 255, 153);
    public static final Color infoColour = new Color(51,51,51);
    private static final int tileSize = 88;
    private final BufferedImage invisible = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
    private final ImageIcon invisibleIcon = new ImageIcon(invisible);
    private int counter = 0;
    private int numberOfTurns = 0;
    private static final StringBuilder str = new StringBuilder();
    private Piece movingPiece;
    ButtonHandle gameClick = new ButtonHandle();

    public GUIBoard(Pieces p) {
        setTitle("Omega_Chess");
        setBackground(Color.black);
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());

        pieces = p;

        JPanel boardPanel = new JPanel(new GridLayout(dimension, dimension));
        for (int rank = dimension; rank >= firstRank; rank--) {
            for (int file = 1; file <= dimension; file++) {
                char fileChar = (char) (charFile + file);
                Coordinate tileCoord = new Coordinate(fileChar, rank);
                board[rank - 1][file - 1] = setButton(tileCoord, p);
                board[rank - 1][file - 1].addActionListener(gameClick);
                boardPanel.add(board[rank - 1][file - 1]);
            }
        }

        JPanel fullBoardPanel = new JPanel(new BorderLayout());
        fullBoardPanel.add(createFileLabelsTop(),BorderLayout.NORTH);
        fullBoardPanel.add(createRankLabelsLeft(),BorderLayout.WEST);
        fullBoardPanel.add(boardPanel, BorderLayout.CENTER);
        fullBoardPanel.add(createRankLabelsRight(),BorderLayout.EAST);
        fullBoardPanel.add(createFileLabelsBottom(), BorderLayout.SOUTH);
        contents.add(fullBoardPanel, BorderLayout.WEST);
        contents.add(createInfoPanel(), BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel createRanks() {
        JPanel ranks = new JPanel(new GridLayout(dimension,0));
        ranks.setBackground(pastel);

        int rankPad = 4;

        for (int rank = dimension; rank >= firstRank; rank--) {
            JLabel rankLabel = new JLabel(String.valueOf(rank));
            rankLabel.setFont(new Font("TimesRoman", Font.BOLD, 23));
            rankLabel.setForeground(brown);
            rankLabel.setBorder(new EmptyBorder(0,rankPad,0,rankPad));
            ranks.add(rankLabel);
        }

        return ranks;
    }

    public JPanel createFiles() {
        JPanel files = new JPanel(new GridLayout(0,dimension));
        files.setBackground(pastel);

        int filePad = 42;

        for (char file = firstFile; file <= lastFile; file++) {
            JLabel fileLabel = new JLabel(String.valueOf(file));

            fileLabel.setFont(new Font("TimesRoman", Font.BOLD, 23));
            fileLabel.setForeground(brown);
            fileLabel.setBorder(new EmptyBorder(0,filePad,0,filePad));
            files.add(fileLabel);
        }
        return files;
    }

    public JPanel createBorder() {
        JPanel border = new JPanel();
        border.setBackground(brown);

        return border;
    }

    public Border createCorner() {
        int borderPad = 20;
        return new MatteBorder(0,borderPad,0,borderPad,pastel);
    }

    public JPanel createRankLabelsLeft() {
        JPanel rankLabels = new JPanel(new BorderLayout());

        rankLabels.add(createRanks(), BorderLayout.WEST);
        rankLabels.add(createBorder(), BorderLayout.EAST);

        return rankLabels;
    }

    public JPanel createFileLabelsBottom() {
        JPanel fileLabels = new JPanel(new BorderLayout());

        fileLabels.add(createFiles(), BorderLayout.SOUTH);
        fileLabels.add(createBorder(), BorderLayout.NORTH);

        fileLabels.setBorder(createCorner());

        return fileLabels;
    }

    public JPanel createRankLabelsRight() {
        JPanel rankLabels = new JPanel(new BorderLayout());

        rankLabels.add(createRanks(), BorderLayout.EAST);
        rankLabels.add(createBorder(), BorderLayout.WEST);

        return rankLabels;
    }

    public JPanel createFileLabelsTop() {
        JPanel fileLabels = new JPanel(new BorderLayout());

        fileLabels.add(createFiles(), BorderLayout.NORTH);
        fileLabels.add(createBorder(), BorderLayout.SOUTH);

        fileLabels.setBorder(createCorner());

        return fileLabels;
    }

    private void backgroundSetter (Coordinate coordinate, JButton b){
        int signature = coordinate.getFile() - charFile + coordinate.getRank();
        if (signature % 2 == 0) {
            b.setBackground(brown);
        } else {
            b.setBackground(pastel);
        }
    }

    private JButton setButton (Coordinate coordinate, Pieces pieces){
        JButton b = new JButton();

        Piece piece;

        backgroundSetter(coordinate, b);

        if (pieces.getPieces().get(coordinate) == null) {
            piece = null;
        } else {
            piece = pieces.getPiece(coordinate);
        }

        if (piece != null)
            b.setIcon(piece.getImageIcon());
        else
            b.setIcon(invisibleIcon);

        formatButton(b);

        return b;
    }

    private void formatButton (JButton b) {
        b.setSize(tileSize, tileSize);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setVisible(true);
    }

    public static void formatInvisibleButton (JButton b) {
        b.setSize(tileSize, tileSize);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setVisible(true);
    }

    private void disableBoardButtons() {
        for (int row = 0; row < dimension; row++) {
            for (int file = 0; file < dimension; file++) {
                board[row][file].removeActionListener(gameClick);
            }
        }
    }

    private JPanel createInfoPanel() {

        JPanel movePanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        movePanel.setBackground(infoColour);
        movePanel.setVisible(true);

        movePanel.setPreferredSize(new Dimension(300,800));

        movePane.setEditable(false);
        movePane.setForeground(Color.white);
        movePane.setBackground(infoColour);
        movePane.setFont(new Font("Arial", Font.BOLD, 14));
        movePane.setBorder(new EmptyBorder(40,20,40,20));
        JScrollPane scrollMoves = new JScrollPane(movePane);

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;

        movePanel.add(scrollMoves,gbc);

        saveButton.setBackground(Color.orange);
        saveButton.setForeground(Color.white);
        saveButton.setOpaque(true);
        saveButton.setContentAreaFilled(true);
        saveButton.setBorderPainted(false);
        SaveHandle saver = new SaveHandle();
        saveButton.addActionListener(saver);

        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;

        movePanel.add(saveButton,gbc);

        matePane.setEditable(false);
        matePane.setForeground(Color.white);
        matePane.setBackground(infoColour);
        matePane.setFont(new Font("Arial", Font.BOLD, 20));
        matePane.setBorder(new EmptyBorder(0,80,40,80));

        gbc.fill = GridBagConstraints.HORIZONTAL;

        movePanel.add(matePane,gbc);

        return movePanel;
    }

    private void setTurn() {
        turn = COLOUR.not(turn);
    }

    private void clearCounter() {
        counter = 0;
    }

    private void processClick(HashSet<Coordinate> potentials) {

        for (int rank = 1; rank <= dimension; rank++) {
            for (char file = firstFile; file <= lastFile; file++) {
                int processedRank = rank - firstRank;
                int processedFile = file - firstFile;
                Coordinate potentialCoord = new Coordinate(file, rank);
                if (potentials.contains(potentialCoord))
                    board[processedRank][processedFile].setBackground(intermediate);
                else
                    backgroundSetter(potentialCoord, board[processedRank][processedFile]);
            }
        }
    }

    private class ButtonHandle implements ActionListener {
        private void selectPiece(Piece piece) {
            processClick(piece.getPotentialMoves());
            counter++;
            movingPiece = piece;
        }

        private Coordinate toCoordinate(int row, int column) {
            int rank = row + firstRank;
            char file = (char) (column + firstFile);

            return new Coordinate(file, rank);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Coordinate coordinate = null;

            JButton source = (JButton) actionEvent.getSource();

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (board[i][j].equals(source)) {
                        coordinate = toCoordinate(i, j);
                    }
                }
            }

            Piece originPiece = pieces.getPieces().get(coordinate);

            if (coordinate != null && Coordinate.inBoard(coordinate)) {
                if (counter == 0) {
                    if (originPiece != null && originPiece.getColour() == turn) {
                        selectPiece(originPiece);
                    }
                } else {
                    Piece piece = movingPiece;

                    if (piece.isValidMove(coordinate, turn)) {
                        pieces.makeMove(coordinate, piece);
                        if (turn == COLOUR.W) {
                            numberOfTurns++;
                            str.append(numberOfTurns).append(". ").append(ChessIO.moveString(pieces, coordinate, piece)).append(" ");
                        } else
                            str.append(ChessIO.moveString(pieces, coordinate, piece)).append(" ");
                        movePane.setText(str.toString());
                        for (int rank = 1; rank <= dimension; rank++) {
                            for (char file = firstFile; file <= lastFile; file++) {
                                int processedRank = rank - firstRank;
                                int processedFile = file - firstFile;
                                Coordinate potentialCoord = new Coordinate(file, rank);
                                backgroundSetter(potentialCoord, board[processedRank][processedFile]);
                                board[processedRank][processedFile].setIcon(invisibleIcon);
                                if (pieces.getPieces().get(potentialCoord) != null) {
                                    Piece updatePiece = pieces.getPiece(potentialCoord);
                                    board[processedRank][processedFile].setIcon(updatePiece.getImageIcon());
                                }
                            }
                        }

                        clearCounter();
                        setTurn();

                        if (pieces.isMate(turn)) {
                            matePane.setText(COLOUR.not(turn).toString() + " won by checkmate.");
                            disableBoardButtons();
                        } else if (pieces.isStalemate(COLOUR.not(turn))) {
                            matePane.setText("Draw by stalemate.");
                            disableBoardButtons();
                        } else if (pieces.isDraw()) {
                            matePane.setText("It's a draw.");
                            disableBoardButtons();
                        }
                    } else {
                        if (originPiece != null && originPiece.getColour() == turn) {
                            selectPiece(originPiece);
                        }
                    }
                }
            }
        }
    }

    public static class SaveHandle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            ImageIcon icon = new ImageIcon("WKing.png");

            UIManager.put("OptionPane.background", infoColour);
            UIManager.put("Panel.background", infoColour);
            UIManager.put("OptionPane.messageForeground", Color.white);

            String fileSave = (String) JOptionPane.showInputDialog(null,
                    "Enter a file name to save the game:",
                    "Save Game",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon,null,null);

            if (fileSave != null) {

                String filePath = ChessIO.toTxt(fileSave);

                if (ChessIO.isErrorSave(filePath)) {
                    JOptionPane.showMessageDialog(null,
                            fileSave + " is an incorrect file name.",
                            "Failed Saving",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                } else {
                    if (ChessIO.saveGame(str.toString(), Paths.get(filePath)))
                        JOptionPane.showMessageDialog(null,
                                "Game saved successfully on path " + filePath,
                                "Save Successful",
                                JOptionPane.INFORMATION_MESSAGE,
                                icon);
                    else
                        JOptionPane.showMessageDialog(null,
                                "There was an error saving the file on the path " + filePath + ". The name provided might be a duplicate.",
                                "Failed Saving",
                                JOptionPane.ERROR_MESSAGE,
                                icon);

                }
            }
        }
    }

    public static void main (String[]args){
        Pieces pieces = new Pieces();
        pieces.setGUIGame(true);
        new GUIBoard(pieces);
    }
}