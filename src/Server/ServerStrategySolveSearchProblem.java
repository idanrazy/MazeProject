package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

/**
 * Created by idanr on 22/05/2017.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy{
    public ServerStrategySolveSearchProblem(){

    }
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze m = (Maze)fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(m);
            Solution s = new BreadthFirstSearch().solve(searchableMaze);
            toClient.writeObject(s);
            toClient.flush();


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
