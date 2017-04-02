package com.emptyshit.hsay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameEnterTheName extends AppCompatActivity {

    private TextView givenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_enter_the_name);
        givenText = (TextView) findViewById(R.id.givenText);
        givenText.setText("Gib dies ein");
    }

}
