package model;

import java.util.ArrayList;

public class Rook extends Piece {

    public boolean has_moved = false;

    /**
     * Constructor initializes the piece's name as "R", its file as the input file, its rank as the input rank. A "w" or "b" is added before the name and its white_side value is set when the piece is created either in {@link #initialize()} or by a Pawn's promotion method
     *
     * @author Jake
     * @param file - the file where the piece was created
     * @param rank - the rank where the piece was created
     */
    public Rook(char file, int rank) {
        this.name = "R";
        this.file = file;
        this.rank = rank;
    }

    /**
     * Rooks may move to any position in their file or rank as long as the path is clear and the destination is not occupied by a piece of the same side. All moves are ensured not to place the piece's own King in check by {@link #putsOwnKingInCheck(Piece[][]) putsOwnKingInCheck} method before being committed. If a move is valid, this piece's position is changed in the global board and its own file and rank fields are updated
     *
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
        } //If moving to somewhere besides this position's file and rank, throw exception
        else if(move_file != this.file && move_rank != this.rank) {
            throw new IllegalArgumentException();
        } //If moving along the file
        else if(move_file == this.file) {
            //Moving up the board
            if(move_rank > this.rank) {
                //Checking to see if path clear
                for(int i = this.rank + 1; i < move_rank; i++) {
                    if(board[i][fileToNum(this.file)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(board[move_rank][fileToNum(this.file)] != null) {
                    //making sure the piece isn't on the same side
                    if(board[move_rank][fileToNum(this.file)].white_side == this.white_side) {
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
                board[move_rank][fileToNum(this.file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                this.has_moved = true;
                checkForCheck(board);
                return;
            } //Moving down the board
            else {
                //Checking to see if path clear
                for(int i = this.rank - 1; i > move_rank; i--) {
                    if(board[i][fileToNum(this.file)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(board[move_rank][fileToNum(this.file)] != null) {
                    //making sure the piece isn't on the same side
                    if(board[move_rank][fileToNum(this.file)].white_side == this.white_side) {
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
                board[move_rank][fileToNum(this.file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.has_moved = true;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            }
        } //Moving along the rank
        else {
            //Moving to the right
            if(move_file > this.file) {
                //Checking to see if path clear
                for(int i = this.file + 1; i < move_file; i++) {
                    if(board[this.rank][fileToNum((char) i)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(board[this.rank][fileToNum(move_file)] != null) {
                    //making sure the piece isn't on the same side
                    if(board[this.rank][fileToNum(move_file)].white_side == this.white_side) {
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
                board[this.rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.has_moved = true;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } //Moving to the left
            else {
                //Checking to see if path clear
                for(int i = this.file - 1; i > move_file; i--) {
                    if(board[this.rank][fileToNum((char) i)] != null) {
                        throw new IllegalArgumentException();
                    }
                }
                //Seeing if there is a piece there and
                if(board[this.rank][fileToNum(move_file)] != null) {
                    //making sure the piece isn't on the same side
                    if(board[this.rank][fileToNum(move_file)].white_side == this.white_side) {
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
                board[this.rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.has_moved = true;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
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

        //Check for checks on the row to the left
        for(int f = fileToNum(this.file) - 1; f >= 0; f--) {
            temp = board[this.rank][f];
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
        //Check for checks on the row to the right
        for(int f = fileToNum(this.file) + 1; f < 8; f++) {
            temp = board[this.rank][f];
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
        //Checks for checks on top
        for(int r = this.rank + 1; r < 9; r++) {
            temp = board[r][fileToNum(this.file)];
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
        //Check for checks below
        for(int r = this.rank - 1; r > 0; r--) {
            temp = board[r][fileToNum(this.file)];
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

        //Checking all possible moves up
        for(int r = this.rank + 1; r < 9; r++) {
            //If there is no piece in this position
            if(board[r][fileToNum(this.file)] == null) {
                //Check to see if this move puts the king in check
                board_copy = copyBoard();
                board_copy[r][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[r][fileToNum(this.file)].rank = r;
                white_moves = board_copy[r][fileToNum(this.file)].white_side;

                //display(board_copy);

                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat(r + "");
                    result.add(move);
                }
                white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(board[r][fileToNum(this.file)].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = copyBoard();
                    board_copy[r][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[r][fileToNum(this.file)].rank = r;
                    white_moves = board_copy[r][fileToNum(this.file)].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat(r + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }
        //Checking all possible moves down
        for(int r = this.rank - 1; r > 0; r--) {
            //If there is no piece in this position
            if(board[r][fileToNum(this.file)] == null) {
                //Check to see if this move puts the king in check
                board_copy = copyBoard();
                board_copy[r][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[r][fileToNum(this.file)].rank = r;
                white_moves = board_copy[r][fileToNum(this.file)].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat(r + "");
                    result.add(move);
                }
                white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(board[r][fileToNum(this.file)].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = copyBoard();
                    board_copy[r][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[r][fileToNum(this.file)].rank = r;
                    white_moves = board_copy[r][fileToNum(this.file)].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat(r + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }
        //Checking all possible moves right
        for(int f = fileToNum(this.file) + 1; f < 8; f++) {
            //If there is no piece in this position
            if(board[this.rank][f] == null) {
                //Check to see if this move puts the king in check
                board_copy = copyBoard();
                board_copy[this.rank][f] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank][f].file = numToFile(f);
                white_moves = board_copy[this.rank][f].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(numToFile(f)).concat(this.rank + "");
                    result.add(move);
                }
                white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(board[this.rank][f].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = copyBoard();
                    board_copy[this.rank][f] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank][f].file = numToFile(f);
                    white_moves = board_copy[this.rank][f].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(numToFile(f)).concat(this.rank + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }
        //Checking all possible moves left
        for(int f = fileToNum(this.file) - 1; f >= 0; f--) {
            //If there is no piece in this position
            if(board[this.rank][f] == null) {
                //Check to see if this move puts the king in check
                board_copy = copyBoard();
                board_copy[this.rank][f] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank][f].file = numToFile(f);
                white_moves = board_copy[this.rank][f].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(numToFile(f)).concat(this.rank + "");
                    result.add(move);
                }
                white_moves = side_playing;

            }
            else {
                //Can capture this piece
                if(board[this.rank][f].white_side != this.white_side) {
                    //Check to see if this move puts the king in check
                    board_copy = copyBoard();
                    board_copy[this.rank][f] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank][f].file = numToFile(f);
                    white_moves = board_copy[this.rank][f].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(numToFile(f)).concat(this.rank + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                //Can only do it once, not to the pieces behind it too
                break;
            }
        }

        return result;
    }

}
