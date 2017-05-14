package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by idanr on 14/05/2017.
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}

