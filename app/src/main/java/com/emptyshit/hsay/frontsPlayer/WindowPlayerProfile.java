package com.emptyshit.hsay.frontsPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

public class WindowPlayerProfile extends AppCompatActivity {

    private TextView playerProfileEmailTextView;
    private PlayerComponentInterface playerComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_player_profile);

        playerProfileEmailTextView = (TextView) findViewById(R.id.playerProfileEmailTextView);

        playerProfileEmailTextView.setText(playerComponentInterface.getEmail().toString());
    }
}
