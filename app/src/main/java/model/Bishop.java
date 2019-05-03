package model;

import java.util.ArrayList;

import controller.MainController;

public class Bishop extends Piece {

    /**
     * Constructor initializes the piece's name as "B", its file as the input file, its rank as the input rank. A "w" or "b" is added before the name and its white_side value is set when the piece is created either in {@link #initialize()} or by a Pawn's promotion method
     *
     * @author Jake
     * @param file - the file where the piece was created
     * @param rank - the rank where the piece was created
     */
    public Bishop(char file, int rank) {
        this.name = "B";
        this.file = file;
        this.rank = rank;
    }

    /**
     * Bishops may move to any position that is diagonal to them as long as the path is clear and the target destination does not contain a piece of the same side. All moves are ensured not to place the piece's own King in check by {@link #MainController.putsOwnKingInCheck(Piece[][]) MainController.putsOwnKingInCheck} method before being committed. If a move is valid, this piece's position is changed in the global MainController.board and its own file and rank fields are updated	 *
     * @author Jake
     * @param move_to a two part String with the file and the rank that they are to move to
     * @throws IllegalArgumentException if the move_to position is not valid
     */
    public void move(String move_to)  throws IllegalArgumentException{

			/*if(!MainController.board[this.rank][MainController.fileToNum(this.file)].equals(this)) {
				System.out.println("we have not tracked this file and rank properly.");
				System.out.println("File: " + this.file);
				System.out.println("Rank: " + this.rank);
				in.close();
				System.exit(0);
			}*/

        //Trying to move opponent's piece
        if(this.white_side != MainController.white_moves) {
            throw new IllegalArgumentException();
        }

        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        Piece[][] board_copy = MainController.copyBoard();

        //If trying to move to the same spot
        if(move_file == this.file && move_rank == this.rank) {
            throw new IllegalArgumentException();
        } //If not moving on a diagonal
        else if(Math.abs(move_rank - this.rank) != Math.abs(MainController.fileToNum(move_file) - MainController.fileToNum(this.file))) {
            //System.out.println("TESTING DIAGONAL MATH WRONG");
            throw new IllegalArgumentException();
        } //Moving diagonally
        else {
            //Moving up right
            if(move_file > this.file && move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = (move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank + i][MainController.fileToNum((char) (this.file + i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
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
                MainController.checkForCheck(MainController.board);
                return;
            } //Moving up left
            else if(move_file < this.file && move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = (move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank + i][MainController.fileToNum((char) (this.file - i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
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
                MainController.checkForCheck(MainController.board);
                return;
            } //Moving down right
            else if(move_file > this.file && move_rank < this.rank) {
                //Checking to see if path clear
                for(int i = Math.abs(move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank - i][MainController.fileToNum((char) (this.file + i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
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
                MainController.checkForCheck(MainController.board);
                return;
            } //Moving down left
            else if(move_file < this.file && move_rank < this.rank){
                //Checking to see if path clear
                for(int i = Math.abs(move_rank - this.rank) - 1; i > 0; i--) {
                    if(MainController.board[this.rank - i][MainController.fileToNum((char) (this.file - i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Checking its side
                    if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
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
                MainController.checkForCheck(MainController.board);
                return;
            } //Something's wrong
            else {
                //System.out.println("SOMETHING WRONG");
                throw new IllegalArgumentException();
            }
        }
    }

    public boolean check(Piece[][] board) {

        Piece temp;

        //Check up-right
        for(int r = this.rank + 1; r < 9; r++) {
            char tempFile = (char) (this.file + (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            temp = MainController.board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {
                    //checkmate(temp.file, temp.rank);
                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check up-left
        for(int r = this.rank + 1; r < 9; r++) {
            char tempFile = (char) (this.file - (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            temp = MainController.board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {
                    //checkmate(temp.file, temp.rank);
                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check down-right
        for(int r = this.rank - 1; r > 0; r--) {
            char tempFile = (char) (this.file + (this.rank - r));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            temp = MainController.board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {
                    //checkmate(temp.file, temp.rank);
                    return true;
                } //Piece blocking king
                else {
                    break;
                }
            }
        }
        //Check down-left
        for(int r = this.rank - 1; r > 0; r--) {
            char tempFile = (char) (this.file - (this.rank - r));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = MainController.fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            temp = MainController.board[r][f];
            //Piece on diagonal
            if(temp != null) {
                if(temp.white_side != this.white_side && temp instanceof King) {
                    //checkmate(temp.file, temp.rank);
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

        //Check up-right
        for(int r = this.rank + 1; r < 9; r++) {
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
        for(int r = this.rank + 1; r < 9; r++) {
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
        for(int r = this.rank - 1; r > 0; r--) {
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
        for(int r = this.rank - 1; r > 0; r--) {
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
