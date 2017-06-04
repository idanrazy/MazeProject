package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by idanr on 15/05/2017.
 */
public interface IClientStrategy {
    /**
     * @param inFromServer input from server
     * @param outToServer output from server
     * method with the stragety
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);

}


