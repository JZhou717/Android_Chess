package controller;

import java.util.ArrayList;

import model.Bishop;
import model.Black_Pawn;
import model.King;
import model.Knight;
import model.Piece;
import model.Queen;
import model.Rook;
import model.White_Pawn;

public class MainController {

    public static final int NO_CHECK = 0;
    public static final int CHECK = 1;
    public static final int CHECKMATE = 2;

    /**
     * board is the data structure that we will be using. It is a 9x8 2D Array of Pieces.
     * row[0] is ignored so that we do not need to convert the input ranks that range from 1-8 to 0-7 to minimize confusion while coding.
     * Blank spots are by default NULL while occupied positions are pointers to concrete implementations of the abstract Piece class.
     *
     * @author Jake
     *
     */
    public static Piece[][] board = new Piece[8][8];
    public static Piece[][] prevBoard = new Piece[8][8];

    /**
     * white_moves is a flag that is true if it is white's turn, false if it is black's turn. white_moves always starts as true and is reversed after a valid move has been committed.
     * white_enpassant is usually 0 unless white had just taken a pawn double step as their last move, in which case the variable stores the file that pawn the pawn moved in
     * black_enpassant is the same for black
     *
     * @author Jake
     *
     */
    public static boolean white_moves = true;
    public static char white_enpassant = 0;
    public static char black_enpassant = 0;

    public static void init_board() {
        //Initializing white pieces
        board[0][0] = new Rook('a', 0);
        board[0][1] = new Knight('b', 0);
        board[0][2] = new Bishop('c', 0);
        board[0][3] = new Queen('d', 0);
        board[0][4] = new King('e', 0);
        board[0][5] = new Bishop('f', 0);
        board[0][6] = new Knight('g', 0);
        board[0][7] = new Rook('h', 0);
        for(int j = 0; j < 8; j++) {
            Piece piece = board[0][j];
            piece.name = "w" + piece.name;
            piece.white_side = true;
        }
        for(int j = 0; j < 8; j++) {
            board[1][j] = new White_Pawn(numToFile(j), 1);
            board[1][j].white_side = true;
        }

        //Initializing black pieces
        board[7][0] = new Rook('a', 7);
        board[7][1] = new Knight('b', 7);
        board[7][2] = new Bishop('c', 7);
        board[7][3] = new Queen('d', 7);
        board[7][4] = new King('e', 7);
        board[7][5] = new Bishop('f', 7);
        board[7][6] = new Knight('g', 7);
        board[7][7] = new Rook('h', 7);
        for(int j = 0; j < 8; j++) {
            Piece piece = board[7][j];
            piece.name = "b" + piece.name;
            piece.white_side = false;
        }
        for(int j = 0; j < 8; j++) {
            board[6][j] = new Black_Pawn(numToFile(j), 6);
            board[6][j].white_side = false;
        }
        for (int i=2;i<6;i++){
            for (int j=0;j<8;j++){
                board[i][j] = null;
            }
        }
        white_moves = true;
    }

