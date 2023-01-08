package cn.chunhui.chunhui_math_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.chunhui.chunhui_math_quiz.model.Quiz;
import cn.chunhui.chunhui_math_quiz.model.User;

public class ResultActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    Spinner spinnerAnswer;
    TextView registerName, scoreResult;
    Button returnButton;
    //ListView:
    ListView answerList;
    User user;
    ArrayAdapter<Quiz>quizArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
        getUserInformation();
    }

    private void getUserInformation(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("intentExtra");
        Serializable bundleUser = bundle.getSerializable("bundleExtra");
        user = (User)bundleUser;
        showQuizList(user);
    }

    private void initialize(){
        // spinner:
        spinnerAnswer = findViewById(R.id.answerType);
        spinnerAnswer.setOnItemSelectedListener(this);
        // Views:
        registerName = findViewById(R.id.playerNameText);
        scoreResult = findViewById(R.id.scoreText);
        // Buttons:
        returnButton = findViewById(R.id.goBackButton);
        returnButton.setOnClickListener(this);
        // ListView:
        answerList = findViewById(R.id.quizDetailText);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.goBackButton){
            finish();
        }
    }

    private void showQuizList(User user){
        StringBuilder quizList = new StringBuilder();
        for(int i=0; i<user.getQuizzes().size(); i++){
            quizList.append(user.getQuizzes().get(i));
        }
        quizArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, user.getQuizzes());
        answerList.setAdapter(quizArrayAdapter);
        registerName.setText(user.getUserName());
        scoreResult.setText(user.getScore());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Quiz>correctQuiz = new ArrayList<>();
        ArrayList<Quiz>wrongQuiz = new ArrayList<>();
        TextView textView = (TextView) view;
        String selectTextView = textView.getText().toString();
        switch (selectTextView) {
            case "Correct":
                for (Quiz quiz : user.getQuizzes()) {
                    boolean judge = quiz.getQuizJudgeResult().contains("correct");
                    if (judge) {
                        correctQuiz.add(quiz);
                    }
                    quizArrayAdapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1, correctQuiz);
                    answerList.setAdapter(quizArrayAdapter);
                }
                break;
            case "Wrong":
                for (Quiz quiz : user.getQuizzes()) {
                    boolean judge = quiz.getQuizJudgeResult().contains("wrong");
                    if (judge) {
                        wrongQuiz.add(quiz);
                    }
                    quizArrayAdapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1,wrongQuiz);
                    answerList.setAdapter(quizArrayAdapter);
                }
                break;
            case "All":
                quizArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, user.getQuizzes());
                answerList.setAdapter(quizArrayAdapter);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}