package model;

import java.util.ArrayList;
import java.util.Calendar;

public class MainModel {
    //Might not want to initialize it here, but fine for now
    private static ArrayList<Game> prevGames = new ArrayList<Game>();



    public static ArrayList<Game> getGames(){
        return prevGames;
    }
    public static ArrayList<String> getGameNames(){
        ArrayList<String> names = new ArrayList<String>();
        for (int i=0;i<prevGames.size();i++){
            names.add(prevGames.get(i).getName());
        }

        return names;
    }
    public static Game getGameAt(int i){
        return prevGames.get(i);
    }
    public static void addGame(Game g){
        prevGames.add(g);
    }

}

