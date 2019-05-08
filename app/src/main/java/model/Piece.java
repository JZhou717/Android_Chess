package model;

import java.util.ArrayList;

public abstract class Piece {

    //Name of the piece, color, then type: "bb" for black bishop
    public String name;
    public char file;
    public int rank;
    public boolean white_side;

    /**
     * Move takes an input string of 2 characters composing of a file and a rank of the destination. Move always runs  MainController.putsOwnKingInCheck} before committing the move. Move also checks to see if the move has placed the opponent's king in check with checkForCheck method. If a move were valid and to be committed, the Piece's position on the global MainController.board is changed and its file and rank fields are updated. More specific descriptions of move exists for each class that extends Piece.
     *
     * @author Jake
     * @param move_to a two part String with the file and the rank that they are to move to
     * @throws IllegalArgumentException if the move_to position is not valid
     */
    public abstract void move(String move_to) throws IllegalArgumentException;

    /**
     * check checks the positions in the inputed MainController.board that this piece can capture in to see if the opponent side's King is there. In the checkForCheck method if check returns true, checkmate is called. Check does not call checkmate itself since the check may be in a temporary MainController.board used in testing like the ones used in {@link #allValidMoves() allValidMoves} method
     *
     * @author Jake
     * @param board the board the that check is being tested in. This can be the global MainController.board or a temporary MainController.board created in MainController.putsOwnKingInCheck for instance
     * @return true if it is checking the opponent King, false otherwise
     */
    public abstract boolean check(Piece[][] board);

    /**
     * Returns all the positions that this piece can move to as an ArrayList of Strings. Each String is 2 characters consisting of a file and a rank
     *
     * @author Jake
     * @return ArrayList of strings of FileRank format of all the places this piece can move to
     */
    public abstract ArrayList<String> allValidMoves();

}
