package com.emptyshit.hsay.front.lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.navigation.WindowGateWay;
import com.emptyshit.hsay.lockComponent.LockComponentInterface;

public class WindowLock extends Activity {

    private Button lockSperrenButton;
    private CheckBox lockWhatsAppCheckBox;
    private LockComponentInterface lockComponentInterface = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_lock);

        this.lockComponentInterface = App.getLockComponentInterface();
        this.lockSperrenButton = (Button) findViewById(R.id.lockSperrenButton);
        this.lockWhatsAppCheckBox = (CheckBox) findViewById(R.id.lockWhatsAppCheckBox);
        setupClickListener();
    }

    private void setupClickListener() {
        this.lockSperrenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lockWhatsAppCheckBox.isChecked()) {
                    lockComponentInterface.lock();
                    Intent intent = new Intent(getApplicationContext(), WindowGateWay.class);
                    startActivity(intent);
                }
            }
        });

    }
}
