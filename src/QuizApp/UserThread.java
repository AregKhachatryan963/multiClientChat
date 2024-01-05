package QuizApp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UserThread implements Runnable {
    private Socket userSocket;
    private ArrayList<UserThread> userThreads;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;
    private String test;
    private Integer score;
    private List<User> usersData = new ArrayList<>();
    private List<User> usersDataFromFile;
    private ObjectOutputStream writeResult;
    private ObjectInputStream readResult;
    UserThread(Socket userSocket, ArrayList<UserThread> userThreads) throws IOException {
        this.userSocket = userSocket;
        this.userThreads = userThreads;
        in = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
        out = new PrintWriter(userSocket.getOutputStream(), true);
        writeResult = new ObjectOutputStream(new FileOutputStream("/home/areg/Desktop/JAVA-notes/fff1", true));
        readResult = new ObjectInputStream(new FileInputStream("/home/areg/Desktop/JAVA-notes/fff1"));
    }
    public void run() {
        while (true){
            try {
                out.println("Enter your name: ");
                userName = in.readLine().trim();
                System.out.println(userName);
                if(userName.equals("exit")){
                    break;
                }
                out.println("Enter test name / test1 / test2");
                test = in.readLine().trim();
                System.out.println(test);
                Quiz1 quiz = new Quiz1(userName);

                if(test.equals("test1")){
                    score = 0;
                    for (Question1 q: quiz.questions1){
                        out.println(q.question);
                        String answer = in.readLine().trim();
                        if(answer.equals(q.answer)){
                            score++;
                        }
                    }
                    out.println("Your score is - " + score + "from " + quiz.questions1.size() + ". Save in file? - y/n");
                }
                if(test.equals("test2")){
                    score = 0;
                    for (Question1 q: quiz.questions2){
                        out.println(q.question);
                        String answer = in.readLine().trim();
                        if(answer.equals(q.answer)){
                            score++;
                        }
                    }
                    out.println("Your score is - " + score + "from " + quiz.questions2.size() + ". Save in file? - y/n");
                }

                if(in.readLine().trim().equals("y")){
                    usersData.add(new User(userName, score));
                    writeResult.writeObject(usersData);
                }
                out.println("Want to check results - y/n");
                if(in.readLine().trim().equals("y")){
                    usersDataFromFile = (List<User>) readResult.readObject();
                    for (User u: usersDataFromFile){
                        if(userName.equals(u.name)){
                            System.out.println(userName + ":  date - " + u.date + ", score - " + u.score);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
