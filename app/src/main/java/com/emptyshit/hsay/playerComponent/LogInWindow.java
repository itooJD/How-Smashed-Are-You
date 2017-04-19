package com.emptyshit.hsay.playerComponent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;

public class LogInWindow extends AppCompatActivity {

    private TextView logInPasswordTextView, logInUserNameTextView, logInToRegisterTextView;
    private EditText logInUserNameEditText, logInPasswordEditText;
    private Button logInConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInPasswordTextView = (TextView) findViewById(R.id.logInPasswordTextView);
        logInUserNameTextView = (TextView) findViewById(R.id.logInUserNameTextView);
        logInToRegisterTextView = (TextView) findViewById(R.id.logInToRegisterTextView);
        logInUserNameEditText = (EditText) findViewById(R.id.logInUserNameEditText);
        logInPasswordEditText = (EditText) findViewById(R.id.logInPasswordEditText);
        logInConfirmButton = (Button) findViewById(R.id.logInConfirmButton);
    }

    private void setupClickListener(){
        logInConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                // call method from PlayerComponent
                //   e.g.: boolean logIn(username, password)
                // redirect further
            }
        });
        logInToRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                // redirect to Registration
            }
        });
    }
}
