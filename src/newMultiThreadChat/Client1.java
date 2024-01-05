package newMultiThreadChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 implements Runnable {
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8081);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(System.in);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new Thread(new Client1());
        thread.start();
        while (true) {
           String message = scanner.nextLine();
           out.println(message);
        }
    }

    public void run() {
        while (true) {
            try {
                String messageIn = in.readLine().trim();
                System.out.println(messageIn);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
