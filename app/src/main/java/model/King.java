package model;

import java.util.ArrayList;
import java.util.IllformedLocaleException;

import controller.MainController;

public class King extends Piece {

    public boolean has_moved = false;

    //These values are used in king_can_castle to determine what side
    final int WHITE_LEFT = 0;
    final int WHITE_RIGHT = 1;
    final int BLACK_LEFT = 2;
    final int BLACK_RIGHT = 3;


    /**
     * Constructor initializes the piece's name as "K", its file as the input file, its rank as the input rank. A "w" or "b" is added before the name and its white_side value is set when the piece is created in initialize()
     *
     * @author Jake
     * @param file - the file where the piece was created
     * @param rank - the rank where the piece was created
     */
    public King(char file, int rank) {
        this.name = "K";
        this.file = file;
        this.rank = rank;
    }

    public void move(String move_to)  throws IllegalArgumentException {

        //Get the positions to move to
        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        //Trying to move more than two squares away
        if(Math.abs(move_rank - this.rank) + Math.abs(MainController.fileToNum(move_file) - MainController.fileToNum(this.file)) > 2) {
            throw new IllegalArgumentException();
        }
        //Valid move
        else {

            //Creating a copy of the MainController.board to brute force test if this move will put King in check
            Piece[][] board_copy = MainController.copyBoard();

            //Moving up-center
            if (move_file == this.file && move_rank == this.rank + 1) {

            } //Moving up-right
            else if (move_file == this.file + 1 && move_rank == this.rank + 1) {

            } //Moving center-right
            else if (move_file == this.file + 1 && move_rank == this.rank) {

            } //Moving down-right
            else if (move_file == this.file + 1 && move_rank == this.rank - 1) {

            } // Moving down-center
            else if (move_file == this.file && move_rank == this.rank - 1) {

            } //Moving down-left
            else if (move_file == this.file - 1 && move_rank == this.rank - 1) {

            } //Moving center-left
            else if (move_file == this.file - 1 && move_rank == this.rank) {

            } //Moving up-left
            else if (move_file == this.file - 1 && move_rank == this.rank + 1) {

            } //Trying to move twice in one direction
            else {

                //Check for castling
                //If it's the white king castling
                if (this.white_side) {
                    //Trying to castle kingside
                    if (move_to.equals("g0")) {

                        //Testing to see if this king can castle
                        if(!king_can_castle(WHITE_RIGHT)) {
                            throw new IllegalArgumentException();
                        }

                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[0][MainController.fileToNum('f')] = MainController.board[0][MainController.fileToNum('h')];
                        MainController.board[0][MainController.fileToNum('h')] = null;
                        MainController.board[0][MainController.fileToNum('f')].rank = 0;
                        MainController.board[0][MainController.fileToNum('f')].file = 'f';
                        ((Rook) MainController.board[0][MainController.fileToNum('f')]).has_moved = true;

                        return;
                    } //Trying to castle queenside
                    else if (move_to.equals("c0")) {

                        //Testing to see if this king can castle
                        if(!king_can_castle(WHITE_LEFT)) {
                            throw new IllegalArgumentException();
                        }

                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[0][MainController.fileToNum('d')] = MainController.board[0][MainController.fileToNum('a')];
                        MainController.board[0][MainController.fileToNum('a')] = null;
                        MainController.board[0][MainController.fileToNum('d')].rank = 0;
                        MainController.board[0][MainController.fileToNum('d')].file = 'd';
                        ((Rook) MainController.board[0][MainController.fileToNum('d')]).has_moved = true;

                        return;
                    } //Invalid move
                    else {
                        throw new IllegalArgumentException();
                    }
                } //If it is the black king castling
                else {
                    //Trying to castle kingside
                    if (move_to.equals("g7")) {

                        //Testing to see if this king can castle
                        if(!king_can_castle(BLACK_RIGHT)) {
                            throw new IllegalArgumentException();
                        }

                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[7][MainController.fileToNum('f')] = MainController.board[7][MainController.fileToNum('h')];
                        MainController.board[7][MainController.fileToNum('h')] = null;
                        MainController.board[7][MainController.fileToNum('f')].rank = 7;
                        MainController.board[7][MainController.fileToNum('f')].file = 'f';
                        ((Rook) MainController.board[7][MainController.fileToNum('f')]).has_moved = true;

                        return;
                    } //Trying to castle queenside
                    else if (move_to.equals("c7")) {

                        //Testing to see if this king can castle
                        if(!king_can_castle(BLACK_LEFT)) {
                            throw new IllegalArgumentException();
                        }

                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[7][MainController.fileToNum('d')] = MainController.board[7][MainController.fileToNum('a')];
                        MainController.board[7][MainController.fileToNum('a')] = null;
                        MainController.board[7][MainController.fileToNum('d')].rank = 7;
                        MainController.board[7][MainController.fileToNum('d')].file = 'd';
                        ((Rook) MainController.board[7][MainController.fileToNum('d')]).has_moved = true;

                        return;
                    } //Invalid move
                    else {
                        throw new IllegalArgumentException();
                    }
                }

            }


            //Moving on the MainController.board copy
            board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
            board_copy[this.rank][MainController.fileToNum(this.file)] = null;
            //Checking for checks
            if (MainController.putsOwnKingInCheck(board_copy)) {
                throw new IllegalArgumentException();
            }
            //Moving to position
            MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
            MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
            this.rank = move_rank;
            this.file = move_file;
            this.has_moved = true;

            return;
        }
    }

