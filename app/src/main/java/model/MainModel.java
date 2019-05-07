package model;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class MainModel implements Serializable {
    //Might not want to initialize it here, but fine for now
    public static ArrayList<Game> prevGames = new ArrayList<Game>();
    public static ArrayList<String> byName = new ArrayList<String>();
    public static ArrayList<Game> nameOne = new ArrayList<Game>();




    public static ArrayList<Game> getGames(){
        return prevGames;
    }
    public static ArrayList<String> getGameNames(){
        ArrayList<String> names = new ArrayList<String>();

        for (int i=0;i<prevGames.size();i++){
            names.add(prevGames.get(i).getNameAndDate());
        }
        Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
        return names;
    }
    public static ArrayList<String> getGamesDate(){
       ArrayList<String> names = new ArrayList<String>();
       for (int i=0;i<prevGames.size();i++){
           names.add(prevGames.get(i).getNameAndDate());
       }

       return names;
    }
    public static Game getGameAt(int i){
        return prevGames.get(i);
    }



    public static Game getGameByName(int s){

        String str = byName.get(s);
        for (int i=0;i<prevGames.size();i++){
            if (prevGames.get(i).getName().equals(str)){
                return prevGames.get(i);
            }
        }
        return null;
    }

    public static void resetGames(ArrayList<Game> arr){
        prevGames = arr;
        return;
    }

    public static void addGame(Game g) throws IOException {

        prevGames.add(g);
        byName.add(g.getName());
        Collections.sort(byName, String.CASE_INSENSITIVE_ORDER);
        Collections.sort(prevGames);

        ArrayList<Game> ins = new ArrayList<Game>();
        for (int i=0;i<prevGames.size();i++){
            ins.add(prevGames.get(i));
        }

/*
        String filename = "save.txt";
        FileOutputStream outputStream;
        try {

            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.writeObject(ins);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        /*
        try {

            File f = new File("dat/prevGames.dat");
            if (f.createNewFile()){
                System.out.println("Created");
            }else{
                System.out.println("Exists");
            }
            FileOutputStream fos = new FileOutputStream(f);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admin.txt"));
            System.out.println("Writing");
            oos.writeObject(ins);
            oos.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();

            File f = new File("Admin.txt");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ins);
            oos.close();

        }catch(IOException e){
            System.out.println("Problem man");
            e.printStackTrace();
        }
*/

    }


}

