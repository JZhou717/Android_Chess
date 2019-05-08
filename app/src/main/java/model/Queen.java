package model;

import java.util.ArrayList;

import controller.MainController;

public class Queen extends Piece {

    /**
     * Constructor initializes the piece's name as "Q", its file as the input file, its rank as the input rank. A "w" or "b" is added before the name and its white_side value is set when the piece is created either in initialize() or by a Pawn's promotion method
     *
     * @author Jake
     * @param file - the file where the piece was created
     * @param rank - the rank where the piece was created
     */
    public Queen(char file, int rank) {
        this.name = "Q";
        this.file = file;
        this.rank = rank;
    }

    /**
     * Queens move like a {@link Rook} or a {@link Bishop}. All moves are ensured not to place the piece's own King in check by MainController.putsOwnKingInCheck method before being committed. If a move is valid, this piece's position is changed in the global MainController.board and its own file and rank fields are updated
     *
     * @author Jake
     * @param move_to a two part String with the file and the rank that they are to move to
     * @throws IllegalArgumentException if the move_to position is not valid
     */
    public void move(String move_to)  throws IllegalArgumentException{


        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        Piece[][] board_copy = MainController.copyBoard();

        if(move_file == this.file) {
            //Moving up
            if(move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = this.rank + 1; i < move_rank; i++) {
                    if(MainController.board[i][MainController.fileToNum(this.file)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(MainController.board[move_rank][MainController.fileToNum(this.file)] != null) {
                    //making sure the piece isn't on the same side
                    if(MainController.board[move_rank][MainController.fileToNum(this.file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            } //Moving down
            else {
                //Checking to see if path clear
                for(int i = this.rank - 1; i > move_rank; i--) {
                    if(MainController.board[i][MainController.fileToNum(this.file)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(MainController.board[move_rank][MainController.fileToNum(this.file)] != null) {
                    //making sure the piece isn't on the same side
                    if(MainController.board[move_rank][MainController.fileToNum(this.file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            }
        } //Moving horizontally
        else if(move_rank == this.rank) {
            //Moving right
            if(move_file > this.file) {
                //Checking to see if path clear
                for(int i = this.file + 1; i < move_file; i++) {
                    if(MainController.board[this.rank][MainController.fileToNum((char) i)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(MainController.board[this.rank][MainController.fileToNum(move_file)] != null) {
                    //making sure the piece isn't on the same side
                    if(MainController.board[this.rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }
                //Checking on MainController.board copy first

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            } //Moving left
            else {
                //Checking to see if path clear
                for(int i = this.file - 1; i > move_file; i--) {
                    if(MainController.board[this.rank][MainController.fileToNum((char) i)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(MainController.board[this.rank][MainController.fileToNum(move_file)] != null) {
                    //making sure the piece isn't on the same side
                    if(MainController.board[this.rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }
                //Checking on MainController.board copy first

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            }
        } //Moving diagonally
        else if(Math.abs(move_rank - this.rank) == Math.abs(MainController.fileToNum(move_file) - MainController.fileToNum(this.file))) {
            //Moving up-right
            if(move_file > this.file && move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = (move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank + i][MainController.fileToNum((char) (this.file + i))] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            } //Moving up-left
            else if(move_file < this.file && move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = (move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank + i][MainController.fileToNum((char) (this.file - i))] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }
                //Checking on MainController.board copy first

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            } //Moving down-right
            else if(move_file > this.file && move_rank < this.rank) {
                //Checking to see if path clear
                for(int i = Math.abs(move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank - i][MainController.fileToNum((char) (this.file + i))] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }
                //Checking on MainController.board copy first

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            } //Moving down left
            else if(move_file < this.file && move_rank < this.rank) {
                //Checking to see if path clear
                for(int i = Math.abs(move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank - i][MainController.fileToNum((char) (this.file - i))] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        throw new IllegalArgumentException();
                    }
                }
                //Checking on MainController.board copy first

                board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[move_rank][MainController.fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][MainController.fileToNum(move_file)].file = move_file;
                if(MainController.putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                return;
            } //Invalid move
            else {
                throw new IllegalArgumentException();
            }
        } //Invalid move
        else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * check checks the positions in the inputed MainController.board that this piece can capture in to see if the opponent side's King is there. In the MainController.checkForCheck method if check returns true, checkmate is called. Check does not call checkmate itself since the check may be in a temporary MainController.board used in testing like the ones used in {@link #allValidMoves() allValidMoves} method
     *
     * @author Jake
     * @param board the that check is being tested in. This can be the global MainController.board or a temporary MainController.board created in MainController.putsOwnKingInCheck for instance
     * @return true if it is checking the opponent King, false otherwise
     */
    public boolean check(Piece[][] board) {

        Piece temp;

        //Check for checks on the row to the left
        for(int f = MainController.fileToNum(this.file) - 1; f >= 0; f--) {
            temp = board[this.rank][f];
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check for checks on the row to the right
        for(int f = MainController.fileToNum(this.file) + 1; f < 8; f++) {
            temp = board[this.rank][f];
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Checks for checks on top
        for(int r = this.rank + 1; r < 8; r++) {
            temp = board[r][MainController.fileToNum(this.file)];
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check for checks below
        for(int r = this.rank - 1; r >= 0; r--) {
            temp = board[r][MainController.fileToNum(this.file)];
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }

        //Check up-right
        for(int r = this.rank + 1; r < 8; r++) {
            char tempFile = (char) (this.file + (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            temp = board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check up-left
        for(int r = this.rank + 1; r < 8; r++) {
            char tempFile = (char) (this.file - (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            temp = board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check down-right
        for(int r = this.rank - 1; r >= 0; r--) {
            char tempFile = (char) (this.file + (this.rank - r));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            temp = board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check down-left
        for(int r = this.rank - 1; r >= 0; r--) {
            char tempFile = (char) (this.file - (this.rank - r));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            temp = board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {

                    return true;
                } //Piece blocking king
                else {
                    break;
                }
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
        Piece[][] board_copy;
        final boolean side_playing = MainController.white_moves;

        //Checking all possible moves up
        for(int r = this.rank + 1; r < 8; r++) {
            //If there is no piece in this position
            if(MainController.board[r][MainController.fileToNum(this.file)] == null) {
                //Check to see if this move puts the king in check
                board_copy = MainController.copyBoard();
                board_copy[r][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[r][MainController.fileToNum(this.file)].rank = r;
                MainController.white_moves = board_copy[r][MainController.fileToNum(this.file)].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat(r + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(MainController.board[r][MainController.fileToNum(this.file)].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = MainController.copyBoard();
                    board_copy[r][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[r][MainController.fileToNum(this.file)].rank = r;
                    MainController.white_moves = board_copy[r][MainController.fileToNum(this.file)].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat(r + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }
        //Checking all possible moves down
        for(int r = this.rank - 1; r >= 0; r--) {
            //If there is no piece in this position
            if(MainController.board[r][MainController.fileToNum(this.file)] == null) {
                //Check to see if this move puts the king in check
                board_copy = MainController.copyBoard();
                board_copy[r][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[r][MainController.fileToNum(this.file)].rank = r;
                MainController.white_moves = board_copy[r][MainController.fileToNum(this.file)].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat(r + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(MainController.board[r][MainController.fileToNum(this.file)].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = MainController.copyBoard();
                    board_copy[r][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[r][MainController.fileToNum(this.file)].rank = r;
                    MainController.white_moves = board_copy[r][MainController.fileToNum(this.file)].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat(r + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }
        //Checking all possible moves right
        for(int f = MainController.fileToNum(this.file) + 1; f < 8; f++) {
            //If there is no piece in this position
            if(MainController.board[this.rank][f] == null) {
                //Check to see if this move puts the king in check
                board_copy = MainController.copyBoard();
                board_copy[this.rank][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[this.rank][f].file = MainController.numToFile(f);
                MainController.white_moves = board_copy[this.rank][f].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(MainController.numToFile(f)).concat(this.rank + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(MainController.board[this.rank][f].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank][f].file = MainController.numToFile(f);
                    MainController.white_moves = board_copy[this.rank][f].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(MainController.numToFile(f)).concat(this.rank + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }
        //Checking all possible moves left
        for(int f = MainController.fileToNum(this.file) - 1; f >= 0; f--) {
            //If there is no piece in this position
            if(MainController.board[this.rank][f] == null) {
                //Check to see if this move puts the king in check
                board_copy = MainController.copyBoard();
                board_copy[this.rank][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[this.rank][f].file = MainController.numToFile(f);
                MainController.white_moves = board_copy[this.rank][f].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(MainController.numToFile(f)).concat(this.rank + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(MainController.board[this.rank][f].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank][f].file = MainController.numToFile(f);
                    MainController.white_moves = board_copy[this.rank][f].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(MainController.numToFile(f)).concat(this.rank + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }

        //Check up-right
        for(int r = this.rank + 1; r < 8; r++) {
            char tempFile = (char) (this.file + (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            if(MainController.board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = MainController.copyBoard();
                board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = MainController.numToFile(f);
                MainController.white_moves = board_copy[r][f].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = MainController.copyBoard();
                    board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = MainController.numToFile(f);
                    MainController.white_moves = board_copy[r][f].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                break;
            }

        }
        //Check up-left
        for(int r = this.rank + 1; r < 8; r++) {
            char tempFile = (char) (this.file - (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            if(MainController.board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = MainController.copyBoard();
                board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = MainController.numToFile(f);
                MainController.white_moves = board_copy[r][f].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = MainController.copyBoard();
                    board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = MainController.numToFile(f);
                    MainController.white_moves = board_copy[r][f].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                break;
            }
        }
        //Check down-right
        for(int r = this.rank - 1; r >= 0; r--) {
            char tempFile = (char) (this.file + (this.rank - r));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            if(MainController.board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = MainController.copyBoard();
                board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = MainController.numToFile(f);
                MainController.white_moves = board_copy[r][f].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = MainController.copyBoard();
                    board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = MainController.numToFile(f);
                    MainController.white_moves = board_copy[r][f].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                break;
            }
        }
        //Check down-left
        for(int r = this.rank - 1; r >= 0; r--) {
            char tempFile = (char) (this.file - (this.rank - r));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            if(MainController.board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = MainController.copyBoard();
                board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = MainController.numToFile(f);
                MainController.white_moves = board_copy[r][f].white_side;
                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = MainController.copyBoard();
                    board_copy[r][f] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = MainController.numToFile(f);
                    MainController.white_moves = board_copy[r][f].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(MainController.numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                break;
            }
        }

        return result;

    }

}
