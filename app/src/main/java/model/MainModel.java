package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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

    public static void addGame(Game g) throws IOException {
        /*
        prevGames.add(g);
        File gamesTxt;
        try {
            gamesTxt = new File("prevGames.txt");
            Files.deleteIfExists(gamesTxt.toPath());
            gamesTxt.createNewFile();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        try {

            FileWriter fw = new FileWriter(gamesTxt);
            BufferedWriter bw = new BufferedWriter(fw);

            //BufferLine in beginning for readFromFile not to waste first line
            bw.write("Buffer Line");
            bw.newLine();

            Game temp;

            for(int i = 0; i < prevGames.size(); i++) {
                bw.newLine();
                temp = prevGames.get(i);
                bw.write(temp.getName());
                bw.newLine();
                bw.write(temp.getDate());
                bw.newLine();
                bw.write("" + temp.getMoves().size());
                bw.newLine();
                for (int j=0;j<temp.getMoves().size();j++){
                    bw.write(temp.getMoves().get(j));
                    bw.newLine();
                }

            }
            if(bw != null) {
                bw.close();
            }
            if(fw != null) {
                fw.close();
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        for (int i=0;i<prevGames.size();i++){
        }
*/
    }

}

