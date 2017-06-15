package com.emptyshit.hsay.front.games;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.GatewayInfo;
import android.view.Window;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.navigation.WindowGateWay;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

public class WindowShowTime extends AppCompatActivity {

    private TextView showTimeTimeTextView;
    private TimeMeasureComponentInterface timeMeasureComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_show_time);
        this.timeMeasureComponentInterface = App.getTimeMeasureComponentInterface();
        this.showTimeTimeTextView = (TextView) findViewById(R.id.showTimeTimeTextView);
        this.showTimeTimeTextView.setText(timeMeasureComponentInterface.getStoppedTime().toString());
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Intent intent = new Intent(getApplicationContext(), WindowGateWay.class);
        startActivity(intent);
    }
}
