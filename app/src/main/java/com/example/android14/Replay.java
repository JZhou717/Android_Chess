package com.example.android14;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.widget.TextView;

import java.util.ArrayList;

import controller.MainController;
import model.Piece;

public class Replay extends AppCompatActivity implements View.OnClickListener {
    TextView player;
    TextView message;
    ImageView[][] iv_board = new ImageView[8][8];
    int count = 0;
    ArrayList<String> moves = new ArrayList<String>();
    Drawable tran;
    Button back_button;
    //Screen Boot-up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        MainController.init_board();
        //Linking the items on the XML here

        Button next_button = findViewById(R.id.next_button);
        back_button = findViewById(R.id.back_button);
        player = findViewById(R.id.player);
        message = findViewById(R.id.message);
        next_button.setOnClickListener(this);
        back_button.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int size = bundle.getInt("Size");
            for (int i = 0; i < size; i++) {
                moves.add(bundle.getString("" + i));
            }
        }


        //For the a file starting from a1 to a8
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    iv_board[i][j].setBackgroundColor(Color.rgb(0, 128, 0));

                } else {
                    iv_board[i][j].setBackgroundColor(Color.rgb(50, 205, 50));
                }
            }
        }
        tran = iv_board[5][0].getDrawable();

    }

    //User Click
    @Override
    public void onClick(View v) {
        //If it is one of the buttons
        if (v instanceof Button) {
            //ROW EQUALS RANK
            if (v==back_button){
                Intent intent = new Intent(Replay.this, PrevGames.class);
                startActivity(intent);
                return;
            }
            if (count > moves.size()-1){
                return;
            }
            String s = moves.get(count);

            count++;
            String arr[] = s.split(" ");
            int a1 = MainController.fileToNum(arr[0].charAt(0));
            int a2 = Character.getNumericValue(arr[0].charAt(1));
            int move_rank = Character.getNumericValue(arr[1].charAt(1));
            int move_file = MainController.fileToNum(arr[1].charAt(0));

            MainController.board[a2][a1].move(arr[1]);

            if (arr.length > 2){
                String pro = arr[2];

                if (pro.equals("r")&&move_rank==0){

                    ((model.Black_Pawn) (MainController.board[move_rank][move_file])).promote("r");
                }
                if (pro.equals("q")&&move_rank==0){
                    ((model.Black_Pawn) (MainController.board[move_rank][move_file])).promote("q");
                }
                if (pro.equals("b")&&move_rank==0){
                    ((model.Black_Pawn) (MainController.board[move_rank][move_file])).promote("b");
                }
                if (pro.equals("n")&&move_rank==0){
                    ((model.Black_Pawn) (MainController.board[move_rank][move_file])).promote("n");
                }
                if (pro.equals("r")&&move_rank==7){
                    ((model.White_Pawn) (MainController.board[move_rank][move_file])).promote("r");
                }
                if (pro.equals("q")&&move_rank==7){
                    ((model.White_Pawn) (MainController.board[move_rank][move_file])).promote("q");
                }
                if (pro.equals("b")&&move_rank==7){
                    ((model.White_Pawn) (MainController.board[move_rank][move_file])).promote("b");
                }
                if (pro.equals("n")&&move_rank==7){
                    ((model.White_Pawn) (MainController.board[move_rank][move_file])).promote("n");
                }
            }
            sync_boards();

            if(MainController.checkForCheck(MainController.board) == MainController.NO_CHECK) {
                message.setText("");
            }
            else if(MainController.checkForCheck(MainController.board) == MainController.CHECK) {
                message.setText("Check");
            }
            else if(MainController.checkForCheck(MainController.board) == MainController.CHECKMATE) {

                if(MainController.white_moves) {
                    message.setText("White wins");
                }
                else {
                    message.setText("Black wins");
                }
            }





            switch_sides();

        }

    }

    public void sync_boards() {

        //For all the ranks
        for (int r = 0; r < 8; r++) {
            //For all the files
            for (int f = 0; f < 8; f++) {
                //If there is a piece there
                if (MainController.board[r][f] != null) {

                    Piece temp = MainController.board[r][f];
                    //Find the name of the piece there
                    String temp_name = temp.name;

                    //If white rook
                    if (temp_name.equalsIgnoreCase("wR")) {
                        iv_board[r][f].setImageResource(R.drawable.wr);
                    }
                    //Else if white knight
                    else if (temp_name.equalsIgnoreCase("wN")) {
                        iv_board[r][f].setImageResource(R.drawable.wn);
                    }
                    //Else if white bishop
                    else if (temp_name.equalsIgnoreCase("wB")) {
                        iv_board[r][f].setImageResource(R.drawable.wb);
                    }
                    //Else if white queen
                    else if (temp_name.equalsIgnoreCase("wQ")) {
                        iv_board[r][f].setImageResource(R.drawable.wq);
                    }
                    //Else if white king
                    else if (temp_name.equalsIgnoreCase("wK")) {
                        iv_board[r][f].setImageResource(R.drawable.wk);
                    }
                    //Else if white pawn
                    else if (temp_name.equalsIgnoreCase("wp")) {
                        iv_board[r][f].setImageResource(R.drawable.wp);
                    }
                    //Else if white rook
                    else if (temp_name.equalsIgnoreCase("bR")) {
                        iv_board[r][f].setImageResource(R.drawable.br);
                    }
                    //Else if black knight
                    else if (temp_name.equalsIgnoreCase("bN")) {
                        iv_board[r][f].setImageResource(R.drawable.bn);
                    }
                    //Else if black bishop
                    else if (temp_name.equalsIgnoreCase("bB")) {
                        iv_board[r][f].setImageResource(R.drawable.bb);
                    }
                    //Else if black queen
                    else if (temp_name.equalsIgnoreCase("bQ")) {
                        iv_board[r][f].setImageResource(R.drawable.bq);
                    }
                    //Else if black king
                    else if (temp_name.equalsIgnoreCase("bK")) {
                        iv_board[r][f].setImageResource(R.drawable.bk);
                    }
                    //Else if black pawn
                    else if (temp_name.equalsIgnoreCase("bp")) {
                        iv_board[r][f].setImageResource(R.drawable.bp);
                    } else {
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
                    iv_board[r][f].setImageResource(R.drawable.tran40);
                }
            }
        }


    }
    public void switch_sides() {
        MainController.white_moves = !MainController.white_moves;

        //Set the message
        //If it is white's turn
        if(MainController.white_moves) {
            MainController.white_enpassant = 0;
            player.setText("White Moves: ");
        }
        else {
            MainController.black_enpassant = 0;
            player.setText("Black Moves: ");
        }

    }

}
