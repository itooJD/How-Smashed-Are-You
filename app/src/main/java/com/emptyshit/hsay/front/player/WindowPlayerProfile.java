package com.emptyshit.hsay.front.player;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.gameData.FragmentGameData;
import com.emptyshit.hsay.front.navigation.WindowStart;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

public class WindowPlayerProfile extends Activity {

    private TextView playerProfileUsernameTextView, playerProfileEmailTextView;
    private Button playerProfileLogoutButton;
    private PlayerComponentInterface playerComponentInterface;
    private TimeMeasureComponentInterface timeMeasureComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_player_profile);
        this.timeMeasureComponentInterface = App.getTimeMeasureComponentInterface();
        this.playerComponentInterface = App.getPlayerComponentInterface();
        if(!this.playerComponentInterface.loggedIn()){
            Intent intent = new Intent(getApplicationContext(),WindowStart.class);
            startActivity(intent);
        }
        this.playerProfileUsernameTextView = (TextView) findViewById(R.id.playerProfileUsernameTextView);
        this.playerProfileEmailTextView = (TextView) findViewById(R.id.playerProfileEmailTextView);
        this.playerProfileLogoutButton = (Button) findViewById(R.id.playerProfileLogoutButton);
        this.playerProfileUsernameTextView.setText(this.playerComponentInterface.getMyUsername());
        this.playerProfileEmailTextView.setText(playerComponentInterface.getMyEmail().toString());

        if(this.timeMeasureComponentInterface.alreadyPlayed()) {
            FragmentGameData fragmentGameData = new FragmentGameData();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.playerProfileGameDataFragment, fragmentGameData);
            fragmentTransaction.commit();
        }

        setupClickListener();
    }

    private void setupClickListener(){
        this.playerProfileLogoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                playerComponentInterface.logout();
                Intent intent = new Intent(getApplicationContext(),WindowStart.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!this.playerComponentInterface.loggedIn()){
            Intent intent = new Intent(getApplicationContext(),WindowStart.class);
            startActivity(intent);
        }
    }
}
