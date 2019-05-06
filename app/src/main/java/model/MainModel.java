package model;

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

public class MainModel implements Serializable {
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

    public static void resetGames(ArrayList<Game> arr){
        prevGames = arr;
        return;
    }

    public static void addGame(Game g) throws IOException {

        prevGames.add(g);
        ArrayList<Game> ins = new ArrayList<Game>();
        for (int i=0;i<prevGames.size();i++){
            ins.add(prevGames.get(i));
        }

        try {
/*
            File f = new File("dat/prevGames.dat");
            if (f.createNewFile()){
                System.out.println("Created");
            }else{
                System.out.println("Exists");
            }
            FileOutputStream fos = new FileOutputStream(f);
            */
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dat/Admin.dat"));
            oos.writeObject(ins);
            oos.close();
        }catch(FileNotFoundException e) {

            System.out.println("Created new file");
            File f = new File("dat/Admin.dat");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ins);
            oos.close();

        }catch(IOException e){
            System.out.println("Problem man");
            e.printStackTrace();
        }


    }

}

