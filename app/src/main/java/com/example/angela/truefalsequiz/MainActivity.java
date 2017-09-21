package com.example.angela.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton, falseButton, nextButton;
    private TextView questionText, correctText, currentScoreText;
    private List<Question> questionBank;
    private List<Integer> askedQuestions;
    private Question currentQuestion;
    private Boolean resetable;
    private int score;
    public static final String KEY_SCORE = "score key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        initQuestionBank();
        initAskedQuestions();
        setTextView();
        score = 0;

        Intent ib = getIntent();
        reset();
    }

    private void reset() {
        while (askedQuestions.size() != 0){
            askedQuestions.remove(0);
        }
        nextQuestion();
        nextButton.setText(getString(R.string.next_question));
        falseButton.setEnabled(true);
        trueButton.setEnabled(true);
        score = 0;
    }

    private void setTextView() {
        currentQuestion = questionBank.get((int)(Math.random() * questionBank.size()));
        askedQuestions.add(questionBank.indexOf(currentQuestion));
        questionText.setText(currentQuestion.getQuestionText());
        questionText.setMaxWidth(1200);
        questionText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        questionText.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);


    }

    private void initQuestionBank() {
        questionBank = new ArrayList<>(); //Don't need to put the type in a second time
        questionBank.add(new Question(getString(R.string.question_basketball), false));
        questionBank.add(new Question(getString(R.string.question_magma), false));
        questionBank.add(new Question(getString(R.string.question_organ), true));
        questionBank.add(new Question(getString(R.string.question_fahrenheit), true));
        questionBank.add(new Question(getString(R.string.question_ostriches), false));
        questionBank.add(new Question(getString(R.string.question_braille), true));
        questionBank.add(new Question(getString(R.string.question_coffee), true));
    }

    private void initAskedQuestions() {
        askedQuestions = new ArrayList<>();
    }

    private void setListeners() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    private void wireWidgets() {
        //wiring Buttons
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        nextButton = (Button) findViewById(R.id.button_next);

        //wiring Text
        questionText = (TextView) findViewById(R.id.text_question);
        correctText = (TextView) findViewById(R.id.text_correct_incorrect);
        currentScoreText = (TextView) findViewById(R.id.text_current_score);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_false:
                checkAnswer(false);
                break;
            case R.id.button_true:
                checkAnswer(true);
                break;
            case R.id.button_next:
                nextQuestion();
                break;
            }
        }

    private void nextQuestion() {
        if (askedQuestions.size() == questionBank.size()) {
            Intent i = new Intent(MainActivity.this, FinalScoreActivity.class);
            i.putExtra(KEY_SCORE, score);
            startActivity(i);
        }
        else {
            while (askedQuestions.indexOf(questionBank.indexOf(currentQuestion)) != -1) {
                currentQuestion = questionBank.get((int) (Math.random() * questionBank.size()));
            }
            askedQuestions.add(questionBank.indexOf(currentQuestion));
            questionText.setText(currentQuestion.getQuestionText());
            falseButton.setEnabled(true);
            trueButton.setEnabled(true);
            correctText.setText(getString(R.string.text_empty));
        }
    }

    private void checkAnswer(boolean answer) {
        if(currentQuestion.isAnswer() == answer) {
            correctText.setText(R.string.text_correct);
            score++;
            currentScoreText.setText("Score = " + score);
        }
        else {
            correctText.setText(R.string.text_incorrect);
        }
        falseButton.setEnabled(false);
        trueButton.setEnabled(false);
    }
}

