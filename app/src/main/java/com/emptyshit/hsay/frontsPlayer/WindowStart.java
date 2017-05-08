package com.emptyshit.hsay.frontsPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.emptyshit.hsay.R;

public class WindowStart extends AppCompatActivity {

    private Button startWindowRegisterButton;
    private Button startWindowPlayWithoutSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_start);
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
    }
}