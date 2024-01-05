package QuizApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainUser {
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 8081);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println(in.readLine());
            out.println(scanner.nextLine());
        }
    }
}
