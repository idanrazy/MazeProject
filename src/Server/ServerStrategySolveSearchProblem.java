package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by idanr on 22/05/2017.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy{
    Map<Integer,String> DicMap;
    public ServerStrategySolveSearchProblem(){
        DicMap = new HashMap<>();

    }
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            String path = System.getProperty("java.io.tmpdir");
            //read maze form the claint
            Maze m = (Maze)fromClient.readObject();
            int hash = m.toString().hashCode();

            Solution s;
            String FilePath = path+"\\"+hash;
            if(new File(path,FilePath).exists())
            {
                System.out.println("Solution was calculated before");
                ObjectInputStream read = new ObjectInputStream((new FileInputStream(FilePath)));
                s = (Solution) read.readObject();
                read.close();

            }
            else {
                System.out.println("Solution was add to Directory");
                SearchableMaze searchableMaze = new SearchableMaze(m);
                s = new BreadthFirstSearch().solve(searchableMaze);
                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FilePath));
                write.writeObject(s);
                write.close();


            }
            toClient.writeObject(s);
            toClient.flush();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
