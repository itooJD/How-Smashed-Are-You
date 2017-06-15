package com.emptyshit.hsay.front.games;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.gameComponent.EnterWordGame;

public class FragmentEnterWordGame extends Fragment {

    private EnterWordGame enterWordGame = new EnterWordGame();

    private Button enterWordGameConfirmButton;
    private TextView enterWordGameGivenTextTextView, enterWordGameRightOrWrongTextView;
    private EditText enterWordGameTextInputEditText;
    private FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View enterWordGameView = inflater.inflate(R.layout.fragment_enter_word_game, container,false);
        this.enterWordGameConfirmButton = (Button) enterWordGameView.findViewById(R.id.enterWordGameConfirmButton);
        this.enterWordGameGivenTextTextView = (TextView) enterWordGameView.findViewById(R.id.enterWordGameGivenTextTextView);
        this.enterWordGameRightOrWrongTextView = (TextView) enterWordGameView.findViewById(R.id.enterWordGameRightOrWrongTextView);
        this.enterWordGameTextInputEditText = (EditText) enterWordGameView.findViewById(R.id.enterWordGameTextInputEditText);

        this.enterWordGameGivenTextTextView.setText(this.enterWordGame.wordToTypeIn());
        setupClickListener();

        return enterWordGameView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fragmentCommunication = (FragmentCommunication) getActivity();
    }

    private void setupClickListener(){
        this.enterWordGameConfirmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(enterWordGame.compareWords(enterWordGameTextInputEditText.getText().toString())){
                    enterWordGameRightOrWrongTextView.setText("Right");
                    enterWordGameRightOrWrongTextView.setTextColor(Color.GREEN);
                    fragmentCommunication.stopChronometer();
                } else{
                    enterWordGameRightOrWrongTextView.setText("Wrong");
                    enterWordGameRightOrWrongTextView.setTextColor(Color.RED);
                }
            }
        });
    }
}
