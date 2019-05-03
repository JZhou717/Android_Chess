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

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView[][] board = new ImageView[8][8];
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
        board[7][0] = (ImageView) findViewById(R.id.a1);
        board[6][0] = (ImageView) findViewById(R.id.a2);
        board[5][0] = (ImageView) findViewById(R.id.a3);
        board[4][0] = (ImageView) findViewById(R.id.a4);
        board[3][0] = (ImageView) findViewById(R.id.a5);
        board[2][0] = (ImageView) findViewById(R.id.a6);
        board[1][0] = (ImageView) findViewById(R.id.a7);
        board[0][0] = (ImageView) findViewById(R.id.a8);
        //For the b file
        board[7][1] = (ImageView) findViewById(R.id.b1);
        board[6][1] = (ImageView) findViewById(R.id.b2);
        board[5][1] = (ImageView) findViewById(R.id.b3);
        board[4][1] = (ImageView) findViewById(R.id.b4);
        board[3][1] = (ImageView) findViewById(R.id.b5);
        board[2][1] = (ImageView) findViewById(R.id.b6);
        board[1][1] = (ImageView) findViewById(R.id.b7);
        board[0][1] = (ImageView) findViewById(R.id.b8);
        //For the c file
        board[7][2] = (ImageView) findViewById(R.id.c1);
        board[6][2] = (ImageView) findViewById(R.id.c2);
        board[5][2] = (ImageView) findViewById(R.id.c3);
        board[4][2] = (ImageView) findViewById(R.id.c4);
        board[3][2] = (ImageView) findViewById(R.id.c5);
        board[2][2] = (ImageView) findViewById(R.id.c6);
        board[1][2] = (ImageView) findViewById(R.id.c7);
        board[0][2] = (ImageView) findViewById(R.id.c8);
        //For the d file
        board[7][3] = (ImageView) findViewById(R.id.d1);
        board[6][3] = (ImageView) findViewById(R.id.d2);
        board[5][3] = (ImageView) findViewById(R.id.d3);
        board[4][3] = (ImageView) findViewById(R.id.d4);
        board[3][3] = (ImageView) findViewById(R.id.d5);
        board[2][3] = (ImageView) findViewById(R.id.d6);
        board[1][3] = (ImageView) findViewById(R.id.d7);
        board[0][3] = (ImageView) findViewById(R.id.d8);
        //For the e file
        board[7][4] = (ImageView) findViewById(R.id.e1);
        board[6][4] = (ImageView) findViewById(R.id.e2);
        board[5][4] = (ImageView) findViewById(R.id.e3);
        board[4][4] = (ImageView) findViewById(R.id.e4);
        board[3][4] = (ImageView) findViewById(R.id.e5);
        board[2][4] = (ImageView) findViewById(R.id.e6);
        board[1][4] = (ImageView) findViewById(R.id.e7);
        board[0][4] = (ImageView) findViewById(R.id.e8);
        //For the f file
        board[7][5] = (ImageView) findViewById(R.id.f1);
        board[6][5] = (ImageView) findViewById(R.id.f2);
        board[5][5] = (ImageView) findViewById(R.id.f3);
        board[4][5] = (ImageView) findViewById(R.id.f4);
        board[3][5] = (ImageView) findViewById(R.id.f5);
        board[2][5] = (ImageView) findViewById(R.id.f6);
        board[1][5] = (ImageView) findViewById(R.id.f7);
        board[0][5] = (ImageView) findViewById(R.id.f8);
        //For the g file
        board[7][6] = (ImageView) findViewById(R.id.g1);
        board[6][6] = (ImageView) findViewById(R.id.g2);
        board[5][6] = (ImageView) findViewById(R.id.g3);
        board[4][6] = (ImageView) findViewById(R.id.g4);
        board[3][6] = (ImageView) findViewById(R.id.g5);
        board[2][6] = (ImageView) findViewById(R.id.g6);
        board[1][6] = (ImageView) findViewById(R.id.g7);
        board[0][6] = (ImageView) findViewById(R.id.g8);
        //For the h file
        board[7][7] = (ImageView) findViewById(R.id.h1);
        board[6][7] = (ImageView) findViewById(R.id.h2);
        board[5][7] = (ImageView) findViewById(R.id.h3);
        board[4][7] = (ImageView) findViewById(R.id.h4);
        board[3][7] = (ImageView) findViewById(R.id.h5);
        board[2][7] = (ImageView) findViewById(R.id.h6);
        board[1][7] = (ImageView) findViewById(R.id.h7);
        board[0][7] = (ImageView) findViewById(R.id.h8);
        for (int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    board[i][j].setBackgroundColor(Color.rgb(50, 205, 50));
                } else {
                    board[i][j].setBackgroundColor(Color.rgb(0, 128, 0));
                }
            }
        }
        board[7][0].setOnClickListener(this);
        board[6][0].setOnClickListener(this);
        board[5][0].setOnClickListener(this);
        board[4][0].setOnClickListener(this);
        board[3][0].setOnClickListener(this);
        board[2][0].setOnClickListener(this);
        board[1][0].setOnClickListener(this);
        board[0][0].setOnClickListener(this);
        //For the b file
        board[7][1].setOnClickListener(this);
        board[6][1].setOnClickListener(this);
        board[5][1].setOnClickListener(this);
        board[4][1].setOnClickListener(this);
        board[3][1].setOnClickListener(this);
        board[2][1].setOnClickListener(this);
        board[1][1].setOnClickListener(this);
        board[0][1].setOnClickListener(this);
        //For the c file
        board[7][2].setOnClickListener(this);
        board[6][2].setOnClickListener(this);
        board[5][2].setOnClickListener(this);
        board[4][2].setOnClickListener(this);
        board[3][2].setOnClickListener(this);
        board[2][2].setOnClickListener(this);
        board[1][2].setOnClickListener(this);
        board[0][2].setOnClickListener(this);
        //For the d file
        board[7][3].setOnClickListener(this);
        board[6][3].setOnClickListener(this);
        board[5][3].setOnClickListener(this);
        board[4][3].setOnClickListener(this);
        board[3][3].setOnClickListener(this);
        board[2][3].setOnClickListener(this);
        board[1][3].setOnClickListener(this);
        board[0][3].setOnClickListener(this);
        //For the e file
        board[7][4].setOnClickListener(this);
        board[6][4].setOnClickListener(this);
        board[5][4].setOnClickListener(this);
        board[4][4].setOnClickListener(this);
        board[3][4].setOnClickListener(this);
        board[2][4].setOnClickListener(this);
        board[1][4].setOnClickListener(this);
        board[0][4].setOnClickListener(this);
        //For the f file
        board[7][5].setOnClickListener(this);
        board[6][5].setOnClickListener(this);
        board[5][5].setOnClickListener(this);
        board[4][5].setOnClickListener(this);
        board[3][5].setOnClickListener(this);
        board[2][5].setOnClickListener(this);
        board[1][5].setOnClickListener(this);
        board[0][5].setOnClickListener(this);
        //For the g file
        board[7][6].setOnClickListener(this);
        board[6][6].setOnClickListener(this);
        board[5][6].setOnClickListener(this);
        board[4][6].setOnClickListener(this);
        board[3][6].setOnClickListener(this);
        board[2][6].setOnClickListener(this);
        board[1][6].setOnClickListener(this);
        board[0][6].setOnClickListener(this);
        //For the h file
        board[7][7].setOnClickListener(this);
        board[6][7].setOnClickListener(this);
        board[5][7].setOnClickListener(this);
        board[4][7].setOnClickListener(this);
        board[3][7].setOnClickListener(this);
        board[2][7].setOnClickListener(this);
        board[1][7].setOnClickListener(this);
        board[0][7].setOnClickListener(this);
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
        //If it is one of the pieces
        else if(v instanceof ImageView) {
            ImageView image = (ImageView) v;

            image.setBackgroundColor(Color.rgb(100, 100, 230));
        }
    }
}
