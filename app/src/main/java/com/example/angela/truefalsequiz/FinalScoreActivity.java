package com.example.angela.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScoreActivity extends AppCompatActivity {

    private int score1;
    private Button resetButton;
    private TextView scoreText;
    private String scoreDisplayWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        scoreText = (TextView) findViewById(R.id.text_score);
        resetButton = (Button) findViewById(R.id.button_restart);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ib = new Intent(FinalScoreActivity.this, MainActivity.class);
                startActivity(ib);
            }
        });

        Intent i = getIntent();
        score1 = i.getIntExtra(MainActivity.KEY_SCORE, 0);
        scoreDisplayWords = "Score = " + score1;
        scoreText.setText(scoreDisplayWords);
        scoreText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        scoreText.setTextSize(TypedValue.COMPLEX_UNIT_SP,36);
    }
}
