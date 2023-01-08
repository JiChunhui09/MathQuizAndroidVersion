package cn.chunhui.chunhui_math_quiz.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String userName;
    private List<Quiz> quizzes;
    private String score;

    public User() {}

    public User(String userName, List<Quiz> quizzes, String score) {
        this.userName = userName;
        this.quizzes = quizzes;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", quizzes=" + quizzes +
                ", score='" + score + '\'' +
                '}';
    }
}
