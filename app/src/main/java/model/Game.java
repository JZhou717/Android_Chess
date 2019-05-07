package model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Game implements Serializable, Comparable<Game> {
    private ArrayList<String> moves;
    private String name;

    private Date date;

    public Game(Calendar d){
        this.date = d.getTime();
        this.name = "No Name";
        this.moves = new ArrayList<String>();
    }
    public ArrayList<String> getMoves(){
        return this.moves;
    }
    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date.toString();
    }
    public void addMove(String s){
        moves.add(s);
    }
    public void undo(){
        moves.remove(moves.size());
    }
    public void setName(String s){
        this.name = s;
    }

    @Override
    public int compareTo(Game g) {
        return getDate().compareTo(g.getDate());
    }

}
