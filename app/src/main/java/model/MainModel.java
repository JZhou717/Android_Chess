package model;

import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MainModel extends AppCompatActivity implements Serializable {
    //Might not want to initialize it here, but fine for now
    public static ArrayList<Game> prevGames = new ArrayList<Game>();
    public static ArrayList<String> byName = new ArrayList<String>();

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
        prevGames.removeAll(prevGames);
        for (int i =0;i<arr.size();i++){
            prevGames.add(arr.get(i));

        }
        byName.removeAll(byName);
        for (int i=0;i<prevGames.size();i++){
            byName.add(prevGames.get(i).getName());
        }
        Collections.sort(byName, String.CASE_INSENSITIVE_ORDER);
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
        FileOutputStream fos = context.openFileOutput("saveGames", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(ins);
        oos.close();
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

