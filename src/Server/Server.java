package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

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
    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            while (!stop) {
                try {
                    Socket aClient = server.accept(); // blocking call
                    new Thread(() -> {
                        handleClient(aClient);
                    }).start();
                } catch (SocketTimeoutException e) {
                    //System.out.println("SocketTimeout!");
                }
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket aClient) {
        try {
            Thread.sleep(5000);
            System.out.println("Client excepted!");
            serverStrategy.serverStrategy(aClient.getInputStream(), aClient.getOutputStream());
            aClient.getInputStream().close();
          //  aClient.getOutputStream().close();
          //  aClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("Stopping server..");
        stop = true;
    }
}


