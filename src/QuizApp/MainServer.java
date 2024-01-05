package QuizApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {

    private static ServerSocket serverSocket;
    private static Socket userSocket;
    private static ArrayList<UserThread> userThreads;


    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(8081);
        userThreads = new ArrayList<>();
        while (true){
            userSocket = serverSocket.accept();
            UserThread userThread = new UserThread(userSocket, userThreads);
            userThreads.add(userThread);
            Thread thread = new Thread(userThread);
            thread.start();
        }
    }
}
