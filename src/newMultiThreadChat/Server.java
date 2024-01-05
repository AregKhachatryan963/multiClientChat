package newMultiThreadChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static ArrayList<ClientThread> clientThreads;

    public static void main(String[] args) throws IOException {
        clientThreads = new ArrayList<>();
        serverSocket = new ServerSocket(8081);
        while (true) {
            socket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(socket, clientThreads);
            clientThreads.add(clientThread);
            new Thread(clientThread).start();
        }
    }
}