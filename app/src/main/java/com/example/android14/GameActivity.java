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

import java.util.Calendar;

import controller.MainController;
import model.Game;
import model.MainModel;
import model.Piece;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

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

        //Linking the items on the XML here
        TextView message = findViewById(R.id.message);
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
                /**
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
                    //If the piece is on the side playing
                    if(find_piece(i).white_side == MainController.white_moves) {
                        set_selected(rank, file);
                        return;
                    }
                }

                //set_selected(rank, file);
            }







            //What we need to do

            //On first selection
                //Check if there is a piece there
                    //Check if that piece is on the side playing
            //On second selection
                //Check to see if that position is a valid move for the current piece
                    //Move there, update controller board, update white_moves, update message on top




        }
    }

    //Find the piece associated with that imageview
    public Piece find_piece(ImageView iv) {
        String pos = iv.getResources().getResourceEntryName(iv.getId());
        int file = MainController.fileToNum(pos.charAt(0));
        int rank = Character.getNumericValue(pos.charAt(1)) - 1;
        return MainController.board[rank + 1][file];
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