    public boolean check(Piece[][] board) {

        Piece temp;

        //Check above
        if(this.rank != 7) {

            //Check up center
            temp = board[this.rank + 1][MainController.fileToNum(this.file)];
            if(temp != null) {
                //if it's the opposite king
                if(temp instanceof King) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
                //Check up right
                if(this.file != 'h') {
                    temp = board[this.rank + 1][MainController.fileToNum((char) (this.file + 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
                //Check up left
                if(this.file != 'a') {
                    temp = board[this.rank + 1][MainController.fileToNum((char) (this.file - 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
            }

        }
        //Check below
        if(this.rank != 0) {

            //Check down center
            temp = board[this.rank - 1][MainController.fileToNum(this.file)];
            if(temp != null) {
                //if it's the opposite king
                if(temp instanceof King) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
                //Check down right
                if(this.file != 'h') {
                    temp = board[this.rank - 1][MainController.fileToNum((char) (this.file + 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
                //Check down left
                if(this.file != 'a') {
                    temp = board[this.rank - 1][MainController.fileToNum((char) (this.file - 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
            }
        }
        //Check right
        if(this.file != 'h') {
            temp = board[this.rank][MainController.fileToNum((char) (this.file + 1))];
            //if it's the opposite king
            if(temp instanceof King) {
                //checkmate((char) temp.file, temp.rank);
                return true;
            }
        }
        //Check left
        if(this.file != 'a') {
            temp = board[this.rank][MainController.fileToNum((char) (this.file - 1))];
            //if it's the opposite king
            if(temp instanceof King) {
                //checkmate((char) temp.file, temp.rank);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all the positions that this piece can move to as an ArrayList of Strings. Each String is 2 characters consisting of a file and a rank
     *
     * @author Jake
     * @return ArrayList of strings of FileRank format of all the places this piece can move to
     */
    public ArrayList<String> allValidMoves() {

        ArrayList<String> result = new ArrayList<String>();
        String move;

        //Checking Above
        if(this.rank != 7) {
            //Checking up-center
            //If there is no piece there
            if(MainController.board[this.rank + 1][MainController.fileToNum(this.file)] == null) {

                //If the move does not cause self check
                if(!MainController.move_causes_own_check(this.rank, this.file, this.rank + 1, this.file)) {
                    move = Character.toString(this.file).concat((this.rank + 1) + "");
                    result.add(move);
                }
            }
            //There is a piece there
            else {
                //The piece is on the other side
                if(MainController.board[this.rank + 1][MainController.fileToNum(this.file)].white_side != this.white_side) {

                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank + 1, this.file)) {
                        move = Character.toString(this.file).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                }
            }
            //Checking up-right
            if(this.file != 'h') {
                //If there is no piece there
                if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 1))] == null) {

                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank + 1, (char) (this.file + 1))) {
                        move = Character.toString((char) (this.file + 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                }
                //There is a piece there
                else {
                    //The piece is on the other side
                    if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {

                        //If the move does not cause self check
                        if(!MainController.move_causes_own_check(this.rank, this.file, this.rank + 1, (char) (this.file + 1))) {
                            move = Character.toString((char) (this.file + 1)).concat((this.rank + 1) + "");
                            result.add(move);
                        }
                    }
                }
            }
            //Checking up-left
            if(this.file != 'a') {
                //If there is no piece there
                if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 1))] == null) {

                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank + 1, (char) (this.file - 1))) {
                        move = Character.toString((char) (this.file - 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                }
                //There is a piece there
                else {
                    //The piece is on the other side
                    if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {

                        //If the move does not cause self check
                        if(!MainController.move_causes_own_check(this.rank, this.file, this.rank + 1, (char) (this.file - 1))) {
                            move = Character.toString((char) (this.file - 1)).concat((this.rank + 1) + "");
                            result.add(move);
                        }
                    }
                }
            }
        }
        //Checking Below
        if(this.rank != 0) {

            //Checking down-center
            //If there is no piece there
            if(MainController.board[this.rank - 1][MainController.fileToNum(this.file)] == null) {

                //If the move does not cause self check
                if(!MainController.move_causes_own_check(this.rank, this.file, this.rank - 1, this.file)) {
                    move = Character.toString(this.file).concat((this.rank - 1) + "");
                    result.add(move);
                }
            }
            //If there is a piece there
            else {
                //If the piece is on the other side
                if(MainController.board[this.rank - 1][MainController.fileToNum(this.file)].white_side != this.white_side) {

                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank - 1, this.file)) {
                        move = Character.toString(this.file).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                }
            }
            //Checking down-right
            if(this.file != 'h') {
                //If there is no piece there
                if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 1))] == null) {

                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank - 1, (char) (this.file + 1))) {
                        move = Character.toString((char) (this.file + 1)).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                }
                //There is a piece there
                else {
                    //It is on the other side
                    if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {

                        //If the move does not cause self check
                        if(!MainController.move_causes_own_check(this.rank, this.file, this.rank - 1, (char) (this.file + 1))) {
                            move = Character.toString((char) (this.file + 1)).concat((this.rank - 1) + "");
                            result.add(move);
                        }
                    }
                }
            }
            //Checking down-left
            if(this.file != 'a') {
                if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 1))] == null) {

                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank - 1, (char) (this.file - 1))) {
                        move = Character.toString((char) (this.file - 1)).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                }
                else {
                    if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {

                        //If the move does not cause self check
                        if(!MainController.move_causes_own_check(this.rank, this.file, this.rank - 1, (char) (this.file - 1))) {
                            move = Character.toString((char) (this.file - 1)).concat((this.rank - 1) + "");
                            result.add(move);
                        }
                    }
                }
            }
        }
        //Checking Right
        if(this.file != 'h') {
            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + 1))] == null) {

                //If the move does not cause self check
                if(!MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file + 1))) {
                    move = Character.toString((char) (this.file + 1)).concat(this.rank + "");
                    result.add(move);
                }
            }
            else {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file + 1))) {
                        move = Character.toString((char) (this.file + 1)).concat(this.rank + "");
                        result.add(move);
                    }
                }
            }
        }
        //Checking Left
        if(this.file != 'a') {
            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - 1))] == null) {

                //If the move does not cause self check
                if(!MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file - 1))) {
                    move = Character.toString((char) (this.file - 1)).concat(this.rank + "");
                    result.add(move);
                }
            }
            else {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {

                    //If the move does not cause self check
                    if(!MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file - 1))) {
                        move = Character.toString((char) (this.file - 1)).concat(this.rank + "");
                        result.add(move);
                    }
                }
            }
        }
        //Check for castling
        //If it is the white king
        if(this.white_side) {
            if(king_can_castle(WHITE_LEFT)) {
                result.add("c0");
            }
            if(king_can_castle(WHITE_RIGHT)) {
                result.add("g0");
            }
        }
        //If it is the black king
        else {
            if(king_can_castle(BLACK_LEFT)) {
                result.add("c7");
            }
            if(king_can_castle(BLACK_RIGHT)) {
                result.add("g7");
            }
        }

        return result;
    }

    private boolean king_can_castle(int where) {

        //This King has already moved, cannot castle
        if(this.has_moved) {
            return false;
        }

        if(where == WHITE_LEFT) {
            //Checking if Rook moved
            if(MainController.board[this.rank][MainController.fileToNum('a')] == null) {
                return false;
            }
            if(!MainController.board[this.rank][MainController.fileToNum('a')].name.equalsIgnoreCase("wR")) {
                return false;
            }
            if(((Rook) MainController.board[this.rank][MainController.fileToNum('a')]).has_moved) {
                return false;
            }

            //Checking if path clear
            for(int i = 1; i < 4; i++) {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - i))] != null) {
                    return false;
                }
            }

            //Making sure the King doesn't pass through check
            //Checking for one space
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file - 1))) {
                return false;
            }
            //Checking for two spaces
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file - 2))) {
                return false;
            }

            //Everything valid
            return true;
        }
        else if(where == WHITE_RIGHT) {
            //Checking if Rook moved
            if(MainController.board[this.rank][MainController.fileToNum('h')] == null) {
                return false;
            }
            if(!MainController.board[this.rank][MainController.fileToNum('h')].name.equalsIgnoreCase("wR")) {
                return false;
            }
            if(((Rook) MainController.board[this.rank][MainController.fileToNum('h')]).has_moved) {
                return false;
            }

            //Checking if path clear
            for(int i = 1; i < 3; i++) {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + i))] != null) {
                    return false;
                }
            }

            //Making sure the King doesn't pass through check
            //Checking for one space
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file + 1))) {
                return false;
            }
            //Checking for two spaces
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file + 2))) {
                return false;
            }

            //Everything valid
            return true;
        }
        else if(where == BLACK_LEFT) {
            //Checking if Rook moved
            if(MainController.board[this.rank][MainController.fileToNum('a')] == null) {
                return false;
            }
            if(!MainController.board[this.rank][MainController.fileToNum('a')].name.equalsIgnoreCase("bR")) {
                return false;
            }
            if(((Rook) MainController.board[this.rank][MainController.fileToNum('a')]).has_moved) {
                return false;
            }

            //Checking if path clear
            for(int i = 1; i < 4; i++) {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - i))] != null) {
                    return false;
                }
            }

            //Making sure the King doesn't pass through check
            //Checking for one space
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file - 1))) {
                return false;
            }
            //Checking for two spaces
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file - 2))) {
                return false;
            }

            //Everything valid
            return true;
        }
        else if(where == BLACK_RIGHT) {
            //Checking if Rook moved
            if(MainController.board[this.rank][MainController.fileToNum('h')] == null) {
                return false;
            }
            if(!MainController.board[this.rank][MainController.fileToNum('h')].name.equalsIgnoreCase("bR")) {
                return false;
            }
            if(((Rook) MainController.board[this.rank][MainController.fileToNum('h')]).has_moved) {
                return false;
            }

            //Checking if path clear
            for(int i = 1; i < 3; i++) {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + i))] != null) {
                    return false;
                }
            }

            //Making sure the King doesn't pass through check
            //Checking for one space
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file + 1))) {
                return false;
            }
            //Checking for two spaces
            if(MainController.move_causes_own_check(this.rank, this.file, this.rank, (char) (this.file + 2))) {
                return false;
            }

            //Everything valid
            return true;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
