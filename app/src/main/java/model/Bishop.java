package model;

import java.util.ArrayList;

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
     * Bishops may move to any position that is diagonal to them as long as the path is clear and the target destination does not contain a piece of the same side. All moves are ensured not to place the piece's own King in check by {@link #putsOwnKingInCheck(Piece[][]) putsOwnKingInCheck} method before being committed. If a move is valid, this piece's position is changed in the global board and its own file and rank fields are updated	 *
     * @author Jake
     * @param move_to a two part String with the file and the rank that they are to move to
     * @throws IllegalArgumentException if the move_to position is not valid
     */
    public void move(String move_to)  throws IllegalArgumentException{

			/*if(!board[this.rank][fileToNum(this.file)].equals(this)) {
				System.out.println("we have not tracked this file and rank properly.");
				System.out.println("File: " + this.file);
				System.out.println("Rank: " + this.rank);
				in.close();
				System.exit(0);
			}*/

        //Trying to move opponent's piece
        if(this.white_side != white_moves) {
            throw new IllegalArgumentException();
        }

        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        Piece[][] board_copy = copyBoard();

        //If trying to move to the same spot
        if(move_file == this.file && move_rank == this.rank) {
            throw new IllegalArgumentException();
        } //If not moving on a diagonal
        else if(Math.abs(move_rank - this.rank) != Math.abs(fileToNum(move_file) - fileToNum(this.file))) {
            //System.out.println("TESTING DIAGONAL MATH WRONG");
            throw new IllegalArgumentException();
        } //Moving diagonally
        else {
            //Moving up right
            if(move_file > this.file && move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = (move_rank - this.rank) - 1; i > 0; i--) {
                    if(board[this.rank + i][fileToNum((char) (this.file + i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(board[move_rank][fileToNum(move_file)] != null) {
                    //Checking its side
                    if(board[move_rank][fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
                        throw new IllegalArgumentException();
                    }
                }

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } //Moving up left
            else if(move_file < this.file && move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = (move_rank - this.rank) - 1; i > 0; i--) {
                    if(board[this.rank + i][fileToNum((char) (this.file - i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(board[move_rank][fileToNum(move_file)] != null) {
                    //Checking its side
                    if(board[move_rank][fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
                        throw new IllegalArgumentException();
                    }
                }
                //Checking on board copy first

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } //Moving down right
            else if(move_file > this.file && move_rank < this.rank) {
                //Checking to see if path clear
                for(int i = Math.abs(move_rank - this.rank) - 1; i > 0; i--) {
                    if(board[this.rank - i][fileToNum((char) (this.file + i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(board[move_rank][fileToNum(move_file)] != null) {
                    //Checking its side
                    if(board[move_rank][fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
                        throw new IllegalArgumentException();
                    }
                }

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } //Moving down left
            else if(move_file < this.file && move_rank < this.rank){
                //Checking to see if path clear
                for(int i = Math.abs(move_rank - this.rank) - 1; i > 0; i--) {
                    if(board[this.rank - i][fileToNum((char) (this.file - i))] != null) {
                        //System.out.println("TESTING PATH NOT CLEAR");
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there
                if(board[move_rank][fileToNum(move_file)] != null) {
                    //Checking its side
                    if(board[move_rank][fileToNum(move_file)].white_side == this.white_side) {
                        //System.out.println("TESTING PIECE IN POSITION");
                        throw new IllegalArgumentException();
                    }
                }

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving to position
                board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } //Something's wrong
            else {
                //System.out.println("SOMETHING WRONG");
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * check checks the positions in the inputed board that this piece can capture in to see if the opponent side's King is there. In the {@link #checkForCheck(Piece[][]) checkForCheck} method if check returns true, checkmate is called. Check does not call checkmate itself since the check may be in a temporary board used in testing like the ones used in {@link #allValidMoves() allValidMoves} method
     *
     * @author Jake
     * @param board - the board the that check is being tested in. This can be the global board or a temporary board created in putsOwnKingInCheck for instance
     * @return true if it is checking the opponent King, false otherwise
     */
    public boolean check(Piece[][] board) {

        Piece temp;

        //Check up-right
        for(int r = this.rank + 1; r < 9; r++) {
            char tempFile = (char) (this.file + (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            temp = board[r][f];
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
            int f = fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            temp = board[r][f];
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
            int f = fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            temp = board[r][f];
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
            int f = fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            temp = board[r][f];
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
        final boolean side_playing = white_moves;

        //Check up-right
        for(int r = this.rank + 1; r < 9; r++) {
            char tempFile = (char) (this.file + (r - this.rank));
            if(tempFile < 'a' || tempFile > 'h') {
                break;
            }
            int f = fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            if(board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = copyBoard();
                board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = numToFile(f);
                white_moves = board_copy[r][f].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(numToFile(f)).concat(r + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = copyBoard();
                    board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = numToFile(f);
                    white_moves = board_copy[r][f].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
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
            int f = fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            if(board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = copyBoard();
                board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = numToFile(f);
                white_moves = board_copy[r][f].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(numToFile(f)).concat(r + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = copyBoard();
                    board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = numToFile(f);
                    white_moves = board_copy[r][f].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
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
            int f = fileToNum(tempFile);
            if(f > 7) {
                break;
            }
            if(board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = copyBoard();
                board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = numToFile(f);
                white_moves = board_copy[r][f].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(numToFile(f)).concat(r + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = copyBoard();
                    board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = numToFile(f);
                    white_moves = board_copy[r][f].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
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
            int f = fileToNum(tempFile);
            if(f < 0) {
                break;
            }
            if(board[r][f] == null) {
                //Check to see if this move puts our own king in check
                board_copy = copyBoard();
                board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[r][f].rank = r;
                board_copy[r][f].file = numToFile(f);
                white_moves = board_copy[r][f].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(numToFile(f)).concat(r + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[r][f].white_side != this.white_side) {
                    //Check to see if this move puts our own king in check
                    board_copy = copyBoard();
                    board_copy[r][f] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[r][f].rank = r;
                    board_copy[r][f].file = numToFile(f);
                    white_moves = board_copy[r][f].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(numToFile(f)).concat(r + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                break;
            }
        }

        return result;

    }

}
