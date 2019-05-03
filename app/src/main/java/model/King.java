package model;

import java.util.ArrayList;

import controller.MainController;

public class King extends Piece {

    public boolean has_moved = false;

    /**
     * Constructor initializes the piece's name as "K", its file as the input file, its rank as the input rank. A "w" or "b" is added before the name and its white_side value is set when the piece is created in {@link #initialize()}
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

    public void move(String move_to)  throws IllegalArgumentException{

        //Trying to move opponent's piece
        if(this.white_side != MainController.white_moves) {
            throw new IllegalArgumentException();
        }

        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        //If trying to move to the same spot
        if(move_file == this.file && move_rank == this.rank) {
            throw new IllegalArgumentException();
        } //Trying to move more than two squares away
        else if(Math.abs(move_rank - this.rank) + Math.abs(MainController.fileToNum(move_file) - MainController.fileToNum(this.file)) > 2) {
            throw new IllegalArgumentException();
        } //Valid move
        else {

            //Creating a copy of the MainController.board to brute force test if this move will put King in check
            Piece[][] board_copy = MainController.copyBoard();

            //Moving up-center
            if(move_file == this.file && move_rank == this.rank + 1) {

            } //Moving up-right
            else if(move_file == this.file + 1 && move_rank == this.rank + 1) {

            } //Moving center-right
            else if(move_file == this.file + 1 && move_rank == this.rank) {

            } //Moving down-right
            else if(move_file == this.file + 1 && move_rank == this.rank - 1) {

            } // Moving down-center
            else if(move_file == this.file && move_rank == this.rank - 1) {

            } //Moving down-left
            else if(move_file == this.file - 1 && move_rank == this.rank - 1) {

            } //Moving center-left
            else if(move_file == this.file - 1 && move_rank == this.rank) {

            } //Moving up-left
            else if(move_file == this.file - 1 && move_rank == this.rank + 1) {

            } //Trying to move twice in one direction
            else {

                //Check for castling
                //If it's the white king castling
                if(this.white_side) {
                    //Trying to castle kingside
                    if(move_to.equals("g1")) {
                        //If this king has already moved
                        if(this.has_moved) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if King Rook Moved
                        if(MainController.board[1][MainController.fileToNum('h')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!MainController.board[1][MainController.fileToNum('h')].name.equals("wR")) {
                            //System.out.println("TESTING: NAME: " + MainController.board[1][MainController.fileToNum('h')].name);
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) MainController.board[1][MainController.fileToNum('h')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for f1 space
                        board_copy[1][MainController.fileToNum('f')] = board_copy[1][MainController.fileToNum('e')];
                        board_copy[1][MainController.fileToNum('e')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for g1 space
                        board_copy[1][MainController.fileToNum('g')] = board_copy[1][MainController.fileToNum('f')];
                        board_copy[1][MainController.fileToNum('f')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[1][MainController.fileToNum('f')] = MainController.board[1][MainController.fileToNum('h')];
                        MainController.board[1][MainController.fileToNum('h')] = null;
                        MainController.board[1][MainController.fileToNum('f')].rank = 1;
                        MainController.board[1][MainController.fileToNum('f')].file = 'f';
                        ((Rook) MainController.board[1][MainController.fileToNum('f')]).has_moved = true;
                        MainController.checkForCheck(MainController.board);
                        return;
                    } //Trying to castle queenside
                    else if(move_to.equals("c1")) {
                        //If this king has already moved
                        if(this.has_moved) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if Queen Rook Moved
                        if(MainController.board[1][MainController.fileToNum('a')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!MainController.board[1][MainController.fileToNum('a')].name.equals("wR")) {
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) MainController.board[1][MainController.fileToNum('a')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for d1 space
                        board_copy[1][MainController.fileToNum('d')] = board_copy[1][MainController.fileToNum('e')];
                        board_copy[1][MainController.fileToNum('e')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for c1 space
                        board_copy[1][MainController.fileToNum('c')] = board_copy[1][MainController.fileToNum('d')];
                        board_copy[1][MainController.fileToNum('d')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[1][MainController.fileToNum('d')] = MainController.board[1][MainController.fileToNum('a')];
                        MainController.board[1][MainController.fileToNum('a')] = null;
                        MainController.board[1][MainController.fileToNum('d')].rank = 1;
                        MainController.board[1][MainController.fileToNum('d')].file = 'd';
                        ((Rook) MainController.board[1][MainController.fileToNum('d')]).has_moved = true;
                        MainController.checkForCheck(MainController.board);
                        return;
                    } //Invalid move
                    else {
                        throw new IllegalArgumentException();
                    }
                } //If it is the black king castling
                else {
                    //Trying to castle kingside
                    if(move_to.equals("g8")) {
                        //If this king has already moved
                        if(this.has_moved) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if King Rook Moved
                        if(MainController.board[8][MainController.fileToNum('h')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!MainController.board[8][MainController.fileToNum('h')].name.equals("bR")) {
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) MainController.board[8][MainController.fileToNum('h')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for f8 space
                        board_copy[8][MainController.fileToNum('f')] = board_copy[8][MainController.fileToNum('e')];
                        board_copy[8][MainController.fileToNum('e')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for g1 space
                        board_copy[8][MainController.fileToNum('g')] = board_copy[1][MainController.fileToNum('f')];
                        board_copy[8][MainController.fileToNum('f')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[8][MainController.fileToNum('f')] = MainController.board[8][MainController.fileToNum('h')];
                        MainController.board[8][MainController.fileToNum('h')] = null;
                        MainController.board[8][MainController.fileToNum('f')].rank = 8;
                        MainController.board[8][MainController.fileToNum('f')].file = 'f';
                        ((Rook) MainController.board[8][MainController.fileToNum('f')]).has_moved = true;
                        MainController.checkForCheck(MainController.board);
                        return;
                    } //Trying to castle queenside
                    else if(move_to.equals("c8")) {
                        //If this king has already moved
                        if(this.has_moved) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if Queen Rook Moved
                        if(MainController.board[8][MainController.fileToNum('a')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!MainController.board[8][MainController.fileToNum('a')].name.equals("bR")) {
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) MainController.board[8][MainController.fileToNum('a')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for d8 space
                        board_copy[8][MainController.fileToNum('d')] = board_copy[8][MainController.fileToNum('e')];
                        board_copy[8][MainController.fileToNum('e')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for c8 space
                        board_copy[8][MainController.fileToNum('c')] = board_copy[8][MainController.fileToNum('d')];
                        board_copy[8][MainController.fileToNum('d')] = null;
                        if(MainController.putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
                        MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        MainController.board[8][MainController.fileToNum('d')] = MainController.board[8][MainController.fileToNum('a')];
                        MainController.board[8][MainController.fileToNum('a')] = null;
                        MainController.board[8][MainController.fileToNum('d')].rank = 8;
                        MainController.board[8][MainController.fileToNum('d')].file = 'd';
                        ((Rook) MainController.board[8][MainController.fileToNum('d')]).has_moved = true;
                        MainController.checkForCheck(MainController.board);
                        return;
                    } //Invalid move
                    else {
                        throw new IllegalArgumentException();
                    }
                }

            }

            //Checking to see if path clear
            if(MainController.board[move_rank][MainController.fileToNum(move_file)] != null) {
                //Checking if same side
                if(MainController.board[move_rank][MainController.fileToNum(move_file)].white_side == this.white_side) {
                    throw new IllegalArgumentException();
                }
            }
            //Moving on the MainController.board copy
            board_copy[move_rank][MainController.fileToNum(move_file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
            board_copy[this.rank][MainController.fileToNum(this.file)] = null;
            //Checking for checks
            if(MainController.putsOwnKingInCheck(board_copy)) {
                throw new IllegalArgumentException();
            }
            //Moving to position
            MainController.board[move_rank][MainController.fileToNum(move_file)] = MainController.board[this.rank][MainController.fileToNum(this.file)];
            MainController.board[this.rank][MainController.fileToNum(this.file)] = null;
            this.rank = move_rank;
            this.file = move_file;
            this.has_moved = true;
            MainController.checkForCheck(MainController.board);
            return;
        }
    }

    public boolean check(Piece[][] board) {

        Piece temp;

        //Check above
        if(this.rank != 8) {

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
                    temp =board[this.rank + 1][MainController.fileToNum((char) (this.file - 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
            }

        }
        //Check below
        if(this.rank != 1) {

            //Check down center
            temp = MainController.board[this.rank - 1][MainController.fileToNum(this.file)];
            if(temp != null) {
                //if it's the opposite king
                if(temp instanceof King) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
                //Check down right
                if(this.file != 'h') {
                    temp = MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
                //Check down left
                if(this.file != 'a') {
                    temp = MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 1))];
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
            temp = MainController.board[this.rank][MainController.fileToNum((char) (this.file + 1))];
            //if it's the opposite king
            if(temp instanceof King) {
                //checkmate((char) temp.file, temp.rank);
                return true;
            }
        }
        //Check left
        if(this.file != 'a') {
            temp = MainController.board[this.rank][MainController.fileToNum((char) (this.file - 1))];
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

        //System.out.println("TESTING: King Valid Moves");

        ArrayList<String> result = new ArrayList<String>();
        String move;
        Piece[][] board_copy;
        final boolean side_playing = MainController.white_moves;

        //System.out.println("TESTING: side_playing: MainController.white_moves: " + side_playing);

        //Checking Above
        if(this.rank != 8) {
            //Checking up-center
            if(MainController.board[this.rank + 1][MainController.fileToNum(this.file)] == null) {
                //Making sure it doesn't put itself in check
                board_copy = MainController.copyBoard();
                board_copy[this.rank + 1][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[this.rank + 1][MainController.fileToNum(this.file)].rank = board_copy[this.rank + 1][MainController.fileToNum(this.file)].rank + 1;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum(this.file)].white_side;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);


                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat((this.rank + 1) + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[this.rank + 1][MainController.fileToNum(this.file)].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank + 1][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][MainController.fileToNum(this.file)].rank = board_copy[this.rank + 1][MainController.fileToNum(this.file)].rank + 1;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum(this.file)].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
            }
            //Checking up-right
            if(this.file != 'h') {
                if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 1))] == null) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].rank + 1;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].file + 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char ) (this.file + 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].rank + 1;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].file + 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file + 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 1)).concat((this.rank + 1) + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
            //Checking up-left
            if(this.file != 'a') {
                if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 1))] == null) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].rank + 1;
                    board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].file - 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char ) (this.file - 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].rank + 1;
                        board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].file - 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        MainController.white_moves = board_copy[this.rank + 1][MainController.fileToNum((char) (this.file - 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 1)).concat((this.rank + 1) + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
        }
        //Checking Below
        if(this.rank != 1) {

            //System.out.println("TESTING: Checking valid King moves below");

            //Checking down-center
            if(MainController.board[this.rank - 1][MainController.fileToNum(this.file)] == null) {

                //Making sure it doesn't put itself in check
                board_copy = MainController.copyBoard();
                board_copy[this.rank - 1][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[this.rank - 1][MainController.fileToNum(this.file)].rank = board_copy[this.rank - 1][MainController.fileToNum(this.file)].rank - 1;

                //System.out.println("TESTING: below king: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum(this.file)].white_side;

                //System.out.println("TESTING: below king set: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat((this.rank - 1) + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[this.rank - 1][MainController.fileToNum(this.file)].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank - 1][MainController.fileToNum(this.file)] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][MainController.fileToNum(this.file)].rank = board_copy[this.rank - 1][MainController.fileToNum(this.file)].rank - 1;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum(this.file)].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
            }
            //Checking down-right
            if(this.file != 'h') {
                if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 1))] == null) {

                    //System.out.println("TESTING: Checking valid King moves below - no piece down-right");

                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].rank - 1;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].file + 1);

                    //System.out.println("TESTING: board_copy");
                    //display(board_copy);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].white_side;

                    //System.out.println("TESTING: After setting: WHITE_SIDE = TRUE?: " + MainController.white_moves);
                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        //System.out.println("TESTING: AM I GETTING HERE?????");
                        move = String.valueOf((char ) (this.file + 1)).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].rank - 1;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].file + 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file + 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 1)).concat((this.rank - 1) + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
            //Checking down-left
            if(this.file != 'a') {
                if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 1))] == null) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].rank - 1;
                    board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].file - 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char ) (this.file - 1)).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
                else {
                    if(MainController.board[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = MainController.copyBoard();
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                        board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].rank = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].rank - 1;
                        board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].file - 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        MainController.white_moves = board_copy[this.rank - 1][MainController.fileToNum((char) (this.file - 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                        if(!MainController.putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 1)).concat((this.rank - 1) + "");
                            result.add(move);
                        }
                        MainController.white_moves = side_playing;
                    }
                }
            }
        }
        //Checking Right
        if(this.file != 'h') {
            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + 1))] == null) {
                //Making sure it doesn't put itself in check
                board_copy = MainController.copyBoard();
                board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))].file + 1);

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                MainController.white_moves = board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))].white_side;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf((char) (this.file + 1)).concat(this.rank + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))].file + 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank][MainController.fileToNum((char) (this.file + 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file + 1)).concat(this.rank + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
            }
        }
        //Checking Left
        if(this.file != 'a') {
            if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - 1))] == null) {
                //Making sure it doesn't put itself in check
                board_copy = MainController.copyBoard();
                board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))].file - 1);

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                MainController.white_moves = board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))].white_side;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                if(!MainController.putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf((char) (this.file - 1)).concat(this.rank + "");
                    result.add(move);
                }
                MainController.white_moves = side_playing;
            }
            else {
                if(MainController.board[this.rank][MainController.fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = MainController.copyBoard();
                    board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))] = board_copy[this.rank][MainController.fileToNum(this.file)];
                    board_copy[this.rank][MainController.fileToNum(this.file)] = null;
                    board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))].file - 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    MainController.white_moves = board_copy[this.rank][MainController.fileToNum((char) (this.file - 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + MainController.white_moves);

                    if(!MainController.putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file - 1)).concat(this.rank + "");
                        result.add(move);
                    }
                    MainController.white_moves = side_playing;
                }
            }
        }

        return result;
    }
}
