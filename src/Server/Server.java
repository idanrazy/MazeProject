package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by idanr on 14/05/2017.
 */
//add to client
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private java.util.Properties prop;
    private InputStream input = null;
    private int MaxThread;

    /**
     * choose the properties that enter
     */
    public static class Properties {

        public java.util.Properties prop = new java.util.Properties();
        OutputStream output = null;
        public  Properties(){
            try {

                output = new FileOutputStream("config.properties");
                prop.setProperty("GenerateAlg","MyMazeGenerator");
                prop.setProperty("SearchAlg","BestFirstSearch");
                prop.setProperty("MaxThread","10");
                prop.store(output,null);


            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if(output!=null)
                    try{
                        output.close();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
            }
        }

    }


    /**
     * @param port the server port
     * @param listeningInterval  time to check if there is new client
     * @param serverStrategy the server stragety how to handle the client
     */
    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;

        //load properties
        File f = new File("./config.properties");
        Properties prop;
        if(!f.exists())
             prop = new Properties();
         try {
             input = new FileInputStream("config.properties");
             java.util.Properties prop1 = new java.util.Properties();
             prop1.load(input);
             String t =prop1.getProperty("MaxThread");
             MaxThread = Integer.parseInt(t);

         }
         catch (IOException e){
             e.printStackTrace();
         }

    }

    /**
     * start the server
     */
    public void start()  {

        //change to executor
        /*new Thread(() -> {
            runServer();
        }).start();*/
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        executor1.execute(new Runnable() {
            @Override
            public void run() {
                runServer();
            }
        });
    }

    /**
     * run new thread for each new client
     */
    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            ExecutorService executor = Executors.newFixedThreadPool(MaxThread);
            while (!stop) {
                try {
                    Socket aClient = server.accept(); // blocking call
                    /*
                    new Thread(() -> {
                        handleClient(aClient);
                    }).start();
                    */
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            handleClient(aClient);
                        }
                    });
                } catch (SocketTimeoutException e) {
                    //System.out.println("SocketTimeout!");
                }
            }
            executor.shutdown();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param aClient the socket of the client
     * handle each client with stragety
     */
    private void handleClient(Socket aClient) {
        try {
            //         Thread.sleep(3000);
            System.out.println("Client excepted!");
            serverStrategy.serverStrategy(aClient.getInputStream(), aClient.getOutputStream());
//           aClient.getInputStream().close();
//            aClient.getOutputStream().close();
            aClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*       catch (InterruptedException e)
        {
            e.printStackTrace();
        }
*/

    }

    /**
     * stopping the server
     */
    public void stop() {
        System.out.println("Stopping server..");
        stop = true;
    }
}


