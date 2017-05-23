package test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by idanr on 15/05/2017.
 */
public class check {
    public static void main(String[] args) {

    try {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(50, 50);
        //  ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        //  ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
        String path = System.getProperty("java.io.tmpdir");
        //read maze form the claint
        int hash = maze.toString().hashCode();
        //add
        String FilePath = path + "\\" + hash;
        ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FilePath));
    }
    catch (Exception e){
        e.printStackTrace();
    }

    }
}




