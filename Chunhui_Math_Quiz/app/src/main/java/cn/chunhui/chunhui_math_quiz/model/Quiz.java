package cn.chunhui.chunhui_math_quiz.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Quiz implements Serializable {
    private String quizFillInformation;
    private String quizJudgeResult;

    public Quiz() {
    }

    public Quiz(String quizFillInformation, String quizJudgeResult) {
        this.quizFillInformation = quizFillInformation;
        this.quizJudgeResult = quizJudgeResult;
    }

    public String getQuizFillInformation() {
        return quizFillInformation;
    }

    public void setQuizFillInformation(String quizFillInformation) {
        this.quizFillInformation = quizFillInformation;
    }

    public String getQuizJudgeResult() {
        return quizJudgeResult;
    }

    public void setQuizJudgeResult(String quizJudgeResult) {
        this.quizJudgeResult = quizJudgeResult;
    }

    @NonNull
    @Override
    public String toString() {
        return " Quiz: " + quizFillInformation + "\n" +
                 " User' answer: " + quizJudgeResult + "\n";
    }
}
