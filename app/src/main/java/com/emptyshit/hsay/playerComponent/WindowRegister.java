package com.emptyshit.hsay.playerComponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;

public class WindowRegister extends AppCompatActivity {

    private TextView registerUsernameTextView, registerEmailTextView, registerPasswordTextView, registerConfirmPasswordTextView;
    private EditText registerUsernameEditText, registerEmailEditText, registerPasswordEditText, registerConfirmPasswordEditText;
    private Button registerConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_register);
        registerUsernameTextView = (TextView) findViewById(R.id.registerUsernameTextView);
        registerEmailTextView = (TextView) findViewById(R.id.registerEMailTextView);
        registerPasswordTextView = (TextView) findViewById(R.id.registerPasswordTextView);
        registerConfirmPasswordTextView = (TextView) findViewById(R.id.registerConfirmPasswordTextView);
        registerUsernameEditText = (EditText) findViewById(R.id.registerUsernameEditText);
        registerEmailEditText = (EditText) findViewById(R.id.registerEmailEditText);
        registerPasswordEditText = (EditText) findViewById(R.id.registerConfirmPasswordEditText);
        registerConfirmPasswordEditText = (EditText) findViewById(R.id.registerConfirmPasswordEditText);
        registerConfirmButton = (Button) findViewById(R.id.registerConfirmButton);
    }

    private void setupClickListener(){
        this.registerConfirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // register method from playerComponent
            }
        });
    }

    private void setupFocusChangeListener() {
        this.registerUsernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // checkUsername Method
            }
        });

        this.registerEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // checkEmail Method
            }
        });

        this.registerPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // check Password
            }
        });

        this.registerConfirmPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // check Password
            }
        });

    }
}
