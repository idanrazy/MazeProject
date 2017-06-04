package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.io.*;
import java.util.Properties;

/**
 * Created by idanr on 14/05/2017.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {


    /**
     * @param inFromClient input to the server
     * @param outToClient  output from the server
     * thw stragety that the server work with
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            System.out.println("check");
        //covert client command to generate maze
            int[] a;
            IMazeGenerator mgen = null;
            if (fromClient != null ){
                try {
                    java.util.Properties prop = new Properties();
                    InputStream input = new FileInputStream("config.properties");
                    prop.load(input);
                    String Generate = prop.getProperty("GenerateAlg");
                    a =(int[])fromClient.readObject();
                    switch (Generate){
                        case "MyMazeGenerator":
                             mgen = new MyMazeGenerator();
                             break;
                        case "SimpleMazeGenerator":
                             mgen = new SimpleMazeGenerator();
                             break;
                    }
                    Maze maze = mgen.generate(a[0],a[1]);
                    byte[] bm = maze.toByteArray();
                    // decode with part A
                    ByteArrayOutputStream bcom = new ByteArrayOutputStream();
                    MyCompressorOutputStream os = new MyCompressorOutputStream(bcom);
                    os.write(bm);
                    bm =bcom.toByteArray();
                    toClient.writeObject(bm);
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