    /**
     * This helper method takes in the character for the file from the input and returns a number 0-7 correlating with the file. Useful when referencing a position in the board 2D array
     *
     * @author Jake
     * @param file - a character that is denotes the file of the board ranging from a-h
     * @return int value between 0-7 associated with file character if successful.
     * @throws IllegalArgumentException if the input is not a char value from a-h
     */
    public static int fileToNum(char file) throws IllegalArgumentException{
        switch (file) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default: throw new IllegalArgumentException();
        }

    }

    /**
     * This helper method takes an int and returns a char for the corresponding file. Useful when assigning file value to a piece
     *
     * @author Jake
     * @param file - an int value associated with a column of the board 2D array ranging from 0-7
     * @return char value of the file name ranging from a-h
     * @throws IllegalArgumentException if input is not int between 0-7
     */
    public static char numToFile(int file) throws IllegalArgumentException{
        switch (file) {
            case 0: return 'a';
            case 1: return 'b';
            case 2: return 'c';
            case 3: return 'd';
            case 4: return 'e';
            case 5: return 'f';
            case 6: return 'g';
            case 7: return 'h';
            default: throw new IllegalArgumentException("Invalid File Num to convert to Letter");
        }

    }

    /**
     * copyboard creates a copy of the current global board. All moves are first made on the copied board where they are ran through the {@link #putsOwnKingInCheck(Piece[][]) putsOwnKingInCheck} method that runs through the copied board to see if any of the opposing pieces are checking the current side's King.
     *
     * @author Jake
     * @return a copy of the global board
     */
    public static Piece[][] copyBoard() {
        Piece[][] board_copy = new Piece[8][8];
        Piece original;
        Piece copy;

        for(int r = 0; r < 8; r++) {
            for(int f = 0; f < 8; f++) {
                if(board[r][f] != null) {
                    //Create a copy of the piece there
                    original = board[r][f];
                    //If the piece is a White_Pawn
                    if(original.name.equals("wp")) {
                        copy = new White_Pawn(original.file, original.rank);
                        copy.white_side = original.white_side;
                    }//If the piece is a Black_Pawn
                    else if(original.name.equals("bp")) {
                        copy = new Black_Pawn(original.file, original.rank);
                        copy.white_side = original.white_side;
                    }//If the piece is a rook
                    else if(original.name.charAt(1) == 'R') {
                        copy = new Rook(original.file, original.rank);
                        copy.name = original.name;
                        copy.white_side = original.white_side;
                        ((Rook) copy).has_moved = ((Rook) original).has_moved;
                    }//If the piece is a knight
                    else if(original.name.charAt(1) == 'N') {
                        copy = new Knight(original.file, original.rank);
                        copy.name = original.name;
                        copy.white_side = original.white_side;
                    }//If the piece is a bishop
                    else if(original.name.charAt(1) == 'B') {
                        copy = new Bishop(original.file, original.rank);
                        copy.name = original.name;
                        copy.white_side = original.white_side;
                    }//If the piece is a Queen
                    else if(original.name.charAt(1) == 'Q') {
                        copy = new Queen(original.file, original.rank);
                        copy.name = original.name;
                        copy.white_side = original.white_side;
                    }//The piece is a King
                    else {
                        copy = new King(original.file, original.rank);
                        copy.name = original.name;
                        copy.white_side = original.white_side;
                        ((King) copy).has_moved = ((King) original).has_moved;
                    }

                    board_copy[r][f] = copy;

                }else if(board[r][f]==null){
                    board_copy[r][f]=null;
                }


            }
        }

        return board_copy;
    }

    /**
     * putsOwnKingInCheck checks to see if the current side playing, indicated by the global variable white_moves, is in check. This method is called before any move is committed since players are not allowed to place their own King in check with a move. This method is also called by every pieces' allValidMoves method that finds all of that piece instance's valid moves. These moves are first checked to ensure they do not place the piece's own King in check before they are considered valid.
     *
     * @author Jake
     * @param board_copy - a copy of the global board created by {@link #copyBoard()} after a move has been made on the board_copy
     * @return returns true if the input board has the current side's King in chess, meaning the last move was illegal, otherwise returns false
     */
    public static boolean putsOwnKingInCheck(Piece[][] board_copy) {

        Piece temp;

        //Going through all the ranks
        for(int r = 0; r < 8; r++) {
            //Going through all the files
            for(int f = 0; f < 8; f++) {
                //If there is a piece in this spot
                if(board_copy[r][f] != null) {
                    temp = board_copy[r][f];

                    //If the piece is not the current side playing
                    if(temp.white_side != white_moves) {
                        if(temp.check(board_copy)) {
                            //if the move in the input board has current side's King in check
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Tests if the move puts the same side's king in check
     * Inputs: from_rank, from_file,
     */
    public static boolean move_causes_own_check(int from_rank, char from_file, int to_rank, char to_file) {

        //True if the piece in the from position is white side, false if black side
        boolean white_playing = board[from_rank][fileToNum(from_file)].white_side;

        //Create a copy of the board
        Piece[][] board_copy =  copyBoard();

        //Moving the piece on the copy to test
        board_copy[to_rank][fileToNum(to_file)] = board_copy[from_rank][fileToNum(from_file)];
            //Setting the piece's position to where it now is
            board_copy[to_rank][fileToNum(to_file)].rank = to_rank;
            board_copy[to_rank][fileToNum(to_file)].file = to_file;
        board_copy[from_rank][fileToNum(from_file)] = null;

        //Testing the board with the move implemented to see if it puts the king in check
        Piece temp;
        //Going through all the ranks
        for(int r = 0; r < 8; r++) {
            //Going through all the files
            for(int f = 0; f < 8; f++) {
                //If there is a piece in this spot
                if(board_copy[r][f] != null) {
                    temp = board_copy[r][f];
                    //If the piece is on the other side
                    if(temp.white_side != white_playing) {

                        if(temp.check(board_copy)) {
                            //The piece does place the King in check
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * checkForCheck runs through the entire board and finds all pieces of the side playing and sees if they are currently checking the opponent's King. If so, calls checkmate, otherwise, we continue normally
     *
     * @author Jake
     * @param board the board that is being checked for checks on the opponent King. Can be the global board or a temporary board used for {@link #putsOwnKingInCheck(Piece[][]) putsOwnKingInCheck} for instance
     */
    public static int checkForCheck(Piece[][] board) {

        Piece temp;

        int ret = NO_CHECK;

        //Going through all the ranks
        for(int r = 0; r < 8; r++) {
            //Going through all the files
            for(int f = 0; f < 8; f++) {
                //If there is a piece in this spot
                if(board[r][f] != null) {
                    temp = board[r][f];

                    //If the piece is on the current side playing
                    if(temp.white_side == white_moves) {

                        if(temp.check(board)) {
                            //There is a checkmate
                            if(checkmate()) {
                                return CHECKMATE;
                            }
                            //There is only a check
                            else {
                                return CHECK;
                            }
                        }
                    }
                }
            }
        }

        return ret;

    }

    /**
     * checkmate checks to see if the opposing king, whose white_side variable should be the opposite value of the global {@link #white_moves} variable, is in checkmate or just a check. This method is ran by each piece's check function upon finding a check on the opponent side's King
     *
     * <p>This method goes through the global board and finds all pieces on the opposite side of the current side playing and checks for any valid moves that puts their King out of check
     *
     * <p>If a valid move is found, the method prints Check and returns. If no valid move is found, the method prints Checkmate, indicates the winning side, and exists the program.
     *
     * @author Jake
     */
    public static boolean checkmate() {

        Piece temp;
        ArrayList<String> tempMoves;

        //Going through all the ranks
        for(int r = 0; r < 8; r++) {
            //Going through all the files
            for(int f = 0; f < 8; f++) {
                //Checking to see if there is a piece in this spot
                if(board[r][f] != null) {
                    temp = board[r][f];
                    //If the piece is on the opponent side
                    if(temp.white_side != white_moves) {

                        //Grab their valid moves
                        tempMoves = temp.allValidMoves();

                        //Go through all their valid moves
                        for(int i = 0; i < tempMoves.size(); i++) {

                            char move_file = tempMoves.get(i).charAt(0);
                            int move_rank = Character.getNumericValue(tempMoves.get(i).charAt(1));

                            //Testing the valid move to see if it places the king out of check
                            if(!move_causes_own_check(r, numToFile(f), move_rank, move_file)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        //No valid moves that places the king out of check
        return true;
    }

    /**
     * Stalemate goes through the entire global board and finds pieces of the side that is currently playing, which are the pieces whose white_side variables are equal to the global {@link #white_moves} variable, and tests to see if those pieces have a valid move. Upon the first piece that has a valid move, the method is returned out of. If the entire board has been iterated through and no pieces of the playing side has a valid move, there is a stalemate and the proper messages a printed and the program exited. This method is called at the beginning of every turn.
     *
     * @author Jake
     */
    public static boolean stalemate() {

        Piece temp;
        ArrayList<String> tempMoves;

        //Going through all the ranks
        for(int r = 0; r < 8; r++) {
            //Going through all the files
            for(int f = 0; f < 8; f++) {
                //If there is a piece in this spot
                if(board[r][f] != null) {
                    temp = board[r][f];
                    //If the piece is on the current side
                    if(temp.white_side == white_moves) {

                        //Get all its valid moves
                        tempMoves = temp.allValidMoves();

                        //If there is a valid move
                        if(tempMoves.size() != 0) {
                            //There is no stalemate
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
