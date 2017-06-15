package com.emptyshit.hsay.front.navigation;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.player.WindowLogIn;
import com.emptyshit.hsay.front.player.WindowRegister;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

public class WindowStart extends Activity {

    private Button startRegisterButton, startWithoutSignInButton, startLogInButton;
    private PlayerComponentInterface playerComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_start);
        this.playerComponentInterface = App.getPlayerComponentInterface();
        if(this.playerComponentInterface.loggedIn()){
            Intent intent = new Intent(getApplicationContext(), WindowGateWay.class);
            startActivity(intent);
        }
        startRegisterButton = (Button) findViewById(R.id.startRegisterButton);
        startWithoutSignInButton = (Button) findViewById(R.id.startPlaySignInButton);
        startLogInButton = (Button) findViewById(R.id.startLogInButton);
        setupClickListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void setupClickListener(){
        startRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WindowRegister.class);
                startActivity(intent);
            }
        });
        startWithoutSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getPlayerComponentInterface().withoutRegister();
                Intent intent = new Intent(getApplicationContext(), WindowGateWay.class);
                startActivity(intent);
            }
        });
        startLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), WindowLogIn.class);
                startActivity(intent);
            }
        });
    }
}
