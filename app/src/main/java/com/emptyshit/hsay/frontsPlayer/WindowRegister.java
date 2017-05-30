package com.emptyshit.hsay.frontsPlayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;

import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

public class WindowRegister extends AppCompatActivity {

    private TextView registerUsernameTextView, registerEmailTextView, registerPasswordTextView, registerConfirmPasswordTextView;
    private EditText registerUsernameEditText, registerEmailEditText, registerPasswordEditText, registerConfirmPasswordEditText;
    private Button registerConfirmButton;

    private PlayerComponentInterface playerComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_register);
        registerUsernameTextView = (TextView) findViewById(R.id.registerUsernameTextView);
        registerEmailTextView = (TextView) findViewById(R.id.registerEmailTextView);
        registerPasswordTextView = (TextView) findViewById(R.id.registerPasswordTextView);
        registerConfirmPasswordTextView = (TextView) findViewById(R.id.registerConfirmPasswordTextView);
        registerUsernameEditText = (EditText) findViewById(R.id.registerUsernameEditText);
        registerEmailEditText = (EditText) findViewById(R.id.registerEmailEditText);
        registerPasswordEditText = (EditText) findViewById(R.id.registerConfirmPasswordEditText);
        registerConfirmPasswordEditText = (EditText) findViewById(R.id.registerConfirmPasswordEditText);
        registerConfirmButton = (Button) findViewById(R.id.registerConfirmButton);
        playerComponentInterface = App.getPlayerComponentInterface();
        setupClickListener();
    }

    private void setupClickListener() {
        this.registerConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = registerUsernameEditText.getText().toString(); // TODO get oben edit/text view einbinden.
                String email = registerEmailEditText.getText().toString();
                String password = registerPasswordEditText.getText().toString();
                String confirmPassword = registerConfirmPasswordEditText.getText().toString();

                if(playerComponentInterface != null && playerComponentInterface.register(username, email, password, confirmPassword) == true) {
                    Intent intent = new Intent(WindowRegister.this, WindowPlayerProfile.class);
                    startActivity(intent);
                }
            }
        });
    }

    /*private void setupFocusChangeListener() {
        this.registerUsernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                playerComponentInterface.checkUsername().getText().toString();
                ok.setOnClickListener(okListener);
                //playerComponentInterface.Integer.parseInt(checkUsername().getText().toString());// checkUsername Method
            }
        });

        this.registerEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                playerComponentInterface.checkEmail().getText().toString();// checkEmail Method
            }
        });

        this.registerPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                playerComponentInterface.checkPassword().getText().toString();// check Password
            }
        });

        this.registerConfirmPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                playerComponentInterface.checkConfiPassword().getText().toString();// check Password
            }
        });
    }

    private View.OnClickListener okListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            //sendSMS("9960510915",username);
            Toast.makeText(getBaseContext(),"Alright", Toast.LENGTH_SHORT).show();

        }

    };*/

}
