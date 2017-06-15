package com.emptyshit.hsay.front.lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emptyshit.hsay.R;

public class WindowLock extends Activity {

    private Button lockSperrenButton;
    private CheckBox lockWhatsAppCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_lock);

        this.lockSperrenButton = (Button) findViewById(R.id.lockSperrenButton);
        this.lockWhatsAppCheckBox = (CheckBox) findViewById(R.id.lockWhatsAppCheckBox);
        setupClickListener();
    }

    private void setupClickListener() {
        this.lockSperrenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lockWhatsAppCheckBox.isChecked()) {
                    new Lock().lockApp();
                }
            }
        });

    }
}
