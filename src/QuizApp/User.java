package QuizApp;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    public String name;
    public int score;
    public LocalDate date;
    public User(String name, Integer score){
        this.name = name;
        this.score = score;
        date = LocalDate.now();
    }
}
