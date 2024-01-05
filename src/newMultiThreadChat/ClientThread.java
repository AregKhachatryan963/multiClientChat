package newMultiThreadChat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientThread implements Runnable {

    Socket socket;
    ArrayList<ClientThread> clientThreads;
    BufferedReader in;
    PrintWriter out;
    String name;
    String message;

    ClientThread(Socket socket, ArrayList<ClientThread> clientThreads) throws IOException {
        this.socket = socket;
        this.clientThreads = clientThreads;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        out.println("Hello. Enter your name: ");
        String nameOfTheRecipient;
        try {
            name = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                message = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (message.equals("private")) {
                out.println("Enter name of the recipient");
                try {
                    nameOfTheRecipient = in.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                out.println("Enter message for " + nameOfTheRecipient);
                try {
                    String privateMessage = in.readLine();
                    for (ClientThread clientThread : clientThreads) {
                        if (nameOfTheRecipient.equals(clientThread.name)) {
                            clientThread.out.println("Private for " + nameOfTheRecipient + " from " + name +": "+ privateMessage);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                for (ClientThread clientThread : clientThreads) {
                    if (!name.equals(clientThread.name)) {
                        clientThread.out.println(name + " send message: " + message);
                    }
                }
            }
        }
    }
}