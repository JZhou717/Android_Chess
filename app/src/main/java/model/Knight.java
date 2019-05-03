package model;

import java.util.ArrayList;

import controller.MainController;

public class Knight extends Piece {

    /**
     * Constructor initializes the piece's name as "N", its file as the input file, its rank as the input rank. A "w" or "b" is added before the name and its white_side value is set when the piece is created either in {@link #initialize()} or by a Pawn's promotion method
     *
     * @author Jake
     * @param file - the file where the piece was created
     * @param rank - the rank where the piece was created
     */
    public Knight(char file, int rank) {
        this.name = "N";
        this.file = file;
        this.rank = rank;
    }

    /**
     * Knights have at most 8 possible moves at a given point. They may not move to one of these possible positions if a piece of the same side is in that position of the position is out of the bounds of the playing MainController.board. All moves are ensured not to place the piece's own King in check by {@link #MainController.putsOwnKingInCheck(Piece[][]) MainController.putsOwnKingInCheck} method before being committed. If a move is valid, this piece's position is changed in the global MainController.board and its own file and rank fields are updated
     *
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
        } //Moving horizontally first
        else if(Math.abs(move_file - this.file) == 2) {
            //Moving 1 vertical space
            if(Math.abs(move_rank - this.rank) == 1) {
                //Checking if piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Making sure piece isn't on the same side
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
                MainController.checkForCheck(MainController.board);
                return;
            } //Invalid move
            else {
                throw new IllegalArgumentException();
            }
        } //Moving vertically first
        else if(Math.abs(move_rank - this.rank) == 2) {
            //Moving 1 horizontal space
            if(Math.abs(move_file - this.file) == 1) {
                //Checking if piece there
                if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                    //Making sure piece isn't on the same side
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
                MainController.checkForCheck(MainController.board);
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


    public boolean check(Piece[][] board) {

        Piece temp;

        //Check 1st layer above
        if(this.rank <= 7) {
            //Check up-left one
            if(this.file >= 'c') {
                temp = MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 2))];
                if(temp != null) {
                    if(temp.white_side != this.white_side && temp instanceof King) {
                        //checkmate(temp.file, temp.rank);
                        return true;
                    }
                }
            }
            //Check up-right one
            if(this.file <= 'f') {
                temp = MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 2))];
                if(temp != null) {
                    if(temp.white_side != this.white_side && temp instanceof King) {
                        //checkmate(temp.file, temp.rank);
                        return true;
                    }
                }
            }
            //Check 2nd layer above
            if(this.rank <= 6) {
                //Check up-left two
                if(this.file >= 'b') {
                    temp = MainController.board[this.rank + 2][MainController.fileToNum((char) (this.file - 1))];
                    if(temp != null) {
                        if(temp.white_side != this.white_side && temp instanceof King) {
                            //checkmate(temp.file, temp.rank);
                            return true;
                        }
                    }
                }
                //Check up-right two
                if(this.file <= 'g') {
                    temp = MainController.board[this.rank + 2][MainController.fileToNum((char) (this.file + 1))];
                    if(temp != null) {
                        if(temp.white_side != this.white_side && temp instanceof King) {
                            //checkmate(temp.file, temp.rank);
                            return true;
                        }
                    }
                }
            }
        }
        //Check 1st layer below
        if(this.rank >= 2) {
            //Check down-left one
            if(this.file >= 'c') {
                temp = MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 2))];
                if(temp != null) {
                    if(temp.white_side != this.white_side && temp instanceof King) {
                        //checkmate(temp.file, temp.rank);
                        return true;
                    }
                }
            }
            //Check down-right one
            if(this.file <= 'f') {
                temp = MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 2))];
                if(temp != null) {
                    if(temp.white_side != this.white_side && temp instanceof King) {
                        //checkmate(temp.file, temp.rank);
                        return true;
                    }
                }
            }
            //Check 2nd layer below
            if(this.rank >= 3) {
                //Check down-left two
                if(this.file >= 'b') {
                    temp = MainController.board[this.rank - 2][MainController.fileToNum((char) (this.file - 1))];
                    if(temp != null) {
                        if(temp.white_side != this.white_side && temp instanceof King) {
                            //checkmate(temp.file, temp.rank);
                            return true;
                        }
                    }
                }
                //Check down-right two
                if(this.file <= 'g') {
                    temp = MainController.board[this.rank - 2][MainController.fileToNum((char) (this.file + 1))];
                    if(temp != null) {
                        if(temp.white_side != this.white_side && temp instanceof King) {
                            //checkmate(temp.file, temp.rank);
                            return true;
                        }
                    }
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

        //Check first layer above
        if(this.rank <= 7) {
            //Check up-left one
            if(this.file >= 'c') {
                if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 2))] == null) {
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].rank + 1;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].file - 2);
                    MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file - 2)).concat(this.rank + 1 + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].white_side != this.white_side) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].rank + 1;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].file - 2);
                        MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 2))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 2)).concat(this.rank + 1 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
            //Check up-right one
            if(this.file <= 'f') {
                if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 2))] == null) {
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].rank + 1;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].file + 2);
                    MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file + 2)).concat(this.rank + 1 + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].white_side != this.white_side) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].rank + 1;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].file + 2);
                        MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 2))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 2)).concat(this.rank + 1 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
            //Check 2nd layer above
            if(this.rank <= 6) {
                //Check up-left two
                if(this.file >= 'b') {
                    if(MainController.board[this.rank + 2][MainController.fileToNum((char) (this.file - 1))] == null) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].rank + 2;
                        board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].file - 1);
                        MainController.white_moves = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 1)).concat(this.rank + 2 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                    else {
                        if(MainController.board[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                            board_copy = MainController.copyBoard();
                            board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                            board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                            board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].rank + 2;
                            board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].file - 1);
                            MainController.white_moves = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file - 1))].white_side;
                            if(!MainController.putsOwnKingInCheck(board_copy)) {
                                move = String.valueOf((char) (this.file - 1)).concat(this.rank + 2 + "");
                                result.add(move);
                            }
                            MainController.white_moves = side_playing;
                        }
                    }
                }
                //Check up-right two
                if(this.file <= 'g') {
                    if(MainController.board[this.rank + 2][MainController.fileToNum((char) (this.file + 1))] == null) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].rank + 2;
                        board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].file + 1);
                        MainController.white_moves = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 1)).concat(this.rank + 2 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                    else {
                        if(MainController.board[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                            board_copy = MainController.copyBoard();
                            board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                            board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                            board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].rank + 2;
                            board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].file + 1);
                            MainController.white_moves = board_copy[this.rank + 2][MainController.fileToNum((char) (this.file + 1))].white_side;
                            if(!MainController.putsOwnKingInCheck(board_copy)) {
                                move = String.valueOf((char) (this.file + 1)).concat(this.rank + 2 + "");
                                result.add(move);
                            }
                            MainController.white_moves = side_playing;
                        }
                    }
                }
            }
        }
        //Check 1st layer below
        if(this.rank >= 2) {

            //Check down-left one
            if(this.file >= 'c') {
                if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 2))] == null) {
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].rank - 1;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].file - 2);
                    MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file - 2)).concat(this.rank - 1 + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].white_side != this.white_side) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].rank - 1;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].file - 2);
                        MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 2))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 2)).concat(this.rank - 1 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
            //Check down-right one
            if(this.file <= 'f') {
                if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 2))] == null) {
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].rank - 1;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].file + 2);
                    MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].white_side;
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file + 2)).concat(this.rank - 1 + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].white_side != this.white_side) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].rank - 1;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].file + 2);
                        MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 2))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 2)).concat(this.rank - 1 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
            //Check 2nd layer below
            if(this.rank <= 6) {
                //Check down-left two
                if(this.file >= 'b') {
                    if(MainController.board[this.rank - 2][MainController.fileToNum((char) (this.file - 1))] == null) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].rank - 2;
                        board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].file - 1);
                        MainController.white_moves = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 1)).concat(this.rank - 2 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                    else {
                        if(MainController.board[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                            board_copy = MainController.copyBoard();
                            board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                            board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                            board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].rank - 2;
                            board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].file - 1);
                            MainController.white_moves = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file - 1))].white_side;
                            if(!MainController.putsOwnKingInCheck(board_copy)) {
                                move = String.valueOf((char) (this.file - 1)).concat(this.rank - 2 + "");
                                result.add(move);
                            }
                            MainController.white_moves = side_playing;
                        }
                    }
                }
                //Check down-right two
                if(this.file <= 'g') {
                    if(MainController.board[this.rank - 2][MainController.fileToNum((char) (this.file + 1))] == null) {
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].rank - 2;
                        board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].file + 1);
                        MainController.white_moves = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].white_side;
                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 1)).concat(this.rank - 2 + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                    else {
                        if(MainController.board[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                            board_copy = MainController.copyBoard();
                            board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                            board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                            board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].rank - 2;
                            board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].file + 1);
                            MainController.white_moves = board_copy[this.rank - 2][MainController.fileToNum((char) (this.file + 1))].white_side;
                            if(!MainController.putsOwnKingInCheck(board_copy)) {
                                move = String.valueOf((char) (this.file + 1)).concat(this.rank - 2 + "");
                                result.add(move);
                            }
                            MainController.white_moves = side_playing;
                        }
                    }
                }
            }
        }

        return result;
    }

}
