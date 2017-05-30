package com.emptyshit.hsay.frontsPlayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;

public class WindowLogIn extends AppCompatActivity {

    private TextView logInPasswordTextView, logInUserNameTextView, logInToRegisterTextView;
    private EditText logInUserNameEditText, logInPasswordEditText;
    private Button logInConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_log_in);

        logInPasswordTextView = (TextView) findViewById(R.id.logInPasswordTextView);
        logInUserNameTextView = (TextView) findViewById(R.id.logInUserNameTextView);
        logInToRegisterTextView = (TextView) findViewById(R.id.logInToRegisterTextView);
        logInUserNameEditText = (EditText) findViewById(R.id.logInUserNameEditText);
        logInPasswordEditText = (EditText) findViewById(R.id.logInPasswordEditText);
        logInConfirmButton = (Button) findViewById(R.id.logInConfirmButton);
    }

    private void setupClickListener(){
        logInConfirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        logInToRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WindowRegister.class);
                startActivity(intent);
            }
        });
    }
}
