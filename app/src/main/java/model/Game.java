package model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Game implements Serializable {
    private ArrayList<String> moves;
    private String name;
    private Calendar date;
    public Game(Calendar d){
        this.date = d;
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

}
