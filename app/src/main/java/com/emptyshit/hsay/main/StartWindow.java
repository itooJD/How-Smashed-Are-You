package com.emptyshit.hsay.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emptyshit.hsay.GameEnterTheName;
import com.emptyshit.hsay.R;

public class StartWindow extends AppCompatActivity {

    private Button registerButton;
    private Button playWithoutSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_window);
        registerButton = (Button) findViewById(R.id.registerButton);
        playWithoutSignIn = (Button) findViewById(R.id.playWithoutSignIn);
        setupClickListener();
    }

    private void setupClickListener(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                // change
                Intent intent = new Intent(getApplicationContext(), GameEnterTheName.class);
                startActivity(intent);
            }
        });
        playWithoutSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }
}
