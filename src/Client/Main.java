package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by idanr on 16/05/2017.
 */
public class Main {
    public static void main(String[] args) {
     ConnectToServer();
    }

    private static void ConnectToServer() {
        try {
            Client client = new Client(InetAddress.getLocalHost(),5400,new CLIstrategyAsync()/*new SendObjectArrayListStrategy()*/);
            client.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
