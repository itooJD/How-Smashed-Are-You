package com.emptyshit.hsay.front.player;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.navigation.WindowGateWay;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

public class WindowLogIn extends Activity {

    private EditText logInUserNameEditText, logInPasswordEditText;
    private Button logInConfirmButton;
    private PlayerComponentInterface playerComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_log_in);
        this.playerComponentInterface = App.getPlayerComponentInterface();

        logInUserNameEditText = (EditText) findViewById(R.id.logInUserNameEditText);
        logInPasswordEditText = (EditText) findViewById(R.id.logInPasswordEditText);
        logInConfirmButton = (Button) findViewById(R.id.logInConfirmButton);

        setupClickListener();
    }

    private void setupClickListener(){
        logInConfirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(playerComponentInterface.login(logInUserNameEditText.getText().toString(), logInPasswordEditText.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(), WindowGateWay.class);
                    startActivity(intent);
                } else {
                    //TODO
                    // Fehlerausgabe
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this.playerComponentInterface.loggedIn()){
            Intent intent = new Intent(getApplicationContext(), WindowGateWay.class);
            startActivity(intent);
        }
    }
}
