package Server;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

/**
 * Created by idanr on 14/05/2017.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            System.out.println("check");
        //covert client command to generate maze
            int[] a;
            if (fromClient != null ){
                try {
                    a =(int[])fromClient.readObject();
                    IMazeGenerator mgen = new MyMazeGenerator();
                    Maze maze = mgen.generate(a[0],a[1]);
                    // decode with part A

                    toClient.write(maze.toByteArray()); // byte[] maze
                    toClient.flush();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else
                throw new RuntimeException("no command was send");




        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
