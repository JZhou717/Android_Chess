package com.example.android14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.Game;
import model.MainModel;

public class PrevGames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_games);
        try{
            /*
            System.out.println("Reading");
            File f = new File("prevGames.dat");
            FileInputStream fis = new FileInputStream(f);
            */
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dat/Admin.dat"));

            ArrayList<Game> games = (ArrayList<Game>) ois.readObject();
            if (games.size()>0){
                System.out.println("Got some games");
            }
            MainModel.resetGames(games);
        }catch(FileNotFoundException e){

        }catch(IOException e){

        }catch(ClassNotFoundException e) {

        }
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


                }

                Intent intent = new Intent(PrevGames.this, Replay.class);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
