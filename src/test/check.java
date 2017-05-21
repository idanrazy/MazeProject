package test;

import Server.*;

import java.util.Scanner;

/**
 * Created by idanr on 15/05/2017.
 */
public class check {
    public static void main(String[] args) {
        StartingServer();

    }
    private static void StartingServer() {
        Server server = new Server(5400, 1000, new ServerStrategyGenerateMaze()/*new ObjectArrayListAddValues()*/);
        server.start();
        StartCLI();
        server.stop();
    }

    private static void StartCLI() {
        System.out.println("Server started!");
        System.out.println("Enter 'exit' to close server.");
        Scanner reader = new Scanner(System.in);

        do {
            System.out.print(">>");
        } while (!reader.next().toLowerCase().equals("exit"));
    }

}



