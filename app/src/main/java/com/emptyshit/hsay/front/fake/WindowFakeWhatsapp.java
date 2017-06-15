package com.emptyshit.hsay.front.fake;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.front.games.WindowGame;

public class WindowFakeWhatsapp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_fake_whatsapp);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), WindowGame.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
