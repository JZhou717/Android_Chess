package model;

import java.util.ArrayList;

public class White_Pawn extends Piece {

    /**
     * Constructor initializes the piece's name as "wp", its file as the input file, its rank as the input rank, and its white_side as true
     *
     * @author Jake
     * @param file - the file where the piece was created
     * @param rank - the rank where the piece was created
     */
    public White_Pawn(char file, int rank) {
        this.name = "wp";
        this.file = file;
        this.rank = rank;
        this.white_side = true;
    }

    /**
     * White_Pawns may move up towards ranks of greater value. They can move up one in their file if the path is clear, up two in their file if the path is clear and they are still in their starting position, up-left or up-right if there is an opponent piece there or if they are performing an En Passant. When they reach the opposite side's final rank, they are {@link #promote(String) promoted}. All moves are ensured not to place the piece's own King in check by {@link #putsOwnKingInCheck(Piece[][]) putsOwnKingInCheck} method before being committed. If a move is valid, this piece's position is changed in the global board and its own file and rank fields are updated
     *
     * @author Jake
     * @param move_to a two part String with the file and the rank that they are to move to
     * @throws IllegalArgumentException if the move_to position is not valid
     */
    public void move(String move_to) throws IllegalArgumentException{

			/*
			if(!board[this.rank][fileToNum(this.file)].equals(this)) {
				System.out.println("we have not tracked this file and rank properly.");
				System.out.println("File: " + this.file);
				System.out.println("Rank: " + this.rank);
				in.close();
				System.exit(0);
			}*/

        //If you are trying to move a white pawn on a black turn
        if(!white_moves) {
            throw new IllegalArgumentException();
        }
        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        Piece[][] board_copy = copyBoard();

        if(move_file != this.file) {
            //System.out.println("Pawn moving to different file");
            //If the designated move is not in this file, then we have to see if it is an attempt to capture
            if(move_file == (this.file + 1) && move_rank == (this.rank + 1)) {
                //System.out.println("Pawn moving up-right");
                //Checking to see there is a piece there
                if(board[this.rank + 1][fileToNum(this.file) + 1] == null) {
                    //Checking for Enpassant
                    if(move_file == black_enpassant && move_rank == 6) {

                        //Removing the black pawn
                        board_copy[this.rank][fileToNum(this.file) + 1] = null;
                        //Moving
                        board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                        board_copy[this.rank][fileToNum(this.file)] = null;
                        board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                        board_copy[move_rank][fileToNum(move_file)].file = move_file;
                        if(putsOwnKingInCheck(board_copy)) {

                            throw new IllegalArgumentException();
                        }

                        //Actual move
                        //Removing the black pawn
                        board[this.rank][fileToNum(this.file) + 1] = null;
                        //Moving
                        board[this.rank + 1][fileToNum(this.file) + 1] = board[this.rank][fileToNum(this.file)];
                        board[this.rank][fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        checkForCheck(board);
                        return;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
                //Making sure the piece isn't on the same side
                if(board[this.rank + 1][fileToNum(this.file) + 1].white_side == true) {
                    throw new IllegalArgumentException();
                }


                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;

                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving
                board[this.rank + 1][fileToNum(this.file) + 1] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } else if (move_file == (this.file - 1) && move_rank == (this.rank + 1)) {
                //System.out.println("Pawn moving up-left");
                //Checking to see there is a piece there
                if(board[this.rank + 1][fileToNum(this.file) - 1] == null) {
                    //Checking for Enpassant
                    if(move_file == black_enpassant && move_rank == 6) {

                        board_copy[this.rank][fileToNum(this.file) - 1] = null;

                        board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                        board_copy[this.rank][fileToNum(this.file)] = null;
                        board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                        board_copy[move_rank][fileToNum(move_file)].file = move_file;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }

                        //Removing the black pawn
                        board[this.rank][fileToNum(this.file) - 1] = null;
                        //Moving
                        board[this.rank + 1][fileToNum(this.file) - 1] = board[this.rank][fileToNum(this.file)];
                        board[this.rank][fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        checkForCheck(board);
                        return;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
                //Making sure the piece isn't on the same side
                if(board[this.rank + 1][fileToNum(this.file) - 1].white_side == true) {
                    throw new IllegalArgumentException();
                }

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving
                board[this.rank + 1][fileToNum(this.file) - 1] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } else {
                //System.out.println("Something wrong");
                throw new IllegalArgumentException();
            }
        } else {

            //System.out.println("TESTING: We should be in here");

            if(move_rank == this.rank + 1) {
                //Checking to see if path clear
                if(board[this.rank + 1][fileToNum(this.file)] != null) {
                    throw new IllegalArgumentException();
                }

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //Moving piece
                board[this.rank + 1][fileToNum(this.file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                checkForCheck(board);
                return;
            } else if(move_rank == this.rank + 2 && this.rank == 2) {

                //System.out.println("TESTING: WE ARE MOVING TWO SPACES");

                //Checking to see if path clear
                if(board[this.rank + 1][fileToNum(this.file)] != null
                        || board[this.rank + 2][fileToNum(this.file)] != null) {
                    throw new IllegalArgumentException();
                }

                board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[move_rank][fileToNum(move_file)].rank = move_rank;
                board_copy[move_rank][fileToNum(move_file)].file = move_file;
                if(putsOwnKingInCheck(board_copy)) {
                    throw new IllegalArgumentException();
                }

                //System.out.println("TESTING ARE WE GETTTING HERE?");

                //Moving piece
                board[this.rank + 2][fileToNum(this.file)] = board[this.rank][fileToNum(this.file)];
                board[this.rank][fileToNum(this.file)] = null;
                this.rank = move_rank;
                this.file = move_file;
                white_enpassant = this.file;
                checkForCheck(board);

                //System.out.println("TESTING: ABOUT TO FINISH MOVING");

                return;
            } else {
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

        //If the pawn is in the last rank, it should have promoted
        if(this.rank == 8) {
            throw new IllegalArgumentException();
        }
        //a pawns only check one side
        if(this.file == 'a') {
            //checking up 1 right 1
            temp = board[this.rank + 1][fileToNum((char) (this.file + 1))];
            if(temp != null) {
                //checking if black King
                if(temp.name.equals("bK")) {
                    //checkmate(temp.file, temp.rank);
                    return true;
                }
            }
        } //h pawns only check one side
        else if(this.file == 'h') {
            //checking up 1 left 1
            temp = board[this.rank + 1][fileToNum((char) (this.file - 1))];
            if(temp != null) {
                //checking if black King
                if(temp.name.equals("bK")) {
                    //checkmate(temp.file, temp.rank);
                    return true;
                }
            }
        } //middle pawns check for two sides
        else {
            //checking up 1 right 1
            temp = board[this.rank + 1][fileToNum((char) (this.file + 1))];
            if(temp != null) {
                //checking if black King
                if(temp.name.equals("bK")) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
            }
            //checking up 1 left 1
            temp = board[this.rank + 1][fileToNum((char) (this.file - 1))];
            if(temp != null) {
                //checking if black King
                if(temp.name.equals("bK")) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Takes in an input indicating what the pawn should be prmoted to. Replaces the piece in the pawn's position on the final rank with a piece of the indicated promotion type. Initializes the new piece's values to be the proper values. It also checks to see if this promotion places the opponent King in check
     *
     * @author Jake
     * @param promote_to - should be one of the 4 possible values to promote to. It is a lowercase lettering indicating Rook, Knight, Bishop, or Queen
     * @throws IllegalArgumentException if the input is not one of four the valid promotion types
     */
    void promote(String promote_to) throws IllegalArgumentException{
        if(promote_to.equals("r")) {
            Piece newPiece = new Rook(this.file, this.rank);
            newPiece.name = "w" + newPiece.name;
            newPiece.white_side = true;
            board[this.rank][fileToNum(this.file)] = newPiece;
            newPiece.check(board);
        }
        else if(promote_to.equals("n")) {
            Piece newPiece = new Knight(this.file, this.rank);
            newPiece.name = "w" + newPiece.name;
            newPiece.white_side = true;
            board[this.rank][fileToNum(this.file)] = newPiece;
            newPiece.check(board);
        }
        else if(promote_to.equals("b")) {
            Piece newPiece = new Bishop(this.file, this.rank);
            newPiece.name = "w" + newPiece.name;
            newPiece.white_side = true;
            board[this.rank][fileToNum(this.file)] = newPiece;
            newPiece.check(board);
        }
        else if(promote_to.equals("q")) {
            Piece newPiece = new Queen(this.file, this.rank);
            newPiece.name = "w" + newPiece.name;
            newPiece.white_side = true;
            board[this.rank][fileToNum(this.file)] = newPiece;
            newPiece.check(board);
        } else {
            throw new IllegalArgumentException();
        }
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

        //Checking up 1
        if(board[this.rank + 1][fileToNum(this.file)] == null) {
            //Testing if this move puts own King in check
            board_copy = copyBoard();
            board_copy[this.rank + 1][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
            board_copy[this.rank][fileToNum(this.file)] = null;
            board_copy[this.rank + 1][fileToNum(this.file)].rank = board_copy[this.rank + 1][fileToNum(this.file)].rank + 1;
            white_moves = board_copy[this.rank + 1][fileToNum(this.file)].white_side;
            if(!putsOwnKingInCheck(board_copy)) {
                move = String.valueOf(this.file).concat((this.rank + 1) + "");
                result.add(move);
            }
            white_moves = side_playing;

            //Checking up 2
            if(this.rank == 2 && board[this.rank + 2][fileToNum(this.file)] == null) {
                //Testing if this move puts own King in check
                board_copy = copyBoard();
                board_copy[this.rank + 2][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank + 2][fileToNum(this.file)].rank = board_copy[this.rank + 2][fileToNum(this.file)].rank + 2;
                white_moves = board_copy[this.rank + 2][fileToNum(this.file)].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat((this.rank + 2) + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
        }
        //Checking up-left
        if(this.file != 'a') {
            //If you can enpassant
            if(this.rank == 5 && this.file - 1 == black_enpassant) {
                //Testing if this move puts own King in check
                board_copy = copyBoard();
                board_copy[6][fileToNum(black_enpassant)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[6][fileToNum(black_enpassant)].rank = 6;
                board_copy[6][fileToNum(black_enpassant)].file = black_enpassant;
                white_moves = board_copy[6][fileToNum(black_enpassant)].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf((char) (this.file - 1)).concat((this.rank + 1) + "");
                    result.add(move);
                }
                white_moves = side_playing;

            }//Else if you can capture
            else if(board[this.rank + 1][fileToNum((char) (this.file - 1))] != null) {
                if(board[this.rank + 1][fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                    //Testing if this move puts own King in check
                    board_copy = copyBoard();
                    board_copy[this.rank + 1][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].rank + 1;
                    board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].file - 1);
                    white_moves = board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file - 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
            }
        }
        //Checking up-right
        if(this.file != 'h') {
            //If you can enpassant
            if(this.rank == 5 && this.file + 1 == black_enpassant) {
                //Testing if this move puts own King in check
                board_copy = copyBoard();
                board_copy[6][fileToNum(black_enpassant)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[6][fileToNum(black_enpassant)].rank = 6;
                board_copy[6][fileToNum(black_enpassant)].file = black_enpassant;
                white_moves = board_copy[6][fileToNum(black_enpassant)].white_side;
                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf((char) (this.file + 1)).concat((this.rank + 1) + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }//Else if you can capture
            else if(board[this.rank + 1][fileToNum((char) (this.file + 1))] != null) {
                if(board[this.rank + 1][fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                    //Testing if this move puts own King in check
                    board_copy = copyBoard();
                    board_copy[this.rank + 1][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].rank + 1;
                    board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].file + 1);
                    white_moves = board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].white_side;
                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file + 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
            }
        }


        return result;
    }
}
