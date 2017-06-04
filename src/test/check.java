package test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by idanr on 15/05/2017.
 */
public class check {
    public static Maze checkmaze(){
    try {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(2, 3);
        //  ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        //  ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
        String path = System.getProperty("java.io.tmpdir");
        //read maze form the claint
        int hash = maze.toString().hashCode();
        //add
        String FilePath = path + "\\" + hash;
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution s = new BreadthFirstSearch().solve(searchableMaze);
        ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FilePath));
        write.writeObject(s);
        write.flush();
        write.close();
        return maze;
    }
    catch (Exception e){
        e.printStackTrace();
    }

        return null;
    }
}




