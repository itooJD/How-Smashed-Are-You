package com.emptyshit.hsay.frontsPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.timeMeasureComponent.WindowTimeMeasure;

public class WindowStart extends AppCompatActivity {

    private Button startWindowRegisterButton, startWindowLoginButton;
    private Button startWindowPlayWithoutSignIn, timerButton;
    private TextView startWindowTesterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_start);
        startWindowLoginButton = (Button) findViewById(R.id.startWindowLoginButton);
        timerButton = (Button) findViewById(R.id.timerButton);
        startWindowRegisterButton = (Button) findViewById(R.id.startWindowRegisterButton);
        startWindowPlayWithoutSignIn = (Button) findViewById(R.id.startWindowPlayWithoutSignIn);
        setupClickListener();
    }

    private void setupClickListener(){
        startWindowRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WindowRegister.class);
                startActivity(intent);
            }
        });
        startWindowPlayWithoutSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                // change
            }
        });

        //TODO
        // wieder loeschen
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getPlayerComponentInterface().withoutRegister();
                Intent intent = new Intent(getApplicationContext(), WindowTimeMeasure.class);
                startActivity(intent);
            }
        });
        startWindowLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WindowLogIn.class);
                startActivity(intent);
            }
        });
    }
}
