package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by idanr on 14/05/2017.
 */
public interface IServerStrategy {
    /**
     * @param inFromClient input to the sever
     * @param outToClient output from the server
     * the stragety of the server
     */
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}

