package com.example.android14;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    //Screen Boot-up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    //User Click
    //@Override
    public void onClick(View v) {
        //If it is one of the buttons
        if(v instanceof Button) {

        }
        //If it is one of the pieces
        else if(v instanceof ImageView) {

        }
    }
}
