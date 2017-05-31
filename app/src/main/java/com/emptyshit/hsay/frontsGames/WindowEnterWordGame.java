package com.emptyshit.hsay.frontsGames;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.gameComponent.EnterWordGame;
import com.emptyshit.hsay.timeMeasureComponent.WindowTimeMeasure;

public class WindowEnterWordGame extends AppCompatActivity {

    //TODO
    private EnterWordGame enterWordGame = new EnterWordGame();

    private Button enterWordGameConfirmButton;
    private TextView enterWordGameGivenTextTextView, enterWordGameRightOrWrongTextView;
    private EditText enterWordGameTextInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_enter_word_game);

        this.enterWordGameConfirmButton = (Button) findViewById(R.id.enterWordGameConfirmButton);
        this.enterWordGameGivenTextTextView = (TextView) findViewById(R.id.enterWordGameGivenTextTextView);
        this.enterWordGameRightOrWrongTextView = (TextView) findViewById(R.id.enterWordGameRightOrWrongTextView);
        this.enterWordGameTextInputEditText = (EditText) findViewById(R.id.enterWordGameTextInputEditText);

        this.enterWordGameGivenTextTextView.setText(this.enterWordGame.wordToTypeIn());
        setupClickListener();
    }

    private void setupClickListener(){
        this.enterWordGameConfirmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(enterWordGame.compareWords(enterWordGameTextInputEditText.getText().toString())){
                    enterWordGameRightOrWrongTextView.setText("Right");
                    enterWordGameRightOrWrongTextView.setTextColor(Color.GREEN);
                    Intent intent = new Intent(getApplicationContext(), WindowTimeMeasure.class);
                    startActivity(intent);
                } else{
                    enterWordGameRightOrWrongTextView.setText("Wrong");
                    enterWordGameRightOrWrongTextView.setTextColor(Color.RED);
                }
            }
        });
    }
}
