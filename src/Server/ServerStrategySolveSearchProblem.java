package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

/**
 * Created by idanr on 22/05/2017.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy{

    /**
     * empty constructor
     */
    public ServerStrategySolveSearchProblem(){

    }

    /**
     * @param inFromClient input to the sever
     * @param outToClient  output from the server
     * the stragety that the server work with
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            String path = System.getProperty("java.io.tmpdir");
            //read properties
            java.util.Properties prop = new java.util.Properties();
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            String Searchalg = prop.getProperty("SearchAlg");
            //read maze form the claint
            Maze m = (Maze)fromClient.readObject();
            int hash = m.toString().hashCode();

            Solution s = null;
            String FilePath = path+"\\"+hash;
            if(new File(path,""+hash).exists())
            {
                System.out.println("Solution was calculated before");
                ObjectInputStream read = new ObjectInputStream((new FileInputStream(FilePath)));
                s = (Solution) read.readObject();
                read.close();

            }
            else {
                System.out.println("Solution was add to Directory");
                SearchableMaze searchableMaze = new SearchableMaze(m);
                switch (Searchalg){
                    case("BreadthFirstSearch"):
                        s = new BreadthFirstSearch().solve(searchableMaze);
                        break;
                    case("BestFirstSearch"):
                        s = new BestFirstSearch().solve(searchableMaze);
                        break;
                    case("DepthFirstSearch"):
                        s = new DepthFirstSearch().solve(searchableMaze);
                        break;
                }
                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FilePath));
                write.writeObject(s);
                write.flush();
                write.close();


            }
            toClient.writeObject(s);
            toClient.flush();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
