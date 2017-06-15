package com.emptyshit.hsay.front.navigation;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.games.WindowGame;
import com.emptyshit.hsay.front.lock.WindowLock;
import com.emptyshit.hsay.front.player.WindowPlayerProfile;

public class WindowGateWay extends Activity {

    private TextView gateWayPlayGamesTextView, gateWayActivateLockTextView, gateWayPlayerProfileTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_gate_way);

        gateWayPlayGamesTextView = (TextView) findViewById(R.id.gateWayPlayGamesTextView);
        gateWayActivateLockTextView = (TextView) findViewById(R.id.gateWayActivateLockTextView);
        gateWayPlayerProfileTextview = (TextView) findViewById(R.id.gateWayPlayerProfileTextview);

        if(!App.getPlayerComponentInterface().loggedIn()){
            gateWayPlayerProfileTextview.setVisibility(View.INVISIBLE);
        }
        setupClickListener();
    }

    private void setupClickListener(){
        gateWayPlayGamesTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WindowGame.class);
                startActivity(intent);
            }
        });
        gateWayActivateLockTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WindowLock.class);
                startActivity(intent);
            }
        });
        if(App.getPlayerComponentInterface().loggedIn()) {
            gateWayPlayerProfileTextview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), WindowPlayerProfile.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!App.getPlayerComponentInterface().loggedIn()){
            gateWayPlayerProfileTextview.setVisibility(View.INVISIBLE);
        }
        this.setupClickListener();
    }
}
