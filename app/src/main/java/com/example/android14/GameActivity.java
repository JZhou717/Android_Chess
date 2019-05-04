package com.example.android14;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import controller.MainController;
import model.Game;
import model.MainModel;
import model.Piece;


//CURRENT THESE THINGS NEED TO BE DONE

//1. PROMOTION














public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //The textview above the board
    TextView message;

    //The ImageView iv_board that is being displayed
    ImageView[][] iv_board = new ImageView[8][8];
    //The current selected piece
    ImageView image = null;
    //The piece that image represents, is null if not piece in that image
    Piece image_piece = null;


    //Screen Boot-up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MainController.init_board();

        //Linking the items on the XML here
        message = findViewById(R.id.message);
        Button resign_button = findViewById(R.id.resign_button);
        Button draw_button = findViewById(R.id.draw_button);
        Button ai_button = findViewById(R.id.ai_button);
        Button undo_button = findViewById(R.id.undo_button);
        //Setting listeners for them
        resign_button.setOnClickListener(this);
        draw_button.setOnClickListener(this);
        ai_button.setOnClickListener(this);
        undo_button.setOnClickListener(this);

        //For the a file starting from a1 to a8
        iv_board[0][0] = (ImageView) findViewById(R.id.a1);
        iv_board[1][0] = (ImageView) findViewById(R.id.a2);
        iv_board[2][0] = (ImageView) findViewById(R.id.a3);
        iv_board[3][0] = (ImageView) findViewById(R.id.a4);
        iv_board[4][0] = (ImageView) findViewById(R.id.a5);
        iv_board[5][0] = (ImageView) findViewById(R.id.a6);
        iv_board[6][0] = (ImageView) findViewById(R.id.a7);
        iv_board[7][0] = (ImageView) findViewById(R.id.a8);
        //For the b file
        iv_board[0][1] = (ImageView) findViewById(R.id.b1);
        iv_board[1][1] = (ImageView) findViewById(R.id.b2);
        iv_board[2][1] = (ImageView) findViewById(R.id.b3);
        iv_board[3][1] = (ImageView) findViewById(R.id.b4);
        iv_board[4][1] = (ImageView) findViewById(R.id.b5);
        iv_board[5][1] = (ImageView) findViewById(R.id.b6);
        iv_board[6][1] = (ImageView) findViewById(R.id.b7);
        iv_board[7][1] = (ImageView) findViewById(R.id.b8);
        //For the c file
        iv_board[0][2] = (ImageView) findViewById(R.id.c1);
        iv_board[1][2] = (ImageView) findViewById(R.id.c2);
        iv_board[2][2] = (ImageView) findViewById(R.id.c3);
        iv_board[3][2] = (ImageView) findViewById(R.id.c4);
        iv_board[4][2] = (ImageView) findViewById(R.id.c5);
        iv_board[5][2] = (ImageView) findViewById(R.id.c6);
        iv_board[6][2] = (ImageView) findViewById(R.id.c7);
        iv_board[7][2] = (ImageView) findViewById(R.id.c8);
        //For the d file
        iv_board[0][3] = (ImageView) findViewById(R.id.d1);
        iv_board[1][3] = (ImageView) findViewById(R.id.d2);
        iv_board[2][3] = (ImageView) findViewById(R.id.d3);
        iv_board[3][3] = (ImageView) findViewById(R.id.d4);
        iv_board[4][3] = (ImageView) findViewById(R.id.d5);
        iv_board[5][3] = (ImageView) findViewById(R.id.d6);
        iv_board[6][3] = (ImageView) findViewById(R.id.d7);
        iv_board[7][3] = (ImageView) findViewById(R.id.d8);
        //For the e file
        iv_board[0][4] = (ImageView) findViewById(R.id.e1);
        iv_board[1][4] = (ImageView) findViewById(R.id.e2);
        iv_board[2][4] = (ImageView) findViewById(R.id.e3);
        iv_board[3][4] = (ImageView) findViewById(R.id.e4);
        iv_board[4][4] = (ImageView) findViewById(R.id.e5);
        iv_board[5][4] = (ImageView) findViewById(R.id.e6);
        iv_board[6][4] = (ImageView) findViewById(R.id.e7);
        iv_board[7][4] = (ImageView) findViewById(R.id.e8);
        //For the f file
        iv_board[0][5] = (ImageView) findViewById(R.id.f1);
        iv_board[1][5] = (ImageView) findViewById(R.id.f2);
        iv_board[2][5] = (ImageView) findViewById(R.id.f3);
        iv_board[3][5] = (ImageView) findViewById(R.id.f4);
        iv_board[4][5] = (ImageView) findViewById(R.id.f5);
        iv_board[5][5] = (ImageView) findViewById(R.id.f6);
        iv_board[6][5] = (ImageView) findViewById(R.id.f7);
        iv_board[7][5] = (ImageView) findViewById(R.id.f8);
        //For the g file
        iv_board[0][6] = (ImageView) findViewById(R.id.g1);
        iv_board[1][6] = (ImageView) findViewById(R.id.g2);
        iv_board[2][6] = (ImageView) findViewById(R.id.g3);
        iv_board[3][6] = (ImageView) findViewById(R.id.g4);
        iv_board[4][6] = (ImageView) findViewById(R.id.g5);
        iv_board[5][6] = (ImageView) findViewById(R.id.g6);
        iv_board[6][6] = (ImageView) findViewById(R.id.g7);
        iv_board[7][6] = (ImageView) findViewById(R.id.g8);
        //For the h file
        iv_board[0][7] = (ImageView) findViewById(R.id.h1);
        iv_board[1][7] = (ImageView) findViewById(R.id.h2);
        iv_board[2][7] = (ImageView) findViewById(R.id.h3);
        iv_board[3][7] = (ImageView) findViewById(R.id.h4);
        iv_board[4][7] = (ImageView) findViewById(R.id.h5);
        iv_board[5][7] = (ImageView) findViewById(R.id.h6);
        iv_board[6][7] = (ImageView) findViewById(R.id.h7);
        iv_board[7][7] = (ImageView) findViewById(R.id.h8);
        for (int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                set_bg_color(i, j, false);
                /*
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    iv_board[i][j].setBackgroundColor(Color.rgb(50, 205, 50));
                } else {
                    iv_board[i][j].setBackgroundColor(Color.rgb(0, 128, 0));
                }
                 */
            }
        }
        iv_board[0][0].setOnClickListener(this);
        iv_board[1][0].setOnClickListener(this);
        iv_board[2][0].setOnClickListener(this);
        iv_board[3][0].setOnClickListener(this);
        iv_board[4][0].setOnClickListener(this);
        iv_board[5][0].setOnClickListener(this);
        iv_board[6][0].setOnClickListener(this);
        iv_board[7][0].setOnClickListener(this);
        //For the b file
        iv_board[0][1].setOnClickListener(this);
        iv_board[1][1].setOnClickListener(this);
        iv_board[2][1].setOnClickListener(this);
        iv_board[3][1].setOnClickListener(this);
        iv_board[4][1].setOnClickListener(this);
        iv_board[5][1].setOnClickListener(this);
        iv_board[6][1].setOnClickListener(this);
        iv_board[7][1].setOnClickListener(this);
        //For the c file
        iv_board[0][2].setOnClickListener(this);
        iv_board[1][2].setOnClickListener(this);
        iv_board[2][2].setOnClickListener(this);
        iv_board[3][2].setOnClickListener(this);
        iv_board[4][2].setOnClickListener(this);
        iv_board[5][2].setOnClickListener(this);
        iv_board[6][2].setOnClickListener(this);
        iv_board[7][2].setOnClickListener(this);
        //For the d file
        iv_board[0][3].setOnClickListener(this);
        iv_board[1][3].setOnClickListener(this);
        iv_board[2][3].setOnClickListener(this);
        iv_board[3][3].setOnClickListener(this);
        iv_board[4][3].setOnClickListener(this);
        iv_board[5][3].setOnClickListener(this);
        iv_board[6][3].setOnClickListener(this);
        iv_board[7][3].setOnClickListener(this);
        //For the e file
        iv_board[0][4].setOnClickListener(this);
        iv_board[1][4].setOnClickListener(this);
        iv_board[2][4].setOnClickListener(this);
        iv_board[3][4].setOnClickListener(this);
        iv_board[4][4].setOnClickListener(this);
        iv_board[5][4].setOnClickListener(this);
        iv_board[6][4].setOnClickListener(this);
        iv_board[7][4].setOnClickListener(this);
        //For the f file
        iv_board[0][5].setOnClickListener(this);
        iv_board[1][5].setOnClickListener(this);
        iv_board[2][5].setOnClickListener(this);
        iv_board[3][5].setOnClickListener(this);
        iv_board[4][5].setOnClickListener(this);
        iv_board[5][5].setOnClickListener(this);
        iv_board[6][5].setOnClickListener(this);
        iv_board[7][5].setOnClickListener(this);
        //For the g file
        iv_board[0][6].setOnClickListener(this);
        iv_board[1][6].setOnClickListener(this);
        iv_board[2][6].setOnClickListener(this);
        iv_board[3][6].setOnClickListener(this);
        iv_board[4][6].setOnClickListener(this);
        iv_board[5][6].setOnClickListener(this);
        iv_board[6][6].setOnClickListener(this);
        iv_board[7][6].setOnClickListener(this);
        //For the h file
        iv_board[0][7].setOnClickListener(this);
        iv_board[1][7].setOnClickListener(this);
        iv_board[2][7].setOnClickListener(this);
        iv_board[3][7].setOnClickListener(this);
        iv_board[4][7].setOnClickListener(this);
        iv_board[5][7].setOnClickListener(this);
        iv_board[6][7].setOnClickListener(this);
        iv_board[7][7].setOnClickListener(this);
        Calendar c = Calendar.getInstance();

        Game game = new Game(c);
        //THIS IS JUST TO TEST
        game.addMove("e2 e4");
        game.addMove("e7 e6");
        game.addMove("e4 e5");
        game.setName("GameTest");
        MainModel.addGame(game);
    }

    //User Click
    @Override
    public void onClick(View v) {
        //If it is one of the buttons
        if(v instanceof Button) {
            //TESTING
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("The buttons work!");
            builder.setTitle("Button Test");
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        //If it is one of the board squares
        else if(v instanceof ImageView) {
            //Grab the piece that we have selected and its rank and file
            ImageView i = (ImageView) v;
            String pos = i.getResources().getResourceEntryName(i.getId());
            int file = MainController.fileToNum(pos.charAt(0));
            int rank = Character.getNumericValue(pos.charAt(1)) - 1;
            //System.out.println(pos);
            //System.out.println("rank: " + rank);
            //System.out.println(file);


            //If this selection is the same as the previous selection, deselect it
            if(i.equals(image)) {
                deselect(rank, file);
                return;
            }
            else {
                //If this is the first selection
                if(image == null) {
                    //If there is piece there
                    if(find_piece(i) != null) {
                        //If the piece is on the side playing
                        if(find_piece(i).white_side == MainController.white_moves) {
                            set_selected(rank, file);
                            return;
                        }
                    }
                }
                //If this is the second selection
                else {
                    //If trying to move to an empty position
                    if(find_piece(i) == null) {

                        //Test if valid move
                        if(is_valid_move(rank, file)) {

                            //MOVE IT
                            make_move(image, i);
















                            return;

                        }
                        //Not a valid move
                        else {
                            //Deselect previous selection. No longer any selected piece
                            String temp_pos = image.getResources().getResourceEntryName(image.getId());
                            int temp_file = MainController.fileToNum(temp_pos.charAt(0));
                            int temp_rank = Character.getNumericValue(temp_pos.charAt(1)) - 1;
                            deselect(temp_rank, temp_file);
                            return;
                        }

                    }
                    //Trying to move to a nonempty position
                    //The piece is on the same side
                    if(find_piece(i).white_side == MainController.white_moves) {
                        //Deselect previous selection and select this piece instead
                        String temp_pos = image.getResources().getResourceEntryName(image.getId());
                        int temp_file = MainController.fileToNum(temp_pos.charAt(0));
                        int temp_rank = Character.getNumericValue(temp_pos.charAt(1)) - 1;
                        deselect(temp_rank, temp_file);

                        set_selected(rank, file);
                        return;
                    }
                    //The piece is on the other side
                    else {
//Test if valid move
                        if(is_valid_move(rank, file)) {

                            //MOVE IT
                            iv_board[rank][file].setBackgroundColor(000000);




















                            return;

                        }
                        //Not a valid move
                        else {
                            //Deselect previous selection. No longer any selected piece
                            String temp_pos = image.getResources().getResourceEntryName(image.getId());
                            int temp_file = MainController.fileToNum(temp_pos.charAt(0));
                            int temp_rank = Character.getNumericValue(temp_pos.charAt(1)) - 1;
                            deselect(temp_rank, temp_file);
                            return;
                        }
                    }
                }
            }

            //What we need to do

            //On second selection
                //Check to see if that position is a valid move for the current piece
                    //Move there, update controller board, update white_moves, update message on top


        }
    }

    //Makes a move after it has been validated
    public void make_move(ImageView from_pos, ImageView to_pos) {

        //Get the position to move to in the form of FileRank
        String move_to = get_move_from_pos(to_pos);
        System.out.println(move_to);

        //Move on MainController.board


        //Update imageview board to match MainController.board
        sync_boards();
        //Switch sides
        switch_sides();

    }

    //Changes the current player and the message
    public void switch_sides() {
        MainController.white_moves = !MainController.white_moves;

        //Set the message
        //If it is white's turn
        if(MainController.white_moves) {
            MainController.white_enpassant = 0;
            message.setText("White Moves: ");
        }
        else {
            MainController.black_enpassant = 0;
            message.setText("Black Moves: ");
        }

    }

    public void sync_boards() {

        //For all the ranks
        for(int r = 0; r < 8; r++) {
            //For all the files
            for(int f = 0; f < 8; f++) {
                //If there is a piece there
                if(MainController.board[r][f] != null) {

                    Piece temp = MainController.board[r][f];
                    //Find the name of the piece there
                    String temp_name = temp.name;

                    //If white rook
                    if(temp_name.equalsIgnoreCase("wR")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "wr", null, null));
                    }
                    //Else if white knight
                    else if(temp_name.equalsIgnoreCase("wN")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "wn", null, null));
                    }
                    //Else if white bishop
                    else if(temp_name.equalsIgnoreCase("wB")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "wb", null, null));
                    }
                    //Else if white queen
                    else if(temp_name.equalsIgnoreCase("wQ")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "wq", null, null));
                    }
                    //Else if white king
                    else if(temp_name.equalsIgnoreCase("wK")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "wk", null, null));
                    }
                    //Else if white pawn
                    else if(temp_name.equalsIgnoreCase("wp")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "wp", null, null));
                    }
                    //Else if white rook
                    else if(temp_name.equalsIgnoreCase("bR")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "br", null, null));
                    }
                    //Else if black knight
                    else if(temp_name.equalsIgnoreCase("bN")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "bn", null, null));
                    }
                    //Else if black bishop
                    else if(temp_name.equalsIgnoreCase("bB")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "bb", null, null));
                    }
                    //Else if black queen
                    else if(temp_name.equalsIgnoreCase("bQ")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "bq", null, null));
                    }
                    //Else if black king
                    else if(temp_name.equalsIgnoreCase("bK")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "bk", null, null));
                    }
                    //Else if black pawn
                    else if(temp_name.equalsIgnoreCase("bp")) {
                        iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "bp", null, null));
                    }
                    else {
                        //Something went wrong
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Something went wrong with matching a piece to its image. FIND MEEEEEEEEEE");
                        builder.setTitle("TESTING ERROR REMOVE LATER");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
                //There is no piece there
                else {
                    iv_board[r][f].setImageResource(getResources().getIdentifier("Android14:drawable/" + "tran40", null, null));
                }
            }
        }


    }

    //Returns true if the given rank and file is a valid position for the previously selected piece to move to
    public boolean is_valid_move(int rank, int file) {

        String move_to = get_move_from_pos(rank, file);

        ArrayList<String> move_list = find_piece(image).allValidMoves();

        for(int i = 0; i < move_list.size(); i++) {
            if(move_to.equalsIgnoreCase(move_list.get(i))) {
                return true;
            }
        }
        return false;
    }

    //returns a two character string with the move indicated by the given rank and file positions
    public String get_move_from_pos(int rank, int file) {
        String ret;
        ret = Character.toString(MainController.numToFile(file));
        ret += rank;
        return ret;
    }

    //Returns a two character string with the move indicated by the position of the image view
    public String get_move_from_pos(ImageView iv) {
        String ret;

        String pos = iv.getResources().getResourceEntryName(iv.getId());
        int file = MainController.fileToNum(pos.charAt(0));
        int rank = Character.getNumericValue(pos.charAt(1)) - 1;

        ret = Character.toString(MainController.numToFile(file));
        ret += rank;
        return ret;
    }

    //Find the piece associated with that imageview
    public Piece find_piece(ImageView iv) {
        String pos = iv.getResources().getResourceEntryName(iv.getId());
        int file = MainController.fileToNum(pos.charAt(0));
        int rank = Character.getNumericValue(pos.charAt(1)) - 1;
        return MainController.board[rank][file];
    }

    public void deselect(int rank, int file) {
        image = null;
        image_piece = null;
        set_bg_color(rank, file, false);
    }

    public void set_selected(int rank, int file) {
        image = iv_board[rank][file];
        image_piece = MainController.board[rank + 1][file];
        set_bg_color(rank, file, true);
    }

    public void set_bg_color(int rank, int file, boolean is_selected) {
        if(is_selected) {
            iv_board[rank][file].setBackgroundColor(Color.rgb(100, 100, 230));
        }
        else {
            if (rank % 2 != file % 2 ) {
                iv_board[rank][file].setBackgroundColor(Color.rgb(50, 205, 50));
            } else {
                iv_board[rank][file].setBackgroundColor(Color.rgb(0, 128, 0));
            }
        }
    }
}
