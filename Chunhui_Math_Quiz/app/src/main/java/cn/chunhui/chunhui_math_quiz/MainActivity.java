package cn.chunhui.chunhui_math_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import cn.chunhui.chunhui_math_quiz.model.Quiz;
import cn.chunhui.chunhui_math_quiz.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView nameTextView, questionText, judgeResult, scoreDisplay;
    EditText writeAnswer;
    Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn,
           decimalBtn, negativeBtn, generateBtn, validateBtn, clearBtn, scoreBtn, finishBtn;
    double result, userAnswer, scorePercent;
    ArrayList<Quiz> quizArrayList = new ArrayList<>();
    ArrayList<Quiz> correctQuiz = new ArrayList<>();
    ArrayList<Quiz> wrongQuiz = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        getMyIntent();
    }

    private void getMyIntent(){
        String userName = getIntent().getStringExtra("userName");
        String stringUserName = String.valueOf(userName);
        nameTextView.setText(stringUserName);
    }

    private void initialize(){
        // Text and Edit:
        nameTextView = findViewById(R.id.registerTextView);
        questionText = findViewById(R.id.questionTextView);
        writeAnswer = findViewById(R.id.answerType);
        judgeResult = findViewById(R.id.validateTheAnswer);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        // Buttons:
        oneBtn = findViewById(R.id.one); oneBtn.setOnClickListener(this);
        twoBtn = findViewById(R.id.two); twoBtn.setOnClickListener(this);
        threeBtn = findViewById(R.id.three); threeBtn.setOnClickListener(this);
        fourBtn = findViewById(R.id.four); fourBtn.setOnClickListener(this);
        fiveBtn = findViewById(R.id.five); fiveBtn.setOnClickListener(this);
        sixBtn = findViewById(R.id.six); sixBtn.setOnClickListener(this);
        sevenBtn = findViewById(R.id.seven); sevenBtn.setOnClickListener(this);
        eightBtn = findViewById(R.id.eight); eightBtn.setOnClickListener(this);
        nineBtn = findViewById(R.id.nine); nineBtn.setOnClickListener(this);
        zeroBtn = findViewById(R.id.zero); zeroBtn.setOnClickListener(this);
        // functional buttons:
        decimalBtn = findViewById(R.id.decimal); decimalBtn.setOnClickListener(this);
        negativeBtn = findViewById(R.id.positionOrNegative); negativeBtn.setOnClickListener(this);
        generateBtn = findViewById(R.id.generate); generateBtn.setOnClickListener(this);
        validateBtn = findViewById(R.id.validation); validateBtn.setOnClickListener(this);
        clearBtn = findViewById(R.id.clear); clearBtn.setOnClickListener(this);
        scoreBtn = findViewById(R.id.score); scoreBtn.setOnClickListener(this);
        finishBtn = findViewById(R.id.finish); finishBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view){
        String inputText = writeAnswer.getText().toString();
        switch (view.getId()){
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero:
                inputText += ((Button)view).getText().toString();
                writeAnswer.setText(inputText);
                break;
            case R.id.decimal:
                if(!inputText.equals("")){
                    if(!inputText.contains(".")){
                        inputText = inputText + ((Button)view).getText().toString();
                        writeAnswer.setText(inputText);
                    }
                }
                break;
            case R.id.positionOrNegative:
                if(!inputText.equals("")){
                    if(inputText.charAt(0) == '-'){
                        writeAnswer.setText(inputText.substring(1));
                    } else if(inputText.charAt(0)>='0' && inputText.charAt(0)<='9'){
                        inputText = "-" + inputText;
                        writeAnswer.setText(inputText);
                    } else if(inputText.charAt(0) == '.'){
                        inputText = "-0" + inputText;
                        writeAnswer.setText(inputText);
                    }
                }
                break;
            case R.id.generate:
                generateQuestion();
                break;
            case R.id.validation:
                validateAnswer();
                break;
            case R.id.clear:
                clearText();
                break;
            case R.id.score:
                goToCheckScore();
                break;
            case R.id.finish:
                finish();
                break;
        }
    }

    private void generateQuestion(){
        Random random = new Random();
        int operand1 = random.nextInt(10);
        int operand2 = random.nextInt(9)+1;
        String operand1_string = String.valueOf(operand1);
        String operand2_string = String.valueOf(operand2);
        String[] operators = {"+","-","x","÷"};
        String quizQuestion;
        int operatorIndex = random.nextInt(4);
        switch(operatorIndex){
            case 0:
                quizQuestion = operand1_string + operators[0] + operand2_string;
                questionText.setText(quizQuestion);
                result = operand1 + operand2;
                break;
            case 1:
                quizQuestion = operand1_string + operators[1] + operand2_string;
                questionText.setText(quizQuestion);
                result = operand1 - operand2;
                break;
            case 2:
                quizQuestion = operand1_string + operators[2] + operand2_string;
                questionText.setText(quizQuestion);
                result = operand1 * operand2;
                break;
            case 3:
                quizQuestion = operand1_string + operators[3] + operand2_string;
                questionText.setText(quizQuestion);
                try{
                    result = (float)operand1 / operand2;
                }catch (Exception e){
                    String errorMessage = "";
                    writeAnswer.setText(errorMessage);
                }
                break;
            default:
                break;
        }
        clearText();
        judgeResult.setText(null);
    }

    private void validateAnswer(){
        userAnswer = Double.parseDouble(writeAnswer.getText().toString());
        String question = questionText.getText().toString();
        String message;
        String score;
        if (result == userAnswer){
            message = question + " = " + userAnswer + " is correct!\n" + " Good Job!";
            judgeResult.setText(message);
            Quiz quiz = new Quiz(question, message);
            correctQuiz.add(quiz);
            quizArrayList.add(quiz);
            Toast.makeText(this,"Excellent! Correct + " +
                    correctQuiz.size(), Toast.LENGTH_LONG).show();
        }
        else{
            String resultString = String.format(Locale.CANADA,"%.2f",result);
            message = question + " = " + userAnswer + " is wrong!\n" +
                    " Answer should be " + resultString;
            judgeResult.setText(message);
            Quiz quiz = new Quiz(question, message);
            wrongQuiz.add(quiz);
            quizArrayList.add(quiz);
            Toast.makeText(this, "OH~~Wrong + " +
                    wrongQuiz.size(), Toast.LENGTH_LONG).show();
        }
        scorePercent = (float)correctQuiz.size() / quizArrayList.size() * 100;
        score = correctQuiz.size() + " of " +
                quizArrayList.size() + " = "
                + String.format(Locale.CANADA,"%.2f",scorePercent) +"%";
        scoreDisplay.setText(score);
    }

    private void clearText(){
        writeAnswer.setText(null);
    }

    private void goToCheckScore(){
        String userName = nameTextView.getText().toString();
        String score = scoreDisplay.getText().toString();
        User user = new User(userName,quizArrayList,score);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra",user);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("intentExtra",bundle);
        startActivity(intent);
    }
}