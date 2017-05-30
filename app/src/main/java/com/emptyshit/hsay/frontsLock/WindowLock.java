package com.emptyshit.hsay.frontsLock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.lockComponent.LockComponent;

/**
 * Created by Alex on 30.05.2017.
 */

public class WindowLock extends AppCompatActivity {
    private CheckBox whatsappCheckbox;
    private Button lockButton;
    private LockComponent lock;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_lock);

        whatsappCheckbox = (CheckBox) findViewById(R.id.checkBoxWA);
        lockButton = (Button) findViewById(R.id.checkBoxWA);
    }

    private void setupClickListener() {
        this.lockButton.setOnClickListener(new View.OnClickListener() {

            /*
            @Override
            public void onClick(View v) {
                if(whatsappCheckbox.isChecked()) {
                    Intent intent = new Intent(AppsToLockScreen.this, LockScreen.class);
                    startActivity(intent);
                }
            }
            */

            @Override
            public void onClick(View v) {
                if(whatsappCheckbox.isChecked()) {
                    lock.lockApp();
                }
            }
        });
    }

}
