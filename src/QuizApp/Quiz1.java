package QuizApp;


import java.io.IOException;
import java.util.List;

public class Quiz1 {
    String userName;
    List<Question1> questions1;
    List<Question1> questions2;

    public Quiz1(String userName)  throws IOException {
        this.userName = userName;

        questions1 = List.of(new Question1("first question :  1) answer 1 , 2) answer 2 , 3) answer 3", "1"),
                new Question1("second question :  1) answer 1 , 2) answer 2 , 3) answer 3", "2"),
                new Question1("third question :  1) answer 1 , 2) answer 2", "1"));

        questions2 = List.of(new Question1("first question : \n 1) answer 1 \n 2) answer 2 \n 3) answer 3", "1"),
                new Question1("second question : \n 1) answer 1 \n 2) answer 2 \n 3) answer 3", "2"),
                new Question1("third question : \n 1) answer 1 \n 2) answer 2", "1"));
    }
}
