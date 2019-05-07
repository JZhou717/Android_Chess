package com.example.android14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.Game;
import model.MainModel;

public class PrevGames extends AppCompatActivity implements View.OnClickListener {
    Button date_button;
    Button name_button;
    ListView games;
    Boolean nameOrDate = false;
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
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.txt"));

            ArrayList<Game> games = (ArrayList<Game>) ois.readObject();
            if (games.size()>0){
                System.out.println("Got some games");
            }
            MainModel.resetGames(games);
        }catch(FileNotFoundException e){

        }catch(IOException e){

        }catch(ClassNotFoundException e) {

        }
        date_button = findViewById(R.id.date_button);
        name_button = findViewById(R.id.name_button);

        date_button.setOnClickListener(this);
        name_button.setOnClickListener(this);
        games = findViewById(R.id.games_list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview, MainModel.getGameNames());
        games.setAdapter(adapter);

        games.setClickable(true);
        games.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game g;
                Bundle bundle = new Bundle();
                if (nameOrDate == true){
                    g = MainModel.getGameAt(position);
                }else{
                    g = MainModel.getGameByName(position);
                }


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
    //User Click for pieces or buttons
    @Override
    public void onClick(View v) {
        //If it is one of the buttons
        if (v instanceof Button) {
            if (v == date_button){

                games = findViewById(R.id.games_list);
                ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview, MainModel.getGamesDate());
                games.setAdapter(adapter);
                nameOrDate = true;
            }
            if (v == name_button){

                games = findViewById(R.id.games_list);
                ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview, MainModel.getGameNames());
                games.setAdapter(adapter);
                nameOrDate = false;
            }
        }
    }

}
