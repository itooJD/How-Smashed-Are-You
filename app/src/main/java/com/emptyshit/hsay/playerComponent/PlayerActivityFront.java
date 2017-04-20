package com.emptyshit.hsay.playerComponent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emptyshit.hsay.R;

public class PlayerActivityFront extends AppCompatActivity implements View.OnClickListener {

    public Button button;
    public TextView text;
    EditText benutzername;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        button = (Button) findViewById(R.id.Erstellen);
       // text = (TextView) findViewById(R.id._text);

        button.setOnClickListener(this);

        benutzername = (EditText) findViewById(R.id.Benutzername);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Passwort);

        benutzername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (benutzername.getText().length() < 20){
                    benutzername.setError("Error: Der Benutzername ist zu lang");
                }
                if (benutzername.getText().length() <= 5) {
                    return;
                }
                benutzername.setError("Error: sDer Benutzername ist zu kurz");
            }
        });


    }

    @Override
    public void onClick(View v) {
        text.setText("erfolgreich gespeichert");
    }
}
