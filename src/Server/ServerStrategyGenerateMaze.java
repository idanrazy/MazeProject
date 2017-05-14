package Server;

import java.io.*;

/**
 * Created by idanr on 14/05/2017.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
        BufferedWriter toClient = new BufferedWriter(new PrintWriter(outToClient));

        String clientCommand;

    }
}
