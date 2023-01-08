package cn.chunhui.chunhui_math_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CoverActivity extends AppCompatActivity {
    EditText nameText;
    Button playButton, finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        initialize();
    }

    private void initialize(){
        nameText = findViewById(R.id.registerText);
        playButton = findViewById(R.id.playGameButton);
        finishButton = findViewById(R.id.finishButton);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.playGameButton:
                quizStart();
                break;
            case R.id.finishButton:
                finish();
                break;
        }
    }

    // send name to main activity:
    private void quizStart(){
        String userName = nameText.getText().toString();
        if(userName.equals("")){
            userName = "Math Quiz";
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
}