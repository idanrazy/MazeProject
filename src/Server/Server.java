package Server;

/**
 * Created by idanr on 14/05/2017.
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;


    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }
    
}
