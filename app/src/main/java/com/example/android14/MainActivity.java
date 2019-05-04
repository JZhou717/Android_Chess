package com.example.android14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import model.Game;
import model.MainModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play_game_button = (Button) findViewById(R.id.play_game_button);
        Button list_previous_games_button = findViewById(R.id.list_previous_games_button);
        Calendar c = Calendar.getInstance();
        Game game = new Game(c);
        //THIS IS JUST TO TEST
        game.addMove("e2 e4");
        game.addMove("e7 e6");
        game.addMove("e4 e5");
        game.addMove("b8 c6");
        game.addMove("d2 d4");
        game.setName("GameTest");
        MainModel.addGame(game);
        //Play a game
        play_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        //Show previous games
        list_previous_games_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrevGames.class);
                startActivity(intent);
            }
        });
    }
}
