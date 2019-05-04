package com.example.android14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Game;
import model.MainModel;

public class PrevGames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_games);

        ListView games = findViewById(R.id.games_list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview, MainModel.getGameNames());
        games.setAdapter(adapter);

        games.setClickable(true);
        games.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                Game g = MainModel.getGameAt(position);

                ArrayList<String> mo = g.getMoves();
                bundle.putInt("Size", mo.size());
                for (int i=0;i<mo.size();i++) {

                    String s = "" + i;
                    bundle.putString(s, mo.get(i));
                    System.out.println(mo.get(i));

                }

                Intent intent = new Intent(PrevGames.this, Replay.class);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
