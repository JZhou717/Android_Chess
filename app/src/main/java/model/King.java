package model;

import java.util.ArrayList;

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

    /**
     * Kings may move to any space adjacent to any space adjacent to them as long as there is not a piece of the same side in that position and the move will not place it in check. Kings may also castle if they have not yet moved, have a clear path to one of the rooks, and that rook has not moved. King's move method is the only move method that moves another Piece, which is the Rook that is moved during castling. A King may not castle if it moves through a check or if it will be in check after the castling. If a move is valid, this piece's position is changed in the global board and its own file and rank fields are updated
     *
     * <p>King's move method does not call {@link #putsOwnKingInCheck(Piece[][]) putsOwnKingInCheck} since it was written before that method existed. Instead, each move is checked to ensure it does not place this King in check in the move method itself.
     *
     * @author Jake
     * @param move_to a two part String with the file and the rank that they are to move to
     * @throws IllegalArgumentException if the move_to position is not valid
     */
    public void move(String move_to)  throws IllegalArgumentException{

        //Trying to move opponent's piece
        if(this.white_side != white_moves) {
            throw new IllegalArgumentException();
        }

        char move_file = move_to.toLowerCase().charAt(0);
        int move_rank = Character.getNumericValue(move_to.charAt(1));

        //If trying to move to the same spot
        if(move_file == this.file && move_rank == this.rank) {
            throw new IllegalArgumentException();
        } //Trying to move more than two squares away
        else if(Math.abs(move_rank - this.rank) + Math.abs(fileToNum(move_file) - fileToNum(this.file)) > 2) {
            throw new IllegalArgumentException();
        } //Valid move
        else {

            //Creating a copy of the board to brute force test if this move will put King in check
            Piece[][] board_copy = copyBoard();

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
                        if(board[1][fileToNum('h')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!board[1][fileToNum('h')].name.equals("wR")) {
                            //System.out.println("TESTING: NAME: " + board[1][fileToNum('h')].name);
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) board[1][fileToNum('h')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(board[this.rank][fileToNum((char) (this.file + i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for f1 space
                        board_copy[1][fileToNum('f')] = board_copy[1][fileToNum('e')];
                        board_copy[1][fileToNum('e')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for g1 space
                        board_copy[1][fileToNum('g')] = board_copy[1][fileToNum('f')];
                        board_copy[1][fileToNum('f')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                        board[this.rank][fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        board[1][fileToNum('f')] = board[1][fileToNum('h')];
                        board[1][fileToNum('h')] = null;
                        board[1][fileToNum('f')].rank = 1;
                        board[1][fileToNum('f')].file = 'f';
                        ((Rook) board[1][fileToNum('f')]).has_moved = true;
                        checkForCheck(board);
                        return;
                    } //Trying to castle queenside
                    else if(move_to.equals("c1")) {
                        //If this king has already moved
                        if(this.has_moved) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if Queen Rook Moved
                        if(board[1][fileToNum('a')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!board[1][fileToNum('a')].name.equals("wR")) {
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) board[1][fileToNum('a')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(board[this.rank][fileToNum((char) (this.file - i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for d1 space
                        board_copy[1][fileToNum('d')] = board_copy[1][fileToNum('e')];
                        board_copy[1][fileToNum('e')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for c1 space
                        board_copy[1][fileToNum('c')] = board_copy[1][fileToNum('d')];
                        board_copy[1][fileToNum('d')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                        board[this.rank][fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        board[1][fileToNum('d')] = board[1][fileToNum('a')];
                        board[1][fileToNum('a')] = null;
                        board[1][fileToNum('d')].rank = 1;
                        board[1][fileToNum('d')].file = 'd';
                        ((Rook) board[1][fileToNum('d')]).has_moved = true;
                        checkForCheck(board);
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
                        if(board[8][fileToNum('h')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!board[8][fileToNum('h')].name.equals("bR")) {
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) board[8][fileToNum('h')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(board[this.rank][fileToNum((char) (this.file + i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for f8 space
                        board_copy[8][fileToNum('f')] = board_copy[8][fileToNum('e')];
                        board_copy[8][fileToNum('e')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for g1 space
                        board_copy[8][fileToNum('g')] = board_copy[1][fileToNum('f')];
                        board_copy[8][fileToNum('f')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                        board[this.rank][fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        board[8][fileToNum('f')] = board[8][fileToNum('h')];
                        board[8][fileToNum('h')] = null;
                        board[8][fileToNum('f')].rank = 8;
                        board[8][fileToNum('f')].file = 'f';
                        ((Rook) board[8][fileToNum('f')]).has_moved = true;
                        checkForCheck(board);
                        return;
                    } //Trying to castle queenside
                    else if(move_to.equals("c8")) {
                        //If this king has already moved
                        if(this.has_moved) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if Queen Rook Moved
                        if(board[8][fileToNum('a')] == null) {
                            throw new IllegalArgumentException();
                        }
                        if(!board[8][fileToNum('a')].name.equals("bR")) {
                            throw new IllegalArgumentException();
                        }
                        if(((Rook) board[8][fileToNum('a')]).has_moved == true) {
                            throw new IllegalArgumentException();
                        }
                        //Checking if path clear
                        for(int i = 1; i < 3; i++) {
                            if(board[this.rank][fileToNum((char) (this.file - i))] != null) {
                                throw new IllegalArgumentException();
                            }
                        }
                        //Making sure King doesn't pass through check
                        //Checking for d8 space
                        board_copy[8][fileToNum('d')] = board_copy[8][fileToNum('e')];
                        board_copy[8][fileToNum('e')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Checking for c8 space
                        board_copy[8][fileToNum('c')] = board_copy[8][fileToNum('d')];
                        board_copy[8][fileToNum('d')] = null;
                        if(putsOwnKingInCheck(board_copy)) {
                            throw new IllegalArgumentException();
                        }
                        //Moving King
                        board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
                        board[this.rank][fileToNum(this.file)] = null;
                        this.rank = move_rank;
                        this.file = move_file;
                        this.has_moved = true;
                        //Moving Rook
                        board[8][fileToNum('d')] = board[8][fileToNum('a')];
                        board[8][fileToNum('a')] = null;
                        board[8][fileToNum('d')].rank = 8;
                        board[8][fileToNum('d')].file = 'd';
                        ((Rook) board[8][fileToNum('d')]).has_moved = true;
                        checkForCheck(board);
                        return;
                    } //Invalid move
                    else {
                        throw new IllegalArgumentException();
                    }
                }

            }

            //Checking to see if path clear
            if(board[move_rank][fileToNum(move_file)] != null) {
                //Checking if same side
                if(board[move_rank][fileToNum(move_file)].white_side == this.white_side) {
                    throw new IllegalArgumentException();
                }
            }
            //Moving on the board copy
            board_copy[move_rank][fileToNum(move_file)] = board_copy[this.rank][fileToNum(this.file)];
            board_copy[this.rank][fileToNum(this.file)] = null;
            //Checking for checks
            if(putsOwnKingInCheck(board_copy)) {
                throw new IllegalArgumentException();
            }
            //Moving to position
            board[move_rank][fileToNum(move_file)] = board[this.rank][fileToNum(this.file)];
            board[this.rank][fileToNum(this.file)] = null;
            this.rank = move_rank;
            this.file = move_file;
            this.has_moved = true;
            checkForCheck(board);
            return;
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

        //Check above
        if(this.rank != 8) {

            //Check up center
            temp = board[this.rank + 1][fileToNum(this.file)];
            if(temp != null) {
                //if it's the opposite king
                if(temp instanceof King) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
                //Check up right
                if(this.file != 'h') {
                    temp = board[this.rank + 1][fileToNum((char) (this.file + 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
                //Check up left
                if(this.file != 'a') {
                    temp = board[this.rank + 1][fileToNum((char) (this.file - 1))];
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
            temp = board[this.rank - 1][fileToNum(this.file)];
            if(temp != null) {
                //if it's the opposite king
                if(temp instanceof King) {
                    //checkmate((char) temp.file, temp.rank);
                    return true;
                }
                //Check down right
                if(this.file != 'h') {
                    temp = board[this.rank - 1][fileToNum((char) (this.file + 1))];
                    //if it's the opposite king
                    if(temp instanceof King) {
                        //checkmate((char) temp.file, temp.rank);
                        return true;
                    }
                }
                //Check down left
                if(this.file != 'a') {
                    temp = board[this.rank - 1][fileToNum((char) (this.file - 1))];
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
            temp = board[this.rank][fileToNum((char) (this.file + 1))];
            //if it's the opposite king
            if(temp instanceof King) {
                //checkmate((char) temp.file, temp.rank);
                return true;
            }
        }
        //Check left
        if(this.file != 'a') {
            temp = board[this.rank][fileToNum((char) (this.file - 1))];
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
        final boolean side_playing = white_moves;

        //System.out.println("TESTING: side_playing: white_moves: " + side_playing);

        //Checking Above
        if(this.rank != 8) {
            //Checking up-center
            if(board[this.rank + 1][fileToNum(this.file)] == null) {
                //Making sure it doesn't put itself in check
                board_copy = copyBoard();
                board_copy[this.rank + 1][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank + 1][fileToNum(this.file)].rank = board_copy[this.rank + 1][fileToNum(this.file)].rank + 1;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                white_moves = board_copy[this.rank + 1][fileToNum(this.file)].white_side;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);


                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat((this.rank + 1) + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[this.rank + 1][fileToNum(this.file)].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank + 1][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][fileToNum(this.file)].rank = board_copy[this.rank + 1][fileToNum(this.file)].rank + 1;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank + 1][fileToNum(this.file)].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
            }
            //Checking up-right
            if(this.file != 'h') {
                if(board[this.rank + 1][fileToNum((char) (this.file + 1))] == null) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank + 1][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].rank + 1;
                    board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].file + 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char ) (this.file + 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                else {
                    if(board[this.rank + 1][fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = copyBoard();
                        board_copy[this.rank + 1][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                        board_copy[this.rank][fileToNum(this.file)] = null;
                        board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].rank = board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].rank + 1;
                        board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].file + 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        white_moves = board_copy[this.rank + 1][fileToNum((char) (this.file + 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        if(!putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 1)).concat((this.rank + 1) + "");
                            result.add(move);
                        }
                        white_moves = side_playing;
                    }
                }
            }
            //Checking up-left
            if(this.file != 'a') {
                if(board[this.rank + 1][fileToNum((char) (this.file - 1))] == null) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank + 1][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].rank + 1;
                    board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].file - 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char ) (this.file - 1)).concat((this.rank + 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                else {
                    if(board[this.rank + 1][fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = copyBoard();
                        board_copy[this.rank + 1][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                        board_copy[this.rank][fileToNum(this.file)] = null;
                        board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].rank = board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].rank + 1;
                        board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].file - 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        white_moves = board_copy[this.rank + 1][fileToNum((char) (this.file - 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        if(!putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 1)).concat((this.rank + 1) + "");
                            result.add(move);
                        }
                        white_moves = side_playing;
                    }
                }
            }
        }
        //Checking Below
        if(this.rank != 1) {

            //System.out.println("TESTING: Checking valid King moves below");

            //Checking down-center
            if(board[this.rank - 1][fileToNum(this.file)] == null) {

                //Making sure it doesn't put itself in check
                board_copy = copyBoard();
                board_copy[this.rank - 1][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank - 1][fileToNum(this.file)].rank = board_copy[this.rank - 1][fileToNum(this.file)].rank - 1;

                //System.out.println("TESTING: below king: WHITE_SIDE = TRUE?: " + white_moves);

                white_moves = board_copy[this.rank - 1][fileToNum(this.file)].white_side;

                //System.out.println("TESTING: below king set: WHITE_SIDE = TRUE?: " + white_moves);

                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf(this.file).concat((this.rank - 1) + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[this.rank - 1][fileToNum(this.file)].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank - 1][fileToNum(this.file)] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][fileToNum(this.file)].rank = board_copy[this.rank - 1][fileToNum(this.file)].rank - 1;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank - 1][fileToNum(this.file)].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf(this.file).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
            }
            //Checking down-right
            if(this.file != 'h') {
                if(board[this.rank - 1][fileToNum((char) (this.file + 1))] == null) {

                    //System.out.println("TESTING: Checking valid King moves below - no piece down-right");

                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank - 1][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].rank = board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].rank - 1;
                    board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].file + 1);

                    //System.out.println("TESTING: BOARD_COPY");
                    //display(board_copy);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].white_side;

                    //System.out.println("TESTING: After setting: WHITE_SIDE = TRUE?: " + white_moves);
                    if(!putsOwnKingInCheck(board_copy)) {
                        //System.out.println("TESTING: AM I GETTING HERE?????");
                        move = String.valueOf((char ) (this.file + 1)).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                else {
                    if(board[this.rank - 1][fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = copyBoard();
                        board_copy[this.rank - 1][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                        board_copy[this.rank][fileToNum(this.file)] = null;
                        board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].rank = board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].rank - 1;
                        board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].file + 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        white_moves = board_copy[this.rank - 1][fileToNum((char) (this.file + 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        if(!putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file + 1)).concat((this.rank - 1) + "");
                            result.add(move);
                        }
                        white_moves = side_playing;
                    }
                }
            }
            //Checking down-left
            if(this.file != 'a') {
                if(board[this.rank - 1][fileToNum((char) (this.file - 1))] == null) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank - 1][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].rank = board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].rank - 1;
                    board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].file - 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char ) (this.file - 1)).concat((this.rank - 1) + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
                else {
                    if(board[this.rank - 1][fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                        //Making sure it doesn't put itself in check
                        board_copy = copyBoard();
                        board_copy[this.rank - 1][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                        board_copy[this.rank][fileToNum(this.file)] = null;
                        board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].rank = board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].rank - 1;
                        board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].file - 1);

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        white_moves = board_copy[this.rank - 1][fileToNum((char) (this.file - 1))].white_side;

                        //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                        if(!putsOwnKingInCheck(board_copy)) {
                            move = String.valueOf((char) (this.file - 1)).concat((this.rank - 1) + "");
                            result.add(move);
                        }
                        white_moves = side_playing;
                    }
                }
            }
        }
        //Checking Right
        if(this.file != 'h') {
            if(board[this.rank][fileToNum((char) (this.file + 1))] == null) {
                //Making sure it doesn't put itself in check
                board_copy = copyBoard();
                board_copy[this.rank][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank][fileToNum((char) (this.file + 1))].file + 1);

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                white_moves = board_copy[this.rank][fileToNum((char) (this.file + 1))].white_side;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf((char) (this.file + 1)).concat(this.rank + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[this.rank][fileToNum((char) (this.file + 1))].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank][fileToNum((char) (this.file + 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank][fileToNum((char) (this.file + 1))].file = (char) (board_copy[this.rank][fileToNum((char) (this.file + 1))].file + 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank][fileToNum((char) (this.file + 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file + 1)).concat(this.rank + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
            }
        }
        //Checking Left
        if(this.file != 'a') {
            if(board[this.rank][fileToNum((char) (this.file - 1))] == null) {
                //Making sure it doesn't put itself in check
                board_copy = copyBoard();
                board_copy[this.rank][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                board_copy[this.rank][fileToNum(this.file)] = null;
                board_copy[this.rank][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank][fileToNum((char) (this.file - 1))].file - 1);

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                white_moves = board_copy[this.rank][fileToNum((char) (this.file - 1))].white_side;

                //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                if(!putsOwnKingInCheck(board_copy)) {
                    move = String.valueOf((char) (this.file - 1)).concat(this.rank + "");
                    result.add(move);
                }
                white_moves = side_playing;
            }
            else {
                if(board[this.rank][fileToNum((char) (this.file - 1))].white_side != this.white_side) {
                    //Making sure it doesn't put itself in check
                    board_copy = copyBoard();
                    board_copy[this.rank][fileToNum((char) (this.file - 1))] = board_copy[this.rank][fileToNum(this.file)];
                    board_copy[this.rank][fileToNum(this.file)] = null;
                    board_copy[this.rank][fileToNum((char) (this.file - 1))].file = (char) (board_copy[this.rank][fileToNum((char) (this.file - 1))].file - 1);

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    white_moves = board_copy[this.rank][fileToNum((char) (this.file - 1))].white_side;

                    //System.out.println("TESTING: WHITE_SIDE = TRUE?: " + white_moves);

                    if(!putsOwnKingInCheck(board_copy)) {
                        move = String.valueOf((char) (this.file - 1)).concat(this.rank + "");
                        result.add(move);
                    }
                    white_moves = side_playing;
                }
            }
        }

        return result;
    }
}
