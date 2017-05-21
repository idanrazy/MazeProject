package Client;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;

import java.io.*;

/**
 * Created by idanr on 15/05/2017.
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);

}

class CLIstrategyAsync implements IClientStrategy {

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
           // BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); //output form user
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);

            //Sending Commands to the server
            int a[] = {10,10};
            toServer.writeObject(a);
            toServer.flush();
            //receiver commands from the Server
            byte[] serverResponse;
            serverResponse =(byte[])fromServer.readObject();
            System.out.println("Server response");
            InputStream in = new MyDecompressorInputStream(new ByteArrayInputStream(serverResponse));
            byte[] decomprcemaze = new byte[26];
            in.read(decomprcemaze);
            Maze m = new Maze(decomprcemaze);
            m.print();
            fromServer.close();
            toServer.close();
       } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


